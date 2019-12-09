package myMath;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.fail;

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
	public ComplexFunction(function left,Operation sign,function right) { 
		this.left=left;
		this.right=right;
		this.sign=sign;
	}
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
	public ComplexFunction(Operation sign, function f1, function f2) {
		this.setSign(sign);
		this.setLeft(f1);
		this.setRight(f2);
		
	}
	public ComplexFunction(function f1) {
		this.setSign(Operation.None);
		this.setLeft(f1);
	}
	public ComplexFunction(String sign,function f1, function f2) {
		if(sign.equals("plus")) {
			this.setSign(Operation.Plus);

		}
		else if(sign.equals("mul")) {	
			this.setSign(Operation.Times);
		}
		else if(sign.equals("div")) {
			this.setSign(Operation.Divid);
		}
		else if(sign.equals("max")) {
			this.setSign(Operation.Max); 
		}
		else if(sign.equals("min")) {
			this.setSign(Operation.Min); 
		}
		else if(sign.equals("comp")) {
			this.setSign(Operation.Comp);
		}
		else if(sign.equals("none")) {
			this.setSign(Operation.None);
		}
		else if(sign.equals("error")) {
			this.setSign(Operation.Error); 
		}

		else
			throw new RuntimeException("this is not a tipe of a complex function-operation is not correct");
		
		this.setLeft(f1);
		this.setRight(f2);

		
	}

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

	public String toString() {
		return this.sign+"("+this.left+","+this.right+")";

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Polynom x=new Polynom("3X+5");
		Polynom x2=new Polynom("3X+5");
		String a="mul(sub(X+2,4X+5),div(4X,X)";
		function b=null;
		//b.initFromString(a);
		ComplexFunction c= new ComplexFunction();
		ComplexFunction c2= new ComplexFunction();
		c2.sign=Operation.Divid;
		c2.left=x;
		c2.right=x2;

		//System.out.println(c.toString());

		ComplexFunction c3= new ComplexFunction();
		ComplexFunction c4= new ComplexFunction();
		c3.sign=Operation.Comp;
		c3.left=x;
		c3.right=x2;
		c4.sign=Operation.Divid;
		c4.left=c2;
		c4.right=x;
		//System.out.println(c4.toString());

		//System.out.println("***");

		String p="Divid(Divid(3X,4),2)";
		//System.out.println(p);
		ComplexFunction q= new ComplexFunction();
		function f=new ComplexFunction();
		f=q.initFromString(p);     //init does not returns a good string


		//System.out.println(f.toString());

		String p2="Times(Divid(3X,2),Plus(X,2))";
		ComplexFunction q2= new ComplexFunction();
		function f2=new ComplexFunction();
		f2=q2.initFromString(p2);
		//		System.out.println(f2.toString());
		String p5="Times(Divid(3X,2),2)";
		ComplexFunction q5= new ComplexFunction();
		function f4=new ComplexFunction();
		f4=q5.initFromString(p5);
		
		//System.out.println("f4(1)="+f4.f(1));

		function f3=new ComplexFunction();

		//		System.out.println(f3);
		//		System.out.println(f2.equals(f3));
		//		System.out.println(f3);
		//		

		ComplexFunction x3=new ComplexFunction(x,Operation.Divid,x);
		ComplexFunction q4= new ComplexFunction(x,Operation.Plus,x3);
		//System.out.println(q4);
		//System.out.println(q3);
		q4.mul(x);
		//System.out.println(q4);
		

		f3=q4.copy();
		//System.out.println(f3);
		//System.out.println(f3.f(2));
		//System.out.println(q4.f(2));

		//System.out.println(q4);
//		System.out.println(q4.equals(f3));



		//		
//				System.out.println("f2(1)-   "+f2.f(1));
//				System.out.println("f3(1)-   "+f3.f(1));
		//		
				
		
				
				

				Polynom aa=new Polynom ("2X^2");
				Polynom bb=new Polynom("-4X^3");
				Polynom cc=new Polynom ("8X^2");
				Polynom dd=new Polynom("3X");
				
				ComplexFunction A= new ComplexFunction(aa,Operation.Plus,bb);
				ComplexFunction B= new ComplexFunction(cc,Operation.Plus,dd);
				
				B.comp(A);
//				System.out.println(B);
//				System.out.println(B.f(0.5));
				
				
				
				String t="Times(Divid(Times(8,8),4x^2),Divid(10,5))";
				//System.out.println(p);
				ComplexFunction t2= new ComplexFunction();
				function ft=new ComplexFunction();
				ft=t2.initFromString(t);
				System.out.println(ft);
				System.out.println(ft.f(1));
				//System.out.println(ft.f(3));
				
				String hh="x^2";
				String newnew =hh.replace("x","X");
				//System.out.println(newnew);
				hh=newnew;
				//System.out.println(hh);
				
				
				String qq = "Times(Divid(Times(8,8),4x^2),Divid(10,5))";
				
					
				ComplexFunction p7=new ComplexFunction();
				System.out.println(p7);
				System.out.println(p7.f(1));
					
					

				
				
				
				
				
				
				
				
				



	}
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
	@Override
	public function initFromString(String s) {
		// TODO Auto-generated method stub
		//function a= helpinit(s);
		function a=new ComplexFunction();
		return a=helpinit(s);


	}
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
		//System.out.println(move);
		//System.out.println(before);
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



			if(s.charAt(s.length()-1)!=')')
				throw new RuntimeException("this is not a tipe of a complex function-there is not (,) correct");

			//System.out.println(move2+1);
			//System.out.println(s.length()-1);
			//System.out.println(move2+1);
			//System.out.println(s.length());
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
					//}
					//					catch(Exception e) {
					//						if(this.sign==Operation.None) {
					//							answer.setRight(null);
					//						}
					//							
					//					}


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
	@Override
	public function copy() {
		// TODO Auto-generated method stub
		String b=this.toString();
		function a= initFromString(b);
		return a;
	}

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
