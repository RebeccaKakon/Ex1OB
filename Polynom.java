package myMath;

import static org.junit.Assert.fail;

import java.nio.channels.Pipe;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.function.Predicate;

import myMath.Monom;
/**
 * This class represents a Polynom with add, multiply functionality, it also should support the following:
 * 1. Riemann's Integral: https://en.wikipedia.org/wiki/Riemann_integral
 * 2. Finding a numerical value between two values (currently support root only f(x)=0).
 * 3. Derivative
 * right input("3X^2+5X+8","3.22X+5","2X^4+8+2")
 *  wrong input("(3X+5)","2x","3X^3.2")
 *   Defined by a collection of monomes in a linked list,
 *    in our function we defined the Polynom by the following features: 
 *    - The polynomial does not work with the '/ ' operation 
 *    -The polynomial does not contain the characters: ")", "("
 * 
 * @author shaharivka
 *
 */
public class Polynom implements Polynom_able{
	//private static final String  = null;
	private  LinkedList<Monom> save = new LinkedList<Monom>();

	/**
	 * Zero (empty polynom)
	 */

	public static void main(String[] args) {
		
//		String p="3X^2-6X^3+9X-2";
//		Polynom pp= new Polynom (p);
//		Double rootp=pp.root(0, 1, 0.0001);
//		System.out.println(rootp);
//		
		
		Polynom a= new Polynom();
		function f=new Polynom();
		f=a.initFromString("2x^2+3x+5");
		System.out.println(f);
		Polynom x=new Polynom("2x^2+3x+5");
		System.out.println(x);
		if(f.equals(x)==false)
			System.out.println("noe equal");
		
		double xx=1.99999999999999999999999;
		System.out.println(xx);
		System.out.println((double)(Math.round(xx/16)));




	}
	/*
	 * A constructor that built a new Polynom- as zero 
	 */


	public Polynom() 
	{
		Monom a=new Monom("0X^1");
		save.add(a);

	}
	/**
	 * init a Polynom from a String such as:
	 *  {"x", "3+1.4X^3-34x", "(2x^2-4)*(-1.2x-7.1)", "(3-3.4x+1)*((3.1x-1.2)-(3X^2-3.1))"};
	 * @param s: is a string represents a Polynom
	 */
	public Polynom(String s) {

		if(s.length()!=0) {
			helppolynom(s);
			gather();
		}
		else 
			throw new RuntimeException ("this is not a type of Polynom");

	}
	/*
	 * Helps us to build Polynom(String) function
	 * @param s: is a string represents a Polynom
	 */

	public  void helppolynom (String s) {

		String start="";
		int place=0;
		boolean flag=false;
		if(s.charAt(0)=='+'||s.charAt(0)=='-') {
			start=start+s.charAt(0);
			place=1;
		}


		for(int i=place;i<s.length()&&flag==false;i++) {
			char c= s.charAt(i);

			if(s.charAt(i)=='+'||s.charAt(i)=='-') {
				flag=true;
			}
			if(flag==false) {
				start=start+c;
				place++;
			}
		}                                   

		if(place==s.length()) {

			Monom r=new Monom(start);
			this.save.add(r);

		}


		else {

			helppolynom (start);
			if(s.charAt(place)=='-')
				helppolynom ((s.substring(place, s.length())));
			else	
				helppolynom ((s.substring(place+1, s.length())));



		}


	}


	@Override
	/**
	 *  Calculates the function value at a point
	 *  @param x -the point its Calculates
	 *  @return the value 
	 *  

	 */
	public double f(double x) {
		// TODO Auto-generated method stub

		double sum=0.0;
		for(int i=0;i<save.size();i++) {
			Monom move=save.get(i);
			sum=sum+move.f(x);

		}
		return sum;
	}
	/**
	 * Adding a Polynom to this Polynom
	 */
	

	@Override
	public void add(Polynom_able p1) {
		// TODO Auto-generated method stub

		Iterator i=p1.iteretor();
		while(i.hasNext()) {
			Monom c=((Monom)i.next());
			this.add(c);
		}
		sort();         

	}
	/**
	 * @param p1- Monom
	 * Adding a Monom to this Polynom
	 * 
	 */

	@Override
	public void add(Monom m1) 
	{
		this.sort();
		// TODO Auto-generated method stub
		boolean in=false;


		for(int i=0;i<save.size()&&in==false;i++) {
			Monom move=save.get(i);
			if(m1.get_power()==move.get_power()) {

				Monom hadash=new Monom(m1.get_coefficient()+move.get_coefficient(),m1.get_power());
				save.set(i,hadash );
				in=true;

			}
		}

		if(in==false) {
			save.add(m1);

		}

		this.sort();


	}
	/*
	 * @param p1- Polynom
	 * Substract a Polynom from this Polynom
	 * 
	 */



	@Override
	public void substract(Polynom_able p1) {
		// TODO Auto-generated method stub
		Monom m=new Monom("-1");
		Iterator i=p1.iteretor();
		while(i.hasNext()) {
			Monom c=new Monom((Monom)i.next());   
			c.multipy(m);

			this.add(c);

		}
		sort();        



	}
	/**
	 * @param p1- Polynom
	 * Multiply a Polynom with this Polynom
	 */

	@Override
	public void multiply(Polynom_able p1) {
		// TODO Auto-generated method stub
		sort();


		Polynom copyp1=this.copyy(this);
		Iterator<Monom> j=p1.iteretor();
		String answer="";
		while(j.hasNext()) {
			Polynom temp=new Polynom(copyp1.toString());
			temp.multiply((Monom)j.next());
			if(temp.toString().charAt(0)=='-')
				answer=answer+temp.toString();
			else
				answer=answer+"+"+temp.toString();


		}

		Polynom polyanswer=new Polynom(answer);

		this.add(polyanswer);
		this.substract(copyp1);
		//System.out.println(this);
		sort();     //11/12
		//System.out.println(this);

	}
	/**
	 * Copy the receiving Polynom and returns it
	 * @param p- Polynom
	 * @return - Polynom
	 */

	public Polynom copyy (Polynom p) {
		Polynom a=new Polynom(p.toString());
		return a;
	}
	/**
	 * 
	 * checking if this Polynom and the receiving Polynom are equals
	 * @param p1- Polynom
	 * @return - true/false
	 */
	 
	

	@Override
	public boolean equals(Polynom_able p1) 
	{      	
		
		sort();
		Iterator<Monom> placep1=p1.iteretor();
		int place=0;

		while(placep1.hasNext()&& place<save.size()) 
		{
			Monom c=((Monom)placep1.next());
			if(!save.get(place).equals(c))
				return false;
			place++;	
		}

		//System.out.println(place);
		if(placep1.hasNext())
			//System.out.println("has next");

			if(place==save.size())
				if(placep1.hasNext())
					return false;
				else 
					return true;
		return true;


	}
	/**
	 * checking if this Polynom and the receiving Polynom are equals
	 * @param p1- Polynom
	 * @return - true/false
	 */
	@Override
	public boolean equals(Object p1) {
		if(p1 instanceof Polynom_able)
			return this.equals((Polynom)p1);
		else
			throw new RuntimeException ("not a type of Polynom");


	}
	/**
	 * Checks if this Polynom is the zere Polynom
	 *  @return - true/false
	 */

	@Override
	public boolean isZero() {
		// TODO Auto-generated method stub
		for(int i=0;i<save.size();i++) {
			if(!save.get(i).isZero())
				return false;

		}
		return true;
	}
	/*
	 * Returns the x value where f(x)=0 , only if f(x1)*f(x2)<0 - different sign
	 * @param x0- start check
	 * @param x1- end check
	 * @param eps-minimal distance between x1 and x2
	 *  @return- a value of X 
	 */

	@Override
	public double root(double x0, double x1, double eps) {
		// TODO Auto-generated method stub
		if (f(x0)*f(x1)>0) throw new RuntimeException ("this is not a right input, please enter different signs for x0 and x1");
		if(x0>x1) throw new RuntimeException ("x0 supose to be smaller then x1, please enter again");
		return ((roothelp( x0, x1,eps))) ;

	}
	/**
	 * helps with root function
	 * @param x0
	 * @param x1
	 * @param eps
	 * @return double value
	 */
	public double roothelp(double x0, double x1,double eps) {

		if (f(x0)==0) return x0;
		if(f(x1)==0)return x1;
		

		if((Math.abs(x1-x0)<eps)&&(Math.abs(f(x1))-eps<0||Math.abs(f(x0))-eps<0))
			return x1;


		double middle;

		if(x1>0&&(x0<0))
			middle=x1-(Math.abs(x0)+Math.abs(x1))/2;
		else
			middle=x0+(x1-x0)/2;
		if(f(middle)*f(x1)>0)	
			return roothelp( x0, middle, eps) ;
		else
			return roothelp( middle, x1, eps) ;


	}
	
	/**
	 * Copy this Polynom and returns it
	 *
	 */ 
	 

	@Override
	public Polynom_able copy() {     
		// TODO Auto-generated method stub
		String a=this.toString();
		Polynom b=new Polynom(a);
		this.sort();
		return b;

		

	}
	
	 
	 /**
		 * this method returns the derivative Polynom of this
		 */
	

	@Override
	public Polynom_able derivative() {
		// TODO Auto-generated method stub

		Iterator<Monom> i=this.iteretor();
		while(i.hasNext()) {
			((Monom)i.next()).derivative();
		}
		return this;
	}
	/*
	 * calculation the area of this Polynom
	 * @param x0-start point
	 * @param x1-end point
	 * @param eps-how to divide the blocks 
	 * #area(double, double, double)
	 */

	@Override
	public double area(double x0, double x1, double eps) {
		// TODO Auto-generated method stub
		if(x0>x1) return 0;

		double s=x0;
		double e=x0+ eps;
		double areaa=0;
		while(e<=x1) {
			if(this.f(s)>0&&this.f(e)>0) {
				areaa=areaa+(eps*this.f(s));	
			}
			s=e;
			e=e+eps;

		}
		return areaa;
	}
	/*
	 * A way to go over our Polynom
	 * 
	 */

	@Override
	public Iterator<Monom> iteretor() {
		// TODO Auto-generated method stub
		return save.iterator();
	}
	/**
	 * Multiplication of Polynomials
	 * @param m1- Monom
	 */
	@Override
	public void multiply(Monom m1) {
		// TODO Auto-generated method stub


		for(int i=0;i<this.save.size();i++) {
			save.get(i).multipy(m1);


		}



	}
	/**
	 * 
	 * Sort our Polynom by the power of the Monoms 
	 *
	 */
	public class Sortbypower implements Comparator<Monom> {  
		public int compare(Monom s1,Monom s2){  
			return(s2.get_power()-s1.get_power());
		}
	}

	public void sort() {

		Collections.sort(save, new Sortbypower());
	}
	
	/*
	 * @return a String that represent our Polynom
	 * 
	 */


	public String toString() {

		if (save==null ) return "";

		sort();
		gather();

		String answer="";
		for (int i = 0; i < save.size(); i++) {
			String a=this.save.get(i).toString();

			Monom c=new Monom(a);
			if(c.get_coefficient()!=0.0)
			{
				if(a.charAt(0)!='+'&&a.charAt(0)!='-')
					answer=answer+"+"+a;
				else
					answer=answer+a;
			}	
			else
				answer=answer+" ";

		}
		gather();

		if(answer.charAt(0)=='+')
			answer=answer.substring(1);

		if(answer.charAt(0)==' ')
			answer="0";

		return answer;

	}
	/**
	 * Conectinting Monoms that have the same power, to avoide the same power in one Polynom
	 */

	public void gather () {
		sort();
		Monom m=new Monom("0");
		for (int i = 0; i < save.size()-1; i++) {
			if(this.save.get(i).get_power()==this.save.get(i+1).get_power()) {
				this.save.get(i).add(this.save.get(i+1));
				this.save.get(i+1).multipy(m);		
			}

		}
	}


	public static Polynom_able parse(String s1) {   
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * receives a String and built a type of function from this string
	 *  @param s -String
	 *  @return -function that represent this String
	 * 
	 */


	@Override
	public function initFromString(String s) {
		// TODO Auto-generated method stub
		function answer=new Polynom(s);
		return answer;



	}

}

