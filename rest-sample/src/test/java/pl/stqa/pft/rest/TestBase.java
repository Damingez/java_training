package pl.stqa.pft.rest;

import com.jayway.restassured.RestAssured;
import org.testng.SkipException;
import org.testng.annotations.BeforeSuite;
import pl.stqa.pft.rest.appmanager.ApplicationManager;

import java.io.IOException;
import java.math.BigInteger;

public class TestBase {

  protected static final ApplicationManager app =
          new ApplicationManager();

  @BeforeSuite
  public void init() {
    RestAssured.authentication = RestAssured.basic("25c6b66f028c417c8ade51cfd2c906e9", "");
  }


  private boolean isIssueOpen(BigInteger issueId) throws IOException {
    if ((app.rest().getIssueStatus(issueId).equals("Deleted") || app.rest().getIssueStatus(issueId).equals("Closed"))
            || app.rest().getIssueStatus(issueId).equals("Resolved")) {
      return false;
    } else return true;
  }


  public void skipIfNotFixed(BigInteger issueId) throws IOException {
    if (isIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }
}
