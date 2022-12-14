package rzk;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
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
	@SuppressWarnings("unchecked")
    public void loadTypes() {
		System.out.println(this.getClass().getSimpleName() + ": Created");
    	types = (ArrayList<EventType>) em.createQuery("SELECT t FROM EventType t").getResultList();
    }
    
    public ArrayList<EventType> getTypes() {
		return types;
	}
    
    @PreDestroy
	public void preDestroy() {
		System.out.println(this.getClass().getSimpleName() + ": Is gonna be destroyed");
	}
}
