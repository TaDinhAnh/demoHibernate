package com.demo.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.List;

import com.demo.entities.Transactiondetails;


public  class transactionModel {
	private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	private static Session session = null;
	private static Transaction transaction = null;

	public static boolean Create(Transactiondetails trans) {
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			session.save(trans);
			transaction.commit();
			return true;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			return false;
		} finally {
			session.close();
		}
		
	}
	public static List<Transactiondetails> FindByIdAccAndTypeTrasn(String idAcc, int typeTrans) {
		List<Transactiondetails> list = null;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			list = session.createQuery("from Transactiondetails  where Accid= :accid AND Transtype= :transtype").setParameter("accid", idAcc).setParameter("transtype", typeTrans).getResultList();
			transaction.commit();
		} catch (Exception e) {
			System.out.print("dsa"+e.getMessage()+"dsa");
			if (transaction != null) {
				transaction.rollback();
			}
			list = null;
		} finally {
			session.close();
		}
		return list;
	}
}
