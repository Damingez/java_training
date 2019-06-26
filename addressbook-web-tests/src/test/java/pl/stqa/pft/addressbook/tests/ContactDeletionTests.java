package pl.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase{

  @Test

  public void testContactDeletion() {

    app.getNavigationHelper().goToHomePage();
    if (! app.getContactHelper().isThereContact()) {
      app.getContactHelper().createContact(new ContactData("Wasylecki","Mijacze","843534534","please@gmail.com","test1"),true);
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContact(before.size() -1);
    app.getContactHelper().initContactRemoval();
    app.getContactHelper().acceptPopup();
    List<ContactData> after = app.getContactHelper().getContactList();


    Assert.assertEquals(after.size(),before.size() -1  ); // strange error happens when  before - 1 is set
  }
}
