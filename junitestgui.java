package myMath;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class junitestgui {

	@Test
	void testreadandwrite() {
		
		Functions_GUI a=new Functions_GUI();
		Range x=new Range(-10,10);
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
	
		String out="testdrawing.txt";
		try {
		a.saveToFile(out);
		Functions_GUI b=new Functions_GUI();
		b.initFromFile("C:/Users/dalia/eclipse-workspace/secondyear/testdrawing.txt");
		}
		catch(Exception e) {
			fail();
		}
		

	}

}
