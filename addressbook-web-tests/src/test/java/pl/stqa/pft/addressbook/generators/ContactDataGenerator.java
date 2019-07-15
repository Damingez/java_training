package pl.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import pl.stqa.pft.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

  @Parameter(names = "-c", description = "contact count")
  public int count;

  @Parameter(names = "-f", description = "target file")
  public String file;

  @Parameter(names = "-d", description = "data format")
  public String format;


  public static void main (String[] args) throws IOException {

    ContactDataGenerator generator = new ContactDataGenerator();
    JCommander jcommander = new JCommander(generator);
    try {
      jcommander.parse(args);
    } catch (ParameterException ex){
      jcommander.usage();
      return;
    }
    generator.run();


  }

  private void run() throws IOException {
    List<ContactData> contacts = generateContacts(count);

     if (format.equals("xml"))
    {
      saveAsXml(contacts, new File(file));
    } else if (format.equals("json"))
    {
      saveAsJson(contacts, new File(file));
    }
    else {
      System.out.println("Format is not recognized " + format);
    }
  }

  private void saveAsJson(List<ContactData> contacts, File file) throws IOException {

    Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
    String json = gson.toJson(contacts);
  try (Writer writer = new FileWriter(file))
    {
      writer.write(json);
    }
  }

  private void saveAsXml(List<ContactData> contacts, File file) throws IOException {
    XStream xstream = new XStream();
    xstream.processAnnotations( ContactData.class);
    String xml = xstream.toXML(contacts);
   try ( Writer writer = new FileWriter(file))
   {
     writer.write(xml);
   }


  }


  private List<ContactData> generateContacts(int count)
  {
    List<ContactData> contacts = new ArrayList<ContactData>();

    for (int i =0; i < count; i++)
    {
      contacts.add( new ContactData()
              .withFirstname(String.format("Jara%s", i))
              .withSurname(String.format("Kowal%s", i))
              .withHomeNumber(String.format("%s49201", i))
       //    .withGroup(String.format("test %s" ,i))
      );
    }
    return contacts;
  }

}
