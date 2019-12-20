package ru.stqa.pft.sandbox;

public class MyFirstProgram {
	public static void main(String[] arg) {
		System.out.println("Hello, world!");
		hello("VAsz");
		Square len = new Square(5);

		Rectangle r = new Rectangle(3,5);

		System.out.println("Area of rectangle = " + area(r));
		System.out.println("Плащадь квадрата равна " + area(len));
	}
	public static void hello(String name) {
		System.out.println("Hello " + name);

	}
	public static double area(Square len){
		return len.l * len.l;
	}
	public static double area(Rectangle r){
		return r.a * r.b;
	}
}