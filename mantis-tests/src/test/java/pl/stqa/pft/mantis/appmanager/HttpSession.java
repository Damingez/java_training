package pl.stqa.pft.mantis.appmanager;


import jdk.internal.org.objectweb.asm.tree.analysis.BasicValue;
import org.omg.CORBA.NameValuePair;

import java.util.ArrayList;
import java.util.List;

public class HttpSession {

  private CloseableHttpClient httpClient;
  private ApplicationManager app;

  public HttpSession(ApplicationManager app) {
    this.app = app;
    httpClient = HttpClients.custom().setRedirectStrategy(new LaxRedirectStrategy()).build();

  }

  public boolean login(String username, String password )
  {
    HttpPost post = new HttpPost(app.getProperty("web.baseUrl") + "/login.php" );
    List<NameValuePair> params = new ArrayList<>();
    params.add(new BasicNameValuePair("username", username));
    params.add(new BasicNameValuePair("password", password));
    params.add(new BasicNameValuePair("secure_session", "on"));
    params.add(new BasicNameValuePair("return", "index.php"));
    post.setEntity(new UrlEncodedFormEntity(params));

    CloseableHttpResponse response = httpClient.execute(post);
    return true;
  }


}
