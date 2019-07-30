package pl.stqa.pft.mantis.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import pl.stqa.pft.mantis.model.UserData;

import java.util.List;

public class DbHelper {

  private final SessionFactory sessionFactory;

  public DbHelper()

  {
    // A SessionFactory is set up once for an application!
    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure() // configures settings from hibernate.cfg.xml
            .build();

    sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

  }
    /*
  public Users users()
  {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<UserData> result = session.createQuery( "from UserData").list();
    for ( UserData user : result)  {
      System.out.println(user.getUser_id() + " " + user.getUsername());
    }
    session.getTransaction().commit();
    session.close();
    return new Users(result);
  }     */

  public String getUsernameFromDb(int id)
  {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<UserData> result = session.createQuery( "from UserData").list();

    System.out.println(result.get(id).getUser_id() + " " + result.get(id).getUsername());

    session.getTransaction().commit();
    session.close();
    return result.get(id).getUsername();
  }
  public String getUserMailFromDb(int id)
  {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<UserData> result = session.createQuery( "from UserData").list();

    System.out.println(result.get(id).getUser_id() + " " + result.get(id).getEmail());

    session.getTransaction().commit();
    session.close();
    return result.get(id).getEmail();
  }

  public int getNumberOfUsers ()
  {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<UserData> result = session.createQuery( "from UserData where enabled ='1' ").list();

    return result.size();
  }

}
