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

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

/**
 * Client for calling Chuck Norris facts service via HTTP.
 * 
 * @author Florian Assmus, PRODYNA AG
 *
 */
public final class ChuckNorrisFactsServiceClient {

	/**
	 * Chuck Norris facts service endpoint URL (hardcoded as this is only a demo
	 * :)).
	 */
	private static final String CHUCK_NORRIS_SERVICE_ENDPOINT = "http://localhost:8081/chucknorrisfact";

	/**
	 * Private constructure as this is a Util.
	 */
	private ChuckNorrisFactsServiceClient() {
	}

	/**
	 * Call Chuck Norris facts service using simple {@link HttpURLConnection}.
	 * 
	 * @return Fact about Chuck Norris.
	 * @throws IOException
	 *             is thrown if the service could not be called for some reason.
	 */
	public static String callChuckNorrisFactsService() throws IOException {
		URL url = new URL(CHUCK_NORRIS_SERVICE_ENDPOINT);

		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		InputStream inputStream = connection.getInputStream();

		String response = IOUtils.toString(inputStream);

		JSONObject jsonResponse = new JSONObject(response);
		String chuckNorrisFact = jsonResponse.getString("fact");

		return chuckNorrisFact;
	}

}
