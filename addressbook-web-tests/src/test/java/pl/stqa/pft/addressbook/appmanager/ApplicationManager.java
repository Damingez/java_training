package pl.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
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

    wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    groupHelper = new GroupHelper(wd);
    wd.get("http://localhost/addressbook/group.php#");
    navigationHelper = new NavigationHelper(wd);
    sessionHelper = new SessionHelper(wd);
    contactHelper = new ContactHelper(wd);
    sessionHelper.login("admin", "secret");
  }


  public void stop() {
    contactHelper.wd.quit();
  }

  public boolean isElementPresent(By by) {
    try {
      contactHelper.wd.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  public boolean isAlertPresent() {
    try {
      contactHelper.wd.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  public GroupHelper getGroupHelper() {
    return groupHelper;
  }

  public NavigationHelper getNavigationHelper() {
    return navigationHelper;
  }


  public ContactHelper getContactHelper() {
    return contactHelper;
  }
}
