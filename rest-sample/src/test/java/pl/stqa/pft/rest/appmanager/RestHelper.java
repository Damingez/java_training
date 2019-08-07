package pl.stqa.pft.rest.appmanager;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.jayway.restassured.RestAssured;
import pl.stqa.pft.rest.Issue;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Set;

public class RestHelper {

  private ApplicationManager app;
  public RestHelper(ApplicationManager app)  {
    this.app=app;
  }

  public Set<Issue> getIssues() throws IOException {
    String json= RestAssured.get("http://demo.bugify.com/api/issues.json?page=1&limit=2000").asString();
    JsonParser jsonParser =new JsonParser();
    JsonElement parsed= jsonParser.parse(json);
    JsonElement issues =parsed.getAsJsonObject().get("issues");
    return  new Gson().fromJson(issues,new TypeToken<Set<Issue>>(){}.getType());
  }


  public BigInteger createIssue(Issue newIssue) throws IOException {
    String json= RestAssured.given().
            parameter("subject",newIssue.getSubject())
            .parameter("description",newIssue.getDescription())
            .post("http://demo.bugify.com/api/issues.json").asString();
    JsonElement parsed = new JsonParser().parse(json);
    return parsed.getAsJsonObject().get("issue_id").getAsBigInteger();
  }

  public Set<Issue> getIssue(BigInteger issue_id) throws IOException {
    String json= RestAssured.get("http://demo.bugify.com/api/issues/"+issue_id+".json").asString();
    JsonParser jsonParser =new JsonParser();
    JsonElement parsed= jsonParser.parse(json);
    JsonElement issues =parsed.getAsJsonObject().get("issues");
    return  new Gson().fromJson(issues,new TypeToken<Set<Issue>>(){}.getType());
  }

  public String getIssueStatus(BigInteger issue_id) throws IOException {
    return getIssue(issue_id).stream().findFirst().get().getStatus_name();
  }

}
