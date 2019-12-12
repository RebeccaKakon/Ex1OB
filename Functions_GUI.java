package myMath;


import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

import com.google.gson.Gson;

import java.awt.Color;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.ArrayList;
/**
 * This class represent a collection of function
 * @throws IOException 
 * 
 */

public class Functions_GUI implements functions  {

	LinkedList <function> save= new LinkedList<function>();

	

	public static void main(String[] args) throws IOException {

		// TODO Auto-generated method stub

		Functions_GUI a=new Functions_GUI();
		Range x=new Range(-10,10);
		//a.drawFunctions(600, 1000, x, x, 200);
		Polynom s= new Polynom ("x^2");
		Polynom h= new Polynom ("X^2+2");
		Polynom r= new Polynom ("X^2+3");
		Polynom v= new Polynom ("X^2+4");
		Polynom k= new Polynom ("2x^3+2x^2");
		Polynom p= new Polynom ("x^4+2x+5");
//		a.add(s);
//		a.add(h);
//		a.add(r);
//		a.add(v);
//		a.add(k);
//		a.add(p);
		//a.drawFunctions(600, 600, x, x, 100);
		//String pp="shahar.txt";
		//a.drawFunctions(pp);
		String out="outest.json";
//		aa.saveToFile(out);
	//	a.saveToFile("yougotit.txt");
		a.initFromFile("yougotit.txt");
		a.drawFunctions(600, 600, x, x, 200);

//		
		

	}

	@Override
	public boolean add(function arg0) {
		// TODO Auto-generated method stub
		return save.add(arg0);
	}

	@Override
	public boolean addAll(Collection<? extends function> arg0) {
		// TODO Auto-generated method stub
		return save.addAll(arg0);
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		save.clear();
	}

	@Override
	public boolean contains(Object arg0) {
		// TODO Auto-generated method stub
		return save.contains(arg0);
	}

	@Override
	public boolean containsAll(Collection<?> arg0) {
		// TODO Auto-generated method stub
		return save.containsAll(arg0);
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return save.isEmpty();
	}

	@Override
	public Iterator<function> iterator() {
		// TODO Auto-generated method stub
		return save.iterator();
	}

	@Override
	public boolean remove(Object arg0) {
		// TODO Auto-generated method stub
		return save.remove(arg0);
	}

	@Override
	public boolean removeAll(Collection<?> arg0) {
		// TODO Auto-generated method stub
		return save.removeAll(arg0);
	}

	@Override
	public boolean retainAll(Collection<?> arg0) {
		// TODO Auto-generated method stub
		return save.retainAll(arg0);
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return save.size();
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return save.toArray();
	}

	@Override
	public <T> T[] toArray(T[] arg0) {
		// TODO Auto-generated method stub
		return save.toArray(arg0);
	}
	/**
	 * This function gets a file and takes the params from it , and Initialize it to our function
	 * @param string file
	 */

	@Override
	public void initFromFile(String file) throws IOException {
		// TODO Auto-generated method stub
		File file1= new File(file);
		BufferedReader br= new BufferedReader(new FileReader(file1));
		String st="";
		while((st=br.readLine())!=null) {
			function f=new ComplexFunction();
			ComplexFunction q=new ComplexFunction();
			try {
			System.out.println(st);
			f=q.initFromString(st);
			this.add(f);
			}
			
			catch(Exception e) {
				System.out.println("not a good text, cant init the CF");
			}
			
			
		}
		

	}
	/**
	 * This function gets a string that represent a file and save the function to the file 
	 * @param string file
	 */

	@Override
	public void saveToFile(String file) throws IOException {
		
		// TODO Auto-generated method stub
		try {
		String fileContent="";
		Iterator s=save.iterator();
		while(s.hasNext()) {
			function c=((function)s.next());
			fileContent=fileContent+c.toString()+"\n";
		}
		FileWriter writer=new FileWriter(file);
		writer.write(fileContent);
		writer.close();
		}
		catch(Exception e) {
			System.out.println("file could not be out");
		}
		
		
		
	}
	/**
	 * This function draw our functions by using stdDraw
	 * @param int width  
	 *  @param int height
	 *   @param Range ry
	 *    @param int resolution
	 */

	@Override
	public void drawFunctions(int width, int height, Range rx, Range ry, int resolution) {
		// TODO Auto-generated method stub
		StdDraw.setCanvasSize(width,height);
		StdDraw.setPenRadius(0.01);
		StdDraw.setXscale(rx.get_min(), rx.get_max());
		StdDraw.setYscale(ry.get_min(), ry.get_max());
		StdDraw.line(rx.get_min(), 0, rx.get_max(), 0);
		StdDraw.line(0,ry.get_min(), 0, ry.get_max());

		StdDraw.setPenRadius(0.001);
		for(int i=(int) rx.get_min();i<rx.get_max();i=i+1) {
			StdDraw.line(i,0, i, ry.get_max());
			StdDraw.line(i,0, i, ry.get_min());
			String num=String.valueOf(i);
			StdDraw.text(i, 0+0.5, num);

		}
		StdDraw.text(rx.get_max()-2,ry.get_min()-2, "@shaharivka-follow us");
		
		for(int i=(int) ry.get_min();i<ry.get_max();i=i+1) {
			StdDraw.line(0,i, rx.get_max(),i);
			StdDraw.line(0, i, rx.get_min(),i);
			String num=String.valueOf(i);
			StdDraw.text(0+0.5,i, num);

		}
		StdDraw.text(-7,8, "@shaharivka-follow us");
		
		Color[] colors= {StdDraw.BLACK,StdDraw.BLUE,StdDraw.BOOK_BLUE,
				StdDraw.BOOK_RED,StdDraw.PINK,StdDraw.CYAN,StdDraw.DARK_GRAY,StdDraw.DARK_GRAY,
				StdDraw.GRAY,StdDraw.GREEN,StdDraw.LIGHT_GRAY,StdDraw.MAGENTA,StdDraw.ORANGE,
				StdDraw.PRINCETON_ORANGE,StdDraw.RED,StdDraw.YELLOW};
		int count=0;


		Iterator s=save.iterator();
		StdDraw.setPenRadius(0.005);
		while(s.hasNext()) {
			//StdDraw.setFont(new Font("calibrity",Font.PLAIN,14));
			StdDraw.setPenColor(colors[count]);
			function c=((function)s.next());
			System.out.println(c);
			double step=(Math.abs(rx.get_min())+Math.abs(rx.get_max()))/resolution;
			for(double i=rx.get_min();i<rx.get_max();i=i+step) {
				double y=c.f(i);
				double y2=c.f(i+step);
				StdDraw.line(i,y,i+step, y2);
			}
			count++;
			if(count==colors.length-1)
				count=0;


		}




	}
	/**
	 * This function draw our functions by using stdDraw and reading a json file
	 * @param String json_file  
	 *  
	 */


	@Override
	public void drawFunctions(String json_file) {
		// TODO Auto-generated method stub
		Gson gson = new Gson();
		
		try {
			 FileReader readerparam= new FileReader(json_file);
			Params meters=gson.fromJson(readerparam,Params.class);
			 Range rx=new Range(meters.Range_X[0],meters.Range_X[1]);
			 Range ry=new Range(meters.Range_Y[0],meters.Range_Y[1]);
		    
				
			drawFunctions(meters.Width, meters.Height,  rx,  ry, meters.Resolution);	
				
		}
		catch(Exception e) {
			System.out.println("cant read this file");
			
		}
		
	
	}
	public class Params {
		
		private int Width;
		private int Resolution;
		private int Height;
		private double[] Range_X;
		private double[] Range_Y;
		
	}
	
}

