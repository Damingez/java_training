package pl.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class GroupRemoveTests extends TestBase {


  @Test
  public void testGroupRemoveTests() throws Exception {

    app.gotoGroupPage();
    app.selectGroup();
    app.deleteChoosenGroups();
    app.returntoGroupPage();
  }


}
