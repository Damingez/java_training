package pl.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pl.stqa.pft.addressbook.model.ContactData;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void submitContactForm() {
    wd.findElement(By.xpath("(//input[@name='submit'])[2]")).click();
  }

  public void fillAddContactForm(ContactData contactData) {
    type(By.name("firstname"),contactData.getFirstname());
    type(By.name("lastname"),contactData.getSurname());
    type(By.name("home"),contactData.getHomeNumber());
    type(By.name("email"),contactData.getEmail());

  }

  public void initContactRemoval() {
   click(By.xpath("//input[@value='Delete']"));

  }

  public void selectContact() {
    click(By.name("selected[]"));
  }


  public void acceptPopup() {
    wd.switchTo().alert().accept();
  }


}
