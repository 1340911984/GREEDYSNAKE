package moonsnake;

import javax.swing.JFrame;

public class Mysnake {

	public static void main(String[] args) {
		// 建立窗口
		JFrame frame =new JFrame();//import
		frame.setBounds(10,10,900,720);//900x700(720是因为有放大缩小删除)
		frame.setResizable(false);//不可以改变大小
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//可以关掉
		frame.add(new MPanel());
		frame.setVisible(true);//可见
	}

}