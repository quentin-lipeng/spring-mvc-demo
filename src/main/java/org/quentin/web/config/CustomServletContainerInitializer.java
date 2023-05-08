package org.quentin.web.config;

import java.util.Set;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * See {@link ServletContainerInitializer} for more detail
 * The class which implements ServletContainerInitializer will run very early
 * @author quentin
 */
public class CustomServletContainerInitializer implements ServletContainerInitializer {
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomServletContainerInitializer.class);

	@Override
	public void onStartup(Set<Class<?>> c, ServletContext ctx) throws ServletException {
		LOGGER.info("init --->");
	}
}
