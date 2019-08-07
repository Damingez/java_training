package pl.stqa.pft.rest;

import org.testng.annotations.Test;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class RestAssuredTests extends TestBase{

  int issueId=4203;
  BigInteger bigInteger = BigInteger.valueOf(issueId);

  @Test
  public void testCreateIssue() throws IOException {
    skipIfNotFixed(bigInteger);
    Set<Issue> oldIssues = app.rest().getIssues();
    Issue newIssue =  new Issue().withSubject("TestIssue19").withDescription("NewTestIssue19");
    app.rest().createIssue(newIssue);
//    Set<Issue> newIssues = app.rest().getIssues();
//     oldIssues.add(newIssue.withId(issueId));
    Issue createdIssue =app.rest().getIssue(newIssue.getId()).stream().findFirst().get();

    assertEquals(app.rest().getIssue(newIssue.getId()).stream().findFirst().get(),newIssue);
  }


  }
