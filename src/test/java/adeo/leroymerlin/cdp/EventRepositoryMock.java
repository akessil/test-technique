package adeo.leroymerlin.cdp;

import java.util.ArrayList;
import java.util.List;

public class EventRepositoryMock implements EventRepository {
    @Override
    public void delete(Long eventId) {

    }

    @Override
    public List<Event> findAllBy() {
        List<Event> events = new ArrayList<>();
        Event event = new Event();
        event.setTitle("Test event");

        events.add(event);
        return events;
    }

    @Override
    public void saveCommentAndNbStars(Long id, int nbStars, String comment) {

    }
}
