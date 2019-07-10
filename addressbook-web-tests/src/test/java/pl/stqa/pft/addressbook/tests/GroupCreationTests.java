package pl.stqa.pft.addressbook.tests;

import org.omg.CORBA.Object;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.GroupData;
import pl.stqa.pft.addressbook.model.Groups;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class GroupCreationTests extends TestBase {


  @DataProvider
  public Iterator <Object[]> validGroups() throws IOException {
    List<Object[]> list = new ArrayList<Object[]>();
    BufferedReader reader = new BufferedReader(new FileReader( new File("src/test/resources/groups.csv")));
    String line = reader.readLine();

    while (line!=null)
    {
      String[] split = line.split(";");
      list.add( new  Object[] {new GroupData().withName(split[0]).withHeader(split[1]).withFooter(split[2])});

    }

    return list.iterator();
  }

  @Test (dataProvider = "validGroups")
  public void testGroupCreation() throws Exception {
    app.goTo().groupPage();
    Groups before = app.group().all();

    GroupData group = new GroupData().withName("test3");
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
