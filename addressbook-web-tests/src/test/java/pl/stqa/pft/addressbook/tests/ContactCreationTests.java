package pl.stqa.pft.addressbook.tests;


import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.ContactData;
import pl.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {


  @Test ()
  public void testAddingContact() throws Exception {
    app.goTo().homePage();
    Contacts before = app.contact().all();

    ContactData contact = new ContactData().withFirstname("Mara").
            withSurname("Timisoara").withHomeNumber("48668290314").withEmail("magdabec@gmail.com").withGroup("test1");
    app.contact().create(contact, true);

    Contacts after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() + 1);


    assertThat(after.size(), equalTo(before.size() + 1));
    MatcherAssert.assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));

  }

}
