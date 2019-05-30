package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import ru.stqa.pft.mantis.model.UserData;

public class UserHelper extends HelperBase {

  public UserHelper(ApplicationManager app) {
    super(app);
  }

  public void selectUserById(int id) {
    click(By.cssSelector(String.format("a[href='manage_user_edit_page.php?user_id=%s']", id)));
  }

  public void resetPwd(UserData user){
    selectUserById(user.getId());
    click(By.xpath("//input[@value='Reset Password']"));
  }
}
