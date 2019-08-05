package pl.stqa.pft.mantis.tests;

import com.google.protobuf.ServiceException;
import org.openqa.selenium.remote.BrowserType;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import pl.stqa.pft.mantis.appmanager.ApplicationManager;

import java.io.IOException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.rmi.RemoteException;

public class TestBase {


  protected  final ApplicationManager app =
          new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX));

  @BeforeSuite(alwaysRun = true)
  public void setUp() throws Exception {
    app.init();
  //  app.ftp().upload(
  //     new File("src/test/resources/config_inc.php"),"config_inc.php","config_inc.php.bak");
  }

  @AfterSuite(alwaysRun = true)
  public void tearDown() throws IOException {
  //  app.ftp().restore("config_inc.php.bak","config_inc.php");
  // app.stop();

  }

  private boolean isIssueOpen(BigInteger issueId) throws RemoteException,  MalformedURLException, javax.xml.rpc.ServiceException {
    if ((app.soap().getIssueStatus(issueId).equals("closed") || app.soap().getIssueStatus(issueId).equals("resolved"))) {
      return false;
    } else return true;
  }

  public void skipIfNotFixed(BigInteger issueId) throws RemoteException, ServiceException, MalformedURLException, javax.xml.rpc.ServiceException {
    if (isIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }

  }


