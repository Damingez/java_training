package pl.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.GroupData;

public class GroupDeletionTests extends TestBase {


  @Test
  public void testGroupRemoveTests() throws Exception {

    app.getNavigationHelper().gotoGroupPage();
    int before = app.getGroupHelper().getGroupNumber();
    if (! app.getGroupHelper().isGroupExisting())
    {
      app.getGroupHelper().createGroup(new GroupData("Franki","Kimonowskie","Januszowe"));
    }
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().deleteChoosenGroups();
    app.getGroupHelper().returntoGroupPage();

    int after = app.getGroupHelper().getGroupNumber();

    Assert.assertEquals(after,before - 1);
  }


}
