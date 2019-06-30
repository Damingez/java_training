package pl.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase{

  public void ensurePreconditions(){
    app.goTo().goToHomePage();

    if (! app.getContactHelper().isThereContact())
    {
      app.getContactHelper().createContact(new ContactData("Julio","Berdys","743534534","please@gmail.com","test1"),true);
    }
  }


  @Test ()

  public void testContactDeletion() {


    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContact(before.size() -1);
    app.getContactHelper().initContactRemoval();
    app.getContactHelper().acceptPopup();
    List<ContactData> after = app.getContactHelper().getContactList();

    Assert.assertEquals(after.size(),before.size() -1  );

    before.remove(before.size()-1);
    Assert.assertEquals(before,after);


  }
}
