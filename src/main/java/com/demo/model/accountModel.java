package com.demo.model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;



import com.demo.entities.Account;

public class accountModel {
	private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	private static Session session = null;
	private static Transaction transaction = null;

	public static Account find(String id) {
		Account acc = null;
		try {
		
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			acc = (Account) session.createQuery("from Account where id= :id").setParameter("id", id).getSingleResult();
			transaction.commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());	
			acc = null;
		} finally {
			session.close();
		}
		return acc;
	}

	public static List<Account> findAll() {
		List<Account> listAcc = null;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			listAcc = session.createQuery("from Account").getResultList();
			transaction.commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());	
			listAcc = null;
		} finally {
			session.close();
		}
		return listAcc;
	}
	public static boolean Update(Account acc) {
		List<Account> listAcc = null;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			session.update(acc);
			transaction.commit();
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());	
			return false;
		} finally {
			session.close();
		}
		
	}
}
