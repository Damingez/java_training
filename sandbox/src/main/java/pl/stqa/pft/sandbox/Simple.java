package pl.stqa.pft.sandbox;

public class Simple {
  public static void main(String args[]) {

    // three functions

            hello("Janek");
    double x = 7;
     Square sqa = new Square(x);
    System.out.println("The area of square with side " + sqa.a + " is " + sqa.area() );

    double y = 9;

    Rectangle rect = new Rectangle(x, y);

    System.out.println("The area of rectangle with sides " + x + " and " + y + " is " + rect.area() );


    Point first = new Point(2,5);

    Point second = new Point(5,9);

    System.out.println( "Distance between two points is " + distance(first,second) + " when using function ");
    System.out.println("Distance between two points is " + second.distance(first) + " when using method" );

  }

  public static void hello(String parameter)
  {
    System.out.println("Hello " + parameter);
  }

  public static double distance (Point one, Point two)
  {
    return  Math.sqrt((one.x-two.x)*(one.x-two.x)+(one.y-two.y)*(one.y-two.y));
  }

}