package myMath;

import java.util.Iterator;

public class PolynomTest {
	public static void main(String[] args) {
		
		
		test1();
		test2();
		test3();
		test4();
		test5();
		test6();
		

	}
	public static void test1() {
		Polynom p1 = new Polynom();
		String[] monoms = {"1","X","X^2","0.5X^2"};
		Monom m = new Monom(monoms[1]);
		p1.add(m);
		double aa = p1.area(0, 1, 0.0001);
		p1.substract(p1);
		System.out.println(p1);
	}
	public static void test2() {
		Polynom p1 = new Polynom(), p2 =  new Polynom();
		String[] monoms1 = {"2", "-X","-3.2X^2","4","-1.5X^2"};
		String[] monoms2 = {"5", "1.7X","3.2X^2","-3","-1.5X^2"};
		for(int i=0;i<monoms1.length;i++) {
			Monom m = new Monom(monoms1[i]);
			p1.add(m);
		}
		for(int i=0;i<monoms2.length;i++) {
			Monom m = new Monom(monoms2[i]);
			p2.add(m);
		}
		System.out.println("p1: "+p1);
		System.out.println("p2: "+p2);
		p1.add(p2);
		System.out.println("p1+p2: "+p1);
		p1.multiply(p2);
		System.out.println("(p1+p2)*p2: "+p1);
		String s1 = p1.toString();
		Polynom_able pp1 = Polynom.parse(s1);
		System.out.println("from string: "+pp1);
	}

	public static void test3() {    
		Polynom p=new Polynom("3X^2+4X+2");
		Polynom a=new Polynom("2");
		p.multiply(a);
		

		Polynom z=new Polynom("3X^2");
		Monom p1=new Monom("4X");
		Monom p2=new Monom("2");		
		Polynom a2=new Polynom("-2");
		z.add(p1);
		z.add(p2);
		z.multiply(a2);
		z.add(p);
		System.out.println(z);

	}
	public static void test4() {   
		Polynom p= new Polynom("2X^2+4X");
		p.derivative();
		double extreme =p.root(-2, 2, 0.00001);
		System.out.println("extreme Point: "+ extreme);
		
		Polynom p1= new Polynom("3X^3+12X^2");
		p1.derivative();
		double extreme2 =p1.root(-2, 2, 0.00001);
		System.out.println("extreme Point 2: "+ extreme2);
		
		
	}
	public static void test5() {  
		Polynom a=new Polynom("-X^2+3");
		double area1=a.area(-2,0, 0.0001);
		double area2=a.area(0, 2, 0.0001);
		System.out.print("is simetry to x  ");
		System.out.println(area1-area2<0.0001);
		
		Polynom b=new Polynom("-X^4+3X");
		double area11=b.area(-2,0, 0.0001);
		double area21=b.area(0, 2, 0.0001);
		System.out.print("is simetry to x  ");
		if(area11==area21) System.out.println(true);
		else System.out.println(false);
		
		
	}
	
	public static void test6() {   
		Polynom a= new Polynom("8.5X^8+2.22X");
		Polynom c=new Polynom(a.copy().toString());
		System.out.println(a.equals(c));
		
		a.substract(a);
		System.out.println(a);
	}

	
	
	
	
}
