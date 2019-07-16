package pl.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.ContactData;
import pl.stqa.pft.addressbook.model.GroupData;
import pl.stqa.pft.addressbook.model.Groups;

public class removeContactFromGroup extends TestBase{

  @BeforeMethod
  public void ensurePreconditions(){

    if (app.db().groups().size()==0)
    {
      GroupData group = new GroupData().withName("Grupka1").withHeader("Jojoj").withFooter("Morele");
      app.goTo().groupPage();
      app.group().create(group);
    }

    Groups groups = app.db().groups();
    if ( app.db().contacts().size() == 0)
    {
      app.goTo().homePage();
      app.contact().create(new ContactData().withFirstname("Julio").withSurname("Berdys")
              .withHomeNumber("743534534").withEmail("please@gmail.com")
              .inGroup(groups.iterator().next()),true);
    }
  }



  //

  @Test
     public void removingContactFromGroup()
  {
           app.goTo().homePage();
           // Contacts before = app.db().contacts();  checking
           app.contact().clickGroupChoiceList();
           app.contact().clickFirstAvailableGroup();
           app.contact().selectContact();
           app.contact().clickRemoveFromGroup();
  }
}
