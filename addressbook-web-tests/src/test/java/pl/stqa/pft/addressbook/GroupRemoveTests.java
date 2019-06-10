package pl.stqa.pft.addressbook;

import org.testng.annotations.Test;

public class GroupRemoveTests extends TestBase {


  @Test
  public void testGroupRemoveTests() throws Exception {

    gotoGroupPage();
    selectGroup();
    deleteChoosenGroups();
    returntoGroupPage();
  }


}
