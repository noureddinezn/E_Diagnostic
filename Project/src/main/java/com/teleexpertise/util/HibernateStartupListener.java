package com.teleexpertise.util;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class HibernateStartupListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        HibernateUtil.getSessionFactory();
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        HibernateUtil.getSessionFactory().close();
    }
}
