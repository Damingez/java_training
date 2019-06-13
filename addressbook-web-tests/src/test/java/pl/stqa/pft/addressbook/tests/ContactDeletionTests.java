package pl.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase{

  @Test

  public void testContactDeletion() {

    app.getNavigationHelper().goToContactPage();
    app.getContactHelper().selectContact();
    app.getContactHelper().initContactRemoval();
    app.getContactHelper().acceptPopup();

  }
}
