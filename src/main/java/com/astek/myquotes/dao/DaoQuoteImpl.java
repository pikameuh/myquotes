package com.astek.myquotes.dao;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.StringTokenizer;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.astek.myquotes.entitites.Quote;
import com.astek.myquotes.repositories.QuoteRepository;
import com.astek.myquotes.utility.Log;
import com.astek.myquotes.utility.SQL_CTE;

@Repository
public class DaoQuoteImpl implements DaoQuote {
	
	private boolean debug = false;

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private QuoteRepository repo;

	@Override
	public Quote findRandomQuote(Boolean onlyPublicQuotes) {

		Query countQuery;
		if (onlyPublicQuotes) {
			countQuery = em.createQuery("select count(*) from Quote q where q.privee='false'");
		//	countQuery.setParameter("public", Boolean.);
		} else {
			countQuery = em.createQuery("select count(*) from Quote");
		}

//		Query countQuery = em.createQuery("select count(*) from Quote q where q.privee=:public");
//		countQuery.setParameter("public", onlyPublicQuotes);

//		Query countQuery = em.createNativeQuery(req);
		long count = (long) countQuery.getSingleResult();

		Random random = new Random();
		int number = random.nextInt((int) count);

		Query selectQuery;
		if (onlyPublicQuotes) {
			selectQuery = em.createQuery("select q from Quote q where q.privee='false'");
			//selectQuery.setParameter("public", onlyPublicQuotes);
		} else {
			selectQuery = em.createQuery("select q from Quote q");
		}

//		Query selectQuery = em.createQuery("select q from Quote q where q.privee=:public");
//		selectQuery.setParameter("public", onlyPublicQuotes);

//		Query selectQuery = em.createQuery(req);
		selectQuery.setFirstResult(number);
		selectQuery.setMaxResults(1);
		return (Quote) selectQuery.getSingleResult();
	}

	@Override
	public List<Quote> findAll() {
		Query query = em.createQuery("from Quote p");
		return query.getResultList();
	}

	@Override
	public Optional<Quote> findById(Integer id) {
		return Optional.ofNullable(em.find(Quote.class, id));
	}

	@Override
	public Quote save(Quote obj) {
		if (obj.getId() == null) {
			em.persist(obj);
		} else {
			obj = em.merge(obj);
		}

		return obj;
	}

	@Override
	public void delete(Quote obj) {
		em.remove(em.merge(obj));
	}

	@Override
	public void deleteById(Integer id) {
		Quote q = em.find(Quote.class, id);
		em.remove(q);
	}

	@Override
	public List<Quote> searchByKeyword(String keyword) {
		if (keyword != null) {
			Log.debug("keyword="+keyword);

			if (keyword.replace("+", "").replace(" ", "").replace("*", "").isEmpty()) {
				String req = SQL_CTE.selectQuotePublic;
				 Log.debug("SQL : " + req);
				Query selectQuery  = em.createQuery(req);				
				return selectQuery.getResultList();
			}else if (keyword.contains(" ")) {
				Log.debug("** search multi");
				
//				String req = "select q from Quote q where q.privee=:public ";
				String closeFacto = SQL_CTE.concatSearch + " LIKE ";
				String req = "SELECT q FROM Quote q WHERE (";
				StringTokenizer st=new StringTokenizer(keyword," ");
			     while (st.hasMoreTokens()) {
			    	 String tmp = st.nextToken();
			    	 if(!tmp.isEmpty()) {
			    		req += closeFacto + " '%" + tmp + "%'" + " or ";
			    	 }
			     }
			     req = req.substring(0, req.length() - 3);
			     
			     req += ") and q.privee= 'false'";
			     
			     Log.debug("SQL : " + req);
			     
				Query selectQuery  = em.createQuery(req);				
				return selectQuery.getResultList();
			} else {
				// "SELECT q FROM Quote q WHERE CONCAT(q.value, q.contexte, q.lieu, q.titre, CONCAT(q.dtEvenement, ''), CONCAT(q.dtCreation, ''), q.auteur.prenom ) LIKE %?1% AND (q.privee = 'true')
				return repo.searchInAllPublic(keyword);
			}
		} else {
			Log.debug("!!! Error : DaoQuoteImpl.searchByKeyword > keyword is null");
			return null;
		}
	}

	@Override
	public long numberOfPublicQuote() {
		Query countQuery = em.createQuery("select count(*) from Quote");
		long count = (long) countQuery.getSingleResult();
		System.out.println("******* " + count);
		return count;
	}
}
