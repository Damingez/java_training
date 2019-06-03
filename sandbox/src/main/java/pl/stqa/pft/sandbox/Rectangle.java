package pl.stqa.pft.sandbox;

public class Rectangle {

  public double a;
  public double b;

  public Rectangle (double param1, double param2)
  {
    this.a = param1;
    this.b = param2;
  }

  public double area ()
  {
    return this.a * this.b ;
  }

}
