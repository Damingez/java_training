package pl.stqa.pft.addressbook.tests;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.GroupData;
import pl.stqa.pft.addressbook.model.Groups;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class GroupCreationTests extends TestBase {



  @DataProvider
  public Iterator <Object[]> validGroupsJson() throws IOException {
        List<Object[]> list = new ArrayList<Object[]>();
    try (BufferedReader reader = new BufferedReader(new FileReader( new File("src/test/resources/groups.json"))))
    {
      String line = reader.readLine();
      String json = "";
      while (line != null)
      {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<GroupData> groups = gson.fromJson(json, new TypeToken<List<GroupData>>(){}.getType()); // List <GroupData>.class

      return groups.stream().map((g)-> new Object[] {g} ).collect(Collectors.toList()).iterator();
    }


  }

  @DataProvider
  public Iterator <Object[]> validGroupsXml() throws IOException {
    List<Object[]> list = new ArrayList<Object[]>();
    try (BufferedReader reader = new BufferedReader(new FileReader( new File("src/test/resources/groups.xml"))))
    {
      String line = reader.readLine();
      String xml = "";
      while (line != null)
      {
        xml += line;
        line = reader.readLine();
      }
      XStream xStream = new XStream();
      xStream.processAnnotations(GroupData.class);
      List<GroupData> groups = (List<GroupData>) xStream.fromXML(xml);
      return groups.stream().map((g)-> new Object[] {g} ).collect(Collectors.toList()).iterator();
    }

  }

  @Test (dataProvider = "validGroupsJson")
  public void testGroupCreation(GroupData group)  {


    app.goTo().groupPage();
    Groups before = app.db().groups();
    app.group().create(group);

    assertThat(app.group().count(), equalTo(before.size() + 1));
    Groups after = app.db().groups();

    assertThat(after, equalTo(
            before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));

    verifyGroupListInUI();
  }


  @Test (enabled = false)
  public void testBadGroupCreation()  {
    app.goTo().groupPage();
    Groups before = app.db().groups();
    GroupData group = new GroupData().withName("testowo'");
    app.group().create(group);
    assertThat(app.group().count(), equalTo(before.size()  ));
    Groups after = app.db().groups();
    assertThat(after, equalTo(before));

  }

}
