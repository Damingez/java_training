package pl.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class GroupModificationTests extends TestBase {


  @Test

  public void testGroupModification ()
  {
    app.getNavigationHelper().gotoGroupPage();

     if (! app.getGroupHelper().isGroupExisting())
    {
      app.getGroupHelper().createGroup(new GroupData("Franki","Kimonowskie","Januszowe"));
    }
    List<GroupData> before = app.getGroupHelper().getGroupList();
    app.getGroupHelper().selectGroup(before.size() - 1);
    app.getGroupHelper().initGroupModification();
    app.getGroupHelper().fillGroupForm(new GroupData("test0", "Emile", "dranskie"));
    app.getGroupHelper().submitGroupModification();
    app.getGroupHelper().returntoGroupPage();

    List<GroupData> after = app.getGroupHelper().getGroupList();

     Assert.assertEquals(after.size(),before.size());
  }

}
