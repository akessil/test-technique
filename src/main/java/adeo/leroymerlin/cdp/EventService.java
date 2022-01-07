package adeo.leroymerlin.cdp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventService {

    private final EventRepository eventRepository;

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<Event> getEvents() {
        return eventRepository.findAllBy();
    }

    public void delete(Long id) {
        eventRepository.delete(id);
    }

    public List<Event> getFilteredEvents(String query) {
        List<Event> events = eventRepository.findAllBy();

        final List<Event> filteredEvents =  events.stream()
                .filter(e -> e.getBands().stream()
                        .anyMatch(b -> b.getMembers().stream()
                                .anyMatch(m -> m.getName().contains(query))))
                .collect(Collectors.toList());


        List<Event > result = filteredEvents.stream().map(event -> {
            long nbMatchs = event.getBands().stream().map(band -> {
                final long nbMembersMatchs = band.getMembers().stream().filter(member -> member.getName().contains(query)).count();
                band.setName( band.getName() + "[" + nbMembersMatchs + "]");
                return nbMembersMatchs;
            }).collect(Collectors.summingLong(Long::longValue));
            event.setTitle(event.getTitle() + "[" + nbMatchs + "]");
            return event;
        }).collect(Collectors.toList());

        return result;
    }

    public void update(Event event){
        eventRepository.saveCommentAndNbStars(event.getId(), event.getNbStars(), event.getComment());
    }
}
