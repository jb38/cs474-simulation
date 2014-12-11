package edu.hood.cs.sim.repositories;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import edu.hood.cs.sim.domain.Metric;
import edu.hood.cs.sim.util.HibernateUtil;

public class MetricRepository {

	private static final Logger log = LogManager.getLogger(MetricRepository.class);
	
	private static final MetricRepository instance = new MetricRepository();

	public static MetricRepository getInstance() {
		return instance;
	}

	private MetricRepository() {

	}

	public void insert(Metric metric) {
		
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
		    transaction = session.beginTransaction();
		    
			session.save(metric);
			
			transaction.commit();
		} catch (Exception ex) {
			log.error(ex);
		} finally {
			if (session != null) {
				session.close();
			}
		}
		
	}
}
