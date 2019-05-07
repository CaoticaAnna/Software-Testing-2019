package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import java.util.List;


public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void submitContactCreation() {
    click(By.name("submit"));
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getName());
    type(By.name("lastname"), contactData.getSurName());
    // type(By.name("address"), contactData.getAddress());
    //type(By.name("mobile"), contactData.getPhone());
    //type(By.name("email"), contactData.getEmail());
    // select(By.name("bday"), contactData.getDay());
    // select(By.name("bmonth"), contactData.getMonth());
    //type(By.name("byear"), contactData.getYear());
    //if (creation) {
    //select(By.name("new_group"), contactData.getGroup());
    //} else {
    //Assert.assertFalse(isElementPresent(By.name("new_group")));
    //}
  }

  public void initContactCreation() {
    click(By.linkText("add new"));
  }

  public void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }

  public void modify(ContactData contact) {
    selectContactById(contact.getId());
    initContactModificationById(contact.getId());
    fillContactForm((contact), false);
    submitContactModification();
    contactCache = null;
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    deleteSelectedContact();
    contactCache = null;
  }

  public void deleteSelectedContact() {
    click(By.xpath("//input[@value='Delete']"));
    closeAlert();
  }

  public void initContactModificationById(int id) {
   wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
  }

  public void submitContactModification() {
    click(By.name("update"));
  }

  public boolean isThereAContact() {
    return (isElementPresent(By.name("selected[]")));
  }

  public void create(ContactData contact, boolean b) {
    initContactCreation();
    fillContactForm(contact, b);
    submitContactCreation();
    contactCache = null;
  }

  public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();
  }

  private Contacts contactCache = null;

  public Contacts all() {
    // if (groupCache != null){
    //      return new Groups(groupCache);
    //    }
    //    groupCache = new Groups();

    if (contactCache != null){
      return new Contacts(contactCache);
    }
    contactCache = new Contacts();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    if (areElementsPresent(By.name("entry"))) {
      for (WebElement entry : elements) {
        List<WebElement> entries = entry.findElements(By.tagName("td"));
        int id = Integer.parseInt(entry.findElement(By.tagName("input")).getAttribute("value"));
        String name = entries.get(2).getText();
        String surName = entries.get(1).getText();
        contactCache.add(new ContactData().withId(id).withSurName(surName).withName(name));
      }
    }
      return contactCache;
    }
}

