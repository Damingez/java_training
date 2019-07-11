package pl.stqa.pft.addressbook.tests;


import org.hamcrest.MatcherAssert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.ContactData;
import pl.stqa.pft.addressbook.model.Contacts;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validContacts() {
    List<Object[]> list = new ArrayList<Object[]>();
    list.add(new Object[] {new ContactData().withFirstname("Michal").withSurname("Kichal")
            .withHomeNumber("382993").withGroup("test 1")});
    list.add(new Object[] {new ContactData().withFirstname("Rafalkie").withSurname("Dudycze")
            .withHomeNumber("6821093").withGroup("test 2")});
    list.add(new Object[] {new ContactData().withFirstname("Jura").withSurname("Lesna")
            .withHomeNumber("92072993").withGroup("test 3")});
    return list.iterator();
  }


  @Test (dataProvider = "validContacts")
  public void testAddingContact(ContactData contact)  {
    app.goTo().homePage();
    Contacts before = app.contact().all();
    app.contact().create(contact, true);

    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();

    MatcherAssert.assertThat(after, equalTo(before.
            withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));

  }

}
