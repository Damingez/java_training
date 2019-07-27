package pl.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.stqa.pft.mantis.model.MailMessage;
import ru.lanwen.verbalregex.VerbalExpression;

import java.util.List;

public class ResetPasswordTests extends TestBase {


  @BeforeMethod
  public void startMailServer()
  {
    app.mail().start();
  }

  // Logowanie admina + akcje związane z resetem hasła

  @Test
  public void ResetPassword () {
    String username = app.db().getUsernameFromDb(2);
    String email = app.db().getUserMailFromDb(2);
    String newPassword = "qwerty";

    app.ui().loginByUI("administrator","root");
    app.ui().resetPassword(username);
    //app.ui().logout();
    List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
    String confirmationLink = findConfirmationLink(mailMessages, email);
    app.ui().resetPasswordPage(confirmationLink,newPassword);
    System.out.println(username);

  }

  // wyłapanie właściwego maila plus przejście po linku i zmiana hasła przez użytkownika

  // sprawdzenie czy użytkownik może się zalogować


  private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();

    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();

    return regex.getText(mailMessage.text);
  }

  @AfterMethod(alwaysRun = true)
  public void stopMailServer()
  {
    app.mail().stop();
  }

}
