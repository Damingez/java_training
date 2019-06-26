package pl.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class GroupDeletionTests extends TestBase {


  @Test
  public void testGroupRemoveTests() throws Exception {

    app.getNavigationHelper().gotoGroupPage();

    if (! app.getGroupHelper().isGroupExisting())
    {
      app.getGroupHelper().createGroup(new GroupData("Franki","Kimonowskie","Januszowe"));
    }
    List<GroupData> before = app.getGroupHelper().getGroupList();
    app.getGroupHelper().selectGroup(before.size()-1);
    app.getGroupHelper().deleteChoosenGroups();
    app.getGroupHelper().returntoGroupPage();

    List<GroupData> after = app.getGroupHelper().getGroupList();

    Assert.assertEquals(after.size(),before.size() - 1);

    before.remove(before.size()-1);
    Assert.assertEquals(before,after);


  }


}
