package com.fullstop.snake;
/*
 * ̰������̨
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.*;
import java.util.Timer;

import javax.swing.*;
import javax.swing.border.LineBorder;
public class WormStage extends JPanel {
	/**
	 * ��̨
	 */
	private static final long serialVersionUID = 1L;
	public static final int ROWS=35;
	public static final int COLS=35;
	public static final int CELL_SIZE=10;//���Ӵ�СΪ10������
	private Worm worm;
	private Cell food;
	public WormStage() {
		worm = new Worm();
		food = createFood();
	}
	/**��д��ͼ����	 */
	public void paint(Graphics g) {
		//��䱳��ɫ
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 0, getWidth(), getHeight());
		//���ʳ��
		g.setColor(Color.RED);
		g.fill3DRect(CELL_SIZE*food.getX(), 
				CELL_SIZE*food.getY(), CELL_SIZE, CELL_SIZE, true);
		//������
		g.setColor(Color.GREEN);
		Cell[] cells = worm.getCells();
		for(int i=0;i<cells.length;i++){
			Cell node  = cells[i];
			g.fill3DRect(CELL_SIZE*node.getX(), CELL_SIZE*node.getY(),
					CELL_SIZE, CELL_SIZE, true);
		}
	}
	public void action() {
		//�ᾡ��ص���paint(g)�������ƽ���
		Timer timer = new Timer();
		timer.schedule(new TimerTask(){
			public void run() {
				//�ߵ������߼�
				if(worm.hit()){//�����ײ ��������ϴ����µ��ߺ��µ�ʳ��
					worm=new Worm();
					food = createFood();
				}else{
					boolean eat =worm.defaultMove(food);
					if(eat){
						food =createFood();
					}
				}
				repaint();
			}
		}, 0, 1000/7);
		this.requestFocus();
		this.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				switch (key) {
				case KeyEvent.VK_UP:
					moveTo(Worm.UP);
					break;
				case KeyEvent.VK_DOWN:
					moveTo(Worm.DOWN);
					break;
				case KeyEvent.VK_LEFT:
					moveTo(Worm.LEFT);
					break;
				case KeyEvent.VK_RIGHT:
					moveTo(Worm.RIGHT);
					break;
				}
			}//keyPressed
		});//KeyAdapter
	}
	public static void main(String[] args) {
		//�������
		JFrame frame = new JFrame("̰����");
		WormStage pane = new WormStage();
		frame.setLayout(null);//ȡ�����ڵ�Ĭ�ϲ���
		frame.add(pane);
		pane.setSize(CELL_SIZE*COLS,CELL_SIZE*ROWS );//��������С
		pane.setLocation(50, 50);//�������λ��
		frame.setSize(450, 480);//���ô��ڴ�С
		pane.setBorder(new LineBorder(Color.black));
		frame.setLocationRelativeTo(null);//Frame����
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pane.action();
	}
//	����һ��ʳ��
	
	private Cell createFood() {
		Random random = new Random();
		int a,b;
		do{
			a = random.nextInt(COLS);
			b = random.nextInt(ROWS);
		}while(worm.contains(a, b));
		return new Cell(a,b);
	}
	public String toString() {
		return "worm:"+worm+"\nfood:"+food;
	}
	private void moveTo(int direction){
		if(worm.hit()){//�����ײ ��������ϴ����µ��ߺ��µ�ʳ��
			worm=new Worm();
			food = createFood();
		}else{
			boolean eat =worm.defaultmove(direction, food);
			if(eat){
				food =createFood();
				}
			}
		repaint();
	}
}
