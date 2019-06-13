package pl.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {


  @Test

  public void modifyContact()
   {
     app.getNavigationHelper().goToContactPage();
     app.getContactHelper().selectContact();
     app.getContactHelper().initContactModification();
     app.getContactHelper().fillAddContactForm(new ContactData("Jaro", "Badach", "723684177", "jaro@gmail.com"));
     app.getContactHelper().submitContactUpdate();

   }
}