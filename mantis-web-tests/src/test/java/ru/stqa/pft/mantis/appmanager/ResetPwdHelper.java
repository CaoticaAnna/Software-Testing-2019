package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

import java.io.IOException;

public class ResetPwdHelper extends HelperBase {
  public ResetPwdHelper(ApplicationManager app) {
    super(app);
  }

  public void loginAsAdmin() throws IOException {
    wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
    type(By.name("username"), "administrator");
    type(By.name("password"), "root");
    click(By.cssSelector("input[value='Login']"));
  }
}
