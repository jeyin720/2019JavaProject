package minesweeper;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class gameclear extends JPanel {

	ImageIcon gameclear=new ImageIcon("images/clear.jpg");
	ImageIcon button_img=new ImageIcon("images/story_arrow.png");
	ImageIcon showRank=new ImageIcon("images/showRank.png");
	ImageIcon addRank=new ImageIcon("images/addScore.png");
	
	JButton next=new JButton();
	JButton ShowRank=new JButton();
	JButton AddRank=new JButton();
	int count=1;
	int score=0;
	Main mainFrame=null;
	gameclear(Main mainFrame){
		this.mainFrame=mainFrame;
		
		Image img =button_img.getImage() ;  
		Image newimg = img.getScaledInstance( 50,50,  java.awt.Image.SCALE_SMOOTH ) ;  
		button_img = new ImageIcon( newimg );
		
		
		next.setIcon(button_img);
		next.setBorderPainted(false);
		next.setContentAreaFilled(false);
		next.setFocusPainted(false);
		
		
		next.setBounds(700, 700, 50, 50);
		
		setLayout(null);
		next.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				mainFrame.change("MainPanel",0);
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		
		
		AddRank.setBounds(120, 500, 280, 80);
		
		AddRank.setIcon(addRank);
		AddRank.setBorderPainted(false);
		AddRank.setContentAreaFilled(false);
		AddRank.setFocusPainted(false);
		
		AddRank.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				String Myname=JOptionPane.showInputDialog("이름을 입력하세요");
				if(Myname!=null) {
					mainFrame.gameRank.insert(Myname,score);
				}
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		
		ShowRank.setIcon(showRank);
		ShowRank.setBorderPainted(false);
		ShowRank.setContentAreaFilled(false);
		ShowRank.setFocusPainted(false);
		ShowRank.setBounds(420, 500, 280, 80);
		
		ShowRank.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				mainFrame.showRank=new rankPanel(mainFrame);
				mainFrame.change("showRank", score);
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		add(ShowRank);
		add(AddRank);
		add(next);
		
	}
	public void getScore(int s) {
		score=s;
	}
	public void paintComponent(Graphics g) {
        g.drawImage(gameclear.getImage(), 0, 0, null);
        setOpaque(false); //그림을 표시하게 설정,투명하게 조절
        super.paintComponent(g);
 }
	
}
