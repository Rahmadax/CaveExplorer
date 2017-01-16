import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GameFrame {
   
	private static class ButtonHandlerPlay implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			CoreClass core = new CoreClass();
			core.startGame();
		}
	}
	
	private static class ButtonHandlerGM implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
		}
	}
	
	private static class ButtonHandlerOptions implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.out.println("Options");
		}
	}
	
	private static class ButtonHandlerExit implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}

	public static void main(String[] args) {
      
		JButton ButtonPlay = new JButton("Play");
		JButton ButtonGM = new JButton("Choose Game Mode");
		JButton ButtonExit = new JButton("Exit");
		JButton ButtonOptions = new JButton("Options");
		ButtonHandlerPlay listenerPlay = new ButtonHandlerPlay();
		ButtonHandlerExit listenerExit = new ButtonHandlerExit();
		ButtonHandlerGM listenerGM = new ButtonHandlerGM();
		ButtonHandlerOptions listenerOptions = new ButtonHandlerOptions();
		
		
		ButtonPlay.setBounds(50,100,300,30);
		ButtonPlay.addActionListener(listenerPlay);
		ButtonGM.setBounds(50,175,300,30);
		ButtonGM.addActionListener(listenerGM);
		ButtonOptions.setBounds(50,250,300,30);
		ButtonOptions.addActionListener(listenerOptions);
		ButtonExit.setBounds(50,325,300,30);
		ButtonExit.addActionListener(listenerExit);
	  

		JPanel content = new JPanel();
		content.setLayout(new BorderLayout());
		content.add(ButtonPlay);
		content.add(ButtonGM);
		content.add(ButtonOptions);
		content.add(ButtonExit);
		content.add(new JLabel(new ImageIcon("U:/CaveExplorer/cave.jpg")));


		JFrame window = new JFrame("Cave Explorer");
		window.setContentPane(content);
		window.setSize(1375,850);
		window.setLocation(100,100);
		window.setVisible(true);

	}
	
   
}
