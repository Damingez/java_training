package pl.stqa.pft.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pl.stqa.pft.addressbook.appmanager.ApplicationManager;

import java.lang.reflect.Method;
import java.util.Arrays;

public class TestBase {

  Logger logger = LoggerFactory.getLogger(TestBase.class);

  protected  final ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX));

  @BeforeMethod(alwaysRun = true)
  public void setUp() throws Exception {
    app.init();

  }

  @AfterMethod(alwaysRun = true)
  public void tearDown()  {
    app.stop();

  }

  @BeforeMethod
  public void logTestStart(Method m)
  {
    logger.info("Start test " + m.getName());
  }

  @AfterMethod (alwaysRun = true)
  public void logTestStop(Method m, Object[] p)
  {
    logger.info("Stop test " + m.getName()+ " with parameters " + Arrays.asList(p));
  }

}
