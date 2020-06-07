import java.security.SecureRandom;

public class TokenGenerator {
	
	
	
	protected static SecureRandom random = new SecureRandom();
	        
	        public static synchronized String generateToken() {
	                long longToken = Math.abs( random.nextLong() );
	                String random = Long.toString( longToken, 16 );
	             
	                return random;

	        }
	        public static long activeTime() {
	        	long nowMillis = System.currentTimeMillis();
                long laterMillis = nowMillis + 1200000;
                
				return laterMillis;
	        	
				
			}
	        public static long getMins(long str3) {
	        	long nowMillis = System.currentTimeMillis();
	        	long milliseconds =  str3 - nowMillis;
	             long minutes = (milliseconds / 1000) / 60;
	             long seconds = (milliseconds / 1000) % 60;
	             
				return minutes;
				
			}
	        public static long getSeconds(long str3) {
	        	long nowMillis = System.currentTimeMillis();
	        	long milliseconds = str3 - nowMillis;
	             long minutes = (milliseconds / 1000) / 60;
	             long seconds = (milliseconds / 1000) % 60;
	             
				return seconds;
				
			}
	        public static String YesNo(long str) {
	        	if (getSeconds(str)>0) {
	        		return "Po, eshte valid.";
				} else {
					return "Jo, nuk eshte valid.";
				}
				
			}
	        public static String isValid(long str3) {
	        	if(getSeconds(str3)>0)
	        	{
	        		return getMins(str3) + " minuta dhe " + getSeconds(str3) + " sekonda.";
	        	}
	        	else {
					return "Ka skaduar.";
				}
			}
	        
	}

