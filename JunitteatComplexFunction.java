package myMath;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class JunitteatComplexFunction {

	@Test
	void testinitfromstring() {

		Polynom a1=new Polynom("3X^2");
		Polynom b1=new Polynom("X");
		Polynom c1=new Polynom("2X");

		String p1="Times(Divid(3X^2,X),Times(X,2X))";
		ComplexFunction q21= new ComplexFunction();
		function f21=new ComplexFunction();
		f21=q21.initFromString(p1);

		ComplexFunction q3= new ComplexFunction(a1,Operation.Divid,b1);
		ComplexFunction q41= new ComplexFunction(b1,Operation.Times,c1);

		q3.mul(q41);

		if(!q3.equals(f21))
			fail();
	}



	@Test
	void testmuldiv() {

		Polynom a1=new Polynom("3X^2");
		Polynom b1=new Polynom("X");
		ComplexFunction q3= new ComplexFunction(a1,Operation.Times,b1);
		if(q3.f(2)!=24)
			fail();

		ComplexFunction q4= q3;

		q3.mul(b1);
		q3.div(b1);
		if(!q4.equals(q3))
			fail();



	}
	@Test
	void testmax() {
		Polynom a1=new Polynom("3X^2");
		Polynom b1=new Polynom("X");
		Polynom a3=new Polynom("4X^3+5");
		ComplexFunction q= new ComplexFunction(a1,Operation.Max,b1);
		double c=q.f(2);
		if(c!=12)
			fail();

		q.max(a3);
		c=q.f(2);
		if(c!=37)
			fail();
	}
	@Test
	void testmin() {
		Polynom a1=new Polynom("3X^2");
		Polynom b1=new Polynom("X");
		Polynom a3=new Polynom("4X^3+5");
		ComplexFunction q= new ComplexFunction(a1,Operation.Min,b1);
		double c=q.f(2);
		if(c!=2)
			fail();

		q.min(a3);
		c=q.f(2);
		if(c!=2)
			fail();
	}
	@Test
	void testcomp() {
		
		Polynom a=new Polynom ("2X^2");
		Polynom b=new Polynom("-4X^3");
		Polynom c=new Polynom ("8X^2");
		Polynom d=new Polynom("3X");
		
		ComplexFunction A= new ComplexFunction(a,Operation.Plus,b);
		ComplexFunction B= new ComplexFunction(c,Operation.Plus,d);
		
		B.comp(A);
		if(B.f(0.5)!=0.0)
			fail();
		
	}
	@Test
	void testcomp() {
		Polynom a=new Polynom ("2X^2");
		Polynom b=new Polynom("-4X^3");
		
		
		ComplexFunction A= new ComplexFunction(a,Operation.Plus,b);
		ComplexFunction B= new ComplexFunction(b,Operation.Plus,a);
		if(A.f(3)
		




	}
}


