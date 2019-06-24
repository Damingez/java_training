package pl.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase {


  @Test
  public void testGroupCreation() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    int before = app.getGroupHelper().getGroupNumber();
    app.getGroupHelper().createGroup(new GroupData("test0", "Emile", "dranskie"));
    int after = app.getGroupHelper().getGroupNumber();
    Assert.assertEquals(after,before+1);


  }


}
