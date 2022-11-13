package rzk;

import java.util.HashMap;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Ogla;

/**
 * Session Bean implementation class Statistika
 */
@Singleton
@Startup
@LocalBean
public class Statistika implements StatistikaLocal {
	@PersistenceContext
	private EntityManager em;

	@Resource
	private TimerService timerService;
	
	private HashMap<Integer, Integer> pregledi;
	private int dailyApplications = 0;
	
	/**
	 * Default constructor.
	 */
	public Statistika() {
		pregledi = new HashMap<Integer, Integer>();
	}

	
	public void updateMap(Ogla o) {
		if (pregledi.containsKey(o.getIdOglas())) {
			pregledi.put(o.getIdOglas(), pregledi.get(o.getIdOglas()) + 1);
		} else {
			pregledi.put(o.getIdOglas(), 1);
		}
	}

//	@Schedule(minute = "*/15", hour = "*", persistent = false)
	@Schedule(second = "*/5", minute = "*", hour = "*", persistent = false) // Za test
	public void updateDB() {
		for (Entry<Integer, Integer> entry : pregledi.entrySet()) {
			em.createQuery("update Ogla set brojPregleda = brojPregleda + :pregleda where idOglas like :id ")
				.setParameter("pregleda", entry.getValue())
				.setParameter("id", entry.getKey())
				.executeUpdate();
		}
		
		pregledi.clear(); // Da se ne dodaju opet
		
		System.out.println("Database updated");
	}
	

	public void increaseDailyApplications() {
		dailyApplications++;
	}
	
	@Timeout
	private void dailyUpdate(Timer timer) {
		System.out.println("Daily applications: " + dailyApplications);
		dailyApplications = 0;
	}
    
	public void startTimer() {
		TimerConfig config = new TimerConfig();
		config.setPersistent(false);
		
		timerService.createIntervalTimer(0, 15_000, config); // 15s - za testiranje
//		timerService.createIntervalTimer(0, 24 * 3600 * 1000, config); // jednom dnevno

		System.out.println("Timer started");
	}
	
	
	@PostConstruct
	private void postConstruct() {
		System.out.println(getClass().getSimpleName() + ": Created");
	
		System.out.println("About to start the timer");
		startTimer();
	}
	
	@PreDestroy
	private void preDestroy() {
		System.out.println(getClass().getSimpleName() + ": Destroyed");
	}
	
	public HashMap<Integer, Integer> getPregledi() {
		return pregledi;
	}
	
	public void setPregledi(HashMap<Integer, Integer> pregledi) {
		this.pregledi = pregledi;
	}
}
