/**
 * Copyright (c) 2001-present PRODYNA AG. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
package com.prodyna.liferay.devcon.hystrix.demo.servlet;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;

import java.util.Map;

import javax.servlet.Servlet;
import javax.servlet.ServletContext;

import org.osgi.framework.Filter;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.util.tracker.ServiceTracker;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

/**
 * Servlet for exposing the Hystrix metrics event stream.
 * 
 * @author Florian Assmus, PRODYNA AG
 *
 */
@Component(immediate = true, property = {
		"osgi.http.whiteboard.servlet.name=com.prodyna.liferay.devcon.hystrix.demo.servlet.LiferayHystrixMetricsStreamServlet",
		"osgi.http.whiteboard.servlet.pattern=" + LiferayHystrixMetricsStreamServlet.METRICS_STREAM_PATH,
		"osgi.http.whiteboard.context.select=(osgi.http.whiteboard.context.name=comprodynaliferaydevconhystrixdemoweb)" }, service = {
				Servlet.class })
public class LiferayHystrixMetricsStreamServlet extends HystrixMetricsStreamServlet
		implements ServiceTrackerCustomizer<ServletContext, ServiceReference<ServletContext>> {

	private static final long serialVersionUID = 1L;

	/**
	 * Path under which to expose the servlet.
	 */
	public static final String METRICS_STREAM_PATH = "/hystrix.demo";

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.util.tracker.ServiceTrackerCustomizer#addingService(org.osgi.
	 * framework.ServiceReference)
	 */
	@Override
	public ServiceReference<ServletContext> addingService(ServiceReference<ServletContext> reference) {
		return reference;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.util.tracker.ServiceTrackerCustomizer#modifiedService(org.osgi.
	 * framework.ServiceReference, java.lang.Object)
	 */
	@Override
	public void modifiedService(ServiceReference<ServletContext> reference, ServiceReference<ServletContext> service) {
		removedService(reference, service);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.util.tracker.ServiceTrackerCustomizer#removedService(org.osgi.
	 * framework.ServiceReference, java.lang.Object)
	 */
	@Override
	public void removedService(ServiceReference<ServletContext> reference, ServiceReference<ServletContext> service) {
		// Shutdown the metrics event stream and all connected dashboards so the
		// service can be removed.
		shutdown();
	}

	/**
	 * OSGi component lifecycle listener for actication and modification of this
	 * service.
	 * 
	 * @param componentContext
	 *            OSGi component context.
	 * @param properties
	 *            OSGi configuration properties.
	 * @throws Exception
	 *             is thrown if the activation or modification of the service
	 *             goes wrong.
	 */
	@Activate
	@Modified
	protected void activate(ComponentContext componentContext, Map<String, Object> properties) throws Exception {

		// Check if a service tracker for the context path exists and close it.
		if (_serviceTracker != null) {
			_serviceTracker.close();
		}

		// Create a filter for ServletContext components and start tracking.
		Filter filter = FrameworkUtil
				.createFilter("(&(objectClass=" + ServletContext.class.getName() + ")(osgi.web.contextpath=*))");

		_serviceTracker = new ServiceTracker<>(componentContext.getBundleContext(), filter, this);

		_serviceTracker.open();
	}

	/**
	 * OSGi component lifecycle listener for deactication which closes the
	 * {@link ServiceTracker} for {@link ServletContext} services.
	 */
	@Deactivate
	protected void deactivate() {
		_serviceTracker.close();

		_serviceTracker = null;
	}

	/**
	 * {@link ServiceTracker} for {@link ServletContext} services
	 */
	private ServiceTracker<ServletContext, ServiceReference<ServletContext>> _serviceTracker;

}
