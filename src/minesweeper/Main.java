package minesweeper;
import javax.swing.*;



import java.awt.*;
public class Main extends JFrame{
	mainpanel MainPanel=null;
	Mine_game Mine_Game=null;
	LOR_game LOR_Game=null;
	story story=null;
	LORstory story2=null;
	gameover over=null;
	gameway way=null;
	gameclear clear=null;
	Rank gameRank=null;
	rankPanel showRank=null;
	
	public void change(String paneName,int key) {
		if(paneName.equals("MinePanel")) {
			Mine_Game.set_landmine(key);
			getContentPane().removeAll();
			getContentPane().add(Mine_Game);
			revalidate();
			repaint();
		}
		else if(paneName.equals("MainPanel")) {
			getContentPane().removeAll();
			getContentPane().add(MainPanel);
			revalidate();
			repaint();
		}
		else if(paneName.equals("LORPanel")) {
			getContentPane().removeAll();
			LOR_Game.other_remove();
			LOR_Game.moksoom=2;
			LOR_Game.stage=0;
			LOR_Game.reset(); //끝나고 다시 게임시작 했을때를 위한 리셋
			getContentPane().add(LOR_Game);
			revalidate();
			repaint();
		}
		else if(paneName.equals("story2")) {
			story2.getKey(key);
			getContentPane().removeAll();
			getContentPane().add(story2);
			revalidate();
			repaint();
		}
		else if(paneName.equals("story")) {
			getContentPane().removeAll();
			getContentPane().add(story);
			revalidate();
			repaint();
		}
		else if(paneName.equals("way")) {
			getContentPane().removeAll();
			way.count=1;
			getContentPane().add(way);
			revalidate();
			repaint();
		}
		else if(paneName.equals("gameover")) {
			getContentPane().removeAll();
			getContentPane().add(over);
			revalidate();
			repaint();
		}
		else if(paneName.equals("gameclear")) {
			clear.getScore(key);
			getContentPane().removeAll();
			getContentPane().add(clear);
			revalidate();
			repaint();
		}
		else if(paneName.equals("showRank")) {
			showRank.getScore(key);
			getContentPane().removeAll();
			getContentPane().add(showRank);
			revalidate();
			repaint();
		}
	}
	public static void main(String[] args) {
		Main mainFrame=new Main();
		
		mainFrame.setTitle("보물을 찾아라");
		mainFrame.MainPanel=new mainpanel(mainFrame);
		mainFrame.story=new story(mainFrame);
		mainFrame.story2=new LORstory(mainFrame);
		mainFrame.way=new gameway(mainFrame);
		mainFrame.over=new gameover(mainFrame);
		mainFrame.clear=new gameclear(mainFrame);
		mainFrame.gameRank=new Rank();
		
		
		mainFrame.add(mainFrame.MainPanel);
		mainFrame.setSize(800, 800);
		mainFrame.setResizable(false);
		mainFrame.setVisible(true);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
