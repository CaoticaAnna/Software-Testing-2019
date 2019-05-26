package ru.stqa.pft.addressbook.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.List;

public class DbHelper {

  private final SessionFactory sessionFactory;

  public DbHelper() {
    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure()
            .build();
    sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
  }

  public Groups groups() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<GroupData> result = session.createQuery("from GroupData").list();
    session.getTransaction().commit();
    session.close();
    return new Groups(result);
  }

  public Contacts contacts() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<ContactData> result = session.createQuery("from ContactData where deprecated = '0000-00-00'").list();
    session.getTransaction().commit();
    session.close();
    return new Contacts(result);
  }

  public Contacts contactsWithGroups() {
     Session session = sessionFactory.openSession();
          session.beginTransaction();

          List<ContactData> result = session.createQuery("from ContactData where deprecated = '0000-00-00'").list();
          session.getTransaction().commit();
          session.close();
          for (ContactData contact : result) {
           contact.getGroups();
          }
          return new Contacts(result);
  }
}

//public Contacts all() {
//    if (contactCache != null){
//      return new Contacts(contactCache);
//    }
//    contactCache = new Contacts();
//    List<WebElement> elements = wd.findElements(By.name("entry"));
//    if (areElementsPresent(By.name("entry"))) {
//      for (WebElement entry : elements) {
//        List<WebElement> entries = entry.findElements(By.tagName("td"));
//        int id = Integer.parseInt(entries.get(0).findElement(By.tagName("input")).getAttribute("value"));
//        String name = entries.get(2).getText();
//        String surName = entries.get(1).getText();
//        String allPhones = entries.get(5).getText();
//        String address = entries.get(3).getText();
//        String email = entries.get(4).getText();
//        contactCache.add(new ContactData().withId(id).withSurName(surName).withName(name)
//                .withAllPhones(allPhones)
//                .withAddress(address).withAllEmails(email));
//      }
//    }
//      return contactCache;
//    }

