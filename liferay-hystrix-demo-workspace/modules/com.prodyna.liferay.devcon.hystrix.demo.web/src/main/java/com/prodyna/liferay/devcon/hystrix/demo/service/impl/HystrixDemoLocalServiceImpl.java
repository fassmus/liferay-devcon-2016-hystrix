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
package com.prodyna.liferay.devcon.hystrix.demo.service.impl;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.netflix.hystrix.Hystrix;
import com.prodyna.liferay.devcon.hystrix.demo.service.ChuckNorrisFactsServiceClient;
import com.prodyna.liferay.devcon.hystrix.demo.service.HystrixDemoLocalService;
import com.prodyna.liferay.devcon.hystrix.demo.service.command.ChuckNorrisFactCommand;

import java.io.IOException;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;

/**
 * Service for demonstraing how to use Hystrix for encapsulating backend calls.
 * 
 * @author Florian Assmus, PRODYNA AG.
 *
 */
@Component(immediate = true, service = HystrixDemoLocalService.class)
public class HystrixDemoLocalServiceImpl implements HystrixDemoLocalService {

	/**
	 * Component deactivation callback which is used to reset Hystrix.
	 */
	@Deactivate
	protected void deactivate() {
		Hystrix.reset();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.prodyna.liferay.devcon.hystrix.demo.service.HystrixDemoLocalService#
	 * getChuckNorrisFactViaHystrix()
	 */
	@Override
	public String getChuckNorrisFactViaHystrix() {
		// Call Chuck Norris facts service via Hystrix.
		ChuckNorrisFactCommand chuckNorrisFactCommand = new ChuckNorrisFactCommand();
		return chuckNorrisFactCommand.execute();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.prodyna.liferay.devcon.hystrix.demo.service.HystrixDemoLocalService#
	 * getChuckNorrisFactViaDirectCall()
	 */
	@Override
	public String getChuckNorrisFactViaDirectCall() {
		String chuckNorrisFact;

		try {
			// Call Chuck Norris facts service directly.
			chuckNorrisFact = ChuckNorrisFactsServiceClient.callChuckNorrisFactsService();
		} catch (IOException e) {
			_log.error("Unable to call Chuck Norris Facts Service", e);
			chuckNorrisFact = "“It works on my machine” always holds true for Chuck Norris.";
		}

		return chuckNorrisFact;
	}

	private static final Log _log = LogFactoryUtil.getLog(HystrixDemoLocalServiceImpl.class);
}