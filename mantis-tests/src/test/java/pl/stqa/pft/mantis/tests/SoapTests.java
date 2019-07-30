package pl.stqa.pft.mantis.tests;

import biz.futureware.mantis.rpc.soap.client.IssueData;
import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import org.testng.annotations.Test;
import pl.stqa.pft.mantis.model.Issue;
import pl.stqa.pft.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class SoapTests extends TestBase{

  @Test
  public void testGetProjects() throws MalformedURLException, ServiceException, RemoteException {

    Set<Project> projects = app.soap().getProjects();
    System.out.println(projects.size());
    for (Project project: projects)
    {
      System.out.println(project.getName());
    }
  }

  @Test
  public void testCreateIssue() throws RemoteException, ServiceException, MalformedURLException {
    Set<Project> projects = app.soap().getProjects();
    Issue issue = new Issue().withSummary("Test issue").withDescription("Test issue description")
            .withProject(projects.iterator().next());

    Issue created = app.soap().addIssue(issue);
    assertEquals(issue.getSummary(), created.getSummary());

  }

  @Test

  public void getIssue() throws MalformedURLException, ServiceException, RemoteException {
    IssueData checkedIssue = new IssueData();
    String issueStatus = "";
    MantisConnectPortType mc = getMantisConnect();
    checkedIssue = mc.mc_issue_get("administrator", "root", BigInteger.valueOf(1));

    System.out.println(checkedIssue.getStatus().getName());
  }

}
