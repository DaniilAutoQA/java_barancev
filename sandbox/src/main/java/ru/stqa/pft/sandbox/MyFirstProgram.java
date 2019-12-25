package ru.stqa.pft.sandbox;

public class MyFirstProgram {
	public static void main(String[] arg) {
		System.out.println("Hello, world!");
		hello("VAsz");
		Square len = new Square(5);

		Rectangle r = new Rectangle(3,5);

		System.out.println("Area of rectangle = " + r.area());
		System.out.println("Плащадь квадрата равна " + len.area());
	}
	public static void hello(String name) {
		System.out.println("Hello " + name);

	}


}