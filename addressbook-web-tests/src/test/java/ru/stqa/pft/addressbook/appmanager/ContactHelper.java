package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase{

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void submitContactCreation() {
    click(By.name("submit"));
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getName());
    type(By.name("lastname"), contactData.getSurName());
    type(By.name("address"), contactData.getAddress());
    type(By.name("mobile"), contactData.getPhone());
    type(By.name("email"), contactData.getEmail());
    select(By.name("bday"), contactData.getDay());
    select(By.name("bmonth"), contactData.getMonth());
    type(By.name("byear"), contactData.getYear());

    if (creation) {
      select(By.name("new_group"), contactData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
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

  public void initContactModification() {
    click(By.xpath("//*[@id=\"maintable\"]/tbody/tr[2]/td[8]/a"));
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
    List<WebElement> elements = (new WebDriverWait(wd,600)).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.name("entry")));
    for (WebElement element : elements) {
      String name = element.getText();
      ContactData contact = new ContactData(name, null, null, null, null, null, null, null, null);
      contacts.add(contact);
    }
    return contacts;
  }
}

