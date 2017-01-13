import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GameFrame {
   
    private static class HelloWorldDisplay extends JPanel {
        public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawString( "Hello World!", 100, 30 );
		}
	}
   
	private static class ButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(
             "..is the loneliest number");
		}
	}
   
	public static void main(String[] args) {
      
		HelloWorldDisplay displayPanel = new HelloWorldDisplay();
		JButton ButtonPlay = new JButton("Play");
		JButton ButtonGM = new JButton("Choose Game Mode");
		JButton ButtonExit = new JButton("Exit");
		ButtonHandler listener = new ButtonHandler();
		
		
		ButtonPlay.setBounds(50,100,300,30);
		ButtonPlay.addActionListener(listener);
		ButtonGM.setBounds(50,175,300,30);
		ButtonGM.addActionListener(listener);
		ButtonExit.setBounds(50,250,300,30);
		ButtonExit.addActionListener(listener);
	  

		JPanel content = new JPanel();
		content.setLayout(new BorderLayout());
		content.add(displayPanel, BorderLayout.CENTER);
		content.add(ButtonPlay);
		content.add(ButtonGM);
		content.add(ButtonExit);
		content.add(new JLabel(new ImageIcon("C:/OllieJava/CaveExplorer/knight.jpg")));


		JFrame window = new JFrame("Cave Explorer");
		window.setContentPane(content);
		window.setSize(500,500);
		window.setLocation(100,100);
		window.setVisible(true);

	}
   
}
