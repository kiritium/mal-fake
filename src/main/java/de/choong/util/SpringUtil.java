package de.choong.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class SpringUtil {

    private static final ApplicationContext context = new FileSystemXmlApplicationContext(
            "src/main/resources/applicationContext.xml");

    public static Object getBean(String beanName) {
        return context.getBean(beanName);
    }
}
