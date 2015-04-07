package de.choong;

import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

// TODO one of many admin statistics, later remove the sysouts
public class ActiveSessionListener implements HttpSessionListener {

    private static AtomicInteger totalActiveSessions = new AtomicInteger();

    public static int getTotalActiveSession() {
        return totalActiveSessions.get();
    }

    @Override
    public void sessionCreated(HttpSessionEvent event) {
        totalActiveSessions.incrementAndGet();
        System.out.println("Sessions active: " + getTotalActiveSession());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        totalActiveSessions.decrementAndGet();
        System.out.println("Sessions active: " + getTotalActiveSession());
    }
}