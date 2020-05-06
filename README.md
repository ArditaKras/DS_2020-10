# Grupi10

Kjo (DS_2020-10) eshte repository qe ka te beje me teknikat klasike te enkriptimit dhe dekriptimit, e krijuar nga grupi i 10,
kontribues te se cilit jane:

-Albina Zekaj,
Endrit Balaj,
Ardita Krasniqi



*Enkriptimi është një proces i cili të dhënën me përmbajtje të dukshme
(te kuptueshme nga te gjitha palet) e transformon në të dhënë të koduar, 
përmbajtja e së cilës do të jetë e pamundur të lexohet pa posedimin e “çelësit”. 

*De-enkriptimi, një proces i kundërt me enkriptimin, mundëson restaurimin e përmbajtjes 
së dukshme të të dhënës nga e dhëna e koduar përmes përdorimit të çelësit. 
Zakonisht çelësi duhet të jetë informatë sekrete.

Nder Kodet me te thjeshta për të koduar/dekoduar mesazhet te paraqitura ne kete Repository jane:

-Tap Code

-Morse Code 

-Vigenere Code

1. Udhëzimet për ekzekutimin e programit. 

-Shkarkimi-

Per te realizuar programin shfrytezojme gjuhen programuese Java ,perkatesisht applikacionin
Eclipse Java 2019-09 ,ne kuader te paketes IDE ,qe mund te downlodohet permes linkut :
https://eclipse.org/downloads/eclipse-packages/
Do  të shkarkohet një pako(.zip),qe mjafton ta ekstraktoni në lokacionin ku dëshironi ta keni.

https://www.youtube.com/watch?v=lXVE34i-QMc(Tutorial - How To: Create a Simple Java Program Using Eclipse)

Opsionet për krijimin e projektit, ekzekutimin dhe debug-imin janë  të qasshme përmes 
menyve mirëpo edhe përmes shiritit  të veglave.

Ne (Command Prompt) te cilen mund ta hapni permes menyse se Start (Search--> cmd) Shkruani Syntax:
Per te ekzekutuar:

✸javac Main.java

per te kompajluar (jep argumentet sipas sintakses ne vazhdim)

✸ Funksioni Tap:

→ 1. java Main tap-code encode | decode <text>

![Tap](https://user-images.githubusercontent.com/58491146/77836141-d0f28380-7153-11ea-9bbf-6bde6fc897e2.png)

✸ Funksioni Morse:

→ 1. java Main morse-code encode | decode <text>
  
→ 2. java Main morse-code beep <text>

![Morse (2)](https://user-images.githubusercontent.com/58491146/77836506-f765ee00-7156-11ea-8c9f-660235c7be43.png)

✸ Funksioni Vigenere:

→ 1. java Main vigenere-cipher encrypt | decrypt <text>

![Vigenere](https://user-images.githubusercontent.com/58491146/77836194-537b4300-7154-11ea-885b-114e85896d3a.png)

✸ Komanda Create-User :

→ java Main create-user emri

![Create-User](https://user-images.githubusercontent.com/58491146/81102779-67af1e80-8f10-11ea-81fc-5ee77a26829c.png)

✸ Komanda delete-user :

→ java Main delete-user <emriUserit>
  
  ![Delete-User](https://user-images.githubusercontent.com/58491146/81103169-f58b0980-8f10-11ea-9b49-d096d9296917.png)
  
  
 ✸ Komanda export-key 
  
  → java Main export-key public|private emri file

![Export1](https://user-images.githubusercontent.com/58491146/81124769-9809b380-8f36-11ea-96c9-69d57e893433.png)


 ✸ Komanda import-key
 
   → java Main import-key <name> <path>
 
 ![Import](https://user-images.githubusercontent.com/58491146/81124495-dc488400-8f35-11ea-8d41-c9adae60dc05.png)

 ✸ Komanda write-message
 
 → java Main write-message name message file
  
  ![Write-message](https://user-images.githubusercontent.com/58491146/81122705-c638c480-8f31-11ea-8b0d-3770701d953d.png)
  
 ✸ Komanda read-message
  
 →  java Main read-message encrypted-message
  
  ![Read-message](https://user-images.githubusercontent.com/58491146/81123166-b5d51980-8f32-11ea-823e-d595096951e2.png)









REFERENCAT:

➣ https://pcworld.al/shkenca-e-kriptografise-te-gjitha-ne-nje-vend/

Morse Code Audio bazuar ne:

➣ https://gist.github.com/Xyene/6478305 

FazaII:
➣ https://www.novixys.com/blog/rsa-file-encryption-decryption-java/
➣ https://en.wikipedia.org/wiki/RSA_(cryptosystem)#Key_generation
