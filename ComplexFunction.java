package myMath;

import static org.junit.Assert.fail;

import static org.junit.jupiter.api.Assertions.fail;
/**
 * 
 * This class represents a ComplexFunction 
 * this class will create CF that are contains Operation and a function.
 * a function can be: Polynom , Monom or a CF.
 * In this way we will describe many function, and drawing them.
 * 
 * @author shaharivka
 *
 
 */

public class ComplexFunction implements function {


	public void setLeft(function left) {
		this.left = left;
	}
	public function getRight() {
		return right;
	}
	public void setRight(function right) {
		this.right = right;
	}
	public Operation getSign() {
		return sign;
	}
	public void setSign(Operation sign) {
		this.sign = sign;
	}
	private function left;
	private function right;
	private Operation sign;

	public ComplexFunction() { 
		left=right=null;
		sign=Operation.None;
	}
	/**
	 * Builts a CF 
	 * @param left- left function
	 * @param sign- the operation between the two function
	 * @param right-right function
	 */
	public ComplexFunction(function left,Operation sign,function right) { 
		this.left=left;
		this.right=right;
		this.sign=sign;
	}
	/**
	 * Plus between this function and the receiving function
	 * @param f1
	 */
	public void plus(function f1) {
		if(this.sign==Operation.None)
		{
			this.sign=Operation.Plus;
			this.right=f1;
		}
		else {	
			
			
			ComplexFunction c=new ComplexFunction(this.left,this.sign,this.right);
			this.left=c;
			this.sign=Operation.Plus;
			this.right=f1;

			
		}

	}
	/**
	 * Builts a CF 
	 * @param left- left function
	 * @param sign- the operation between the two function
	 * @param right-right function
	 * 
	 */
	public ComplexFunction(Operation sign, function f1, function f2) {
		this.setSign(sign);
		this.setLeft(f1);
		this.setRight(f2);
		
	}
	/**
	 * Builts a CF 
	 * @param f1- function
	 * 
	 */
	public ComplexFunction(function f1) {
		this.setSign(Operation.None);
		this.setLeft(f1);
	}
	/**
	 * Builts a CF
	 * @param left- left function
	 * @param sign- the operation between the two function
	 * @param right-right function
	 */
	public ComplexFunction(String sign,function f1, function f2) {
		if(sign.equals("plus")||sign.equals("Plus")) {
			this.setSign(Operation.Plus);

		}
		else if(sign.equals("mul")||sign.equals("Times")||sign.equals("Mul")) {	
			this.setSign(Operation.Times);
		}
		else if(sign.equals("div")||sign.equals("Divid")||sign.equals("Div")) {
			this.setSign(Operation.Divid);
		}
		else if(sign.equals("max")||sign.equals("Max")) {
			this.setSign(Operation.Max); 
		}
		else if(sign.equals("min")||sign.equals("Min")) {
			this.setSign(Operation.Min); 
		}
		else if(sign.equals("comp")||sign.equals("Comp")) {
			this.setSign(Operation.Comp);
		}
		else if(sign.equals("none")||sign.equals("None")) {
			this.setSign(Operation.None);
		}
		else if(sign.equals("error")||sign.equals("Error")) {
			this.setSign(Operation.Error); 
		}

		else
			throw new RuntimeException("this is not a tipe of a complex function-operation is not correct");
		
		this.setLeft(f1);
		this.setRight(f2);

		
	}
	/**
	 * Mul between this function and the receiving function
	 */

	public void mul(function f1) {
		if(this.sign==Operation.None)
		{
			this.sign=Operation.Times;
			this.right=f1;
		}
		else {
			ComplexFunction c=new ComplexFunction(this.left,this.sign,this.right);
			this.left=c;
			this.sign=Operation.Times;
			this.right=f1;
		}
	}
	/**
	 * Div between this function and the receiving function
	 */

	public void div(function f1) {
		if(this.sign==Operation.None)
		{
			this.sign=Operation.Divid;
			this.right=f1;
		}
		else {
			ComplexFunction c=new ComplexFunction(this.left,this.sign,this.right);
			this.left=c;
			this.sign=Operation.Divid;
			this.right=f1;
		}


	}
	/**
	 * Max between this function and the receiving function
	 */

	public void max(function f1) {
		if(this.sign==Operation.None)
		{
			this.sign=Operation.Max;
			this.right=f1;
		}
		else {
			ComplexFunction c=new ComplexFunction(this.left,this.sign,this.right);
			this.left=c;
			this.sign=Operation.Max;
			this.right=f1;
		}

	}
	/**
	 * Min between this function and the receiving function
	 */

	public void min(function f1) {
		if(this.sign==Operation.None)
		{
			this.sign=Operation.Min;
			this.right=f1;
		}
		else {
			ComplexFunction c=new ComplexFunction(this.left,this.sign,this.right);
			this.left=c;
			this.sign=Operation.Min;
			this.right=f1;
		}

	}
	/**
	 * Comp on this function with the receiving function
	 * @param f1- function
	 */

	public void comp(function f1) {	
		if(this.sign==Operation.None)
		{
			this.sign=Operation.Comp;
			this.right=f1;
		}
		else {
			ComplexFunction c=new ComplexFunction(this.left,this.sign,this.right);
			this.left=c;
			this.sign=Operation.Comp;
			this.right=f1;
		}
	}


	public Operation getOp() {
		return this.sign;

	}
	/**
	 * @return a String that represent our CF
	 */

	public String toString() {
		return this.sign+"("+this.left+","+this.right+")";

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		
		Polynom p=new Polynom();
		function pp=new ComplexFunction();
		pp=p.initFromString("3x");
		System.out.println("pp="+pp);
		String s="Divid(plus(3,5x),5)";
		function f=new ComplexFunction();
		ComplexFunction q= new ComplexFunction();
		System.out.println(q);
		f=q.initFromString(s);
		System.out.println(f);
		
				
String www="plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0)";
ComplexFunction t2w= new ComplexFunction();
function ftw=new ComplexFunction();
ftw=t2w.initFromString(www);					
System.out.println(ftw);

       ComplexFunction t= new  ComplexFunction();
       String b="Plus(null,3x)";
       t.initFromString(b);
       System.out.println(t);


					

				
				



	}
	/**
	 * Calculates the function value at a point
	 *  @param x -the point its Calculates
	 * @return f - double value
	 */
	@Override
	public double f(double x) {
		// TODO Auto-generated method stub
		double left;
		double right;
		if(this.left==null) return  Double.POSITIVE_INFINITY;	 

		left=this.left.f(x) ;
		if(this.right==null) return left;
		else
     	right=this.right.f(x);
		


		if(this.sign==Operation.Plus) {
			return left+right;

		}
		else if(this.sign==Operation.Times) {	
			return left*right;
		}
		else if(this.sign==Operation.Divid) {
			if(right==0)
				throw new RuntimeException("sorrry you can not divid by zero");	
			else	
			return left/right;
		}
		else if(this.sign==Operation.Max) {
			return Math.max(left,right);
		}
		else if(this.sign==Operation.Min) {
			return Math.min(left,right);
		}
		else if(this.sign==Operation.Comp) {
			
			return this.left.f(this.right.f(x));
		}
		else if(this.sign==Operation.None) {
			return left;
		}
		else if(this.sign==Operation.Error) {
			return Double.POSITIVE_INFINITY;
		}










		return 0;
	}
	/**
	 * receives a String and built a type of function from this string
	 *  @param s -String
	 *  @return -function that represent this String
	 */
	@Override
	public function initFromString(String s) {
		// TODO Auto-generated method stub
		//function a= helpinit(s);
		function a=new ComplexFunction();
		return a=helpinit(s);


	}
	/**
	 * helps initfromstring
	 * @param s-string
	 */
	public function helpinit(String s) {


		ComplexFunction answer= new ComplexFunction();
		char move;

		String before="";
		int i=0;
		if(s==""||s==" ")
			throw new RuntimeException("this is not a tipe of a complex function-f is empty");
		else
			move=s.charAt(i);

		int move2=i;
		while(move!='('&&move2!=s.length()-1) {
			before=before+move;
			i++;
			if(move2!=s.length()-1) {
				move=s.charAt(i);
				move2=i;
			}

		}
		
		if(move2==s.length()-1) {
			Polynom x= new Polynom(s);
			return x;

		}
		else {
			if(before.equals("plus")||before.equals("Plus")) {
				answer.setSign(Operation.Plus);

			}
			else if(before.equals("mul")||before.equals("Times")) {	
				answer.setSign(Operation.Times);
			}
			else if(before.equals("div")||before.equals("Div")||before.equals("Divid")) {
				answer.setSign(Operation.Divid);
			}
			else if(before.equals("max")||before.equals("Max")) {
				answer.setSign(Operation.Max); 
			}
			else if(before.equals("min")||before.equals("Min")) {
				answer.setSign(Operation.Min); 
			}
			else if(before.equals("comp")||before.equals("Comp")) {
				answer.setSign(Operation.Comp);
			}
			else if(before.equals("none")||before.equals("None")) {
				answer.setSign(Operation.None);
			}
			else if(before.equals("error")||before.equals("Error")) {
				answer.setSign(Operation.Error); 
			}

			else
				throw new RuntimeException("this is not a tipe of a complex function-operation is not correct");



			if(s.charAt(s.length()-1)!=')')
				throw new RuntimeException("this is not a tipe of a complex function-there is not (,) correct");

			
			String save=s.substring(move2+1, s.length()-1);

			if(save.charAt(save.length()-1)==' '||save.charAt(save.length()-1)==',')
				throw new RuntimeException("this is not a tipe of a complex function-f is empty");

			//System.out.println(save);
			int c=0;
			for(int j=save.length()-1;j>=0;j--) {
				//System.out.println(save.charAt(j));

				if((save.charAt(j)==',' && c==0)) {
					String left=save.substring(0, j);

					answer.setLeft(helpinit(left));
					String right=save.substring(j+1);
					//try {
					answer.setRight(helpinit(right));
					

				}

				if(save.charAt(j)==')') {
					c=c+1;
				}

				if(save.charAt(j)=='(') {
					c=c-1;
				}


				//System.out.println(c);


			}
		}
		return answer;

	}
	/**
	 * Copy this CF and returns it
	 */
	@Override
	public function copy() {
		// TODO Auto-generated method stub
		String b=this.toString();
		function a= initFromString(b);
		return a;
	}
	/**
	 * 
	 * 
	 * checking if this CF and the receiving Object are equals
	 * @param p1- Object
	 * @return - true/false
	 */
	 
	 

	public boolean equals (Object f) {
		
		if (!(f instanceof function))
			return false;
		else {

		double x=10;
		while(x!=100) {

			double fthis=this.f(x);
			double ff=((ComplexFunction)f).f(x);
			if(fthis!=ff)
				return false;
			x=x+1;
		}
		return true;
		}
	}

}
