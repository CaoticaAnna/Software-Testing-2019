package ru.stqa.pft.sandbox;

public class Point {

  public double x;
  public double y;

  public Point (double x, double y){
   this.x = x;
   this.y = y;

 }
  public double distance(Point point){
  Double distance = Math.sqrt(Math.pow(x-point.x,2)+Math.pow(y-point.y,2));
  return distance;
  }
}
