package pl.stqa.pft.addressbook.tests;

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


    if ( app.db().contacts().size() == 0)
    {
      app.goTo().homePage();
      app.contact().create(new ContactData().withFirstname("Julio").withSurname("Berdys")
              .withHomeNumber("743534534").withEmail("please@gmail.com"),true);
    }
  }

  @Test ()

  public void modifyContact()
   {
     Contacts before = app.db().contacts();
      ContactData modifiedContact = before.iterator().next();

     ContactData contact = new ContactData().withId(modifiedContact.getId())
             .withFirstname("Marian")
             .withSurname("Silaus")
             .withHomeNumber("45607906")
             .withEmail("terere@gmail.com");
     app.contact().modify(contact);
     app.goTo().homePage();
     assertThat(app.contact().count(), equalTo(before.size() ));
     Contacts after = app.db().contacts();
     assertEquals(after.size(), before.size());
     assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));

   }


}
