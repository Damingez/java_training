package pl.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase{

  public void ensurePreconditions(){
    app.goTo().homePage();

    if (! app.contact().isThereContact())
    {
      app.contact().create(new ContactData().withFirstname("Julio").withSurname("Berdys")
              .withHomeNumber("743534534").withEmail("please@gmail.com").withGroup("test1"),true);
    }
  }

  @Test ()

  public void testContactDeletion() {

    List<ContactData> before = app.contact().list();
    int index = before.size() -1;

    app.contact().delete(index);

    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(),before.size()-1 );

    before.remove(index);
    Assert.assertEquals(before,after);


  }

}
