import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class OptionsFrame {
	
   
	private static class ButtonHandlerBright implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.out.println("Bright Mode Enabled");
			DrawMap MapMode = new DrawMap();
			MapMode.setMode(100);
		}
	}
	
	private static class ButtonHandlerTorch implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.out.println("Torch Mode Enabled");
			DrawMap MapMode = new DrawMap();
			MapMode.setMode(0);
		}
	}

	private static class ButtonHandlerMenu implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			GameFrame back = new GameFrame();
			back.displayMenu();
		}
	}
    
	public static void DisplayOptions(){
		JButton ButtonBright = new JButton("Bright Mode (Easy)");
		JButton ButtonTorch = new JButton("Torch Mode (Hard)");
		JButton ButtonMenu = new JButton("Back to Menu");
		ButtonHandlerBright listenerBright = new ButtonHandlerBright();
		ButtonHandlerTorch listenerTorch = new ButtonHandlerTorch();
		ButtonHandlerMenu listenerMenu = new ButtonHandlerMenu();
		
		ButtonBright.setBounds(50,100,300,30);
		ButtonBright.addActionListener(listenerBright);
		ButtonTorch.setBounds(50,175,300,30);
		ButtonTorch.addActionListener(listenerTorch);
		ButtonMenu.setBounds(50,250,300,30);
		ButtonMenu.addActionListener(listenerMenu);

		JPanel content = new JPanel();
		content.setLayout(new BorderLayout());
		content.add(ButtonBright);
		content.add(ButtonTorch);
		content.add(ButtonMenu);
		content.add(new JLabel(new ImageIcon("C:/CaveExplorer/cave.jpg")));
		
		JFrame window = new JFrame("Cave Explorer");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setContentPane(content);
		window.setSize(1375,850);
		window.setLocation(100,100);
		window.setVisible(true);

	}

	
   
}
