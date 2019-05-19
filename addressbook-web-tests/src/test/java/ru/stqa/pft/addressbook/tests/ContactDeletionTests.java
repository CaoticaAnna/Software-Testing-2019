package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;


public class ContactDeletionTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions(){
    if (app.db().contacts().size() == 0){
      app.contact().create(new ContactData().withSurName("Guseva").withName("Anna").withHomePhone("+1234").withMobilePhone("+7(342)456").withWorkPhone("8-6-6-6")
              .withAddress("Moscow, Lenina St. 51, kv. 12"),true);
    }
  }

  @Test()
  public void testContactDeletion() throws InterruptedException {
    Contacts before = app.db().contacts();
    app.goTo().homePage();
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    app.sleep();
    app.goTo().homePage();
    assertThat(app.contact().count(), equalTo(before.size() - 1));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.without(deletedContact)));
    verifyContactListUi();
  }
}
