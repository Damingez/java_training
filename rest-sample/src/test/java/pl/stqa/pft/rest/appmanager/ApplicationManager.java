package pl.stqa.pft.rest.appmanager;

import java.util.Properties;

public class ApplicationManager {

  private final Properties properties;
  private RestHelper restHelper;

  public ApplicationManager(){
    properties=new Properties();
  }
  public String getProperty(String key){
    return properties.getProperty(key);
  }

  public RestHelper rest(){
    if(restHelper==null){
      restHelper= new RestHelper(this);
    } return restHelper;
  }

}
