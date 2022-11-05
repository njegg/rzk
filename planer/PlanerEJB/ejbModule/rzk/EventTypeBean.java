package rzk;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.EventType;

/**
 * Session Bean implementation class EventTypeBean
 */
@Singleton
@LocalBean
public class EventTypeBean implements EventTypeBeanLocal {

	@PersistenceContext
	EntityManager em;
	
	private ArrayList<EventType> types;
	
    /**
     * Default constructor. 
     */
    public EventTypeBean() {
        // TODO Auto-generated constructor stub
    }

    @PostConstruct
    public void loadTypes() {
    	types = (ArrayList<EventType>) em.createQuery("SELECT t FROM EventType t").getResultList();
    }
    
    public ArrayList<EventType> getTypes() {
		return types;
	}
}
