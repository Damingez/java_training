package pl.stqa.pft.sandbox;

public class Simple {
  public static void main(String args[]) {

    // three functions

            hello("Janek");
    double a= 6;
    System.out.println("The area of square with side " + a + " is " + area(a) );

    double b = 8;

    System.out.println("The area of rectangle with sides " + a + " and " + b + " is " + area(a,b) );
  }

  public static void hello(String parameter)
  {
    System.out.println("Hello " + parameter);
  }

  public static double area (double side)
  {
    return side * side;
  }

  public static double area (double side1, double side2)
  {
    return side1 * side2;
  }

}