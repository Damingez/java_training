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
      ContactData contact = new ContactData().withFirstname("Julio").withSurname("Berdys")
              .withHomeNumber("743534534").withEmail("please@gmail.com");

      app.contact().create(contact,true);
    }

  }


  @Test
public void addingContactToGroupTest()

  {

    ContactData contact = new ContactData().withFirstname("Julio").withSurname("Berdys")
            .withHomeNumber("743534534").withEmail("please@gmail.com");

    app.contact().create(contact,true);

    app.contact().selectContact();



    if (contact.getGroups().size()== app.db().groups().size() )
    {
          // create new group
    }

  //  and  add contact to this new group

    app.goTo().homePage();
  /*  app.contact().clickGroupChoiceList();
    app.contact().clickNoneGroup();
   if (! app.contact().IsContactWithoutGroupAvailable())
    {
      app.goTo().homePage();
      app.contact().create(contact,true);
    }
    app.contact().selectContact();
   */
    app.contact().clickAddToGroup();
  }

}
