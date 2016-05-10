import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;



/**
 * Ke Wei
 * ID: 10126458
 * Project 4
 * 
 */
public class Project4 {
	
	//set up the timer variable
	static Timer t;
	
	/**
	 * read image from file
	 * @param label: The Label component 
	 * @param imgFile: the image file that will be used in label
	 * @throws IOException: file not found exception
	 */
	static void setPic(JLabel label, File imgFile) throws IOException{
		Image img = ImageIO.read(imgFile);
		ImageIcon icon = new ImageIcon(img.getScaledInstance(700, 530, Image.SCALE_SMOOTH));
		label.setIcon(icon);
	}
	
	
	public static void main(String[] args) throws IOException{

	//Menu bar=============================================================
		JMenuBar menuBar= new JMenuBar();
		menuBar.setPreferredSize(new Dimension(800, 30));
		JMenu file = new JMenu("File");
		
		//exit
		JMenuItem exitItem = new JMenuItem("Exit");
		exitItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				System.exit(0);
			}
		});
		file.add(exitItem);
		menuBar.add(file);
	//======================================================================
		
		
	//image panel============================================================
		JPanel cPanel = new JPanel(new GridLayout(1,1));
		JLabel picLabel = new JLabel();
		
		int start = 0;
		String path = System.getProperty("user.home");
		File dir = new File(path+"/Desktop/ProjectImage");
		File [] imgs = dir.listFiles();
		setPic(picLabel, imgs[start]);
		cPanel.add(picLabel);
	//========================================================================
		
		
	//Previous/next===========================================================
		//left panel
		JPanel lPanel = new JPanel(new GridLayout(1,1));
		JButton lBtn = new JButton("<-");
		lBtn.setPreferredSize(new Dimension(50, 630));
		lPanel.add(lBtn);
		
		
		//right panel
		JPanel rPanel = new JPanel(new GridLayout(1,1));
		JButton rBtn = new JButton("->");
		rBtn.setPreferredSize(new Dimension(50, 630));
		rPanel.add(rBtn);
	//======================================================================
		
		
	//slide panel===========================================================
		JPanel bPanel = new JPanel();
		bPanel.setBorder(BorderFactory.createEtchedBorder(0));
		bPanel.setPreferredSize(new Dimension(800, 70));
		JCheckBox autoBox = new JCheckBox("Auto Play");
		JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 10, 3);
		slider.setMinorTickSpacing(1);
		slider.setMajorTickSpacing(5);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		
		bPanel.add(autoBox);
		bPanel.add(slider);
	//======================================================================
		
		
	// Prev/Next Function===================================================
		/*
		 * previous / next action listener
		 */
		class btnListener implements ActionListener {
			int count = 0;
			int last = imgs.length -1;
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand() == "->"){
					count ++;
					if(count == last){
						count = 0;
					}
					try {
						setPic(picLabel,imgs[count]);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				
				if(e.getActionCommand() == "<-"){
					if(count == 0){
						count = last;
					}
					count --;
					try {
						setPic(picLabel,imgs[count]);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		}
		
		ActionListener btnListener = new btnListener();
		lBtn.addActionListener(btnListener);
		rBtn.addActionListener(btnListener);
		
		/*
		 * Key Listener on previous and next functionality 
		 */
        class KeyListen implements KeyListener{

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				int keyCode= e.getKeyCode();
				switch(keyCode){
					case KeyEvent.VK_LEFT:
						System.out.println("left arrow");
						lBtn.doClick();
						break;
					case KeyEvent.VK_RIGHT:
						System.out.println("right arrow");
						rBtn.doClick();
						break;
					default:
						break;
				}
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
        	
        }
        
        KeyListener keylist = new KeyListen();
		lBtn.addKeyListener(keylist);
		rBtn.addKeyListener(keylist);

	//======================================================================
		
		
	//Auto play functions===================================================
		
		//--------------------Timer listener--------------------
		class timerListener implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				rBtn.doClick();
				int val = (int) slider.getValue();
			}
			
		}
		//--------------------check box listener--------------------
		class checkboxListener implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				if(autoBox.isSelected()){
					int sVal = slider.getValue();
					ActionListener listener = new timerListener();
					t = new Timer(sVal*1000,listener);
					t.start();
				}
				else{
					t.stop();
				}
				
			}
			
		}
		
		ActionListener chckBoxLis = new checkboxListener();
		autoBox.addActionListener(chckBoxLis);
		
		//--------------------slider change listener--------------------
		class sliderListener implements ChangeListener{

			@Override
			public void stateChanged(ChangeEvent e) {
				if(autoBox.isSelected()){
					t.stop();
					int val = slider.getValue();
					if(val != 0){
						ActionListener list = new timerListener();
						t = new Timer(val*1000, list);
						t.start();
					}
					else	//if the value is 0
					{
						t.stop();
						autoBox.doClick();
					}
				}
			}
		}
		
		ChangeListener slideList = new sliderListener();
		slider.addChangeListener(slideList);
	//====================================================================
		
	//Frame components====================================================
		JFrame frame = new JFrame("Picture Viewer");
		frame.add(cPanel, BorderLayout.CENTER);
		frame.add(lPanel, BorderLayout.WEST);
		frame.add(rPanel, BorderLayout.EAST);
		frame.add(bPanel, BorderLayout.SOUTH);
		frame.add(menuBar, BorderLayout.NORTH);		
		
		frame.setPreferredSize(new Dimension(800, 600));
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	//=====================================================================
	}
	
}
