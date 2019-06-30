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
    wd.findElement(By.cssSelector("div.msgbox"));
  }


  public void initContactModification(int index) {
//    click(By.xpath("//img[@alt='Edit']"));
    wd.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();
  }

  public void submitContactUpdate() {
    click(By.xpath("//div[@id='content']/form/input[22]"));
  }

  public void create(ContactData contact, boolean isCreation) {

    gotoAddContactPage();
    fillContactForm(contact, isCreation);
    submitContactForm();
  }


  public void modify(int index, ContactData contact) {
    selectContact(index);
    initContactModification(index);
    fillContactForm(contact, false);
    submitContactUpdate();

  }

  public void delete(int index) {
   selectContact(index);
   initContactRemoval();
   acceptPopup();
  }


  public boolean isThereContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int getContactNumber() {
   return wd.findElements(By.name("selected[]")).size();
  }

  public List<ContactData> list() {

    List<ContactData> contacts = new ArrayList<ContactData>();
    List<WebElement> elements = wd.findElements(By.name("entry"));

    for (WebElement element :elements)
    {

      List<WebElement> cells =  element.findElements(By.tagName("td"));
      String lastname = cells.get(1).getText();
      String firstname = cells.get(2).getText();

      ContactData contact = new ContactData(firstname,lastname,null,null,null);
      contacts.add(contact);
    }

    return contacts;

  }
}
