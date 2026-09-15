package com.mcnz.shapes;


public class ShapesApplication {

	public static void main(String[] args) 
	{
		Square s1 = new Square(4);
		System.out.println(s1.calcArea());

		Rectangle r1 = new Rectangle();
		r1.height = 4;
		r1.width = 4;
		System.out.println(compareAreas(s1,r1));

	}

	public static boolean compareAreas(Shape s1, Shape s2) {
		return s1.calcArea() == s2.calcArea();
	}

}
