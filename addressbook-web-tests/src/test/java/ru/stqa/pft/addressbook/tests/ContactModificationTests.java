package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    if (app.db().contacts().size() == 0){
      app.contact().create(new ContactData().withSurName("Guseva").withName("Anna").withHomePhone("+1234").withMobilePhone("+7(342)456").withWorkPhone("8-6-6-6")
              .withAddress("Moscow, Lenina St. 51, kv. 12"),true);
    }
  }

  @Test()
  public void testContactModification(){
    Contacts before = app.db().contacts();
    app.goTo().homePage();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData()
            .withId(modifiedContact.getId()).withSurName("Satarov").withName("Anton")
            .withHomePhone("+1234").withMobilePhone("+7(342)456").withWorkPhone("8-6-6-6")
            .withAddress("Moscow, Lenina St. 51, kv. 12");
    app.contact().modify(contact);
    app.goTo().homePage();
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
  }
}