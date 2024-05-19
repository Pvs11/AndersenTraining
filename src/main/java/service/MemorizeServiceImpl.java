package service;

import config.HibernateUtil;
import models.FlipCard;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.*;

public class MemorizeServiceImpl implements MemorizeService {
	private static final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	public MemorizeServiceImpl() {
	}

	@Override
	public boolean addFlipCard(FlipCard flipCard) {
		if (!getFlipCards().contains(flipCard) && !flipCard.isEmpty()) {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			session.persist(flipCard);
			session.getTransaction().commit();
			session.close();
			return true;
		} else return false;
	}

	@Override
	public Set<FlipCard> getFlipCards() {
		Session session = sessionFactory.openSession();
		Set<FlipCard> resultSet = new HashSet<>(session.createQuery("select f from FlipCard f", FlipCard.class).list());
		session.close();
		return resultSet;
	}

	@Override
	public FlipCard findFlipCardById(int id) {
		Session session = sessionFactory.openSession();
		FlipCard flipCard = session.get(FlipCard.class, id);
		session.close();
		return flipCard;
	}

	@Override
	public FlipCard findFlipCardByNativeWord(String nativeWord) {
		Session session = sessionFactory.openSession();
		FlipCard flipCard = session.createQuery("select f from FlipCard f where nativeWord= :nativeWord", FlipCard.class)
				.setParameter("nativeWord", nativeWord)
				.uniqueResult();
		session.close();
		return flipCard;
	}

	@Override
	public boolean editFlipCard(int idOfOldCard, FlipCard newFlipCard) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		int result = session.createQuery("update FlipCard set nativeWord = :nativeWord, translationWord = :translationWord where id = :id")
				.setParameter("nativeWord", newFlipCard.getNativeWord())
				.setParameter("translationWord", newFlipCard.getTranslationWord())
				.setParameter("id", idOfOldCard)
				.executeUpdate();
		session.getTransaction().commit();
		session.close();
		if (result == 1) {
			return true;
		} else return false;
	}

	@Override
	public boolean removeFlipCard(int id) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		int result = session.createQuery("delete from FlipCard where id = :id")
				.setParameter("id", id)
				.executeUpdate();
		session.getTransaction().commit();
		session.close();
		if (result == 1) {
			return true;
		} else return false;
	}

	@Override
	public FlipCard getRandomCard() {
		List<Integer> idList;
		Session session = sessionFactory.openSession();
		idList = session.createQuery("select f.id from FlipCard f", Integer.class).list();
		idList.sort(Comparator.comparingInt(Integer::intValue));
		int randomId = idList.get(new Random().nextInt(0, idList.size()));
		FlipCard randomFlipCard = session.createQuery("from FlipCard f where f.id=:id", FlipCard.class)
				.setParameter("id", randomId)
				.uniqueResult();
		session.close();
		return randomFlipCard;
	}
}
