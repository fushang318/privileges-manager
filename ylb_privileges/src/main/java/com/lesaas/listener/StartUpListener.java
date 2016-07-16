package com.lesaas.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.support.WebApplicationContextUtils;

import com.lesaas.common.Constants;

public class StartUpListener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent event) {
	}

	public void contextInitialized(ServletContextEvent event) {
		Constants.Context = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
	}

}
