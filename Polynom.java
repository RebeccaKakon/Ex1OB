package myMath;

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
 * 
 * @author Boaz
 *
 */
public class Polynom implements Polynom_able{
	//private static final String  = null;
	private  LinkedList<Monom> save = new LinkedList<Monom>();

	/**
	 * Zero (empty polynom)
	 */

	public static void main(String[] args) {

		Polynom a= new Polynom();
		Monom m1=new Monom("3X");
		a.add(m1);
		//System.out.println(a);
		Polynom m=new Polynom("2X");
		m.derivative();
		//System.out.println(m);
		m.derivative();
		m.derivative();
		//System.out.println(m);

		Polynom w= new Polynom ("X");		
		Polynom n= new Polynom ("3X^2+5");	
		System.out.println(w.equals(n));
		System.out.println(w.root(0,1,0.001));
		//Polynom b=new Polynom("2X+3X^3+6");
		
		String x="Times(3X,2)";
		ComplexFunction q= new ComplexFunction();
		function f=new ComplexFunction();
		f=q.initFromString(x);
		
		f.toString();
		



	}


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
	public double f(double x) {
		// TODO Auto-generated method stub

		double sum=0.0;
		for(int i=0;i<save.size();i++) {
			Monom move=save.get(i);
			sum=sum+move.f(x);

		}
		return sum;
	}

	@Override
	public void add(Polynom_able p1) {
		// TODO Auto-generated method stub

		Iterator i=p1.iteretor();
		while(i.hasNext()) {
			Monom c=((Monom)i.next());
			this.add(c);
		}

	}

	@Override
	public void add(Monom m1) {
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




	}



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



	}

	@Override
	public void multiply(Polynom_able p1) {
		// TODO Auto-generated method stub

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


	}

	public Polynom copyy (Polynom p) {
		Polynom a=new Polynom(p.toString());
		return a;
	}

	@Override
	public boolean equals(Polynom_able p1) {       
		// TODO Auto-generated method stub

		Iterator placep1=p1.iteretor();
		int place=0;
		while(placep1.hasNext()&& place<save.size()) {
			Monom c=((Monom)placep1.next());

			if(!save.get(place).equals(c))
				return false;
			place++;	
		}
		if(place<save.size()&&!placep1.hasNext()) return false;
		else 
			if(place>save.size()&&placep1.hasNext()) return false;

		return true;
	}

	@Override
	public boolean isZero() {
		// TODO Auto-generated method stub
		for(int i=0;i<save.size();i++) {
			if(!save.get(i).isZero())
				return false;

		}
		return true;
	}

	@Override
	public double root(double x0, double x1, double eps) {
		// TODO Auto-generated method stub
		if (f(x0)*f(x1)>0) throw new RuntimeException ("this is not a right input, please enter different signs for x0 and x1");
		if(x0>x1) throw new RuntimeException ("x0 supose to be smaller then x1, please enter again");
		return Math.round((roothelp( x0, x1,eps))) ;

	}
	public double roothelp(double x0, double x1,double eps) {

		if (f(x0)==0) return x0;
		if(f(x1)==0)return x1;
		if(Math.abs(x1-x0)<eps&&(f(x1)+eps<0||(f(x0)+eps<0)))
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





	@Override
	public Polynom_able copy() {     
		// TODO Auto-generated method stub
		Polynom a=new Polynom(this.toString());
		return a;

	}

	@Override
	public Polynom_able derivative() {
		// TODO Auto-generated method stub

		Iterator<Monom> i=this.iteretor();
		while(i.hasNext()) {
			((Monom)i.next()).derivative();
		}
		return this;
	}

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

	@Override
	public Iterator<Monom> iteretor() {
		// TODO Auto-generated method stub
		return save.iterator();
	}
	@Override
	public void multiply(Monom m1) {
		// TODO Auto-generated method stub


		for(int i=0;i<this.save.size();i++) {
			save.get(i).multipy(m1);


		}



	}
	public class Sortbypower implements Comparator<Monom> {  
		public int compare(Monom s1,Monom s2){  
			return(s2.get_power()-s1.get_power());
		}
	}

	public void sort() {

		Collections.sort(save, new Sortbypower());
	}


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


	@Override
	public function initFromString(String s) {
		// TODO Auto-generated method stub
		//function a= helpinit(s);
		function a=new ComplexFunction();
		return a=helpinit(s);


	}
	public function helpinit(String s) {

		ComplexFunction answer= new ComplexFunction();

		String before="";
		int i=0;
		char move=s.charAt(i);
		int move2=i;
		while(move!='('&&move!=s.length()-1) {
			before=before+move;
			i++;
			move=s.charAt(i);
			move2=i;
		}
		System.out.println(move);
		System.out.println(before);
		if(move2==s.length()-1) {
			Polynom x= new Polynom(s);
			return x;

		}
		else {
			if(before.equals("Plus")) {
				answer.setSign(Operation.Plus);

			}
			else if(before.equals("Times")) {	
				answer.setSign(Operation.Times);
			}
			else if(before.equals("Divid")) {
				answer.setSign(Operation.Divid);
			}
			else if(before.equals("Max")) {
				answer.setSign(Operation.Max); 
			}
			else if(before.equals("Min")) {
				answer.setSign(Operation.Min); 
			}
			else if(before.equals("Comp")) {
				answer.setSign(Operation.Comp);
			}
			else if(before.equals("None")) {
				answer.setSign(Operation.None);
			}
			else if(before.equals("Error")) {
				answer.setSign(Operation.Error); 
			}

			else
			throw new RuntimeException("this is not a tipe of a complex function-operation is not correct");


		}
		if(s.charAt(s.length()-1)!=')')
			throw new RuntimeException("this is not a tipe of a complex function-there is not (,) correct");
		
		System.out.println(move2+1);
		System.out.println(s.length()-1);

		s.substring(move2+1, s.length()-1);
		int counter=0;
		for(int j=s.length()-1;j>=0;j--) {
			if((s.charAt(j)==',')&&(counter==0)) {
				System.out.println(s.substring(0,j-1));
				System.out.println(s.substring(j+1,s.length()-1));
				answer.setLeft(helpinit(s.substring(0,j-1)));
				answer.setRight(helpinit(s.substring(j+1,s.length()-1)));
			}

			if(s.charAt(j)==')')
				counter++;
			if(s.charAt(j)=='(')
				counter--;
		}
	
	return answer;

}
}

