package pl.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import pl.stqa.pft.addressbook.model.ContactData;
import pl.stqa.pft.addressbook.model.Contacts;

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
    type(By.name("lastname"),contactData.getLastname());
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
    contactsCache = null;
  }


  public void modify(ContactData contact) {
    selectContactById(contact.getId());
    clickEditContact(contact.getId());
    fillContactForm(contact, false);
    submitContactUpdate();
    contactsCache = null;
  }



  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    initContactRemoval();
    acceptPopup();
    contactsCache = null;
  }

  public ContactData infoFromEditForm(ContactData contact) {
        clickEditContact(contact.getId());
    String firstName = wd.findElement(By.name("firstname")).getAttribute("value");
    String surName = wd.findElement(By.name("lastname")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
  //  String email = wd.findElement(By.name("")).getAttribute("value");
  //  String address = wd.findElement(By.name("")).getAttribute("value");
 //   String group = wd.findElement(By.name("")).getAttribute("value");

    return contact = new ContactData().withFirstname(firstName).withSurname(surName)
            .withHomeNumber(home).withMobileNumber(mobile).withWorkNumber(work);
          //  .withEmail(email).withAddress(address).withGroup(group)
  }

  public boolean isThereContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int count() {
   return wd.findElements(By.name("selected[]")).size();
  }

   private Contacts contactsCache = null;


  public Contacts all() {

    if (contactsCache != null) {
      return new Contacts(contactsCache);
    }

    contactsCache = new Contacts();
    List<WebElement> elements = wd.findElements(By.name("entry"));

    for (WebElement element : elements) {

      List<WebElement> cells = element.findElements(By.tagName("td"));
      String lastname = cells.get(1).getText();
      String firstname = cells.get(2).getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      ContactData contact = new ContactData().withId(id).withFirstname(firstname).withSurname(lastname);
      contactsCache.add(contact);
    }

    return new Contacts(contactsCache);

  }

  public Set<ContactData> all2()
  {
    Set<ContactData> contacts = new HashSet<ContactData>();
    List<WebElement> rows = wd.findElements(By.name("entry"));
    for (WebElement row : rows)
    {
      List<WebElement> cells = row.findElements(By.tagName("td"));
      int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value")) ;
      String lastname = cells.get(1).getText();
      String firstname = cells.get(2).getText();
      String[] phones = cells.get(5).getText().split("\n");

      ContactData contact = new ContactData().withId(id).withFirstname(firstname).withSurname(lastname)
              .withHomeNumber(phones[0]).withMobileNumber(phones[1]).withWorkNumber(phones[2]);
      contacts.add(contact);

    }
    return contacts;
  }


}
