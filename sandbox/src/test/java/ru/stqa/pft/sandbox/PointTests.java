package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {
  @Test
  public void test1(){
    Point p1 = new Point(4, 6);
    Point p2 = new Point(4, 6);

    System.out.println("Расстояние между точками на плоскости = " + p1.distance(p2));
    Assert.assertEquals(p1.distance(p2), 0.0);
  }

  @Test
  public void test2(){
    Point p1 = new Point(12, 9);
    Point p2 = new Point(2, 5);

    System.out.println("Расстояние между точками на плоскости = " + p1.distance(p2));
    Assert.assertEquals(Math.round(p1.distance(p2)), 11);
  }

  @Test
  public void test3(){
    Point p1 = new Point(2, 3);
    Point p2 = new Point(8, 10);

    System.out.println("Расстояние между точками на плоскости = " + p1.distance(p2));
    Assert.assertEquals(Math.round(p1.distance(p2)), 9);
  }
}
