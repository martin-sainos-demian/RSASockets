
package pkg;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Launcher {     
    static JButton keygenbtn, cipherbtn, decipherbtn;
    static RSA rsa;
    
    public static void main(String[] args) throws Exception {
        rsa=new RSA();
        
        JFrame frame = new JFrame("rsa swing");      
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);      
        frame.setSize(960, 360); 
        
        JPanel keygen=new JPanel();
        keygen.setLayout(new BoxLayout(keygen, BoxLayout.Y_AXIS));
        String keygenstr="  generar claves";
        JLabel keygenlabel = new JLabel(keygenstr);
        JTextArea keygenpublictxt = new JTextArea(5, 20);
        keygenpublictxt.setLineWrap(true);
        keygenpublictxt.setWrapStyleWord(true);
        keygenpublictxt.setEditable(false);
        JLabel keygenpublic = new JLabel("publica: ");
        JTextArea keygenprivatetxt = new JTextArea(5, 20);
        keygenprivatetxt.setLineWrap(true);
        keygenprivatetxt.setWrapStyleWord(true);
        keygenprivatetxt.setEditable(false);
        JLabel keygenprivate = new JLabel("privada: ");
        keygenbtn=new JButton("generar");
        keygenbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] keys=rsa.getKeys();
                keygenpublictxt.setText(keys[0]);
                keygenprivatetxt.setText(keys[1]);
            }
        });
        
        keygen.add(keygenlabel);
        keygen.add(keygenpublic);
        keygen.add(keygenpublictxt);
        keygen.add(keygenprivate);
        keygen.add(keygenprivatetxt);
        keygen.add(keygenbtn);
        
        
        JPanel cipher=new JPanel();
        cipher.setLayout(new BoxLayout(cipher, BoxLayout.Y_AXIS));
        JLabel cipherin = new JLabel("num a cifrar/num ya cifrado: ");
        JTextArea cipherintxt = new JTextArea(5, 20);
        cipherintxt.setLineWrap(true);
        cipherintxt.setWrapStyleWord(true);
        JLabel cipherkey = new JLabel("llave: ");
        JTextArea cipherkeytxt = new JTextArea(5, 20);
        cipherkeytxt.setLineWrap(true);
        cipherkeytxt.setWrapStyleWord(true);
        cipher.add(cipherin);
        cipher.add(cipherintxt);
        cipher.add(cipherkey);
        cipher.add(cipherkeytxt);
        
        JLabel cipherres = new JLabel("resultado: ");
        JTextArea cipherrestxt = new JTextArea(5, 20);
        cipherrestxt.setLineWrap(true);
        cipherrestxt.setWrapStyleWord(true);
        cipherrestxt.setEditable(false);
        cipherbtn=new JButton("cifrar");
        cipherbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String res=rsa.cifrar(cipherintxt.getText(), cipherkeytxt.getText());
                cipherrestxt.setText(res);
            }
        });
        decipherbtn=new JButton("descifrar");
        decipherbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String res=rsa.decifrar(cipherintxt.getText(), cipherkeytxt.getText());
                cipherrestxt.setText(res);
            }
        });
        cipher.add(cipherres);
        cipher.add(cipherrestxt);
        cipher.add(cipherbtn);
        cipher.add(decipherbtn);
        
        
        frame.getContentPane().add(BorderLayout.CENTER, keygen);
        frame.getContentPane().add(BorderLayout.EAST, cipher);
        
        frame.setVisible(true); 
    }
}
