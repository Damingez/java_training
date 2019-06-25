package pl.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.GroupData;

public class GroupDeletionTests extends TestBase {


  @Test
  public void testGroupRemoveTests() throws Exception {

    app.getNavigationHelper().gotoGroupPage();

    if (! app.getGroupHelper().isGroupExisting())
    {
      app.getGroupHelper().createGroup(new GroupData("Franki","Kimonowskie","Januszowe"));
    }
    int before = app.getGroupHelper().getGroupNumber();
    app.getGroupHelper().selectGroup(before-1);
    app.getGroupHelper().deleteChoosenGroups();
    app.getGroupHelper().returntoGroupPage();

    int after = app.getGroupHelper().getGroupNumber();

    Assert.assertEquals(after,before - 1);
  }


}
