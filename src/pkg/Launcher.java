
package pkg;

import javax.swing.JFrame;

public class Launcher {
    public static void main(String[] args) throws Exception {
        JFrame frame = new JFrame("Telefono");      
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);      
        frame.setSize(480, 360); 
        
        frame.setVisible(true); 
    }
}
