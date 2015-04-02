package de.choong.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * Easy way to access SpringBeans. Main way to do Dependency Injection in this
 * project.
 *
 */
public class SpringUtil {
	
	private static final String CONTEXT_PATH = "src/main/resources/applicationContext.xml";

	private static final ApplicationContext context = new FileSystemXmlApplicationContext(
			CONTEXT_PATH);

	public static Object getBean(String beanName) {
		return context.getBean(beanName);
	}
}
