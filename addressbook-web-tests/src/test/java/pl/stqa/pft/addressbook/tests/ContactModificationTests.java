package pl.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactModificationTests extends TestBase {


  @Test

  public void modifyContact()
   {
     app.getNavigationHelper().goToHomePage();

     if (! app.getContactHelper().isThereContact())
     {
        app.getContactHelper().createContact(new ContactData("Julio","Berdys","743534534","please@gmail.com","test1"),true);
     }
     List<ContactData> before = app.getContactHelper().getContactList();
     app.getContactHelper().selectContact(before.size() -1);
     app.getContactHelper().initContactModification();
     app.getContactHelper().fillContactForm(new ContactData("Jaro", "Badach", "723684177", "jaro@gmail.com", null), false);
     app.getContactHelper().submitContactUpdate();

     List<ContactData> after = app.getContactHelper().getContactList();

     Assert.assertEquals(after.size(),before.size());


   }
}
