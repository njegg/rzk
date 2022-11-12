package rzk;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import model.Event;
import model.EventType;

@Remote
public interface PlannerBeanRemote {
    String login(String username, String password);
    boolean createEvent(String description, Date fromDate, Date toDate, int eventTypeID);
    List<Event> searchEvents(Date date); 
    List<EventType> getTypes();
    void destroy();
}
