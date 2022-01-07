package adeo.leroymerlin.cdp;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class EventServiceTest {

    private EventService eventService = new EventService(new EventRepositoryMock());

    @Test
    public void testGetEvents(){
        List<Event> result = eventService.getEvents();
        System.out.println("===================================== " + result.size());
    }
}
