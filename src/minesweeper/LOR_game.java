package minesweeper;
import javax.swing.*;


import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
public class LOR_game extends JPanel{

	ImageIcon background_img=new ImageIcon("../images/LOR_background2.jpg");
	int random_sheep[]=new int[4];
	ImageIcon pink_sheep=new ImageIcon("../images/sheep_pink.png");
	ImageIcon blue_sheep=new ImageIcon("../images/sheep_blue.png");
	ImageIcon gray_sheep=new ImageIcon("../images/sheep_gray.png");
	ImageIcon yellow_sheep=new ImageIcon("../images/sheep_yellow.png");
	
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

	JLabel back=new JLabel(background_img);

	int stage_count_sheep[]={20,40,40,45};
	int stage_sheep[]= {2,2,3,4};
	int key[]= {5,10,15,20};
	int moksoom=3;
	int stage=0;
	
	int count_sheep;
	int sheep;
	int get_key;
	
	Main mainFrame=null;
	
	LOR_game(Main mainFrame){
		this.mainFrame=mainFrame;
		
		
        
        for(int i=0;i<4;i++) {
        	show_sheep[i]=new JLabel();
        }
        for(int i=0;i<3;i++) {
        	heart[i]=new JLabel();
        }
        
        setLayout(null);
        
        reset();
        
	    show_other();
        create_sheep();
        show_sheep();
        show_heart();
        
	}
	public void paintComponent(Graphics g) {
        g.drawImage(background_img.getImage(), 0, 0, null);
        setOpaque(false); //그림을 표시하게 설정,투명하게 조절
        super.paintComponent(g);
 }
	
	public void reset() {
		moksoom=3;
		
		get_key=key[stage];
		count_sheep=stage_count_sheep[stage];
        sheep=stage_sheep[0];
        remain_sheep.setText("남은 양 : "+count_sheep);
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
			show_sheep[i].setBounds(x, y, 155, 152);
			y+=200;
			add(show_sheep[i]);
		}
	}
	public void show_other() { // 다른것들 패널에 붙이기
		  
	      left_sheep.setIcon(pink_sheep);
	      right_sheep.setIcon(gray_sheep);
	      left_sheep.setBounds(60,300,155,152);
	      right_sheep.setBounds(620,300,155,152);
	      add(left_sheep);
	      add(right_sheep); // 울타리 속 양
	      
	      left_arrow.setBounds(30, 615, 150, 150);
	      right_arrow.setBounds(620, 615, 150, 150);
	      right_arrow.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
	            	  if(random_sheep[3]==1) {//1 (회색 양이면)
						  count_sheep--;
						  remain_sheep.setText("남은 양 : "+count_sheep);
						  if(count_sheep==0) {
							  String gameclear_button[] = { "다음 스테이지", "보물 찾으러 가기" };
							  int selected=JOptionPane.showOptionDialog(null, "축하합니다! 현재 열쇠 "+get_key+"개를 가질 수 있습니다", "STAGECLEAR",
										JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, gameclear_button, "확인");
							  if(selected==0) {
								  stage++;
								  reset();
							  }
							  else mainFrame.change("MinePanel");
						  }
					  }
	            	  else {
	            		  moksoom--;
	            		  show_heart();
	            		  
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
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
	    	  
	      });
	      left_arrow.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				  if(random_sheep[3]==0) {//0 (핑크 양이면)
					  count_sheep--;
					  remain_sheep.setText("남은 양 : "+count_sheep);
					  if(count_sheep==0) {
						  String gameclear_button[] = { "다음 스테이지", "보물 찾으러 가기" };
						  int selected=JOptionPane.showOptionDialog(null, "축하합니다! 현재 열쇠 "+get_key+"개를 가질 수 있습니다", "STAGECLEAR",
									JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, gameclear_button, "확인");
						  if(selected==0) {
							  stage++;
							  reset();
						  }
						  else mainFrame.change("MinePanel");
						  
					  }
				  }
				  else {
					  moksoom--;
					  show_heart();
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
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
	    	  
	      });
	      add(left_arrow);
	      add(right_arrow); // 방향키 이미지
	      
	      remain_sheep.setBounds(5, 5,200,100);
	      remain_sheep.setText("남은 양 : "+count_sheep);
	     add(remain_sheep); // 남은 양 표시 라벨
	}
	
	public void show_heart() { // 목숨 하트 보여주기
		for(int i=0;i<3;i++) { // 깎인 목숨은 안 보여주게
			heart[i].setIcon(null);
		}
		int x2=700;
	      for(int i=0;i<moksoom;i++) {
	    	  heart[i].setBounds(x2,5,80,80);
	    	  heart[i].setIcon(heartImg);
	    	  add(heart[i]);
	    	  x2-=100;
	      }
	    
	}
}
