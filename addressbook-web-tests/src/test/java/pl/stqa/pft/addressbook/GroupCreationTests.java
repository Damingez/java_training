package pl.stqa.pft.addressbook;

import org.testng.annotations.Test;

public class GroupCreationTests extends TestBase {


  @Test
  public void testGroupCreation() throws Exception {
    gotoGroupPage();
    initGroupCreation();
    fillGroupForm(new GroupData("test0", "needed", "good"));
    submitGroupCreation();
    returntoGroupPage();
  //  wd.findElement(By.linkText("Logout")).click();
  }


}
