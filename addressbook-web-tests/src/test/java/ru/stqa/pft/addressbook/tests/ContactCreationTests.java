package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase{

    @DataProvider
    public Iterator<Object[]> validContacts() throws IOException {
      List<Object[]> list = new ArrayList<Object[]>();
      BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.csv")));
      String line = reader.readLine();
      while(line != null){
        String[] split = line.split(";");
        list.add(new Object[] {new ContactData().withName(split[0]).withSurName(split[1])});
        line = reader.readLine();
      }
      return list.iterator();
    }

  @Test(dataProvider = "validContacts")
  public void testContactCreation(ContactData contact) {
    Contacts before = app.contact().all();
    //File photo = new File("src/test/resources/stru.png");
    //ContactData contact = new ContactData().withSurName("Guseva").withName("Anna")
      //      .withHomePhone("+1234").withMobilePhone("+7(342)456").withWorkPhone("8-6-6-6")
        //    .withAddress("Moscow, Lenina St. 51, kv. 12").withPhoto(photo);
    app.contact().create((contact),true);
    app.goTo().homePage();
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }


}
