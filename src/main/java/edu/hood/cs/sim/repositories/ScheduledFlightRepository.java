package edu.hood.cs.sim.repositories;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.StatelessSession;
import org.hibernate.criterion.Restrictions;

import edu.hood.cs.sim.domain.Aircraft;
import edu.hood.cs.sim.domain.ScheduledFlight;
import edu.hood.cs.sim.util.HibernateUtil;

public class ScheduledFlightRepository {

	private static final ScheduledFlightRepository instance = new ScheduledFlightRepository();

	public static ScheduledFlightRepository getInstance() {
		return instance;
	}

	private ScheduledFlightRepository() {

	}

	public List<ScheduledFlight> getSchedule(Aircraft aircraft) {
		
		List<ScheduledFlight> schedule = new ArrayList<ScheduledFlight>();
		StatelessSession session = null;

		try {
			session = HibernateUtil.getSessionFactory().openStatelessSession();
			Criteria criteria = session.createCriteria(ScheduledFlight.class);
			criteria.add(Restrictions.eq("tailNum", aircraft.getTailNum()));
			
			@SuppressWarnings("unchecked")
			List<ScheduledFlight> list = (List<ScheduledFlight>)criteria.list();
			if (!list.isEmpty()) {
				schedule.addAll(list);
			}
		} catch (Exception ex) {
			
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return schedule;
	}
}
