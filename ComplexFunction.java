package myMath;

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

			this.left=this;
			this.sign=Operation.Plus;
			this.right=f1;
		}

	}

	public void mul(function f1) {
		if(this.sign==Operation.None)
		{
			this.sign=Operation.Times;
			this.right=f1;
		}
		else {
			this.left=this;
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
			this.left=this;
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
			this.left=this;
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
			this.left=this;
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
			this.left=this;
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

		System.out.println(f.toString());
		
		String p2="Times(Divid(3X,4),Comp(2,2X))";
		ComplexFunction q2= new ComplexFunction();
		function f2=new ComplexFunction();
		f2=q2.initFromString(p2);
		
		System.out.println(f2.toString());


	}
	@Override
	public double f(double x) {
		// TODO Auto-generated method stub
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

		String before="";
		int i=0;
		char move=s.charAt(i);
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

		//System.out.println(save);
		int c=0;
		for(int j=save.length()-1;j>=0;j--) {
			//System.out.println(save.charAt(j));

			if((save.charAt(j)==',' && c==0)) {
				String left=save.substring(0, j);
				answer.setLeft(helpinit(left));
				String right=save.substring(j+1);
				
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
	@Override
	public function copy() {
		// TODO Auto-generated method stub
		return null;
	}

}
