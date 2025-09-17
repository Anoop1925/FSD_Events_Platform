package in.chill.main.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.chill.main.entity.Events;
import in.chill.main.repository.EventsRepository;

@Service
public class EventsServiceImplementation implements EventsService {
	
	@Autowired
	private EventsRepository eventsRepository;
	
	@Override
	public Events createEvent(Events event) {
		return eventsRepository.save(event);
	}

	@Override
	public List<Events> getAllEvents() {
		return eventsRepository.findAll();
	}

	@Override
	public Optional<Events> getEventDetails(int id) {
		return eventsRepository.findById(id);
	}

	@Override
	public Events updateEventDetails(int id, Events event) {
		Events existingEvent = eventsRepository.findById(id).orElse(null);
		if(existingEvent != null) {
			// Update all fields
			existingEvent.setEvent_name(event.getEvent_name());
			existingEvent.setEvent_start_date(event.getEvent_start_date());
			existingEvent.setEvent_end_date(event.getEvent_end_date());
			existingEvent.setEvent_time(event.getEvent_time());
			existingEvent.setEvent_type(event.getEvent_type());
			existingEvent.setEvent_description(event.getEvent_description());
			existingEvent.setJudge_id(event.getJudge_id());
			existingEvent.setClub_id(event.getClub_id());
			existingEvent.setVenue_id(event.getVenue_id());
			
			return eventsRepository.save(existingEvent);
		}
		else {
			 throw new RuntimeException("Event does not exist");
		}
	}
	
	@Override
	public Optional<Events> getNextEvent() {
		return eventsRepository.findNextEventNative();
	}
	
	@Override
	public List<Events> getUpcomingEvents() {
		return eventsRepository.findUpcomingEventsNative();
	}
	
	@Override
	public int getCompletedEventsCount() {
		return eventsRepository.countCompletedEvents();
	}

}
