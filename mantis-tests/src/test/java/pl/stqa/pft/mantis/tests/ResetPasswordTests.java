package pl.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.stqa.pft.mantis.appmanager.HttpSession;
import pl.stqa.pft.mantis.model.MailMessage;
import ru.lanwen.verbalregex.VerbalExpression;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import static org.testng.Assert.assertTrue;

public class ResetPasswordTests extends TestBase {


  @BeforeMethod
  public void startMailServer()
  {
    app.mail().start();
  }

  // Logowanie admina + akcje związane z resetem hasła

  @Test
  public void ResetPassword () throws IOException {

    Random rand = new Random();

    int amountOfUsers = app.db().getNumberOfUsers();
    int user_id = rand.nextInt(amountOfUsers);
    if (user_id == 0)
    {
      user_id++;
    }
    String username = app.db().getUsernameFromDb(user_id);
    String email = app.db().getUserMailFromDb(user_id);
    String newPassword = "kowalix";

    app.ui().loginByUI("administrator","root");
    app.ui().resetPasswordByAdmin(username);
    app.ui().logout();
    List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
    String confirmationLink = findConfirmationLink(mailMessages, email);
    app.ui().setNewPasswordPage(confirmationLink,newPassword);
   // System.out.println(username);

    HttpSession session = app.newSession();
    assertTrue(session.login(username,newPassword));
    assertTrue(session.isLoggedInAs(username));

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
