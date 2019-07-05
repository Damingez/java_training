package pl.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import pl.stqa.pft.addressbook.model.ContactData;
import pl.stqa.pft.addressbook.model.Contacts;

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



  private void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[value='"+ id +"']")).click();
  }


  public void acceptPopup() {
    wd.switchTo().alert().accept();
    wd.findElement(By.cssSelector("div.msgbox"));
  }


  public void clickEditContact(int id) {

    wd.findElement(By.cssSelector("[href='edit.php?id=" + id + "']")).click();
  }

  public void submitContactUpdate() {
    click(By.xpath("//div[@id='content']/form/input[22]"));
  }

  public void create(ContactData contact, boolean isCreation) {

    gotoAddContactPage();
    fillContactForm(contact, isCreation);
    submitContactForm();
  }


  public void modify(ContactData contact) {
    selectContactById(contact.getId());
    clickEditContact(contact.getId());
    fillContactForm(contact, false);
    submitContactUpdate();

  }



  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    initContactRemoval();
    acceptPopup();
  }




  public boolean isThereContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int getContactNumber() {
   return wd.findElements(By.name("selected[]")).size();
  }



  public Contacts all() {

    Contacts contacts = new Contacts();
    List<WebElement> elements = wd.findElements(By.name("entry"));

    for (WebElement element :elements)
    {

      List<WebElement> cells =  element.findElements(By.tagName("td"));
      String lastname = cells.get(1).getText();
      String firstname = cells.get(2).getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value")) ;
      ContactData contact = new ContactData().withId(id).withFirstname(firstname).withSurname(lastname);
      contacts.add(contact);
    }

    return contacts;

  }
}
