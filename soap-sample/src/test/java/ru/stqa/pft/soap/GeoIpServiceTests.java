package ru.stqa.pft.soap;

import com.lavasoft.GeoIPService;
import org.testng.annotations.Test;

public class GeoIpServiceTests {

  @Test
  public void testMyIp(){
    String geoIP = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("37.192.45.193");
    System.out.println(geoIP);
  }
}
