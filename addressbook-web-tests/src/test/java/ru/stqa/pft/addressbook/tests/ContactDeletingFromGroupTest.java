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

public class ContactDeletingFromGroupTest extends TestBase{

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
  public void testContactDeletingFromGroup() {
    app.goTo().homePage();
    Contacts contacts = app.db().contacts();
    ContactData chosenContact = contacts.iterator().next();
    int contactId = chosenContact.getId();
    Groups before = chosenContact.getGroups();
    if(before.size() == 0){
      GroupData chosenGroup = app.db().groups().iterator().next();
      app.contact().addToGroup(chosenContact, chosenGroup);
      app.goTo().homePage();
      ContactData contactInGroup = chosenContact.inGroup(chosenGroup).withId(contactId);
      Groups beforeAction = contactInGroup.getGroups();
      GroupData addedGroup = beforeAction.iterator().next();
      app.contact().deleteFromGroup(contactInGroup, addedGroup);
      ContactData afterContact = app.db().contacts().iterator().next().withId(contactId);
      Groups after = afterContact.getGroups();
      assertThat(after, equalTo(
              beforeAction.without(addedGroup)));
    }
    else{
    GroupData chosenGroup = before.iterator().next();
    app.contact().deleteFromGroup(chosenContact, chosenGroup);
    ContactData afterContact = app.db().contacts().iterator().next().withId(contactId);
    Groups after = afterContact.getGroups();
    assertThat(after, equalTo(
            before.without(chosenGroup)));}
    app.goTo().homePage();
    verifyContactListUi();
  }
}
