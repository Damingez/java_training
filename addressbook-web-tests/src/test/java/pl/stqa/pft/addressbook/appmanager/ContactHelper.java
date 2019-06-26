package pl.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import pl.stqa.pft.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void submitContactForm() {
    wd.findElement(By.xpath("(//input[@name='submit'])[2]")).click();
  }

  public void fillContactForm(ContactData contactData, boolean isCreation) {
    type(By.name("firstname"),contactData.getFirstname());
    type(By.name("lastname"),contactData.getSurname());
    type(By.name("home"),contactData.getHomeNumber());
    type(By.name("email"),contactData.getEmail());

    if (isCreation) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    } else
    {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }

  }

  public void gotoAddContactPage() {
    click(By.linkText("add new"));
  }

  public void initContactRemoval() {
   click(By.xpath("//input[@value='Delete']"));

  }

  public void selectContact(int index) {

    wd.findElements(By.name("selected[]")).get(index).click();
  }


  public void acceptPopup() {
    wd.switchTo().alert().accept();
  }


  public void initContactModification() {
    click(By.xpath("//img[@alt='Edit']"));
  }

  public void submitContactUpdate() {
    click(By.xpath("//div[@id='content']/form/input[22]"));
  }

  public void createContact(ContactData contact,boolean isCreation) {

    gotoAddContactPage();
    fillContactForm(contact, isCreation);
    submitContactForm();
  }

  public boolean isThereContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int getContactNumber() {
   return wd.findElements(By.name("selected[]")).size();
  }

  public List<ContactData> getContactList() {

    List<ContactData> contacts = new ArrayList<ContactData>();
    List<WebElement> elements = wd.findElements(By.xpath("//tr[3]/td/input"));
    for (WebElement element :elements)
    {
      String name = element.getText();
      ContactData contact = new ContactData(name,null,null,null,null);
      contacts.add(contact);
    }

    return contacts;

  }
}
