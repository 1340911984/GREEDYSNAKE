package moonsnake;

import javax.swing.JFrame;

public class Mysnake {

	public static void main(String[] args) {
		// ��������
		JFrame frame =new JFrame();//import
		frame.setBounds(10,10,900,720);//900x700(720����Ϊ�зŴ���Сɾ��)
		frame.setResizable(false);//�����Ըı��С
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//���Թص�
		frame.add(new MPanel());
		frame.setVisible(true);//�ɼ�
	}

}