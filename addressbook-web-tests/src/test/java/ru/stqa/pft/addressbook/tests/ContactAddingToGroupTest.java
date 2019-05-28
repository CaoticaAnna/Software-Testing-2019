package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddingToGroupTest extends TestBase{

  @BeforeMethod
  public void ensurePreconditions(){
    if (app.db().contacts().size() == 0){
      app.contact().create(new ContactData().withSurName("Guseva").withName("Anna").withHomePhone("+1234").withMobilePhone("+7(342)456").withWorkPhone("8-6-6-6")
              .withAddress("Moscow, Lenina St. 51, kv. 12"),true);
    }
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1"));
    }
  }

  @Test()
  public void testContactAddingToGroup() {
    app.goTo().homePage();
    Contacts contacts = app.db().contacts();
    Groups groups = app.db().groups();
    ContactData chosenContact = contacts.iterator().next();
    int contactId = chosenContact.getId();
    GroupData chosenGroup = groups.iterator().next();
    Groups before = chosenContact.getGroups();
    if(before.size() == groups.size()){
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1"));
      Groups newGroups = app.db().groups();
      newGroups.removeAll(before);
      GroupData newGroup = newGroups.iterator().next();
      app.goTo().homePage();
      app.contact().addToGroup(chosenContact, newGroup);
      ContactData newContact = app.db().contacts().iterator().next().inGroup(chosenGroup).withId(contactId);
      Groups afterAction = newContact.getGroups();
      assertThat(afterAction, equalTo(
            before.withAdded(newGroup)));}
    else if(before.size() != 0){
    groups.removeAll(before);
    GroupData filteredGroup = groups.iterator().next();
    app.contact().addToGroup(chosenContact, filteredGroup);
    ContactData afterContact = app.db().contacts().iterator().next().inGroup(chosenGroup).withId(contactId);
    Groups after = afterContact.getGroups();
    assertThat(after, equalTo(
              before.withAdded(filteredGroup)));
    }
      else {
    app.contact().addToGroup(chosenContact, chosenGroup);
    ContactData afterContact = app.db().contacts().iterator().next().inGroup(chosenGroup).withId(contactId);
    Groups after = afterContact.getGroups();
    assertThat(after, equalTo(
              before.withAdded(chosenGroup)));
    }
    app.goTo().homePage();
    verifyContactListUi();
  }
}
