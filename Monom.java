
package myMath;

import java.util.Comparator;

/**
 * This class represents a simple "Monom" of shape a*x^b, where a is a real number and a is an integer (summed a none negative), 
 * see: https://en.wikipedia.org/wiki/Monomial 
 * The class implements function and support simple operations as: construction, value at x, derivative, add and multiply. 
 * @author Boaz
 *
 */
public class Monom implements function{
	public static final Monom ZERO = new Monom(0,0);
	public static final Monom MINUS1 = new Monom(-1,0);
	public static final double EPSILON = 0.0000001;
	public static final Comparator<Monom> _Comp = new Monom_Comperator();
	public static Comparator<Monom> getComp() {return _Comp;}
	public Monom(double a, int b){
		this.set_coefficient(a);
		this.set_power(b);
	}
	public Monom(Monom ot) {
		this(ot.get_coefficient(), ot.get_power());
	}

	public double get_coefficient() {
		return this._coefficient;
	}
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
	public double f(double x) {
		double ans=0;
		int p = this.get_power();
		ans = this.get_coefficient()*Math.pow(x, p);
		return ans;
	} 
	public boolean isZero() {
		return this.get_coefficient() == 0;
	}
	// ***************** add your code below **********************

	public Monom(String s) {		

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
		if(countletters==s.length()) {         //only number

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
	public void add(Monom m) {
		if(this.get_power()!=m.get_power())
			throw new RuntimeException ("this is not tipe of Monom");
		else
			this.set_coefficient(this.get_coefficient()+m.get_coefficient());

	}



	public void multipy(Monom d) {
		this.set_coefficient(this.get_coefficient()*d.get_coefficient());
		this.set_power(this.get_power()+d.get_power());

	}

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


	private void set_coefficient(double a){
		this._coefficient = a;
	}
	private void set_power(int p) {
		if(p<0) 
		{
			throw new RuntimeException("ERR the power of Monom should not be negative, got: "+p);}
		this._power = p;
	}

	public boolean equals(Monom a) { 

		if(a.get_coefficient()==0.0&&this._coefficient==0) return true;
		if(Math.abs(this._coefficient-a.get_coefficient())<=EPSILON)
			if(this._power==a.get_power()) 
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
	public function initFromString(String s) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public function copy() {
		// TODO Auto-generated method stub
		return null;
	}


}
