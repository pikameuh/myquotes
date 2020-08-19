package com.astek.myquotes.dao;

import java.util.List;
import java.util.Optional;

public interface DaoGeneric<T, K> {

	List<T> findAll();

	Optional<T> findById(K id);

	// cas 1
//	void insert(T obj);
//	T update(T obj);
	// cas 2
	T save(T obj);

	void delete(T obj);

	void deleteById(K id);
}
