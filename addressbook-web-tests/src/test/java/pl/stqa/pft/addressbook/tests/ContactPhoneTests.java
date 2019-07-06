package pl.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTests extends TestBase {

    @Test

    public void testContactPhones() {
        app.goTo().homePage();
      ContactData contact = app.contact().all2().iterator().next();
      ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

      assertThat(contact.getHomeNumber(), equalTo(contactInfoFromEditForm.getHomeNumber()));
      assertThat(contact.getMobileNumber(), equalTo(contactInfoFromEditForm.getMobileNumber()));
      assertThat(contact.getWorkNumber(), equalTo(contactInfoFromEditForm.getWorkNumber()));


  }

}
