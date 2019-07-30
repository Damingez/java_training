package pl.stqa.pft.mantis.tests;

import biz.futureware.mantis.rpc.soap.client.IssueData;
import biz.futureware.mantis.rpc.soap.client.MantisConnectLocator;
import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import pl.stqa.pft.mantis.appmanager.ApplicationManager;

import javax.xml.rpc.ServiceException;
import java.io.IOException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

public class TestBase {


  protected  final ApplicationManager app =
          new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX));

  @BeforeSuite(alwaysRun = true)
  public void setUp() throws Exception {
    app.init();
  //  app.ftp().upload(
  //          new File("src/test/resources/config_inc.php"),"config_inc.php","config_inc.php.bak");
  }

  @AfterSuite(alwaysRun = true)
  public void tearDown() throws IOException {
  //  app.ftp().restore("config_inc.php.bak","config_inc.php");
  // app.stop();

  }


  public MantisConnectPortType getMantisConnect() throws ServiceException, MalformedURLException, ServiceException {
    return new MantisConnectLocator()
            .getMantisConnectPort(new URL("http://localhost/mantisbt-1.2.20/api/soap/mantisconnect.php"));
  }

  public boolean isIssueOpen(BigInteger issueID) throws MalformedURLException, ServiceException, RemoteException {
    IssueData checkedIssue = new IssueData();
      String issueStatus = "";
   MantisConnectPortType mc = getMantisConnect();
   checkedIssue = mc.mc_issue_get("administrator", "root",issueID);
    checkedIssue.getStatus().toString();

    return false;
  }


  }


