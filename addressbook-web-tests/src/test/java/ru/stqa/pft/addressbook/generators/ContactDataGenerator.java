package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.solidfire.gson.Gson;
import com.solidfire.gson.GsonBuilder;
import ru.stqa.pft.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

  @Parameter(names = "-c", description = "Contact count")
  public int count;

  @Parameter(names = "-f", description = "Target file")
  public String file;

  @Parameter(names = "-d", description = "Data format")
  public String format;

  public static void main(String[] args) throws IOException {
    ContactDataGenerator generator = new ContactDataGenerator();
    JCommander jCommander = new JCommander(generator);
    try {
            jCommander.parse(args);
          } catch (ParameterException ex) {
            jCommander.usage();
            return;
          }
    generator.run();
      }

  private void run() throws IOException {
    List<ContactData> contacts = generateContacts(count);
    if (format.equals("csv")) {
    saveAsCsv(contacts, new File (file));
  } else if (format.equals("json")) {
      saveAsJson(contacts, new File (file));
    } else {
      System.out.println("Unrecognized format " + format);
    }
  }

    private void saveAsCsv(List<ContactData> contacts, File file) throws IOException {
      System.out.println(new File(".").getAbsolutePath());
      Writer writer = new FileWriter(file);
      for (ContactData contact : contacts){
        writer.write(String.format("%s;%s\n", contact.getName(), contact.getSurName()));
      }
      writer.close();
    }

  private void saveAsJson(List<ContactData> contacts, File file) throws IOException {
    Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
    String json = gson.toJson(contacts);
    Writer writer = new FileWriter(file);
    writer.write(json);
    writer.close();
  }

    private List<ContactData> generateContacts(int count) {
      List<ContactData> contacts = new ArrayList<ContactData>();
      for (int i = 0; i < count; i++){
        contacts.add(new ContactData().withName(String.format("Anna %s", i))
        .withSurName(String.format("Guseva %s", i)).withHomePhone("+1234").withMobilePhone("+7(342)456").withWorkPhone("8-6-6-6")
        .withAddress(String.format("Moscow, Lenina St. 51, kv. 12")));
      }
      return contacts;
    }
}
