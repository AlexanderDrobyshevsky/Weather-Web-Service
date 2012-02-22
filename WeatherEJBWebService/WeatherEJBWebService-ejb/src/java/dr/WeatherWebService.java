package dr;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jms.*;
import javax.jws.WebMethod;
import javax.jws.WebService;

@Stateless
@WebService
public class WeatherWebService {
    @EJB
    WeatherRequestDataBean dataBean;
    @Resource(mappedName = "jms/drqFactory")
    private ConnectionFactory connectionFactory;
    @Resource(mappedName = "jms/drq")
    private Queue queue;

    @WebMethod
    public String sendWeatherRequest(String countryName, String cityName) {
        String id = "0";
        System.out.println("## Request accepted: Country=" + countryName + "; cityName=" + cityName);
        Connection connection = null;

        try {
            connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer messageProducer = session.createProducer(queue);

            ObjectMessage message = session.createObjectMessage();

            id = dataBean.getNextResponseId();
            MessageRequest request = new MessageRequest(id, countryName, cityName);
            message.setObject(request);

            messageProducer.send(message);
            System.out.println("## Message has been sended successfully");
        } catch (JMSException ex) {
            System.out.println("JMS Error: " + ex);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException ex) {
                    System.out.println("JMS Error: " + ex);
                }
            } 
        }

        return id;
    }

    @WebMethod
    public String getWeatherResponse(String id) {
        WeatherResponse response = dataBean.getResponse(id);

        if (response != null) {
            return response.getInfo();
        }
            
        return "0";
    }
}
