package pl.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {


  @Test

  public void modifyContact()
   {
     app.getNavigationHelper().goToHomePage();

     if (! app.getContactHelper().isThereContact())
     {
        app.getContactHelper().createContact(new ContactData("Julio","Berdys","743534534","please@gmail.com","test1"),true);
     }
     app.getContactHelper().selectContact();
     app.getContactHelper().initContactModification();
     app.getContactHelper().fillContactForm(new ContactData("Jaro", "Badach", "723684177", "jaro@gmail.com", null), false);
     app.getContactHelper().submitContactUpdate();

   }
}
