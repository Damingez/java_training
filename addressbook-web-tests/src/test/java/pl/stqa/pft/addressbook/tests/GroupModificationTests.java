package pl.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.GroupData;
import pl.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class GroupModificationTests extends TestBase {


  @BeforeMethod
  public void ensurePreconditions() {

    if (app.db().groups().size()== 0)
    {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("Franki")
              .withHeader( "Kimonowskie")
              .withFooter( "Januszowe"));
    }

  }

  @Test

  public void testGroupModification() {

    Groups before = app.db().groups();
    GroupData modifiedGroup =  before.iterator().next();
    GroupData group = new GroupData()
            .withId(modifiedGroup.getId()).withName("test1").withHeader("test2").withFooter("test3");
    app.goTo().groupPage();
    app.group().modify( group);
    assertThat(app.group().count(), CoreMatchers.equalTo(before.size() ));
    Groups after = app.db().groups();
   // assertEquals(after.size(), before.size());
    assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));

    verifyGroupListInUI();

  }




}
