import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class OptionsFrame {
   
	private static class ButtonHandlerBright implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.out.println("Bright Mode Enabled");
			CoreClass MapMode = new CoreClass();
			MapMode.setBright();
		}
	}
	
	private static class ButtonHandlerTorch implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.out.println("Torch Mode Enabled");
			CoreClass MapMode = new CoreClass();
			MapMode.setTorch();
		}
	}

	private static class ButtonHandlerMenu implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			GameFrame back = new GameFrame();
			back.main();
		}
	}

	public static void main(String[] args) {
    ]
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
		ButtonTorch.setBounds(50,250,300,30);
		ButtonTorch.addActionListener(listenerMenu);

		JPanel content = new JPanel();
		content.setLayout(new BorderLayout());
		content.add(ButtonBright);
		content.add(ButtonTorch);
		content.add(ButtonMenu);
		content.add(new JLabel(new ImageIcon("U:/CaveExplorer/cave.jpg")));


		JFrame window = new JFrame("Cave Explorer");
		window.setContentPane(content);
		window.setSize(1375,850);
		window.setLocation(100,100);
		window.setVisible(true);

	}
	
   
}