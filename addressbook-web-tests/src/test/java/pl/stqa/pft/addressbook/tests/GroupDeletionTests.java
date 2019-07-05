package pl.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.GroupData;
import pl.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupDeletionTests extends TestBase {


  @BeforeMethod
  public void ensurePreconditions() {

    app.goTo().groupPage();

    if (app.group().all().size()==0) {
      app.group().create(new GroupData().withName("test1"));
    }

  }

  @Test
  public void testGroupRemoveTests() throws Exception {

    Groups before = app.group().all();
    GroupData deletedGroup =  before.iterator().next();
    //int index= before.size()-1;
    app.group().delete(deletedGroup);
    assertThat(app.group().count(), equalTo(before.size() - 1));
    Groups after = app.group().all();

   // Assert.assertEquals(after.size(),before.size() - 1);
    assertThat(after, equalTo(before.without(deletedGroup)));


  }



}
