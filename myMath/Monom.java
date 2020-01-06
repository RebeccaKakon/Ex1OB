
package myMath;

import java.util.Comparator;

/**
 * This class represents a simple "Monom" of shape a*x^b, where a is a real number and b is an integer (summed a none negative), 
 * see: https://en.wikipedia.org/wiki/Monomial 
 * The class implements function and support simple operations as: construction, value at x, derivative, add and multiply. 
 * @author shaharivka
 *
 */
public class Monom implements function{
	public static final Monom ZERO = new Monom(0,0);
	public static final Monom MINUS1 = new Monom(-1,0);
	public static final double EPSILON = 0.0000001;
	public static final Comparator<Monom> _Comp = new Monom_Comperator();
	public static Comparator<Monom> getComp() {return _Comp;}
	/**
	 * This function represents a simple constructor of Monom 
	 * @param a
	 * @param b
	 * 
	 */
	public Monom(double a, int b){
		this.set_coefficient(a);
		this.set_power(b);
	}
	/**
	 * This function represents a copy constructor of Monom 
	 * @param ot
	 */
	public Monom(Monom ot) {
		this(ot.get_coefficient(), ot.get_power());
	}
	
	/**
	 * This function returns the coefficient of this Monom 
	 * @return
	 */

	public double get_coefficient() {
		return this._coefficient;
	}
	/**
	 * This function returns the Power of this Monom 
	 * @return Power of this Monom 
	 */

	public int get_power() {
		return this._power;
	}
	/** 
	 * this method returns the derivative monom of this.
	 * @return
	 */
	public Monom derivative() {
		if(this.get_power()==0) {
			this.set_coefficient(0);
			this.set_power(0);
		}
		else {
			this.set_coefficient(this.get_coefficient()*this.get_power());
			this.set_power(this.get_power()-1);
		}
		return this;
	}
	
	/**
	 * This function Calculates the function value at a point
	 *  @param x point
	 *  @return function value at a point
	 */
	public double f(double x) {
		double ans=0;
		int p = this.get_power();
		ans = this.get_coefficient()*Math.pow(x, p);
		return ans;
	} 
	/**
	 * 
	 * @return whether this Monom is zero
	 */
	public boolean isZero() {
		return this.get_coefficient() == 0;
	}
	
	// ***************** add your code below **********************
	/**
	 * The function obtains a string that represents a monom,
	 * our function checks whether the string actually is a monom format and, if so, builds a monom by properties 
	 * that represent monom.
	 *  In our function, we have defined monom by the following features:
	 *   -The coefficient of X can only be a decimal number (can also be without a coefficient) 
	 *   - It possession should be an integer 
	 *   -The power represent :^
 
	 * @param s -String that suppose to represent a Monom
	 */

	public Monom(String s) {		
		String h=s.replace("x", "X");      
		String h2=h.replace(" ","");
		s=h2;                                

		String a="";
		if(s.charAt(0)=='X')
			s="1"+s;
		int first= (int)s.charAt(0);
		if (first==46) throw new RuntimeException ("this is not tipe of Monom");
		int countdot=0;
		int countletters=0;

		for (int i=0;i<s.length()&&s.charAt(i)!='X';i++) {     

			int ot=(int)s.charAt(i);
			if ((ot<48)|| (ot>57)) {    
				if (s.charAt(i)=='.')
					countdot++;
				else {
					if (s.charAt(0)!='+' && s.charAt(0)!= '-') 
						throw new RuntimeException ("this is not tipe of Monom");		
					else
						if((s.charAt(0)=='+' || s.charAt(0)== '-')&&(s.charAt(1)=='X'))
							s=s.charAt(0)+"1"+s.substring(1, s.length());

				}

			}
			countletters++;
			a=a+s.charAt(i);

		}                              


		if (countdot>1) 

			throw new RuntimeException (" this is not tipe of Monom");      



		boolean done=false;
		if(countletters==s.length()) {         

			this.set_power(0);
			double coe=Double.valueOf(a);
			this.set_coefficient(coe);


		}



		else {

			if(countletters+1==s.length()) {
				this.set_power(1);
				this.set_coefficient(Double.valueOf(a));
				done=true;
			}
			if(!done) {

				if(s.charAt(countletters+1)!='^') throw new RuntimeException ("this is not tipe of Monom");  
				else {
					int power=Integer.valueOf(s.substring(countletters+2, s.length()));
					this.set_power(power);
					this.set_coefficient(Double.valueOf(a));
				}
			}



		}
	}
	/*
	 * The function receives a Monom and if its power equal to our holding activated monum (this) 
	 * we can connect between the Monomes.
	 * @param m - Monom

	 */
	public void add(Monom m) {
		if(this.get_power()!=m.get_power())
			throw new RuntimeException ("this is not tipe of Monom");
		else
			this.set_coefficient(this.get_coefficient()+m.get_coefficient());

	}
	
	/**
	 * Gets a Monom and multiplies the Monom coefficient and holds it in the activated Monom (this)
	 * 

	 * @param d -Monom 
	 */



	public void multipy(Monom d) {
		this.set_coefficient(this.get_coefficient()*d.get_coefficient());
		this.set_power(this.get_power()+d.get_power());


	}
	/*
	 * retuns this Monom as a String
	 * @return -string
	 */

	public String toString() {
		String ans ="";

		if(this.get_power()==0) {
			ans=ans+this.get_coefficient();
		}
		else	if(this.get_power()==1) {
			ans=ans+this.get_coefficient()+"X";
		}
		else if(this.get_coefficient()==0.0) {
			ans=ans+0.0;
		}
		else {
			ans=ans+this.get_coefficient()+"X^"+this.get_power();

		}

		return ans;
	}
	// you may (always) add other methods.

	//****************** Private Methods and Data *****************
/**
 * set the cofficient of this Monom
 * @param a
 */

	private void set_coefficient(double a){
		this._coefficient = a;
	}
	/**
	 * set the power of this Monom
	 * @param p
	 */
	private void set_power(int p) {
		if(p<0) 
		{
			throw new RuntimeException("ERR the power of Monom should not be negative, got: "+p);}
		this._power = p;
	}
	/**
	 * This function  receives a Monom and compares it with this monom

	 * @param a - an Object
	 */

	public boolean equals(Object a) { 

		if(a instanceof Monom==false)
			throw new RuntimeException("not a type of Monom");
	
		Monom b=(Monom)(a);
				if(b.get_coefficient()==0.0&&this._coefficient==0) return true;
				if(Math.abs(this._coefficient-b.get_coefficient())<=EPSILON)
					if(this._power==b.get_power()) 
						return true;	
		
				return false;



	}

	private double _coefficient() {
		// TODO Auto-generated method stub
		return 0;
	}
	private static Monom getNewZeroMonom() {
		return new Monom(ZERO);
	}
	private double _coefficient; 
	private int _power;
	@Override
	/**
	 *  receives a String and built a type of function from this string
	 *  @param s -String
	 */
	public function initFromString(String s) {
		// TODO Auto-generated method stub
		function answer=new Monom(s);
		//System.out.println(answer);
		return answer;
	}
	@Override
	/**
	 * Copy (this) Monom and returns it as a function type 
	 */
	public function copy() {
		// TODO Auto-generated method stub 
		function b=new Monom(this.get_coefficient(),this.get_power());
		return b;
	}


}
