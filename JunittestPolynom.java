package myMath;

import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;


class JunittestPolynom {
	
	@Test

	 void addtest() { 

		Polynom z=new Polynom("3X^2+5X+6");
		Polynom p1=new Polynom("3X^2");
		Polynom p2=new Polynom("5X");		
		Polynom p3=new Polynom("6");

		p1.add(p2);
		p1.add(p3);
		if(!z.equals(p1))
			fail();

	}

	@Test
	 void testsubstract() {
		Polynom a= new Polynom("8.5X^8+2.22X");
		a.substract(a);
		if(!a.isZero())
			fail();

	}
	@Test

	 void multtest() {    
		System.out.println("******  Test6 equals:  ******");
		String[][] polynoms = {{"3X^2","-6X^3","9X","-2"},
				{"X","5X","0","-5"} , {"3.00000001X^2","-5.9999999999X^3","9.000000001X","-2"}};
		Polynom p1 = new Polynom();
		Polynom p2 = new Polynom();
		Polynom p3 = new Polynom();
		for (int i = 0; i < polynoms[0].length; i++) {
			Monom temp = new Monom(polynoms[0][i]);
			p1.add(temp);
		}		
		for (int i = 0; i < polynoms[1].length; i++) {
			Monom temp = new Monom(polynoms[1][i]);
			p2.add(temp);
		}
		
		for (int i = 0; i < polynoms[2].length; i++) {
			Monom temp = new Monom(polynoms[2][i]);
			p3.add(temp);
		}

		if (p1.equals(p2)) {
			
			System.out.println("fail equals");
		}
		
		if ( !p1.equals(p3)) {
			
			System.out.println("fail equals no numeric checks");
		}
		
	}

	@Test

	 void testderivative() {   
		Polynom p= new Polynom("2X^2+4x");
		Polynom p1= new Polynom("4X+4");

		p.derivative();
		if(!p.equals(p1))
			fail();

	}

	@Test

	 void testroot() {   
		Polynom p= new Polynom("2x^2+4X");
		p.derivative();
		double extreme =p.root(-2, 2, 0.00001);
		if(extreme!=-1)
			fail();

		Polynom p1= new Polynom("3X^3+12X^2");
		p1.derivative();
		double extreme2 =p1.root(-2, 2, 0.00001);
		if(extreme2!=0)
			fail();


	}


	@Test
	 void testarea() {  
		Polynom a=new Polynom("-x^2+3");
		double area1=a.area(-2,0, 0.0001);
		double area2=a.area(0, 2, 0.0001);
		if(area1-area2>0.0001)
			fail();

		Polynom b=new Polynom("-X^4+3X");
		double area11=b.area(-2,0, 0.0001);
		double area21=b.area(0, 2, 0.0001);
		if(area11-area21>0.0001)
			fail();


	}
	@Test      

	 void testcopy() {   
		Polynom a= new Polynom("8.5X^8+2.22X");
		Polynom c=new Polynom(a.copy().toString());
		if(a.toString()==c.toString())		
			fail();
		


	}
	@Test      

	 void testinitfromstring() {   
		Polynom a= new Polynom();
		function f=new Polynom();
		f=a.initFromString("2x^2+3x+5");
		System.out.println(f);
		Polynom x=new Polynom("2x^2+3x+5");
		System.out.println(x);
		if(f.equals(x)==false)
			fail();
	
	}
	
	@Test
	void testequalObject() {    

		Polynom a=new Polynom("-18x^6");
		Object b=new Polynom ("-18x^6");

		if(!a.equals(b))
			fail();

	}
	

}
