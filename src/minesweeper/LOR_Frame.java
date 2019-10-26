package minesweeper;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

public class LOR_Frame extends JFrame {
	JFrame frame=new JFrame();
	ImageIcon background_img=new ImageIcon("../images/LOR_background2.jpg");
	int random_sheep[]=new int[4];
	ImageIcon pink_sheep=new ImageIcon("../images/sheep_pink.png");
	ImageIcon white_sheep=new ImageIcon("../images/sheep_white.png");
	ImageIcon gray_sheep=new ImageIcon("../images/sheep_gray.png");
	ImageIcon left=new ImageIcon("../images/left_arrow.png");
	ImageIcon right=new ImageIcon("../images/right_arrow.png");
	ImageIcon heartImg=new ImageIcon("../images/moksoom.png");
	
	JLabel show_sheep[]=new JLabel[4];
	JLabel heart[]=new JLabel[3];
	JLabel left_sheep=new JLabel();
	JLabel right_sheep=new JLabel();
	JLabel left_arrow=new JLabel(left);//��ȭ
	JLabel right_arrow=new JLabel(right);//��ȭ
	JLabel remain_sheep=new JLabel();

	JPanel background;
	Container c;
	
	int count_sheep=40;
	int sheep=2;
	int moksoom=3;
	
	LOR_Frame(){
		setTitle("LEFT OR RIGHT");
		frame.setSize(800, 800);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		c=frame.getContentPane();
		
		background = new JPanel() {//background �̹���
            public void paintComponent(Graphics g) {
                g.drawImage(background_img.getImage(), 0, 0, null);
                setOpaque(false); //�׸��� ǥ���ϰ� ����,�����ϰ� ����
                super.paintComponent(g);
            }
        };
        
        for(int i=0;i<4;i++) {
        	show_sheep[i]=new JLabel();
        }
        for(int i=0;i<3;i++) {
        	heart[i]=new JLabel();
        }
        
        c.add(background);
	    background.setLayout(null);
        show_other();
        create_sheep();
        show_sheep();
        show_heart();
        frame.addKeyListener(new MyKeyListener());//���� ������ ����Ű �̺�Ʈ
	}
	
	class MyKeyListener implements KeyListener{ //Ű���� ������ �� �̺�Ʈ
		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			  if( e.getKeyCode() == 37 ) {//���� (��ũ ��)
				  if(random_sheep[3]==0) {//0 (��ũ ���̸�)
					  count_sheep--;
					  remain_sheep.setText("���� �� : "+count_sheep);
				  }
				  else {
					  moksoom--;
					  show_heart();
				  }
			  }
              if( e.getKeyCode() == 39 ) {//������ (ȸ�� ��)
            	  if(random_sheep[3]==1) {//1 (ȸ�� ���̸�)
					  count_sheep--;
					  remain_sheep.setText("���� �� : "+count_sheep);
				  }
            	  else {
            		  moksoom--;
            		  show_heart();
            	  }
              }
              //�� �� �� ���� ����
              for(int i=2;i>=0;i--) {
            	  random_sheep[i+1]=random_sheep[i];
              }
              random_sheep[0]=(int)(Math.random()*sheep);
              
              //�� �ٲ� ���ο� �̹����� ����
              for(int i=0;i<4;i++) {
      			if(random_sheep[i]==0) {
      				show_sheep[i].setIcon(pink_sheep);
      			}
      			else if(random_sheep[i]==1) {
      				show_sheep[i].setIcon(gray_sheep);
      			}
              } 
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	public void create_sheep() { // �� ó�� ��� ���� ����
		
		for(int i=0;i<4;i++) {
		random_sheep[i] = (int) (Math.random() * sheep);
		}
	}
	
	public void show_sheep() { // �� �гο� ���̱�
		int x=335;
		int y=10;
		
		for(int i=0;i<4;i++) {
			if(random_sheep[i]==0) {
				show_sheep[i].setIcon(pink_sheep);
			}
			else if(random_sheep[i]==1) {
				show_sheep[i].setIcon(gray_sheep);
			}
			show_sheep[i].setBounds(x, y, 150, 150);
			y+=200;
			background.add(show_sheep[i]);
		}
	}
	public void show_other() { // �ٸ��͵� �гο� ���̱�
		  
	      left_sheep.setIcon(pink_sheep);
	      right_sheep.setIcon(gray_sheep);
	      left_sheep.setBounds(60,300,150,150);
	      right_sheep.setBounds(620,300,150,150);
	      background.add(left_sheep);
	      background.add(right_sheep); // ��Ÿ�� �� ��
	      
	      left_arrow.setBounds(30, 615, 150, 150);
	      right_arrow.setBounds(620, 615, 150, 150);
	      background.add(left_arrow);
	      background.add(right_arrow); // ����Ű �̹���
	      
	      remain_sheep.setBounds(5, 5,200,100);
	      remain_sheep.setText("���� �� : "+count_sheep);
	      background.add(remain_sheep); // ���� �� ǥ�� ��
	}
	
	public void show_heart() { // ��� ��Ʈ �����ֱ�
		for(int i=0;i<3;i++) { // ���� ����� �� �����ְ�
			heart[i].setIcon(null);
		}
		int x2=700;
	      for(int i=0;i<moksoom;i++) {
	    	  heart[i].setBounds(x2,5,80,80);
	    	  heart[i].setIcon(heartImg);
	    	  background.add(heart[i]);
	    	  x2-=100;
	      }
	    
	}
}
