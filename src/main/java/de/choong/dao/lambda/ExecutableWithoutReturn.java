package de.choong.dao.lambda;

import org.hibernate.Session;

public interface ExecutableWithoutReturn {

	public void execute(Session session);
	
}
