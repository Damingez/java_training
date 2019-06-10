package pl.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.GroupData;

public class GroupCreationTests extends TestBase {


  @Test
  public void testGroupCreation() throws Exception {
    app.gotoGroupPage();
    app.initGroupCreation();
    app.fillGroupForm(new GroupData("test0", "needed", "good"));
    app.submitGroupCreation();
    app.returntoGroupPage();
  //  wd.findElement(By.linkText("Logout")).click();
  }


}
