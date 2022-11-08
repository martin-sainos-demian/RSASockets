
package pkg;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Launcher {     
    static JButton keygenbtn;
    static RSA rsa;
    
    public static void main(String[] args) throws Exception {
        rsa=new RSA();
        
        JFrame frame = new JFrame("Telefono");      
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);      
        frame.setSize(480, 360); 
        
        JPanel keygen=new JPanel();
        String keygenstr="generar claves \n";
        JLabel keygenlabel = new JLabel(keygenstr);
        keygenbtn=new JButton("generar");
        keygenbtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String[] keys=rsa.getKeys();
                keygenlabel.setText(keygenstr+"publica: "+keys[0]+"\n privada: "+keys[1]+"\n");
            }

        });
        
        keygen.add(keygenlabel);
        keygen.add(keygenbtn);
        
        frame.getContentPane().add(BorderLayout.NORTH, keygen);
        
        frame.setVisible(true); 
    }
}
