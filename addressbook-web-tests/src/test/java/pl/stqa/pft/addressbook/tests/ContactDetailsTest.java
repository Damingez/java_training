package pl.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDetailsTest extends TestBase {


    @BeforeMethod
    public void ensurePreconditions() {
      if (app.contact().count() == 0) {
        app.goTo().homePage();
        app.contact().create(new ContactData().withFirstname("Damian").withSurname("Kowalski")
                .withAddress("Michalo 1/4\n Krakow").withHomeNumber("673452")
                .withMobileNumber("234910527").withWorkNumber("92342101").withEmail("damian@wp.pl").
                        withEmail2("kowal@wp.pl").withEmail3("kowal2@wp.pl")
                .withAddress2(" Wspanialy 7"), true);
      }
    }

    @Test
    public void testContactDetails() {
      app.goTo().homePage();
      ContactData contact = app.contact().all2().iterator().next();
      ContactData contactInfoFromEditForm =  app.contact().infoFromEditForm(contact);
      app.goTo().homePage();
      assertThat(app.contact().dataFromDetailsForm(contact).replaceAll("(?m)^[ \t]*\r?\n", "").replaceAll("[\\n\\r]" +
                      ".*Member of:\\s*([^\\n\\r]*)",
              ""),
              equalTo(mergeData(contactInfoFromEditForm)));
    }

    private String mergeData(ContactData contactData) {
      return Arrays.asList(contactData.getFirstname()+" "+ " " + contactData.getLastname(),
                contactData.getAddress(),
              "H: " + contactData.getHomeNumber(), "M: " +  contactData.getMobileNumber(),  "W: " +  contactData.getWorkNumber(),
             /* "F:" + */  contactData.getEmail(), contactData.getEmail2(), contactData.getEmail3(),
              contactData.getAddress2()).stream().filter((s) -> !s.equals(""))
              .map(ContactDetailsTest::cleaned).collect(Collectors.joining("\n"));
    }

    public static String cleaned(String email) {
      return email.replaceAll("[ \\f\\t\\v]+$", "").replaceAll("[ ]{2,}", " ");
    }


}
