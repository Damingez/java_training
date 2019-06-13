package pl.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.GroupData;

public class GroupModificationTests extends TestBase {


  @Test

  public void testGroupModification ()
  {
    app.getNavigationHelper().gotoGroupPage();
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().initGroupModification();
    app.getGroupHelper().fillGroupForm(new GroupData("Rebels","brave","questions"));
    app.getGroupHelper().submitGroupModification();
    app.getGroupHelper().returntoGroupPage();

  }

}
