package dr;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;

@Singleton
public class WeatherRequestDataBean {

    private static ConcurrentHashMap<String, WeatherResponse> responses;
    private static long startTime = System.currentTimeMillis();
    private static AtomicLong counter = new AtomicLong(0);
    
    public WeatherRequestDataBean() {
    }

    @PostConstruct
    public void init() {
        responses = new ConcurrentHashMap<String, WeatherResponse>(5000);
    }

    public WeatherResponse getResponse(String id) {
        System.out.println("## Request for get response with id = " + id);
        WeatherResponse response = responses.get(id);
        responses.remove(id);
        return response;
    }

    public String putResponse(String id, WeatherResponse response) {
        System.out.println("## Put Response with id = " + id + ", response = " + response.getInfo());
        responses.put(id, response);
        return id;
    }

    public String getNextResponseId() {
        System.out.println("## Request for new response id");
        long cnt = counter.incrementAndGet();
        return startTime + "-" + cnt;
    }
}
