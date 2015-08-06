package com.fullstop.snake;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;
import javax.swing.border.LineBorder;
/**
 *��ʾ�������ͼ
 */
public class JFrameDemo {
	public static void main(String[] args) {
		JFrame frame = new JFrame("����");
		Stage pane = new Stage();
		frame.setLayout(null);//ȡ�����ڵ�Ĭ�ϲ���
		frame.add(pane);
		pane.setSize(350,350 );//��������С
		pane.setLocation(50, 50);//�������λ��
		frame.setSize(450, 480);//���ô��ڴ�С
		pane.setBorder(new LineBorder(Color.black));
		frame.setLocationRelativeTo(null);//Frame����
		frame.setVisible(true);
		
		pane.requestFocus();//��ȡ���� �����ֵ�λ�ã��н�����ܽ��ܼ������루���뽹�㣩
		pane.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e) {
				System.out.println("hi"+e.getKeyCode());
			}
		});
	}
}
class Stage extends JPanel{
	/**��д��Ĭ�ϻ�ͼ����	 */
	public void paint(Graphics g) {
		g.setColor(Color.darkGray);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		g.setColor(Color.RED);
		g.fill3DRect(50, 50, 30, 20, true);
	}
}
