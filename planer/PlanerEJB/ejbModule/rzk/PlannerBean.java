package rzk;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import model.Event;
import model.EventType;
import model.User;

/**
 * Session Bean implementation class PlannerBean
 */
@Stateful
@LocalBean
public class PlannerBean implements PlannerBeanRemote {

	private int userId;
	private User user;

	@PersistenceContext
	EntityManager em;

	@EJB
	EventTypeBean eventTypeBean;
	
	/**
	 * Default constructor.
	 */
	public PlannerBean() {}

	@Override
	public String login(String username, String password) {
		Query q = em.createQuery("SELECT u FROM User u WHERE u.email LIKE :user AND u.password LIKE :pass");
		q.setParameter("user", username);
		q.setParameter("pass", password);
		List<User> users = q.getResultList();
		
		try {
			user = users.get(0);
			userId = users.get(0).getId();

			return users.get(0).getEmail();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	
	@Override
	public ArrayList<EventType> getTypes() {
		return eventTypeBean.getTypes();
	}

	@Override
	public boolean createEvent(String description, Date fromDate, Date toDate, int eventTypeID) {
		EventType eventType = em.find(EventType.class, eventTypeID);
		
		if (eventType == null) {
			return false;
		}
		
		Event event = new Event();
		event.setDescription(description);
		event.setEventType(eventType);
		event.setFromDate(fromDate);
		event.setToDate(toDate);
		event.setUser(user);
		
		em.persist(event);
		
		return true;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Event> searchEvents(Date date) {
		ZoneId zoneId = ZoneId.systemDefault();
		Instant dateInstant = date.toInstant();
		
		Instant startOfDayInstant = LocalDateTime
			.ofInstant(dateInstant, zoneId) 		// LocalDateTime od prosledjenog datumom
			.with(LocalTime.MIN)					// Vreme pocetka dana
			.atZone(zoneId).toInstant();
	    
		Instant endOfDayInstant = LocalDateTime
			.ofInstant(dateInstant, zoneId)
			.with(LocalTime.MAX)						// Vreme kraja dana
			.atZone(zoneId).toInstant();
		
	    // Eventi koji pocinju na prosledjen datum
	    String query = 
	    		"SELECT e FROM Event e "
	    	  + "WHERE e.fromDate between :startOfDay AND :endOfDay "
	    	  + "AND e.user LIKE :user";
	    
	    return em.createQuery(query)
	    	.setParameter("startOfDay", Date.from(startOfDayInstant))
	    	.setParameter("endOfDay", Date.from(endOfDayInstant))
	    	.setParameter("user", user)
	    	.getResultList();
	}

	@Override
	@Remove
	public void destroy() {
		System.out.println(this.getClass().getSimpleName() + ": @Remove method called");
	}
	
	@PreDestroy
	public void preDestroy() {
		System.out.println(this.getClass().getSimpleName() + ": Is gonna be destroyed");
	}
	
	@PostConstruct
	public void postConstruct() {
		System.out.println(this.getClass().getSimpleName() + ": Created");
	}
}
