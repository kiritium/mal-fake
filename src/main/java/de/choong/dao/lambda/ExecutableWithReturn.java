package de.choong.dao.lambda;

import org.hibernate.Session;

public interface ExecutableWithReturn<T> {

	public T execute(Session session);
	
}
