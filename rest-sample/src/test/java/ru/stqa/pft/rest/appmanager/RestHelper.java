package ru.stqa.pft.rest.appmanager;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import ru.stqa.pft.rest.model.Issue;

import java.io.IOException;
import java.util.Set;


public class RestHelper {
  private ApplicationManager app;

  public RestHelper(ApplicationManager app) {
    this.app = app;
  }

  public Executor getExecutor() {
    return Executor.newInstance().auth(app.getProperty("rest.username"), app.getProperty("rest.password"));
  }

  public int createIssue(Issue newIssue) throws IOException {
    String json = getExecutor().execute(Request.Post(app.getProperty("rest.path"))
            .bodyForm(new BasicNameValuePair("subject", newIssue.getSubject()),
                    new BasicNameValuePair("description", newIssue.getDescription())))
            .returnContent().asString();
    JsonElement parsed = new JsonParser().parse(json);
    return  parsed.getAsJsonObject().get("issue_id").getAsInt();
  }


  public Set<Issue> getIssues() throws IOException {
    String json = getExecutor().execute(Request.Get(app.getProperty("rest.path") + "?limit=1000")).returnContent().asString();
    JsonElement parsed = new JsonParser().parse(json);
    JsonElement issues = parsed.getAsJsonObject().get("issues");
    return new Gson().fromJson(issues, new TypeToken<Set<Issue>>(){}.getType());
  }

  public String getStatus(int issueId) throws IOException {
    String json = getExecutor().execute(Request.Get("http://bugify.stqa.ru/api/issues/" + issueId + ".json"))
            .returnContent().asString();
    JsonElement parsed = new JsonParser().parse(json);
    JsonElement issues = parsed.getAsJsonObject().get("issues");
    Set<Issue> issue = new Gson().fromJson(issues, new TypeToken<Set<Issue>>(){}.getType());
    String status = issue.iterator().next().getState_name();
    return status;
  }
}
