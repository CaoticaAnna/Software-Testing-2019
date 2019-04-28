package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;


public class ContactDeletionTests extends TestBase{

  @Test
  public void testContactDeletion() throws InterruptedException {
    if (! app.getContactHelper().isThereAContact()){
      app.getContactHelper().createContact(new ContactData("Guseva", "Anna", "Novosibirsk, Russkaya St. 23 - 28", "89059586512", "anyuta34@yandex.ru", "18", "August", "1987", "test1"),true);
    }
    app.getNavigationHelper().returnToHomePage();
    app.sleep();
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContact(before.size() - 1);
    app.getContactHelper().deleteSelectedContact();
    app.getNavigationHelper().returnToHomePage();
    app.sleep();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() - 1);
  }
}
