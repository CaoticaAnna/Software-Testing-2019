package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification(){
    int before = app.getContactHelper().getContactCount();
    if (! app.getContactHelper().isThereAContact()){
      app.getContactHelper().createContact(new ContactData("Guseva", "Anna", "Novosibirsk, Russkaya St. 23 - 28", "89059586512", "anyuta34@yandex.ru", "18", "August", "1987", "test1"),true);
    }
    app.getNavigationHelper().returnToHomePage();
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData("Guseva", "Anastasia", "Novosibirsk, Sirenevaya St. 31 - 135", "89059586719", "anastasia@yandex.ru", "26", "January", "1992", null), false);
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().returnToHomePage();
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after, before);
  }
}
