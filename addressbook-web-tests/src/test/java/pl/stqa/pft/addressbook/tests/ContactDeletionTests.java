package pl.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase{

  @Test

  public void testContactDeletion() {

    app.getNavigationHelper().goToHomePage();
    if (! app.getContactHelper().isThereContact()) {
      app.getContactHelper().createContact(new ContactData("Wasylecki","Mijacze","843534534","please@gmail.com","test1"),true);
    }

    app.getContactHelper().selectContact();
    app.getContactHelper().initContactRemoval();
    app.getContactHelper().acceptPopup();

  }
}
