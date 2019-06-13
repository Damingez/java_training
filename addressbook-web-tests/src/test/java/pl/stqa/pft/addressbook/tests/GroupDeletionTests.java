package pl.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class GroupDeletionTests extends TestBase {


  @Test
  public void testGroupRemoveTests() throws Exception {

    app.getNavigationHelper().gotoGroupPage();
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().deleteChoosenGroups();
    app.getGroupHelper().returntoGroupPage();
  }


}
