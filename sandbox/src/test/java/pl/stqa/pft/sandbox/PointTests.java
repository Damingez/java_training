package pl.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

  @Test

  public void testPoint()
  {
    Point p = new Point(2,-4);
    Point p2 = new Point(-3,5);

    Point p3 = new Point(-3,-5);
    Point p4 = new Point(4,-1);

    Assert.assertEquals(p.distance(p2),10.295630140987);
    Assert.assertEquals(p3.distance(p4),8.06225774829855);

  }
}
