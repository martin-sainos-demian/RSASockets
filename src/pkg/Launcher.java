
package pkg;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Launcher {     
    static JButton keygenbtn;
    static RSA rsa;
    
    public static void main(String[] args) throws Exception {
        rsa=new RSA();
        
        JFrame frame = new JFrame("rsa swing");      
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);      
        frame.setSize(960, 360); 
        
        JPanel keygen=new JPanel();
        keygen.setLayout(new BoxLayout(keygen, BoxLayout.Y_AXIS));
        String keygenstr="generar claves";
        JLabel keygenlabel = new JLabel(keygenstr);
        JLabel keygenpublic = new JLabel(" ");
        JLabel keygenprivate = new JLabel(" ");
        keygenbtn=new JButton("generar");
        keygenbtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String[] keys=rsa.getKeys();
                keygenpublic.setText("publica: "+keys[0]);
                keygenprivate.setText("privada: "+keys[1]);
            }

        });
        
        keygen.add(keygenlabel);
        keygen.add(keygenpublic);
        keygen.add(keygenprivate);
        keygen.add(keygenbtn);
        
        frame.getContentPane().add(keygen);
        
        frame.setVisible(true); 
    }
}
