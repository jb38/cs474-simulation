package edu.hood.cs.sim.repositories;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.StatelessSession;
import org.hibernate.criterion.Restrictions;

import edu.hood.cs.sim.domain.Aircraft;
import edu.hood.cs.sim.util.HibernateUtil;

public class AircraftRepository {

	private static final AircraftRepository instance = new AircraftRepository();

	public static AircraftRepository getInstance() {
		return instance;
	}

	private AircraftRepository() {

	}

	public Aircraft fetch(String tailNum) {
		Aircraft aircraft = null;
		StatelessSession session = null;

		try {
			session = HibernateUtil.getSessionFactory().openStatelessSession();
			Criteria criteria = session.createCriteria(Aircraft.class);
			criteria.add(Restrictions.eq("tailNum", tailNum));
			
			@SuppressWarnings("unchecked")
			List<Aircraft> list = (List<Aircraft>)criteria.list();
			if (!list.isEmpty()) {
				aircraft = list.get(0);
			}
		} catch (Exception ex) {
			
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return aircraft;
	}

	public List<Aircraft> fetchAll() {
		List<Aircraft> aircraft = new ArrayList<Aircraft>();
		StatelessSession session = null;

		try {
			session = HibernateUtil.getSessionFactory().openStatelessSession();
			Criteria criteria = session.createCriteria(Aircraft.class);
			criteria.add(Restrictions.not(Restrictions.eq("tailNum", "N969DL")));
			
			@SuppressWarnings("unchecked")
			List<Aircraft> list = (List<Aircraft>)criteria.list();
			if (!list.isEmpty()) {
				aircraft.addAll(list);
			}
		} catch (Exception ex) {

		} finally {
			if (session != null) {
				session.close();
			}
		}

		return aircraft;
	}
}
