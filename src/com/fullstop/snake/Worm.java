package com.fullstop.snake;

import java.util.Arrays;
/**
 * �߱���
 * @author Administrator
 *1.Ĭ�������㷨
 *2.�����������㷨
 *3.ʳ���������㷨
 *	ȷ�ϳԵ�ʳ���Ժ󽫶�����������
 *4.��ײ����㷨
 */
public class Worm {
	public static final int DEFAULT_LENGTH=12;
	private Cell[] cells;//��������,��ʼ����
	//�����ߵ��˶�����
	public static final int UP=1;
	public static final int DOWN=-1;
	public static final int LEFT=2;
	public static final int RIGHT=-2;
	private int currentDirection;//�ߵ��ƶ���������
	public Worm() {
		cells = new Cell[DEFAULT_LENGTH];
		for(int i = 0;i<cells.length;i++){
			cells[i]=new Cell(i,0);
			
		}
		currentDirection=RIGHT;//����Ĭ�������ƶ�
	}
	//�ж�һ�����Ƿ���������
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
	//Ĭ���ƶ��㷨
	public boolean defaultMove(Cell food) {
//		for(int j=0;j<cells.length-1;j++){
//			cells[j]=cells[j+1];
//		}
//		cells[cells.length-1]=createHead(currentDirection);//�����㷨����
		int direction = currentDirection;
		Cell head = createHead(direction);
		//�ж��Ƿ�Ե�ʳ��
		boolean eat = (head.getX()==food.getX()&&head.getY()==food.getY());
		if(eat==true){
			cells = Arrays.copyOf(cells,cells.length+1);
			//��������������������(null,null)
			//����Ĭ���������ƶ�����ʱ����ʹ��Ĭ�������㷨�����,
			//cells[cells.length-2]=(null��null),cells[cells.length-1]=head�����
			//Ϊ�˱�������������֣�ֱ�Ӳ���ͷ��㣬ͬʱʹ��else��������Ʋ��Ե�ʳ���Ĭ�����������
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
	//Ĭ���ƶ����ʳ���㷨
	public boolean defaultmove(int direction, Cell food){
		if(currentDirection+direction==0){
			return false;//��鵱ǰ�������ƶ������Ƿ��෴
		}
		currentDirection = direction;
		Cell head = createHead(direction);
		//�ж��Ƿ�Ե�ʳ��
		boolean eat = (head.getX()==food.getX()&&head.getY()==food.getY());
		if(eat==true){
			cells = Arrays.copyOf(cells,cells.length+1);
			//��������������������(null,null)
			//����Ĭ���������ƶ�����ʱ����ʹ��Ĭ�������㷨�����,
			//cells[cells.length-2]=(null��null),cells[cells.length-1]=head�����
			//Ϊ�˱�������������֣�ֱ�Ӳ���ͷ��㣬ͬʱʹ��else��������Ʋ��Ե�ʳ���Ĭ�����������
			cells[cells.length-1]=head;
		}else{
			for(int i = 0;i<cells.length-1;i++){
				cells[i]=cells[i+1];
			}
			cells[cells.length-1] = head;
		}
		return eat;
	}
	//��ײ����㷨
	public boolean hit(int direction) {
		Cell head = createHead(direction);
		//System.out.println(head);
		if(head.getX()<-1||head.getX()>WormStage.COLS||
			head.getY()<-1||head.getY()>WormStage.ROWS){
			return true;
		}
		//������ײ���
		for(int i = 0;i<cells.length-1;i++){
			Cell node = cells[i];
			if(node.getX()==head.getX()&&node.getY()==head.getY()){
				return true;
			}
		}
		return false;
	}
	//�����㷨����
	public boolean hit() {
		return hit(currentDirection);
	}
	//��ȡworm�е�ÿһ����
	public Cell[] getCells() {
		return Arrays.copyOf(cells, cells.length);
	}
}
