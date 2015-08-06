package com.fullstop.snake;

import java.util.Arrays;
/**
 * 蛇本体
 * @author Administrator
 *1.默认爬行算法
 *2.监听器爬行算法
 *3.食物检查爬行算法
 *	确认吃到食物以后将对象数组扩容
 *4.碰撞检测算法
 */
public class Worm {
	public static final int DEFAULT_LENGTH=12;
	private Cell[] cells;//对象数组,初始化蛇
	//定义蛇的运动方向
	public static final int UP=1;
	public static final int DOWN=-1;
	public static final int LEFT=2;
	public static final int RIGHT=-2;
	private int currentDirection;//蛇的移动方向属性
	public Worm() {
		cells = new Cell[DEFAULT_LENGTH];
		for(int i = 0;i<cells.length;i++){
			cells[i]=new Cell(i,0);
			
		}
		currentDirection=RIGHT;//蛇米默认向右移动
	}
	//判断一个点是否在蛇身上
	public String toString() {
		return Arrays.toString(cells);
	}
	public boolean contains(int x , int y) {
		for(int i = 0;i<cells.length;i++){
			if(cells[i].getX()==x&&cells[i].getY()==y){
				return true;
			}
		}
		return false;
	}
	//默认移动算法
	public boolean defaultMove(Cell food) {
//		for(int j=0;j<cells.length-1;j++){
//			cells[j]=cells[j+1];
//		}
//		cells[cells.length-1]=createHead(currentDirection);//爬行算法核心
		int direction = currentDirection;
		Cell head = createHead(direction);
		//判断是否吃到食物
		boolean eat = (head.getX()==food.getX()&&head.getY()==food.getY());
		if(eat==true){
			cells = Arrays.copyOf(cells,cells.length+1);
			//对象数组扩容在最后插入(null,null)
			//由于默认蛇向右移动，此时继续使用默认爬行算法会出现,
			//cells[cells.length-2]=(null，null),cells[cells.length-1]=head的情况
			//为了避免这种情况出现，直接插入头结点，同时使用else语句来限制不吃到食物的默认爬行情况。
			cells[cells.length-1]=head;
		}else{
			for(int i = 0;i<cells.length-1;i++){
				cells[i]=cells[i+1];
			}
			cells[cells.length-1] = head;
		}
		return eat;
	}
	private Cell createHead(int direction) {
		switch(direction){
		case DOWN:
			int x1 = cells[cells.length-1].getX();
			int y1 = cells[cells.length-1].getY()+1;
			return new Cell(x1,y1);
		case UP:
			int x2 = cells[cells.length-1].getX();
			int y2 = cells[cells.length-1].getY()-1;
			return new Cell(x2,y2);
		case LEFT:
			int x3 = cells[cells.length-1].getX()-1;
			int y3 = cells[cells.length-1].getY();
			return new Cell(x3,y3);
		case RIGHT:
			int x4 = cells[cells.length-1].getX()+1;
			int y4 = cells[cells.length-1].getY();
			return new Cell(x4,y4);
			default:
			break;
		}
		return null;
	}
	//默认移动与吃食物算法
	public boolean defaultmove(int direction, Cell food){
		if(currentDirection+direction==0){
			return false;//检查当前方向与移动方向是否相反
		}
		currentDirection = direction;
		Cell head = createHead(direction);
		//判断是否吃到食物
		boolean eat = (head.getX()==food.getX()&&head.getY()==food.getY());
		if(eat==true){
			cells = Arrays.copyOf(cells,cells.length+1);
			//对象数组扩容在最后插入(null,null)
			//由于默认蛇向右移动，此时继续使用默认爬行算法会出现,
			//cells[cells.length-2]=(null，null),cells[cells.length-1]=head的情况
			//为了避免这种情况出现，直接插入头结点，同时使用else语句来限制不吃到食物的默认爬行情况。
			cells[cells.length-1]=head;
		}else{
			for(int i = 0;i<cells.length-1;i++){
				cells[i]=cells[i+1];
			}
			cells[cells.length-1] = head;
		}
		return eat;
	}
	//碰撞检测算法
	public boolean hit(int direction) {
		Cell head = createHead(direction);
		//System.out.println(head);
		if(head.getX()<-1||head.getX()>WormStage.COLS||
			head.getY()<-1||head.getY()>WormStage.ROWS){
			return true;
		}
		//自身碰撞检测
		for(int i = 0;i<cells.length-1;i++){
			Cell node = cells[i];
			if(node.getX()==head.getX()&&node.getY()==head.getY()){
				return true;
			}
		}
		return false;
	}
	//爬行算法重载
	public boolean hit() {
		return hit(currentDirection);
	}
	//提取worm中的每一个格
	public Cell[] getCells() {
		return Arrays.copyOf(cells, cells.length);
	}
}
