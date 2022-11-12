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
	private HashMap<Integer, Integer> pregledi;

	@PersistenceContext
	private EntityManager em;
	
	private int dailyViews = 0;
	
	@Resource
	private TimerService timerService;
	private Timer timer;
	
	/**
	 * Default constructor.
	 */
	public Statistika() {
		pregledi = new HashMap<Integer, Integer>();
	}
	
	public void startTimer() {
		TimerConfig config = new TimerConfig();
		config.setPersistent(false);
		
		timer = timerService.createIntervalTimer(0, 5_000, config); // 10s - za testiranje
//		timer = timerService.createIntervalTimer(0, 24 * 3600 * 1000, config); // jednom dnevno

		System.out.println("Timer started");
	}
	
	@Timeout
	private void updateDana(Timer timer) {
		System.out.println("Daily views: " + dailyViews);
		dailyViews = 0;
	}

	// 1. metoda za update Mape
	public void updateMap(Ogla o) {
		if (pregledi.containsKey(o.getIdOglas())) {
			pregledi.put(o.getIdOglas(), pregledi.get(o.getIdOglas()) + 1);
		} else {
			pregledi.put(o.getIdOglas(), 1);
		}
		
		dailyViews++;
	}

	// 2. metoda za update baze
//	@Schedule(minute = "*/15", hour = "*", persistent = false)
	@Schedule(second = "*/10", minute = "*", hour = "*", persistent = false)
	public void updateDB() {
		for (Entry<Integer, Integer> entry : pregledi.entrySet()) {
			Ogla o = em.find(Ogla.class, entry.getKey());
			o.setBrojPregleda(o.getBrojPregleda() + entry.getValue());
			em.merge(o);
		}
		
		System.out.println("Database updated");
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
