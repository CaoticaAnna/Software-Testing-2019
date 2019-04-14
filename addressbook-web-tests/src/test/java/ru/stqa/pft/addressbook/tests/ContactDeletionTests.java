package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;

public class ContactDeletionTests extends TestBase{

  @Test
  public void testContactDeletion() {
    app.selectContact();
    app.deleteSelectedContact();
    app.returnToHomePage();
  }

}
