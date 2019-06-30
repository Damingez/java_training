package pl.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {

  WebDriver wd;

  private  ContactHelper contactHelper ;
  private SessionHelper sessionHelper;
  private NavigationHelper navigationHelper;
  private  GroupHelper groupHelper;
  private String browser;

  public ApplicationManager(String browser) {

    this.browser = browser;
  }

  public void init() {


    if (browser.equals(BrowserType.FIREFOX))
    {
      wd = new FirefoxDriver();
    }else if (browser.equals(BrowserType.CHROME))
    {
      wd = new ChromeDriver();
    }else if (browser.equals(BrowserType.IE))
    {
      wd= new InternetExplorerDriver();
    }

    wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    groupHelper = new GroupHelper(wd);
    wd.get("http://localhost/addressbook/");
    navigationHelper = new NavigationHelper(wd);
    sessionHelper = new SessionHelper(wd);
    contactHelper = new ContactHelper(wd);
    sessionHelper.login("admin", "secret");
  }


  public void stop() {
    contactHelper.wd.quit();
  }


  public GroupHelper group() {
    return groupHelper;
  }

  public NavigationHelper goTo() {
    return navigationHelper;
  }


  public ContactHelper getContactHelper() {
    return contactHelper;
  }


}
