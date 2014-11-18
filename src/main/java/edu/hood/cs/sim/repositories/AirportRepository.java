package edu.hood.cs.sim.repositories;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.StatelessSession;
import org.hibernate.criterion.Restrictions;

import edu.hood.cs.sim.domain.Airport;
import edu.hood.cs.sim.util.HibernateUtil;

public class AirportRepository {

	private static final Logger log = LogManager.getLogger(AirportRepository.class);
	
	private static final AirportRepository instance = new AirportRepository();

	public static AirportRepository getInstance() {
		return instance;
	}

	private AirportRepository() {

	}

	public Airport fetch(String icao) {
		Airport airport = null;
		StatelessSession session = null;

		try {
			session = HibernateUtil.getSessionFactory().openStatelessSession();
			Criteria criteria = session.createCriteria(Airport.class);
			criteria.add(Restrictions.eq("icao", icao));
			
			@SuppressWarnings("unchecked")
			List<Airport> list = (List<Airport>)criteria.list();
			if (!list.isEmpty()) {
				airport = list.get(0);
			}
		} catch (Exception ex) {
			log.error(ex);
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return airport;
	}

	public List<Airport> fetchAll() {
		
		List<Airport> airports = new ArrayList<Airport>();
		StatelessSession session = null;

		try {
			session = HibernateUtil.getSessionFactory().openStatelessSession();
			Criteria criteria = session.createCriteria(Airport.class);
			
			@SuppressWarnings("unchecked")
			List<Airport> list = (List<Airport>)criteria.list();
			if (!list.isEmpty()) {
				airports.addAll(list);
			}
		} catch (Exception ex) {
			log.error(ex);
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return airports;
	}
}
