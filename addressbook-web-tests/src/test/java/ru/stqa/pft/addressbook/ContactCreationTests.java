package ru.stqa.pft.addressbook;

import org.testng.annotations.*;

public class ContactCreationTests extends TestBase{

  @Test
  public void testContactCreation() {
    initContactCreation();
    fillContactForm(new ContactData("Guseva", "Anna", "Novosibirsk, Russkaya St. 23 - 28", "89059586512", "anyuta34@yandex.ru", "18", "August", "1987"));
    submitContactCreation();
    returnToHomePage();
  }
}
