package pl.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class UIhelper extends HelperBase {


  public UIhelper(ApplicationManager app) {
    super(app);
  }

  public void loginByUI(String username, String password) {

    wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
    type(By.name("username"), username);
    type(By.name("password"), password);
    click(By.cssSelector("input[value='Login']"));

  }

  public void resetPasswordByAdmin(String user_name)
  {
    click(By.cssSelector("[href='/mantisbt-1.2.20/manage_overview_page.php']"));
    click(By.cssSelector("[href='/mantisbt-1.2.20/manage_user_page.php']"));
    click(By.linkText(user_name));
    click(By.cssSelector("input[value='Reset Password']"));
  }

  public void setNewPasswordPage(String confirmationLink, String password) {
    wd.get(confirmationLink);
    type(By.name("password"), password);
    type(By.name("password_confirm"), password);
    click(By.cssSelector("input[value='Update User']"));
  }

  public void logout ()
  {
    click(By.cssSelector("[href='/mantisbt-1.2.20/logout_page.php']"));
  }

}
