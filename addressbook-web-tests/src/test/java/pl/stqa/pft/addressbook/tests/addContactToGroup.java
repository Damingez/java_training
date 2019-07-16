package pl.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.ContactData;
import pl.stqa.pft.addressbook.model.GroupData;

public class addContactToGroup extends TestBase{

  @BeforeMethod
  public void ensurePreconditions(){

    if (app.db().groups().size()==0)
    {
      GroupData group = new GroupData().withName("Grupka1").withHeader("Jojoj").withFooter("Morele");
        app.goTo().groupPage();
        app.group().create(group);
    }

    if ( app.db().contacts().size() == 0)
    {
      app.goTo().homePage();
      app.contact().create(new ContactData().withFirstname("Julio").withSurname("Berdys")
              .withHomeNumber("743534534").withEmail("please@gmail.com"),true);
    }
  }


  @Test
public void addingContactToGroupTest()

  {
    ContactData contact = new ContactData().withFirstname("Malwowe").withSurname("Brownie")
            .withHomeNumber("24574353").withEmail("carrot@gmail.com");

    app.goTo().homePage();
    app.contact().clickGroupChoiceList();
    app.contact().clickNoneGroup();
    if (! app.contact().IsContactWithoutGroupAvailable())
    {
      app.goTo().homePage();
      app.contact().create(contact,true);
    }
    app.contact().selectContact();
    app.contact().clickAddToGroup();
  }

}
