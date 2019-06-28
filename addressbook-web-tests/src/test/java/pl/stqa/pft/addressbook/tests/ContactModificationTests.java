package pl.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.ContactData;

import java.util.HashSet;
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
    ContactData contact = new ContactData( before.get(before.size()-1).getId(), "Krasa", "Pasa", "45607906", "terere@gmail.com", null);
     app.getContactHelper().fillContactForm(contact, false);
     app.getContactHelper().submitContactUpdate();
     app.getNavigationHelper().goToHomePage();
     List<ContactData> after = app.getContactHelper().getContactList();

     Assert.assertEquals(after.size(),before.size());

     before.remove(before.size()-1);
     before.add(contact);
     Assert.assertEquals(new HashSet<Object>(after), new HashSet<Object>(before));

   }
}
