package pl.stqa.pft.rest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Set;

public class RestTests {


  @Test
  public void testCreateIssue() throws IOException {
   // Set<Issue> oldIssues = getIssues();
   // Issue newIssue =  new Issue();
   // int issueId= createIssue(newIssue);
   Set<Issue> newIssues = getIssues();
  //  oldIssues.add(newIssue.withId(issueId));
  //  assertEquals(newIssues,oldIssues);

  }

  private Set<Issue> getIssues() throws IOException {
    String json=getExecutor().execute(Request.Get("http://demo.bugify.com/api/issues.json"))
            .returnContent().asString();
    JsonParser jsonParser =new JsonParser();
    JsonElement parsed= jsonParser.parse(json);
    JsonElement issues =parsed.getAsJsonObject().get("issues");
    return new Gson().fromJson(issues,new TypeToken<Set<Issue>>(){}.getType());

  }

  private int createIssue(Issue newIssue) throws IOException {
    String json=getExecutor().execute(Request.Post("http://demo.bugify.com/api/issues.json")
            .bodyForm(new BasicNameValuePair("subject",newIssue.getSubject()),
                    new BasicNameValuePair("description",newIssue.getDescription()) ))
            .returnContent().asString();
    JsonElement parsed = new JsonParser().parse(json);
    return parsed.getAsJsonObject().get("issue_id").getAsInt();
  }

  private Executor getExecutor() {
    return Executor.newInstance()
            .auth("25c6b66f028c417c8ade51cfd2c906e9","");
  }

  }
