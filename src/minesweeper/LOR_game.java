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

	ImageIcon background_img=new ImageIcon("images/LOR_background2.jpg");
	int random_sheep[]=new int[4];
	ImageIcon pink_sheep=new ImageIcon("images/sheep_pink.png");
	ImageIcon blue_sheep=new ImageIcon("images/sheep_blue.png");
	ImageIcon gray_sheep=new ImageIcon("images/sheep_gray.png");
	ImageIcon yellow_sheep=new ImageIcon("images/sheep_yellow.png");
	
	ImageIcon stage_img[]=new ImageIcon[4];
	ImageIcon left=new ImageIcon("images/left_arrow.png");
	ImageIcon right=new ImageIcon("images/right_arrow.png");
	ImageIcon left_press=new ImageIcon("images/left_arrow_press.png");
	ImageIcon right_press=new ImageIcon("images/right_arrow_press.png");
	
	ImageIcon heartImg=new ImageIcon("images/moksoom.png");
	
	JLabel show_sheep[]=new JLabel[4];
	JLabel heart[]=new JLabel[3];
	JLabel left_sheep[]=new JLabel[2];
	JLabel right_sheep[]=new JLabel[2];
	JLabel left_arrow=new JLabel(left);//왼화
	JLabel right_arrow=new JLabel(right);//오화
	JLabel remain_sheep=new JLabel();
	JLabel show_stage=new JLabel();
	JLabel timer=new JLabel();
	
	JLabel back=new JLabel(background_img);
	
	Timer th= new Timer(timer);

	int stage_count_sheep[]={10,15,15,50}; //스테이지 별 양의 수  (10,20,20,50)
	int stage_sheep[]= {2,3,4,4};
	int key[]= {5,10,15,20};
	int moksoom=2;
	int stage;
	
	int count_sheep; // 단계 별 양의 수
	int count_num_sheep=0; // 목장을 보낸 모든 양의 수
	int sheep;
	int get_key;
	int a=0;
	
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
        stage_img[0]=new ImageIcon("images/stage-01.png");
        stage_img[1]=new ImageIcon("images/stage-02.png");
        stage_img[2]=new ImageIcon("images/stage-03.png");
        stage_img[3]=new ImageIcon("images/stage-04.png");
       
        for(int i=0;i<4;i++) {
			Image img =stage_img[i].getImage() ;  
			Image newimg = img.getScaledInstance( 200,54,  java.awt.Image.SCALE_SMOOTH ) ;  
			stage_img[i] = new ImageIcon( newimg );	
		}
        remain_sheep.setFont(new Font("HY견고딕",Font.BOLD,22));
        timer.setFont(new Font("HY견고딕",Font.BOLD,22));
        
        setLayout(null);
        
        //reset();
	    add_arrow();
        
	}
	public void paintComponent(Graphics g) {
        g.drawImage(background_img.getImage(), 0, 0, null);
        setOpaque(false); //그림을 표시하게 설정,투명하게 조절
        super.paintComponent(g);
 }
	public void add_stage_img() { //스테이지 이미지 추가
		
		
		switch(stage) {
		case 0:show_stage.setIcon(stage_img[0]);break;
		case 1:show_stage.setIcon(stage_img[1]);break;
		case 2:show_stage.setIcon(stage_img[2]);break;
		case 3:show_stage.setIcon(stage_img[3]);break;
		}
		
		show_stage.setBounds(0, 30, 300, 230);
		add(show_stage);
	}
	public void reset() { //스테이지 올라갔을 때 리셋
		
        
		count_sheep=stage_count_sheep[stage];
		
		
		if(stage!=0) JOptionPane.showMessageDialog(null,"NEXT STAGE!!","next",JOptionPane.PLAIN_MESSAGE);
		if(stage==3)  JOptionPane.showMessageDialog(null,"!! 보이는 것과 반대로 하세요 !!","next",JOptionPane.WARNING_MESSAGE);
		
		switch(stage) {
		case 0: Play("sound/stage_1.wav"); break;
		case 1: Play("sound/stage_2.wav"); break;
		case 2: Play("sound/stage_3.wav"); break;
		case 3: Play("sound/stage_4.wav");break;
		}
		
		
        sheep=stage_sheep[stage];  
        remain_sheep.setText("목장 속 양의 수 : "+count_num_sheep);
       
        show_heart();
        create_sheep();
        show_other();
        show_sheep();
        add_stage_img();
      
        a=0;
	}
		
	 public void Play(String fileName) //소리
	    {
	        try
	        {
	            AudioInputStream ais = AudioSystem.getAudioInputStream(new File(fileName));
	            Clip clip = AudioSystem.getClip();
	            //clip.stop();
	            clip.open(ais);
	            clip.start();
	        }
	        catch (Exception ex)
	        {
	        }
	    }
	public void create_sheep() { // 맨 처음 양들 랜덤 배정
		if(stage==0) {
			for(int i=0;i<4;i++) {
				random_sheep[i] = (int) (Math.random() * sheep);
			}
		}
		else { //다음 스테이지로 넘어갔을 때 이전과 다른 양이 배정되어서 목숨 깎이는 것 방지
			for(int i=0;i<3;i++) {
				random_sheep[i] = (int) (Math.random() * sheep);
			}
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
	public void cal_key(int SheepNum) { // 받는 키 개수 계산
		switch(SheepNum/10) {
		case 0:case 1:case 2: get_key=5;break;
		case 3: case 4: get_key=10;break;  //시연할 때만 5로 원래는 10
		case 5: get_key=15;break;
		default: get_key=20;break;
		}
	}
	public void clickEvent_right() { //올바르게 클릭했을 때 함수
		if(timer.getText().equals("시간 종료")&& a!=1) {
			a=1; // 두 번 들어오는 거 방지
			cal_key(count_num_sheep);
			JOptionPane.showMessageDialog(null,"축하합니다! 열쇠 "+get_key+"개를 받았습니다","시간 종료",JOptionPane.INFORMATION_MESSAGE);
			  mainFrame.Mine_Game=new Mine_game(mainFrame);
			  mainFrame.change("story2",get_key);
		}
		if(a!=1) {
		Play("sound/LOR_correct.wav");
		  count_sheep--;
		  count_num_sheep++;
		  remain_sheep.setText("목장 속 양의 수 : "+count_num_sheep);
		  if(count_sheep==0) {
			  stage++;
			  reset();
		  }
		}
	}
	public void clickEvent_wrong() { // 틀리게 클릭했을 때 함수
		if(timer.getText().equals("시간 종료")&& a!=1) {
			a=1;
			cal_key(count_num_sheep);
			JOptionPane.showMessageDialog(null,"축하합니다! 열쇠 "+get_key+"개를 받았습니다","시간 종료",JOptionPane.INFORMATION_MESSAGE);
			  mainFrame.Mine_Game=new Mine_game(mainFrame);
			  mainFrame.change("story2",get_key);
		 
		}
		if(a!=1) {
			Play("sound/LOR_wrong.wav");
			moksoom--;
			show_heart();
		}
	}
	public void add_arrow() { // 방향키 추가
		left_arrow.setBounds(30, 615, 150, 150);
	      right_arrow.setBounds(620, 615, 150, 150);
	      right_arrow.addMouseListener(new MouseListener() { // 오른쪽 방향키 클릭 이벤트 리스너

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
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
				right_arrow.setIcon(right_press);
				if(stage==0||stage==1) {
	            	  if(random_sheep[3]==1) {//1 (회색 양이면)
	            		  clickEvent_right();
					  }
	            	  else {
	            		  clickEvent_wrong();
	            		  
	            	  }
				}
				else if(stage==2) {
	            	  if(random_sheep[3]==1||random_sheep[3]==3) {//1 (회색 양이면) 3 (노란 양이면)
	            		  clickEvent_right();
					  }
	            	  else {
	            		  clickEvent_wrong();
	            		  
	            	  }
				}
				else if(stage==3) {
					if(random_sheep[3]==0||random_sheep[3]==2) {//0 (핑크 양이면) //1 (파란 양이면)
						  clickEvent_right();
					  }
					  else {
						  clickEvent_wrong();
					  }
				}
				
				
	              //맨 위 양 랜덤 배정
	              random_sheep();
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				right_arrow.setIcon(right);
			}
	    	  
	      });
	      left_arrow.addMouseListener(new MouseListener() { //왼쪽 방향키 클릭 리스너

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
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
				left_arrow.setIcon(left_press);
				if(stage==0) {
					  if(random_sheep[3]==0) {//0 (핑크 양이면)
						  clickEvent_right();
					  }
					  else {
						  clickEvent_wrong();
					  }
					}
					else if(stage==1||stage==2) {
						  if(random_sheep[3]==0||random_sheep[3]==2) {//0 (핑크 양이면) //1 (파란 양이면)
							  clickEvent_right();
						  }
						  else {
							  clickEvent_wrong();
						  }
					}
					else if(stage==3) {
						if(random_sheep[3]==1||random_sheep[3]==3) {//1 (회색 양이면) 3 (노란 양이면)
		            		  clickEvent_right();
						  }
		            	  else {
		            		  clickEvent_wrong();
		            		  
		            	  }
					}
					
					//맨 위 양 랜덤 배정
					random_sheep();
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				left_arrow.setIcon(left);
			}
	    	  
	      });
	      add(left_arrow);
	      add(right_arrow); // 방향키 이미지
	      
	}
	
	public void random_sheep() { // 클릭했을 때 양 내려오게 하는 함수
		for(int i=2;i>=0;i--) { // 하나씩 내려오게
      	  random_sheep[i+1]=random_sheep[i];
        }
        random_sheep[0]=(int)(Math.random()*sheep); //새로운 양 랜덤 배정
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
	public void other_remove() { // 양 수 달라졌을 때 위치 바꾸기 위해 원래 있던  지우는 함수
		if(stage==1) {
			remove(left_sheep[0]);
			remove(left_sheep[1]);
		}
		else if(stage==2||stage==3) {
			remove(left_sheep[0]);
			remove(right_sheep[0]);
			remove(left_sheep[1]);
			remove(right_sheep[1]);
		}
	}
	public void show_other() { // 왼쪽 오른쪽 양들 , label들 패널에 붙이기
		
		  if(stage==0) {
			  
		      left_sheep[0].setIcon(pink_sheep);
		      right_sheep[0].setIcon(gray_sheep);
		      left_sheep[0].setBounds(60,300,155,152);
		      right_sheep[0].setBounds(620,300,155,152);
		      add(left_sheep[0]);
		      add(right_sheep[0]); // 울타리 속 양
		  }
		  else if(stage==1) {
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
	      
	      remain_sheep.setBounds(5, 5,300,100);
	      remain_sheep.setText("목장 속 양의 수 : "+count_num_sheep);
	      add(remain_sheep); // 남은 양 표시 라벨
	      
	       
	     
	      timer.setBounds(5, 30,200,100);
	      add(timer); //타이머 label
	      
	      if(stage==0) {
	    	  th.start();
	      }
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
			//th.interrupt();
			mainFrame.change("gameover",0);
		}
	    
	}
}

