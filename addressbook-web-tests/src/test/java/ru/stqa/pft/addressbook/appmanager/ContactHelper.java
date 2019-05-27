package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
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
    type(By.name("mobile"), contactData.getMobilePhone());
    type(By.name("home"), contactData.getHomePhone());
    type(By.name("work"), contactData.getWorkPhone());
    type(By.name("address"), contactData.getAddress());
    type(By.name("email"), contactData.getEmail());
    //attach(By.name("photo"), contactData.getPhoto());
    // select(By.name("bday"), contactData.getDay());
    // select(By.name("bmonth"), contactData.getMonth());
    //type(By.name("byear"), contactData.getYear());
  }

  public void fillContactFormWithGroup(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getName());
    type(By.name("lastname"), contactData.getSurName());
    type(By.name("mobile"), contactData.getMobilePhone());
    type(By.name("home"), contactData.getHomePhone());
    type(By.name("work"), contactData.getWorkPhone());
    type(By.name("address"), contactData.getAddress());
    type(By.name("email"), contactData.getEmail());
    //attach(By.name("photo"), contactData.getPhoto());
    // select(By.name("bday"), contactData.getDay());
    // select(By.name("bmonth"), contactData.getMonth());
    //type(By.name("byear"), contactData.getYear());
    if (creation) {
      if (contactData.getGroups().size() > 0) {
        Assert.assertTrue(contactData.getGroups().size() == 1);
        new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups().iterator().next().getName());
      } else {
        Assert.assertFalse(isElementPresent(By.name("new_group")));
      }
    }
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

  public void selectToGroupById(int id){
    new Select(wd.findElement(By.name("to_group"))).selectByValue(String.valueOf(id));
  }
  public void selectGroupById(int id){
    new Select(wd.findElement(By.name("group"))).selectByValue(String.valueOf(id));
  }

  public void addToGroup(ContactData contact, GroupData groups){
    selectToGroupById(groups.getId());
    selectContactById(contact.getId());
    click(By.name("add"));
  }

  public void deleteFromGroup(ContactData contact, GroupData groups){
    selectGroupById(groups.getId());
    selectContactById(contact.getId());
    click(By.name("remove"));
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

  public void createWithGroup(ContactData contact, boolean b) {
    initContactCreation();
    fillContactFormWithGroup(contact, b);
    submitContactCreation();
    contactCache = null;
  }

  public int count() {
    return wd.findElements(By.name("selected[]")).size();
  }

  private Contacts contactCache = null;

  public Contacts all() {
    if (contactCache != null){
      return new Contacts(contactCache);
    }
    contactCache = new Contacts();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    if (areElementsPresent(By.name("entry"))) {
      for (WebElement entry : elements) {
        List<WebElement> entries = entry.findElements(By.tagName("td"));
        int id = Integer.parseInt(entries.get(0).findElement(By.tagName("input")).getAttribute("value"));
        String name = entries.get(2).getText();
        String surName = entries.get(1).getText();
        String allPhones = entries.get(5).getText();
        String address = entries.get(3).getText();
        String email = entries.get(4).getText();
        contactCache.add(new ContactData().withId(id).withSurName(surName).withName(name)
                .withAllPhones(allPhones)
                .withAddress(address).withAllEmails(email));
      }
    }
      return contactCache;
    }

  public ContactData infoFromEditForm(ContactData contact) {
    initContactModificationById(contact.getId());
    String name = wd.findElement(By.name("firstname")).getAttribute("value");
    String surName = wd.findElement(By.name("lastname")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getAttribute("value");
    String email = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");
    wd.navigate().back();
    return new ContactData().withId(contact.getId()).withName(name).withSurName(surName)
            .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work)
            .withAddress(address)
            .withEmail(email).withEmail2(email2).withEmail3(email3);
  }
}

