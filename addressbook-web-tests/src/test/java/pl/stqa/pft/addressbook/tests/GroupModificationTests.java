package pl.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.GroupData;
import pl.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;

public class GroupModificationTests extends TestBase {


  @BeforeMethod
  public void ensurePreconditions() {

    app.goTo().groupPage();

    if (app.group().all().size()==0) {
      app.group().create(new GroupData().withName("Franki").withHeader( "Kimonowskie").withFooter( "Januszowe"));
    }

  }

  @Test

  public void testGroupModification() {


    Groups before = app.group().all();
    GroupData modifiedGroup =  before.iterator().next();

    GroupData group = new GroupData()
            .withId(modifiedGroup.getId()).withName("test1").withHeader("test2").withFooter("test3");
    app.group().modify( group);

    Groups after = app.group().all();

    assertEquals(after.size(), before.size());

    assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));

  }


}
