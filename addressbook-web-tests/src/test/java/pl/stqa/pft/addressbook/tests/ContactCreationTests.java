package pl.stqa.pft.addressbook.tests;


import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.ContactData;
import pl.stqa.pft.addressbook.model.Contacts;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validContactsJson() throws IOException {
    List<Object[]> list = new ArrayList<Object[]>();
   try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json"))))
    {
      String line = reader.readLine();
      String json = "";
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<ContactData> groups = gson.fromJson(json, new TypeToken<List<ContactData>>() {
      }.getType()); // List <ContactData>.class

      return groups.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
    }

  }

  @Test (dataProvider = "validContactsJson")
  public void testAddingContact(ContactData contact)  {
    app.goTo().homePage();
    Contacts before = app.db().contacts();
    app.contact().create(contact, true);

    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.db().contacts();

    MatcherAssert.assertThat(after, equalTo(before.
            withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));


  }

}
