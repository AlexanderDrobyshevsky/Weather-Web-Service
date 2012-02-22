
package exwswc;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.5-b04 
 * Generated source version: 2.2
 * 
 */
@WebService(name = "GlobalWeatherHttpPost", targetNamespace = "http://www.webserviceX.NET")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface GlobalWeatherHttpPost {


    /**
     * Get weather report for all major cities around the world.
     * 
     * @param countryName
     * @param cityName
     * @return
     *     returns java.lang.String
     */
    @WebMethod(operationName = "GetWeather")
    @WebResult(name = "string", targetNamespace = "http://www.webserviceX.NET", partName = "Body")
    public String getWeather(
        @WebParam(name = "string", targetNamespace = "http://www.w3.org/2001/XMLSchema", partName = "CityName")
        String cityName,
        @WebParam(name = "string", targetNamespace = "http://www.w3.org/2001/XMLSchema", partName = "CountryName")
        String countryName);

    /**
     * Get all major cities by country name(full / part).
     * 
     * @param countryName
     * @return
     *     returns java.lang.String
     */
    @WebMethod(operationName = "GetCitiesByCountry")
    @WebResult(name = "string", targetNamespace = "http://www.webserviceX.NET", partName = "Body")
    public String getCitiesByCountry(
        @WebParam(name = "string", targetNamespace = "http://www.w3.org/2001/XMLSchema", partName = "CountryName")
        String countryName);

}
