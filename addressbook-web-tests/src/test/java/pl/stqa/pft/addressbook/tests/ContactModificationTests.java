package pl.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.ContactData;
import pl.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.AssertJUnit.assertEquals;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().homePage();

    if ( app.contact().all().size() == 0)
    {
      app.contact().create(new ContactData().withFirstname("Julio").withSurname("Berdys")
              .withHomeNumber("743534534").withEmail("please@gmail.com").withGroup("test1"),true);
    }
  }

  @Test ()

  public void modifyContact()
   {
     Contacts before = app.contact().all();
      ContactData modifiedContact = before.iterator().next();

     ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstname("Mario").withSurname("Krase").withHomeNumber("45607906").withEmail("terere@gmail.com").withGroup("test1");
     app.contact().modify(contact);
     app.goTo().homePage();

     Contacts after = app.contact().all();

     Assert.assertEquals(after, before);

     assertEquals(after.size(), before.size());
     assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
   }


}
