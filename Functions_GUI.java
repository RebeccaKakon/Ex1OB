package myMath;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.awt.Color;
import java.awt.Font;

public class Functions_GUI implements functions  {

	LinkedList <function> save= new LinkedList<function>();

	/**
	 * 
	 */

	public static void main(String[] args) {

		// TODO Auto-generated method stub

		Functions_GUI a=new Functions_GUI();
		Range x=new Range(-5,5);
		//a.drawFunctions(600, 1000, x, x, 200);
		Polynom s= new Polynom ("x^2");
		Polynom h= new Polynom ("X^2+2");
		Polynom r= new Polynom ("X^2+3");
		Polynom v= new Polynom ("X^2+4");
		Polynom k= new Polynom ("2x^3+2x^2");
		Polynom p= new Polynom ("x^4+2x+5");
		a.add(s);
		a.add(h);
		a.add(r);
		a.add(v);
		a.add(k);
		a.add(p);
		a.drawFunctions(600, 600, x, x, 100);
		

	}

	@Override
	public boolean add(function arg0) {
		// TODO Auto-generated method stub
		return save.add(arg0);
	}

	@Override
	public boolean addAll(Collection<? extends function> arg0) {
		// TODO Auto-generated method stub
		return save.addAll(arg0);
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		save.clear();
	}

	@Override
	public boolean contains(Object arg0) {
		// TODO Auto-generated method stub
		return save.contains(arg0);
	}

	@Override
	public boolean containsAll(Collection<?> arg0) {
		// TODO Auto-generated method stub
		return save.containsAll(arg0);
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return save.isEmpty();
	}

	@Override
	public Iterator<function> iterator() {
		// TODO Auto-generated method stub
		return save.iterator();
	}

	@Override
	public boolean remove(Object arg0) {
		// TODO Auto-generated method stub
		return save.remove(arg0);
	}

	@Override
	public boolean removeAll(Collection<?> arg0) {
		// TODO Auto-generated method stub
		return save.removeAll(arg0);
	}

	@Override
	public boolean retainAll(Collection<?> arg0) {
		// TODO Auto-generated method stub
		return save.retainAll(arg0);
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return save.size();
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return save.toArray();
	}

	@Override
	public <T> T[] toArray(T[] arg0) {
		// TODO Auto-generated method stub
		return save.toArray(arg0);
	}

	@Override
	public void initFromFile(String file) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void saveToFile(String file) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void drawFunctions(int width, int height, Range rx, Range ry, int resolution) {
		// TODO Auto-generated method stub
		StdDraw.setCanvasSize(width,height);
		StdDraw.setPenRadius(0.01);
		StdDraw.setXscale(rx.get_min(), rx.get_max());
		StdDraw.setYscale(ry.get_min(), ry.get_max());
		StdDraw.line(rx.get_min(), 0, rx.get_max(), 0);
		StdDraw.line(0,ry.get_min(), 0, ry.get_max());

		StdDraw.setPenRadius(0.001);
		for(int i=(int) rx.get_min();i<rx.get_max();i=i+1) {
			StdDraw.line(i,0, i, ry.get_max());
			StdDraw.line(i,0, i, ry.get_min());

		}
		for(int i=(int) ry.get_min();i<ry.get_max();i=i+1) {
			StdDraw.line(0,i, rx.get_max(),i);
			StdDraw.line(0, i, rx.get_min(),i);

		}
		Color[] colors= {StdDraw.BLACK,StdDraw.BLUE,StdDraw.BOOK_BLUE,
				StdDraw.BOOK_RED,StdDraw.PINK,StdDraw.CYAN,StdDraw.DARK_GRAY,StdDraw.DARK_GRAY,
				StdDraw.GRAY,StdDraw.GREEN,StdDraw.LIGHT_GRAY,StdDraw.MAGENTA,StdDraw.ORANGE,
				StdDraw.PRINCETON_ORANGE,StdDraw.RED,StdDraw.YELLOW};
		int count=0;
		

		Iterator s=save.iterator();
		StdDraw.setPenRadius(0.005);
		while(s.hasNext()) {
			//StdDraw.setFont(new Font("calibrity",Font.PLAIN,14));
			StdDraw.setPenColor(colors[count]);
			function c=((function)s.next());
			System.out.println(c);
			double step=(Math.abs(rx.get_min())+Math.abs(rx.get_max()))/resolution;
			for(double i=rx.get_min();i<rx.get_max();i=i+step) {
				double y=c.f(i);
				double y2=c.f(i+step);
				StdDraw.line(i,y,i+step, y2);
			}
			count++;
			if(count==colors.length-1)
				count=0;
				

		}
		
		
		
		
	}

		@Override
		public void drawFunctions(String json_file) {
			// TODO Auto-generated method stub

		}
//		public void drawFunctionsSave(int width, int height, Range rx, Range ry, int resolution)  {
//			StdDraw.setCanvasSize(width,height);
//			StdDraw.setPenRadius(0.01);
//			StdDraw.setXscale(rx.get_min(), rx.get_max());
//			StdDraw.setYscale(ry.get_min(), ry.get_max());
//			StdDraw.line(rx.get_min(), 0, rx.get_max(), 0);
//			StdDraw.line(0,ry.get_min(), 0, ry.get_max());
//
//			StdDraw.setPenRadius(0.001);
//			for(int i=(int) rx.get_min();i<rx.get_max();i=i+10) {
//				StdDraw.line(i,0, i, ry.get_max());
//				StdDraw.line(i,0, i, ry.get_min());
//
//			}
//			for(int i=(int) ry.get_min();i<ry.get_max();i=i+10) {
//				StdDraw.line(0,i, rx.get_max(),i);
//				StdDraw.line(0, i, rx.get_min(),i);
//
//			}
//			StdDraw.setPenRadius(0.01);
//			StdDraw.setPenColor(20,200,10);
//			Polynom b=new Polynom("0.2X^2");
//			ComplexFunction a=new ComplexFunction(b,Operation.Plus,b);
//			for(int i=(int)rx.get_min();i<(int)rx.get_max();i=i+1) {
//				double y=a.f(i);
//				double x=i;
//				double yy=a.f(i+1);
//				double xx=i+1;
//				StdDraw.line(x,y, xx, yy);
//			}
//
//			StdDraw.point(2, 5);
//			StdDraw.point(10, 10);
//			StdDraw.point(10, 30);
//
//			StdDraw.setPenColor(0,20,100);
//			Polynom bb=new Polynom("X");
//			ComplexFunction aa=new ComplexFunction(bb,Operation.Plus,bb);
//			for(int i=(int)rx.get_min();i<(int)rx.get_max();i=i+1) {
//				double yu=aa.f(i);
//				double xu=i;
//				double yyu=aa.f(i+1);
//				double xxu=i+1;
//				StdDraw.line(xu,yu, xxu, yyu);
//			}
//
//
//			StdDraw.setPenColor(15,200,100);
//			Polynom c=new Polynom ("15");
//			ComplexFunction aaa=new ComplexFunction(c,Operation.Plus,c);
//			for(int i=(int)rx.get_min();i<(int)rx.get_max();i=i+1) {
//				double yuu=aaa.f(i);
//				double xuu=i;
//				double yyuu=aaa.f(i+1);
//				double xxuu=i+1;
//				StdDraw.line(xuu,yuu, xxuu, yyuu);
//			}
//
//
//
//		}

		


	}


