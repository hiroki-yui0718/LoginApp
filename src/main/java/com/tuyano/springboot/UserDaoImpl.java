package com.tuyano.springboot;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class UserDaoImpl implements UserDao<User> {
	private static final long serialVersionUID = 1L;
	
	private EntityManager entityManager; //Entityを操るマネージャー
	
	public UserDaoImpl() {
		super();
	}
	public UserDaoImpl(EntityManager manager) {
		entityManager = manager;
	}
	@Override
	public List<User> getAll() {
		// TODO 自動生成されたメソッド・スタブ
		Query query = entityManager.createQuery("from User");
		List<User> list = query.getResultList();
		entityManager.close();
		return list;
	}

}
