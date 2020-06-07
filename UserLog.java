import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class UserLog {
	     
	
	public static void createuser(String name, String pasi1, String pasi2) throws NoSuchAlgorithmException, IOException {
			
		Create C= new Create();
	       
		try {
				
	            Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3307/eclipse","root","balajendrit");

	            PreparedStatement Pstatement=connection.prepareStatement("insert into student values(?,?,?,?,?)");

	            Pstatement.setString(1,name);
	            Pstatement.setString(2,encodePass(pasi1));
	            Pstatement.setString(3,encodePass(pasi2));
	            Pstatement.setString(4,null);
	            Pstatement.setString(5,null);
	            
	            if(pasi1.equalsIgnoreCase(pasi2))
	            {

	                Pstatement.executeUpdate();
	                C.CreateUser(name);
	            }
	            else
	            {
	                System.out.println("fjalekalimet nuk perputhen");
	            }
	        }        
		 catch (SQLException e1) {
             e1.printStackTrace();
         }
                      
	}
	
	public static void deleteUser(String name) {
		
		Delete D=  new Delete();
		 try{
			 Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3307/eclipse","root","balajendrit");
		      

		      String query = "delete from student where USERNAME= ?";
		      PreparedStatement preparedStmt = connection.prepareStatement(query);
		      preparedStmt.setString(1, name);


		      preparedStmt.execute();
		      D.Delete(name);
		      connection.close();
		    }
		 catch (Exception e)
		    {
		      System.err.println("Got an exception! ");
		      System.err.println(e.getMessage());
		    }  
		
	}
	
	public static void loginPass(String name,String UserPasword) throws NoSuchAlgorithmException, IOException, SQLException {
	       
		Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3307/eclipse","root","balajendrit");
        Statement st = connection.createStatement();
        String sql = ("SELECT * FROM student;");
        ResultSet rs = st.executeQuery(sql);
        for(int i=0;i<50;i++)
        {
	        if(rs.next()) { 
		         String str1 = rs.getString("USERNAME");
		         String str2 = rs.getString("PASSWRD");

		         if(str1.equalsIgnoreCase(name))
		         {
		        	 if(str2.equalsIgnoreCase(encodePass(UserPasword)))
		        	 {
				         String str3 = TokenGenerator.generateToken();

		        		 String query = "update student set the_token = ? where username = ?";
		        		 String query1 = "update student set ontime = ? where username = ?";
		        		 
		        	      PreparedStatement preparedStmt = connection.prepareStatement(query);
		        	      preparedStmt.setString(1, str3);
		        	      preparedStmt.setString(2,name);
		        	      
		        	      PreparedStatement preparedStmt1 = connection.prepareStatement(query1);
		        	      preparedStmt1.setLong(1, TokenGenerator.activeTime());
		        	      preparedStmt1.setString(2,name);
		        	      
		        	      
		        	      preparedStmt.executeUpdate();
		        	      preparedStmt1.executeUpdate();
		        	      
		 	            System.out.println(str3);
		        	 }
		        	 else {
						System.out.println("Shfrytezuesi ose fjalekalimi i gabuar.");
					}
		         }
	        }
        }   	
	} 
	
	public static void getStatus(String token) throws SQLException {
		Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3307/eclipse","root","balajendrit");
        Statement st = connection.createStatement();
        String sql = ("SELECT * FROM student;");
        ResultSet rs = st.executeQuery(sql);
        for(int i=0;i<50;i++)
        {
	        if(rs.next()) { 
	        	if(rs.getString("THE_TOKEN")==null)
	        		continue;
	        	else {
	        		String str1 = rs.getString("THE_TOKEN");
	        		long str3 = rs.getLong("ONTIME");
			         if (str1.equals(token)) {
			        	 String str2 = rs.getString("USERNAME");
			        	 System.out.println("User: "+str2);
			        	 System.out.println("Valid: "+TokenGenerator.YesNo(str3));
			        	 System.out.println("Skadimi: " + TokenGenerator.isValid(str3));
					} else {
						continue;
					}
				}
	        	
		         
	        }
        }
	}
	public static String encodePass(String name) throws NoSuchAlgorithmException, IOException {
		
		String passwordToHash = name;
        String generatedPassword = null;
        try {
	            MessageDigest md = MessageDigest.getInstance("MD5");
	            md.update(passwordToHash.getBytes());
	            byte[] bytes = md.digest();
	            StringBuilder sb = new StringBuilder();
	            for(int i=0; i< bytes.length ;i++)
	            {
	                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
	            }
	            generatedPassword = sb.toString();
        	} 
        catch (NoSuchAlgorithmException e) 
        {
            e.printStackTrace();
        }
        return generatedPassword;
	}
	
	}
