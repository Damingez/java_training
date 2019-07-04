package pl.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.ContactData;

import java.util.Set;

public class ContactDeletionTests extends TestBase{

  public void ensurePreconditions(){
    app.goTo().homePage();

    if ( app.contact().all().size() == 0)
    {
      app.contact().create(new ContactData().withFirstname("Julio").withSurname("Berdys")
              .withHomeNumber("743534534").withEmail("please@gmail.com").withGroup("test1"),true);
    }
  }

  @Test ()

  public void testContactDeletion() {

    Set<ContactData> before = app.contact().all();
    ContactData deletedContact =  before.iterator().next();

    //app.contact().delete(index);
    app.contact().delete(deletedContact);

    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(),before.size()-1 );

    before.remove(deletedContact);
    Assert.assertEquals(before,after);


  }

}
