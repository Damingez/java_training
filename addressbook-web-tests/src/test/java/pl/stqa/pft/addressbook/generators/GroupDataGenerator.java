package pl.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import pl.stqa.pft.addressbook.model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class GroupDataGenerator {

  @Parameter(names = "-c", description = "group count")
  public int count;

  @Parameter(names = "-f", description = "target file")
  public String file;

  @Parameter(names = "-d", description = "data format")
  public String format;


    public static void main (String[] args) throws IOException {

   GroupDataGenerator generator = new GroupDataGenerator();
   JCommander jcommander = new JCommander(generator);
    try {
      jcommander.parse(args);
    } catch (ParameterException ex){
        jcommander.usage();
        return;
    }
      generator.run();
    // int count = Integer.parseInt(args[0]);
     // File file = new File(args[1]);

    }

  private void run() throws IOException {
    List<GroupData> groups = generateGroups(count);

    if (format.equals("csv")){
      saveAsCsv(groups, new File(file));
    } else if (format.equals("xml"))
    {
      saveAsXml(groups, new File(file));
    } else if (format.equals("json"))
    {
      saveAsJson(groups, new File(file));
    }
    else {
      System.out.println("Format is not recognized " + format);
    }
  }

  private void saveAsJson(List<GroupData> groups, File file) throws IOException {

    Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
    String json = gson.toJson(groups);
   try ( Writer writer = new FileWriter(file) )
   {
     writer.write(json);
   }
  }

  private void saveAsXml(List<GroupData> groups, File file) throws IOException {
    XStream xstream = new XStream();
    xstream.processAnnotations( GroupData.class);
    String xml = xstream.toXML(groups);
   try (Writer writer = new FileWriter(file))
   {
     writer.write(xml);
   }
  }


  private  void saveAsCsv(List<GroupData> groups, File file) throws IOException {
      System.out.println(new File(".").getAbsolutePath());

     try (Writer writer = new FileWriter(file)) {
       for (GroupData group : groups) {
         writer.write(String.format("%s;%s;%s\n", group.getName(), group.getHeader(), group.getFooter()));
       }
     }
    }

    private  List<GroupData> generateGroups (int count)
    {
      List<GroupData> groups = new ArrayList<GroupData>();

      for (int i =0; i < count; i++)
      {
        groups.add( new GroupData()
                .withName(String.format("test %s", i))
                .withHeader(String.format("header \n %s", i))
                .withFooter(String.format("footer \n %s", i)));
      }
      return groups;
    }

}
