package pl.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pl.stqa.pft.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

  public void selectGroupById(int id) {

    wd.findElement(By.cssSelector("input[value='"+ id +"']")).click();

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

  public void create(GroupData group) {
    initGroupCreation();
    fillGroupForm(group);
    submitGroupCreation();
    returntoGroupPage();

  }

  public void modify(int index, GroupData group) {
    selectGroup(index);
    initGroupModification();
    fillGroupForm(group);
    submitGroupModification();
    returntoGroupPage();
  }

  public void delete(int index) {
    selectGroup(index);
    deleteChoosenGroups();
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

  public List<GroupData> list() {

    List<GroupData> groups = new ArrayList<GroupData>();
    List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
     for (WebElement element :elements)
     {
       int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value")) ;
       String name = element.getText();
       GroupData group = new GroupData().withId(id).withName(name);
       groups.add(group);
     }

    return groups;
  }


  public Set<GroupData> all() {

    Set<GroupData> groups = new HashSet<GroupData>();
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



