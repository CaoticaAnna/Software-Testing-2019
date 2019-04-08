package ru.stqa.pft.sandbox;

public class PointDistance {
  public static void main(String[] args) {

    Point p1 = new Point(2, 3);
    Point p2 = new Point(8, 10);

    System.out.println("Расстояние между точками на плоскости = " + p1.distance(p2));
  }
}
