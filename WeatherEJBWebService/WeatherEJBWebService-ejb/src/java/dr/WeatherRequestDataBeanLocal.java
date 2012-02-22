package dr;

import javax.ejb.Local;

@Local
public interface WeatherRequestDataBeanLocal {
    public WeatherResponse getResponse(String id);
    public String putResponse(String id, WeatherResponse response);
    public String getNextResponseId();
}
