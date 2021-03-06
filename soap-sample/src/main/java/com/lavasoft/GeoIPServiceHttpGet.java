package com.lavasoft;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 3.3.2
 * 2019-05-31T17:03:55.232+07:00
 * Generated source version: 3.3.2
 *
 */
@WebService(targetNamespace = "http://lavasoft.com/", name = "GeoIPServiceHttpGet")
@XmlSeeAlso({ObjectFactory.class})
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface GeoIPServiceHttpGet {

    /**
     * <br/>Get Country and State XML String for a given ip address in x.x.x.x format<br/><br/>Parameter:GetIpAddress address &nbsp;&nbsp;&nbsp;&nbsp;<b>Type:String</b><br/><br/>Return:format in XML < GeoIP >< Country >US< /Country >< State >PA< /State >< /GeoIP > &nbsp;&nbsp;&nbsp;&nbsp;<b>Type:String</b>
     */
    @WebMethod(operationName = "GetIpLocation")
    @WebResult(name = "string", targetNamespace = "http://lavasoft.com/", partName = "Body")
    public java.lang.String getIpLocation(

        @WebParam(partName = "sIp", name = "sIp", targetNamespace = "")
        java.lang.String sIp
    );
}
