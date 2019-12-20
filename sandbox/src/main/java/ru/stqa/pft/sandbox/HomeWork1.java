package ru.stqa.pft.sandbox;

public class HomeWork1 {
    public static void main(String[] args) {
        Point point1 = new Point(450,658);
        Point point2 = new Point(120, 356);
        double result = Point.distance(point1,point2);
    System.out.println("Расстояние между точек х и у равно " + result);

    }

}

