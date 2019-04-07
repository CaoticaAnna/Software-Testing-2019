package ru.stqa.pft.sandbox;

public class PointDistance {
  public static void main(String[] args) {
    Point p1 = new Point(2, 3);
    Point p2 = new Point(6, 9);
    System.out.println("Расстояние между точками на плоскости = " + distance(p1, p2));
  }

  public static double distance(Point p1, Point p2){
    return Math.sqrt(Math.pow((p2.getX() - p1.getX()),2)+Math.pow((p2.getY() - p1.getY()),2));
  }
}
