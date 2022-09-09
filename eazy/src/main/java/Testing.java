import com.institute.eazy.util.Util;

public class Testing {

    public static void main(String[] args){
      //  System.out.println(" Hash password is      " + Util.hashPassword("password"));


       String encryptedPasswordIs = Util.passwordEncrypt("human@01");
        System.out.println(" Encrypted pass word in testing class is     "+encryptedPasswordIs );

        String decryptedPasswordIs = Util.passwordDecrypt(encryptedPasswordIs);
        System.out.println("Decrypted password in testing class is      "+ decryptedPasswordIs);
    }



}
