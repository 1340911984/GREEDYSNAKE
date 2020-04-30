package moonsnake;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import javax.sound.sampled.*;

public class MPanel extends JPanel implements KeyListener, ActionListener{
	ImageIcon title = new ImageIcon("title.jpg");
	ImageIcon body = new ImageIcon("body.png");
	ImageIcon up = new ImageIcon("up.png");
	ImageIcon food = new ImageIcon("food.png");
	ImageIcon left = new ImageIcon("left.png");
	ImageIcon right = new ImageIcon("right.png");
	ImageIcon down = new ImageIcon("down.png");
	int len=3;
	int score=0;
	
	int[]snakex=new int[750];
	int[]snakey=new int[750];
	String fx="R";//方向：R L D U
	boolean isStarted=false;
	boolean isFailed=false;
	Timer timer =new Timer(100, this);
	int foodx;
	int foody;
	Random rand=new Random();//随机数
	
	Clip bgm;
		
	public MPanel() {
		initSnake();
		this.setFocusable(true);//可聚焦于键盘的输入（？
		this.addKeyListener(this);//监听键盘
		timer.start();
		//loadBGM();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.setBackground(Color.WHITE);
		title.paintIcon(this, g, 25, 11);	
		g.fillRect(25, 75, 850, 600);
		g.setColor(Color.WHITE);
		g.drawString("Length: "+len, 750, 35);
		g.drawString("Score: "+score, 750, 52);
		if(fx=="R") {//蛇头
			right.paintIcon(this, g, snakex[0], snakey[0]);
		}else if(fx=="L") {
			left.paintIcon(this, g, snakex[0], snakey[0]);
		}else if(fx=="D") {
			down.paintIcon(this, g, snakex[0], snakey[0]);
		}else if(fx=="U") {
			up.paintIcon(this, g, snakex[0], snakey[0]);
		}
		for(int i=1;i<len;i++) {
			body.paintIcon(this, g, snakex[i], snakey[i]);
		}
		food.paintIcon(this, g, foodx, foody);//恰饭
		
		if(isStarted==false) {//开始的提示
			g.setColor(Color.WHITE);
			g.setFont(new Font("arial", Font.BOLD,40));
			g.drawString("Press Space to Start", 250, 345);
		}else {
			g.setColor(Color.BLACK);
			g.setFont(new Font("arial", Font.BOLD,18));
			g.drawString("Press Space to Stop", 699, 689);
		}
		if(isFailed) {//开始的提示
			g.setColor(Color.RED);
			g.setFont(new Font("arial", Font.BOLD,40));
			g.drawString("Press Space to ReStart", 250, 345);
		}
	}
	
	public void initSnake() {
		len=3;
		snakex[0]=100;
		snakey[0]=100;
		snakex[1]=75;
		snakey[1]=100;
		snakex[2]=50;
		snakey[2]=100;
		foodx=25+25*rand.nextInt(34);
		foody=75+25*rand.nextInt(24);
		fx="R";
		score=0;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if(keyCode==KeyEvent.VK_SPACE) {
			if(isFailed) {
				isFailed =false;
				initSnake();
			}
			isStarted =!isStarted;
			repaint();
			if(isStarted) {
 				//playBGM();
			}else {
				//stopBGM();
			}
		}else if(keyCode==KeyEvent.VK_LEFT) {
			fx="L";
		}else if(keyCode==KeyEvent.VK_RIGHT) {
			fx="R";
		}else if(keyCode==KeyEvent.VK_UP) {
			fx="U";
		}else if(keyCode==KeyEvent.VK_DOWN) {
			fx="D";
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(isStarted &&!isFailed) {
			for(int i=len-1;i>0;i--) {//身体延续运动
				snakex[i]=snakex[i-1];
				snakey[i]=snakey[i-1];
			}
			if(fx=="R") {
				snakex[0]=snakex[0]+25;
				if(snakex[0]>850)isFailed =true;
			}else if(fx=="L") {
				snakex[0]=snakex[0]-25;
				if(snakex[0]<25)isFailed =true;
			}else if(fx=="U") {
				snakey[0]=snakey[0]-25;
				if(snakey[0]<75)isFailed =true;
			}else if(fx=="D") {
				snakey[0]=snakey[0]+25;
				if(snakey[0]>650)isFailed =true;
			}
			 if(snakex[0]==foodx&&snakey[0]==foody) {
				 len++;
				 score+=100;
				 foodx=25+25*rand.nextInt(34);
				 foody=75+25*rand.nextInt(24);
			 }
			 
			 for(int i=1;i<len;i++) {
				 if(snakex[i]==snakex[0]&&snakey[i]==snakey[0]) {
					 isFailed =true;
				 }
			 }
			 if(isFailed) {
				// stopBGM();
			 }
			repaint();
		}
		timer.start();
	}
	
//	private void loadBGM() {
//		try {
//			bgm=AudioSystem.getClip();
//			InputStream is=this.getClass().getClassLoader().getResourceAsStream("bgm2.wav");
//			AudioInputStream ais= AudioSystem.getAudioInputStream(is);
//			bgm.open(ais);
//		} catch (LineUnavailableException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (UnsupportedAudioFileException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	
//	private void playBGM() {
//		bgm.loop(Clip.LOOP_CONTINUOUSLY);
//	}
//	private void stopBGM() {
//		bgm.stop();
//	}
}