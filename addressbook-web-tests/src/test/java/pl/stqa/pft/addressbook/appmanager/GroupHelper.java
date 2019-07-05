package pl.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pl.stqa.pft.addressbook.model.GroupData;
import pl.stqa.pft.addressbook.model.Groups;

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



  public void selectGroupById(int id) {

    wd.findElement(By.cssSelector("input[value='"+ id +"']")).click();

  }


  public void initGroupModification() {
    click(By.name("edit"));
  }

  public void submitGroupModification() {
    click(By.name("update"));
  }




  public void create(GroupData group) {
    initGroupCreation();
    fillGroupForm(group);
    submitGroupCreation();
    returntoGroupPage();

  }

  public void modify( GroupData group) {
    selectGroupById(group.getId());
    initGroupModification();
    fillGroupForm(group);
    submitGroupModification();
    returntoGroupPage();
  }

  public void delete(GroupData group) {
    selectGroupById(group.getId());
    deleteChoosenGroups();
    returntoGroupPage();
  }

  public int getGroupNumber() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public boolean isGroupExisting() {
    return isElementPresent(By.name("selected[]"));
  }

  public Groups all() {

    Groups groups = new Groups();
    List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
    for (WebElement element :elements)
    {
      String name = element.getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value")) ;
      GroupData group = new GroupData().withId(id).withName(name);
      groups.add(group);
    }
    return groups;
  }


}



