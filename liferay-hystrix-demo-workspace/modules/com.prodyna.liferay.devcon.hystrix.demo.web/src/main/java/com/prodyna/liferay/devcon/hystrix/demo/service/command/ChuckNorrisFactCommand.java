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
package com.prodyna.liferay.devcon.hystrix.demo.service.command;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolProperties;
import com.prodyna.liferay.devcon.hystrix.demo.service.ChuckNorrisFactsServiceClient;

/**
 * Hystrix command for calling the Chuck Norris facts service.
 * 
 * @author Florian Assmus, PRODYNA AG
 *
 */
public class ChuckNorrisFactCommand extends HystrixCommand<String> {

	/**
	 * Hystrix Key for command group.
	 */
	public static final HystrixCommandGroupKey GROUP_KEY = HystrixCommandGroupKey.Factory.asKey("HystrixDemoGroup");

	/**
	 * Hystrix Key for command.
	 */
	public static final HystrixCommandKey COMMAND_KEY = HystrixCommandKey.Factory.asKey("ChuckNorrisFactCommand");

	/**
	 * Default timeout in milliseconds.
	 */
	private static final int DEFAULT_TIMEOUT_IN_MILLISEC = 200;

	/**
	 * Default size of threadpool.
	 */
	private static final int DEFAULT_THREAD_POOL_SIZE = 50;

	/**
	 * Constructor configuring the command using the default parameteres.
	 */
	public ChuckNorrisFactCommand() {
		super(Setter.withGroupKey(GROUP_KEY).andCommandKey(COMMAND_KEY)
				.andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
						.withExecutionTimeoutInMilliseconds(DEFAULT_TIMEOUT_IN_MILLISEC))
				.andThreadPoolPropertiesDefaults(
						HystrixThreadPoolProperties.Setter().withCoreSize(DEFAULT_THREAD_POOL_SIZE)));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.netflix.hystrix.HystrixCommand#run()
	 */
	@Override
	protected String run() throws Exception {
		return ChuckNorrisFactsServiceClient.callChuckNorrisFactsService();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.netflix.hystrix.HystrixCommand#getFallback()
	 */
	@Override
	protected String getFallback() {
		return "“It works on my machine” always holds true for Chuck Norris.";
	}

}
