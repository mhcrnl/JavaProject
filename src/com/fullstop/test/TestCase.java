package com.fullstop.test;
import org.junit.Test;

import com.fullstop.snake.Cell;
import com.fullstop.snake.Worm;
import com.fullstop.snake.WormStage;
public class TestCase {
	//@Test
	public void testWormInit(){
		System.out.println("����Worm������");
		Worm worm = new Worm();
		System.out.println(worm);
	}
	//@Test
	public void testWormContains(){
		System.out.println("����Worm�����㷨");
		Worm worm = new Worm();
		System.out.println(worm.contains(2, 0));
		System.out.println(worm.contains(5, 7));
	}
	//@Test
	public void testWormStage(){
		System.out.println("������̨ʵ��");
		WormStage stage = new WormStage();
		System.out.println(stage);
	}
	//@Test
	public void testMove(){
		System.out.println("���в���");
		Worm worm = new Worm();
		Cell food = new Cell(2, 1);
		System.out.println(worm);
		worm.defaultMove(food);
		System.out.println(worm);
	}
	//@Test
	public void testMove2(){
		System.out.println("ʳ�������㷨����");
		Worm worm = new Worm();
		Cell f1 = new Cell(15,0);
		System.out.println(worm);
		System.out.println(worm.defaultmove(Worm.RIGHT,f1));
		System.out.println(worm);
		System.out.println(worm.defaultmove(Worm.RIGHT,f1));
		System.out.println(worm);
		System.out.println(worm.defaultmove(Worm.RIGHT,f1));
		System.out.println(worm);
		System.out.println(worm.defaultmove(Worm.RIGHT,f1));
		System.out.println(worm);
		System.out.println(worm.defaultmove(Worm.RIGHT,f1));
		System.out.println(worm);
		System.out.println(worm.defaultmove(Worm.DOWN,f1));
		System.out.println(worm);
		System.out.println(worm.defaultmove(Worm.DOWN,f1));
		System.out.println(worm);
		System.out.println(worm.defaultmove(Worm.LEFT,f1));
		System.out.println(worm);
	}
	@Test
	public void testhit(){
		System.out.println("��ײ�㷨����");
		Worm worm = new Worm();
		Cell food = new Cell(15, 0);
		worm.defaultmove(Worm.RIGHT, food);
		System.out.println(worm.hit(Worm.RIGHT));
		System.out.println(worm);
		worm.defaultmove(Worm.UP, food);
		System.out.println(worm);
		System.out.println(worm.hit(Worm.UP));
		worm.defaultmove(Worm.RIGHT, food);
		worm.defaultmove(Worm.DOWN, food);
		System.out.println(worm);
		worm.defaultmove(Worm.DOWN, food);
		System.out.println(worm);
		worm.defaultmove(Worm.DOWN, food);
		System.out.println(worm);
		worm.defaultmove(Worm.LEFT, food);
		System.out.println(worm);
		worm.defaultmove(Worm.LEFT, food);
		System.out.println(worm);
		worm.defaultmove(Worm.UP, food);
		System.out.println(worm);
		System.out.println(worm.hit(Worm.UP));
		System.out.println(worm.hit(Worm.LEFT));
		
		worm.defaultmove(Worm.LEFT, food);
		System.out.println(worm);
		
	}
}
