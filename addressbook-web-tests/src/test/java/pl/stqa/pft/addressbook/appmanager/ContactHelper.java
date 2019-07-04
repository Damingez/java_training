package pl.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import pl.stqa.pft.addressbook.model.ContactData;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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


  public void initContactModification(int index) {

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
    selectContactById(contact.getId());
    initContactModification(index);
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



  public Set<ContactData> all() {

    Set<ContactData> contacts = new HashSet<>();
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
