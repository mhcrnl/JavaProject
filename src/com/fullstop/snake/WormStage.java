package com.fullstop.snake;
/*
 * 贪吃蛇舞台
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
	 * 舞台
	 */
	private static final long serialVersionUID = 1L;
	public static final int ROWS=35;
	public static final int COLS=35;
	public static final int CELL_SIZE=10;//格子大小为10个像素
	private Worm worm;
	private Cell food;
	public WormStage() {
		worm = new Worm();
		food = createFood();
	}
	/**重写绘图方法	 */
	public void paint(Graphics g) {
		//填充背景色
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 0, getWidth(), getHeight());
		//填充食物
		g.setColor(Color.RED);
		g.fill3DRect(CELL_SIZE*food.getX(), 
				CELL_SIZE*food.getY(), CELL_SIZE, CELL_SIZE, true);
		//绘制射
		g.setColor(Color.GREEN);
		Cell[] cells = worm.getCells();
		for(int i=0;i<cells.length;i++){
			Cell node  = cells[i];
			g.fill3DRect(CELL_SIZE*node.getX(), CELL_SIZE*node.getY(),
					CELL_SIZE, CELL_SIZE, true);
		}
	}
	public void action() {
		//会尽快地调用paint(g)方法绘制界面
		Timer timer = new Timer();
		timer.schedule(new TimerTask(){
			public void run() {
				//蛇的爬行逻辑
				if(worm.hit()){//如果碰撞 则在面板上创建新的蛇和新的食物
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
		//启动软件
		JFrame frame = new JFrame("贪吃蛇");
		WormStage pane = new WormStage();
		frame.setLayout(null);//取消窗口的默认布局
		frame.add(pane);
		pane.setSize(CELL_SIZE*COLS,CELL_SIZE*ROWS );//设置面板大小
		pane.setLocation(50, 50);//设置面板位置
		frame.setSize(450, 480);//设置窗口大小
		pane.setBorder(new LineBorder(Color.black));
		frame.setLocationRelativeTo(null);//Frame居中
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pane.action();
	}
//	生成一个食物
	
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
		if(worm.hit()){//如果碰撞 则在面板上创建新的蛇和新的食物
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
