package pl.stqa.pft.sandbox;

public class Point {

  public int x;
  public int y;

  public Point (int a, int b)
  {
    x=a;
    y=b;
  }

  public double distance (Point two)
  {
    return  Math.sqrt((this.x-two.x)*(this.x-two.x)+(this.y-two.y)*(this.y-two.y));
  }

}
