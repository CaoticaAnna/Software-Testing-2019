package ru.stqa.pft.rest.tests;
import org.testng.SkipException;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.rest.appmanager.ApplicationManager;

import java.io.IOException;

public class TestBase {

  protected static final ApplicationManager app
          = new ApplicationManager();

  @BeforeSuite(alwaysRun = true)
  public void setUp() throws Exception{
    app.init();
  }

  boolean isIssueOpen(int issueId) throws IOException {
     String status = app.rest().getStatus(issueId);
        if (!status.equals("Resolved")) {
          return true;
        }
        return false;
      }


  public void skipIfNotFixed(int issueId) throws IOException {
    if (isIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }
}
