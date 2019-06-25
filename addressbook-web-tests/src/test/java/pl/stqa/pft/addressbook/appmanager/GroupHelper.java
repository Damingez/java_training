package pl.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pl.stqa.pft.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;

public class GroupHelper extends HelperBase {

  public GroupHelper(WebDriver wd) {
    super(wd);
  }

  public void returntoGroupPage() {
    click(By.linkText("groups"));
  }

  public void submitGroupCreation() {
    click(By.name("submit"));
  }

  public void fillGroupForm(GroupData group) {
    type(By.name("group_name"), group.getName());
    type(By.name("group_header"), group.getHeader());
    type(By.name("group_footer"), group.getFooter());
  }

  public void initGroupCreation() {
    click(By.name("new"));
  }

  public void deleteChoosenGroups() {
    click(By.name("delete"));
  }

  public void selectGroup(int index) {

    wd.findElements(By.name("selected[]")).get(index).click();

  }


  public void initGroupModification() {
    click(By.name("edit"));
  }

  public void submitGroupModification() {
    click(By.name("update"));
  }


  public boolean isGroupExisting() {
    return isElementPresent(By.name("selected[]"));
  }

  public void createGroup(GroupData group) {
    initGroupCreation();
    fillGroupForm(group);
    submitGroupCreation();
    returntoGroupPage();

  }

  public int getGroupNumber() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public List<GroupData> getGroupList() {

    List<GroupData> groups = new ArrayList<GroupData>();
    List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
     for (WebElement element :elements)
     {
       String name = element.getText();
       GroupData group = new GroupData(name,null,null);
       groups.add(group);
     }

    return groups;
  }
}

 /*
    initGroupCreation();
    fillGroupForm(new GroupData("test0", "Emile", "dranskie"));
    submitGroupCreation();
    returntoGroupPage();

  */