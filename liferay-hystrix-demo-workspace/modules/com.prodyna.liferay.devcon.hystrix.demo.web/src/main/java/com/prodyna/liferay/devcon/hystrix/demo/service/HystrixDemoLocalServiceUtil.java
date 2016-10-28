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
package com.prodyna.liferay.devcon.hystrix.demo.service;

import com.liferay.osgi.util.ServiceTrackerFactory;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Utility for using Hystrix demo service in JSPs.
 * 
 * @author Florian Assmus, PRODYNA AG
 *
 */
public class HystrixDemoLocalServiceUtil {

	/**
	 * Call Chuck Norris facts service via Hystrix.
	 * 
	 * @return Fact about Chuck Norris.
	 */
	public static String getChuckNorrisFactViaHystrix() {
		return getService().getChuckNorrisFactViaHystrix();
	}

	/**
	 * Call Chuck Norris facts service directly.
	 * 
	 * @return Fact about Chuck Norris.
	 */
	public static String getChuckNorrisFactViaDirectCall() {
		return getService().getChuckNorrisFactViaDirectCall();
	}

	/**
	 * Get {@link HystrixDemoLocalService} instance.
	 * 
	 * @return Instance of {@link HystrixDemoLocalService}.
	 */
	public static HystrixDemoLocalService getService() {
		return _serviceTracker.getService();
	}

	/**
	 * OSGi service tracker for {@link HystrixDemoLocalService} instances.
	 */
	private static ServiceTracker<HystrixDemoLocalService, HystrixDemoLocalService> _serviceTracker = ServiceTrackerFactory
			.open(HystrixDemoLocalService.class);
}