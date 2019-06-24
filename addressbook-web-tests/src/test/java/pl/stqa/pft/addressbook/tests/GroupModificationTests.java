package pl.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.GroupData;

public class GroupModificationTests extends TestBase {


  @Test

  public void testGroupModification ()
  {
    app.getNavigationHelper().gotoGroupPage();
      int before = app.getGroupHelper().getGroupNumber();
     if (! app.getGroupHelper().isGroupExisting())
    {
      app.getGroupHelper().createGroup(new GroupData("Franki","Kimonowskie","Januszowe"));
    }
    app.getGroupHelper().selectGroup(before - 1);
    app.getGroupHelper().initGroupModification();
    app.getGroupHelper().fillGroupForm(new GroupData("test0", "Emile", "dranskie"));
    app.getGroupHelper().submitGroupModification();
    app.getGroupHelper().returntoGroupPage();

    int after = app.getGroupHelper().getGroupNumber();

     Assert.assertEquals(after,before);
  }

}
