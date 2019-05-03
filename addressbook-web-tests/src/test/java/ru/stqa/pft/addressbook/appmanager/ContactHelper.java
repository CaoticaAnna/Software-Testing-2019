package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import java.util.ArrayList;
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

  public void deleteSelectedContact() {
    click(By.xpath("//input[@value='Delete']"));
    closeAlert();
  }

  public void selectContact(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }

  public void initContactModification(int index) {
    wd.findElements(By.xpath("(//img[@alt='Edit'])")).get(index).click();
    }

  public void submitContactModification() {
    click(By.name("update"));
  }

  public boolean isThereAContact() {
    return (isElementPresent(By.name ("selected[]")));
  }

  public void createContact(ContactData contact, boolean b) {
    initContactCreation();
    fillContactForm(contact, b);
    submitContactCreation();
  }

  public int getContactCount() {
    return wd.findElements(By.name ("selected[]")).size();
  }

  public List<ContactData> getContactList() {
    List<ContactData> contacts = new ArrayList<ContactData>();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    if (areElementsPresent(By.name("entry"))) {
      for (WebElement entry : elements) {
               List<WebElement> entries = entry.findElements(By.tagName("td"));
               int id = Integer.parseInt(entry.findElement(By.tagName("input")).getAttribute("value"));
               String name = entries.get(2).getText();
               String surName = entries.get(1).getText();
        ContactData contact = new ContactData(id,surName, name);
        contacts.add(contact);
      }
    }
      return contacts;
    }
  }

