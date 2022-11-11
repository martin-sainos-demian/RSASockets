
package pkg;

import java.io.StringReader;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class RSA {
    
    PublicKey clavePublica;
    PrivateKey clavePrivada;
            
    public RSA(){
        Security.addProvider(new BouncyCastleProvider());
    }
    public String[] getKeys(){
        String[] keys= new String[2];
        generateKeys();
        keys[0]=Base64.getEncoder().encodeToString(clavePublica.getEncoded());
        keys[1]=Base64.getEncoder().encodeToString(clavePrivada.getEncoded());
        return keys;
    }
    private void generateKeys(){
        try{
        KeyPairGenerator keygen = KeyPairGenerator.getInstance("RSA", "BC");
        
        //inicializar llave
        keygen.initialize(26);
        
        //vamos a asignar la llave publica y privada
        KeyPair clavesRSA=keygen.generateKeyPair();
        
        //definimos la llave privada
        clavePrivada = clavesRSA.getPrivate();
        clavePublica = clavesRSA.getPublic();
        }catch(Exception e){
            System.out.println("error "+e);
        }
    }
    
    
    public String cifrar(String text, String key){
        String result="";
        result=Base64.getEncoder().encodeToString(cipher(text, key));
        return result;
    }
    private byte[] cipher(String text, String key){
        try {
            Cipher cifrado = Cipher.getInstance("RSA", "BC");
            
            if(text.length()<=64){
                int num=Integer.parseInt(text);
                byte[] bufferplano = BigInteger.valueOf(num).toByteArray();
                //ciframos con publica
                PublicKey clave= getKey(key);
                cifrado.init(Cipher.ENCRYPT_MODE, clave);

                System.out.println("3 ciframos con clave public: ");

                byte[] buffercifrado = cifrado.doFinal(bufferplano);

                System.out.println("txt cipher: ");
                //no tiene format
                mostrarBytes(buffercifrado);
                System.out.println("");
                return buffercifrado;
            }
        } catch (Exception e) {
            System.out.println("error: "+e);
        }
        return null;
    }
    
    public String decifrar(String text, String key){
        String result="";
        byte[] des=decipher(text, key);
        int num=0;
        for (byte b : des) {
            num = (num << 8) + (b & 0xFF);
        }
        result=String.valueOf(num);
        return result;
    }
    private byte[] decipher(String text, String key){
        try {
            Cipher cifrado = Cipher.getInstance("RSA", "BC");
            
            //desciframos con privada
            PrivateKey clave= getKeyPriv(key);
            cifrado.init(Cipher.DECRYPT_MODE, clave);

            System.out.println("4 descipher con clave priv");

            byte[] buffercifrado = Base64.getDecoder().decode(text);
            byte[] bufferdescifrado = cifrado.doFinal(buffercifrado);

            System.out.println("texto descifrado: ");
            mostrarBytes(bufferdescifrado);

            System.out.println("");
            return bufferdescifrado;
        } catch (Exception e) {
            System.out.println("error: "+e);
        }
        return null;
    }
    
    private static PublicKey getKey(String key){
        try{
            System.out.println("get key from "+key);
            byte[] byteKey = Base64.getDecoder().decode(key);
            X509EncodedKeySpec X509publicKey = new X509EncodedKeySpec(byteKey);
            KeyFactory kf = KeyFactory.getInstance("RSA");

            return kf.generatePublic(X509publicKey);
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }
    private static PrivateKey getKeyPriv(String key){
        try{
            System.out.println("get key from "+key);
            byte[] buffer = Base64.getDecoder().decode(key);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(buffer);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return (PrivateKey) keyFactory.generatePrivate(keySpec);
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }
    private static void mostrarBytes(byte[] buffer) {
        System.out.write(buffer, 0, buffer.length);
    }
}