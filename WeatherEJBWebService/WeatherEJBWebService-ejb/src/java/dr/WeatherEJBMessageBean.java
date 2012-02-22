package dr;

import exwswc.GlobalWeather;
import exwswc.GlobalWeatherSoap;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

@MessageDriven(mappedName = "jms/drq", activationConfig = {
    @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class WeatherEJBMessageBean implements MessageListener {

    @EJB
    WeatherRequestDataBean dataBean;

    public WeatherEJBMessageBean() {
    }

    @Override
    public void onMessage(Message message) {
        System.out.println("## Message recieved");
        try {
            if (message instanceof ObjectMessage) {
                ObjectMessage messageRequest = (ObjectMessage) message;
                MessageRequest request = (MessageRequest) messageRequest.getObject();


                System.out.println("## request = " + request.getId());

                GlobalWeather service = new GlobalWeather();
                GlobalWeatherSoap port = service.getGlobalWeatherSoap();
                String response = port.getWeather(request.getCityName(),
                        request.getCountryName());

                System.out.println("## weather xml, external ws response = " + response);

                WeatherResponse weatherResponse = new WeatherResponse(response);

                dataBean.putResponse(request.getId(), weatherResponse);

                System.out.println("## information put to dataBean ");
            }
        } catch (JMSException ex) {
            ex.printStackTrace();
            System.out.println("JMS Error: " + ex);
        } catch (Throwable te) {
            te.printStackTrace();
            System.out.println("Global Error: " + te);
        }
    }
}
