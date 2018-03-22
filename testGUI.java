 import javax.swing.*;
 import java.awt.*;
import java.awt.event.*;

 public class testGUI extends JFrame {
 	public testGUI() {
 		JPanel mainPanel = new JPanel(new FlowLayout());
      	mainPanel.add(new JLabel("Hello, world!"));
      	mainPanel.add(new JButton("Button"));

      	// Set the content-pane of this JFrame to the main JPanel
      	setContentPane(mainPanel);
 	}

 	 public static void main(String[] args) {
 	 	SwingUtilities.invokeLater(new Runnable() {
         @Override
         public void run() {
            new testGUI();  // Let the constructor do the job
         }
      });
 	 }
 }