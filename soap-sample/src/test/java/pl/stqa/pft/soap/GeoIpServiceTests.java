package pl.stqa.pft.soap;

import com.lavasoft.GeoIPService;
import org.testng.annotations.Test;

public class GeoIpServiceTests {

  @Test
  public void testMyIp() {
    String ipLocation = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("192.168.43.127")      /*getIpLocation("192.168.43.127")*/;
    // neds to be investigated
  }
}

