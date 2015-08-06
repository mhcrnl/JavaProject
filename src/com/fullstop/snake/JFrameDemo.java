package com.fullstop.snake;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;
import javax.swing.border.LineBorder;
/**
 *显示窗口与绘图
 */
public class JFrameDemo {
	public static void main(String[] args) {
		JFrame frame = new JFrame("窗口");
		Stage pane = new Stage();
		frame.setLayout(null);//取消窗口的默认布局
		frame.add(pane);
		pane.setSize(350,350 );//设置面板大小
		pane.setLocation(50, 50);//设置面板位置
		frame.setSize(450, 480);//设置窗口大小
		pane.setBorder(new LineBorder(Color.black));
		frame.setLocationRelativeTo(null);//Frame居中
		frame.setVisible(true);
		
		pane.requestFocus();//获取焦点 光标出现的位置，有交点才能接受键盘输入（输入焦点）
		pane.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e) {
				System.out.println("hi"+e.getKeyCode());
			}
		});
	}
}
class Stage extends JPanel{
	/**重写了默认绘图方法	 */
	public void paint(Graphics g) {
		g.setColor(Color.darkGray);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		g.setColor(Color.RED);
		g.fill3DRect(50, 50, 30, 20, true);
	}
}
