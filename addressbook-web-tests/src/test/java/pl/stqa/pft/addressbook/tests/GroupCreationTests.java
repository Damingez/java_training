package pl.stqa.pft.addressbook.tests;


import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.GroupData;
import pl.stqa.pft.addressbook.model.Groups;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class GroupCreationTests extends TestBase {


  @DataProvider
  public Iterator <Object[]> validGroups() throws IOException {
    List<Object[]> list = new ArrayList<Object[]>();
    BufferedReader reader = new BufferedReader(new FileReader( new File("src/test/resources/groups.xml")));
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

  @Test (dataProvider = "validGroups")
  public void testGroupCreation(GroupData group)  {
    app.goTo().groupPage();
    Groups before = app.group().all();
    app.group().create(group);

    assertThat(app.group().count(), equalTo(before.size() + 1));
    Groups after = app.group().all();

    assertThat(after, equalTo(
            before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }


  @Test (enabled = false)
  public void testBadGroupCreation() throws Exception {
    app.goTo().groupPage();
    Groups before = app.group().all();
    GroupData group = new GroupData().withName("testowo'");
    app.group().create(group);
    assertThat(app.group().count(), equalTo(before.size()  ));
    Groups after = app.group().all();
    assertThat(after, equalTo(before));

  }


}
