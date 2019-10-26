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
	JLabel left_arrow=new JLabel(left);//왼화
	JLabel right_arrow=new JLabel(right);//오화
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
		
		background = new JPanel() {//background 이미지
            public void paintComponent(Graphics g) {
                g.drawImage(background_img.getImage(), 0, 0, null);
                setOpaque(false); //그림을 표시하게 설정,투명하게 조절
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
        frame.addKeyListener(new MyKeyListener());//왼쪽 오른쪽 방향키 이벤트
	}
	
	class MyKeyListener implements KeyListener{ //키보드 눌렀을 때 이벤트
		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			  if( e.getKeyCode() == 37 ) {//왼쪽 (핑크 양)
				  if(random_sheep[3]==0) {//0 (핑크 양이면)
					  count_sheep--;
					  remain_sheep.setText("남은 양 : "+count_sheep);
				  }
				  else {
					  moksoom--;
					  show_heart();
				  }
			  }
              if( e.getKeyCode() == 39 ) {//오른쪽 (회색 양)
            	  if(random_sheep[3]==1) {//1 (회색 양이면)
					  count_sheep--;
					  remain_sheep.setText("남은 양 : "+count_sheep);
				  }
            	  else {
            		  moksoom--;
            		  show_heart();
            	  }
              }
              //맨 위 양 랜덤 배정
              for(int i=2;i>=0;i--) {
            	  random_sheep[i+1]=random_sheep[i];
              }
              random_sheep[0]=(int)(Math.random()*sheep);
              
              //양 바뀐 새로운 이미지로 변경
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
	public void create_sheep() { // 맨 처음 양들 랜덤 배정
		
		for(int i=0;i<4;i++) {
		random_sheep[i] = (int) (Math.random() * sheep);
		}
	}
	
	public void show_sheep() { // 양 패널에 붙이기
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
	public void show_other() { // 다른것들 패널에 붙이기
		  
	      left_sheep.setIcon(pink_sheep);
	      right_sheep.setIcon(gray_sheep);
	      left_sheep.setBounds(60,300,150,150);
	      right_sheep.setBounds(620,300,150,150);
	      background.add(left_sheep);
	      background.add(right_sheep); // 울타리 속 양
	      
	      left_arrow.setBounds(30, 615, 150, 150);
	      right_arrow.setBounds(620, 615, 150, 150);
	      background.add(left_arrow);
	      background.add(right_arrow); // 방향키 이미지
	      
	      remain_sheep.setBounds(5, 5,200,100);
	      remain_sheep.setText("남은 양 : "+count_sheep);
	      background.add(remain_sheep); // 남은 양 표시 라벨
	}
	
	public void show_heart() { // 목숨 하트 보여주기
		for(int i=0;i<3;i++) { // 깎인 목숨은 안 보여주게
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
