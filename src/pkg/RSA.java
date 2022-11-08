
package pkg;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
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
        keys[0]=clavePublica.toString();
        keys[1]=clavePrivada.toString();
        return keys;
    }
    private void generateKeys(){
        try{
        KeyPairGenerator keygen = KeyPairGenerator.getInstance("RSA", "BC");
        
        //inicializar llave
        keygen.initialize(512);
        
        //vamos a asignar la llave publica y privada
        KeyPair clavesRSA=keygen.generateKeyPair();
        
        //definimos la llave privada
        clavePrivada = clavesRSA.getPrivate();
        clavePublica = clavesRSA.getPublic();
        }catch(Exception e){
            System.out.println("error "+e);
        }
    }
}