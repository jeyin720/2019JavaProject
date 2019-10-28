package minesweeper;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;


import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
public class LOR_game extends JPanel{

	ImageIcon background_img=new ImageIcon("../images/LOR_background2.jpg");
	int random_sheep[]=new int[4];
	ImageIcon pink_sheep=new ImageIcon("../images/sheep_pink.png");
	ImageIcon blue_sheep=new ImageIcon("../images/sheep_blue.png");
	ImageIcon gray_sheep=new ImageIcon("../images/sheep_gray.png");
	ImageIcon yellow_sheep=new ImageIcon("../images/sheep_yellow.png");
	
	ImageIcon stage_img[]=new ImageIcon[4];
	ImageIcon left=new ImageIcon("../images/left_arrow.png");
	ImageIcon right=new ImageIcon("../images/right_arrow.png");
	ImageIcon heartImg=new ImageIcon("../images/moksoom.png");
	
	JLabel show_sheep[]=new JLabel[4];
	JLabel heart[]=new JLabel[3];
	JLabel left_sheep[]=new JLabel[2];
	JLabel right_sheep[]=new JLabel[2];
	JLabel left_arrow=new JLabel(left);//왼화
	JLabel right_arrow=new JLabel(right);//오화
	JLabel remain_sheep=new JLabel();
	JLabel show_stage=new JLabel();
	
	JLabel back=new JLabel(background_img);

	int stage_count_sheep[]={5,10,20,45};
	int stage_sheep[]= {2,2,3,4};
	int key[]= {5,10,15,20};
	int moksoom=3;
	int stage;
	
	int count_sheep;
	int sheep;
	int get_key;
	
	Main mainFrame=null;
	
	LOR_game(Main mainFrame){
		
		this.mainFrame=mainFrame;
		
		for(int i=0;i<2;i++) {
			left_sheep[i]=new JLabel();
			right_sheep[i]=new JLabel();
		}
        
        for(int i=0;i<4;i++) {
        	show_sheep[i]=new JLabel();
        	
        }
        for(int i=0;i<3;i++) {
        	heart[i]=new JLabel();
        }
        stage_img[0]=new ImageIcon("../images/stage-01.png");
        stage_img[1]=new ImageIcon("../images/stage-02.png");
        stage_img[2]=new ImageIcon("../images/stage-03.png");
        stage_img[3]=new ImageIcon("../images/stage-04.png");
        
        for(int i=0;i<4;i++) {
			Image img =stage_img[i].getImage() ;  
			Image newimg = img.getScaledInstance( 300,230,  java.awt.Image.SCALE_SMOOTH ) ;  
			stage_img[i] = new ImageIcon( newimg );	
		}
        
        setLayout(null);
        
        //reset();
	    add_arrow();
        
	}
	public void paintComponent(Graphics g) {
        g.drawImage(background_img.getImage(), 0, 0, null);
        setOpaque(false); //그림을 표시하게 설정,투명하게 조절
        super.paintComponent(g);
 }
	public void add_stage_img() {
		
		
		switch(stage) {
		case 0:show_stage.setIcon(stage_img[0]);break;
		case 1:show_stage.setIcon(stage_img[1]);break;
		case 2:show_stage.setIcon(stage_img[2]);break;
		case 3:show_stage.setIcon(stage_img[3]);break;
		}
		
		show_stage.setBounds(0, 10, 300, 230);
		add(show_stage);
	}
	public int get_key() {
		return get_key;
	}
	
	public void reset() {
		
		get_key=key[stage];
		count_sheep=stage_count_sheep[stage];
        sheep=stage_sheep[stage];  
        remain_sheep.setText("남은 양 : "+count_sheep);
        
        show_heart();
        create_sheep();
        show_other();
        show_sheep();
        add_stage_img();
	}
		
	 public void Play(String fileName)
	    {
	        try
	        {
	            AudioInputStream ais = AudioSystem.getAudioInputStream(new File(fileName));
	            Clip clip = AudioSystem.getClip();
	            clip.stop();
	            clip.open(ais);
	            clip.start();
	        }
	        catch (Exception ex)
	        {
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
			else if(random_sheep[i]==2) {
				show_sheep[i].setIcon(blue_sheep);
			}
			else if(random_sheep[i]==3) {
				show_sheep[i].setIcon(yellow_sheep);
			}
			
			show_sheep[i].setBounds(x, y, 155, 152);
			y+=200;
			add(show_sheep[i]);
		}
	}
	public void add_arrow() {
		left_arrow.setBounds(30, 615, 150, 150);
	      right_arrow.setBounds(620, 615, 150, 150);
	      right_arrow.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if(stage==0||stage==1||stage==2) {
	            	  if(random_sheep[3]==1) {//1 (회색 양이면)
	            		  Play("../sound/LOR_correct.wav");
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
							  else mainFrame.change("MinePanel",get_key);
						  }
					  }
	            	  else {
	            		  Play("../sound/LOR_wrong.wav");
	            		  moksoom--;
	            		  show_heart();
	            		  
	            	  }
				}
				if(stage==3) {
	            	  if(random_sheep[3]==1||random_sheep[3]==3) {//1 (회색 양이면)
	            		  Play("../sound/LOR_correct.wav");
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
							  
							  else {
								  mainFrame.change("MinePanel",get_key);
							  }
						  }
					  }
	            	  else {
	            		  Play("../sound/LOR_wrong.wav");
	            		  moksoom--;
	            		  show_heart();
	            		  
	            	  }
				}
				
	              //맨 위 양 랜덤 배정
	              for(int i=2;i>=0;i--) {
	            	  random_sheep[i+1]=random_sheep[i];
	            	  //System.out.println(random_sheep[i+1]+"<-"+random_sheep[i]);
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
	      			else if(random_sheep[i]==2) {
	    				show_sheep[i].setIcon(blue_sheep);
	    			}
	    			else if(random_sheep[i]==3) {
	    				show_sheep[i].setIcon(yellow_sheep);
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
				if(stage==0||stage==1) {
				  if(random_sheep[3]==0) {//0 (핑크 양이면)
					  Play("../sound/LOR_correct.wav");
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
						  else mainFrame.change("MinePanel",get_key);
						  
					  }
				  }
				  else {
					  Play("../sound/LOR_wrong.wav");
					  moksoom--;
					  show_heart();
				  }
				}
				if(stage==2||stage==3) {
					  if(random_sheep[3]==0||random_sheep[3]==2) {//0 (핑크 양이면)
						  Play("../sound/LOR_correct.wav");
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
							  else {
								  mainFrame.change("MinePanel",get_key);
							  }
							  
						  }
					  }
					  else {
						  Play("../sound/LOR_wrong.wav");
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
	      			else if(random_sheep[i]==2) {
	    				show_sheep[i].setIcon(blue_sheep);
	    			}
	    			else if(random_sheep[i]==3) {
	    				show_sheep[i].setIcon(yellow_sheep);
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
	      
	}
	public void show_other() { // 다른것들 패널에 붙이기
		
		  if(stage==0||stage==1) {
			  
		      left_sheep[0].setIcon(pink_sheep);
		      right_sheep[0].setIcon(gray_sheep);
		      left_sheep[0].setBounds(60,300,155,152);
		      right_sheep[0].setBounds(620,300,155,152);
		      add(left_sheep[0]);
		      add(right_sheep[0]); // 울타리 속 양
		  }
		  else if(stage==2) {
			  left_sheep[0].setIcon(pink_sheep);
			  left_sheep[1].setIcon(blue_sheep);
		      right_sheep[0].setIcon(gray_sheep);
		      left_sheep[0].setBounds(60,200,155,152);
		      left_sheep[1].setBounds(60,400,155,152);
		      right_sheep[0].setBounds(620,300,155,152);
		      add(left_sheep[0]);
		      add(left_sheep[1]);
		      add(right_sheep[0]); // 울타리 속 양
		  }
		  else {
			  left_sheep[0].setIcon(pink_sheep);
			  left_sheep[1].setIcon(blue_sheep);
		      right_sheep[0].setIcon(gray_sheep);
		      right_sheep[1].setIcon(yellow_sheep);
		      left_sheep[0].setBounds(60,200,155,152);
		      left_sheep[1].setBounds(60,400,155,152);
		      right_sheep[0].setBounds(620,200,155,152);
		      right_sheep[1].setBounds(620,400,155,152);
		      
		      add(left_sheep[0]);
		      add(left_sheep[1]);
		      add(right_sheep[0]);
		      add(right_sheep[1]);// 울타리 속 양
		  }
	      
	      remain_sheep.setBounds(5, 5,200,100);
	      remain_sheep.setText("남은 양 : "+count_sheep);
	     add(remain_sheep); // 남은 양 표시 라벨
	}
	
	public void show_heart() { // 목숨 하트 보여주기
		if(moksoom!=0) {
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
		else {
			mainFrame.change("gameover",0);
		}
	    
	}
}
