package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;
import ru.stqa.pft.mantis.model.Users;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ResetPasswordTest extends TestBase{

  @BeforeMethod
  public void startMailServer(){
    app.mail().start();
  }

  @Test
  public void resetPasswordTest() throws IOException, MessagingException {
    app.resetPwdHelper().loginAsAdmin();
    Users users = app.db().users();
    UserData chosenUser = users.stream().filter((u) -> u.getAccess_level().contains("25")).findFirst().get();
    String password = "pwd";
    app.goTo().manageUsers();
    app.userHelper().resetPwd(chosenUser);
    List<MailMessage> mailMessages = app.mail().waitForMail(1, 1000);
    String confirmationLink = findConfirmationLink(mailMessages, chosenUser.getEmail());
    app.registration().finish(confirmationLink, password);
    assertTrue(app.newSession().login(chosenUser.getUsername(), password));
  }

  private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regexp = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regexp.getText(mailMessage.text);

  }


  @AfterMethod(alwaysRun = true)
  public void stopMailServer() {
    app.mail().stop();
  }
}
