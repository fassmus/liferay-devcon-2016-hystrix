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
package com.prodyna.devcon.backend;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;

import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ReST controller for the Chuck Norris facts service.
 * 
 * @author Florian Assmus, PRODYNA AG
 *
 */
@RestController
public class ChuckNorrisFactController {

	private static final Logger log = Logger.getLogger(ChuckNorrisFactController.class.getName());

	/**
	 * Name of file containing the Chuck Norris facts relative to the current
	 * class loader.
	 */
	private static final String CHUCK_NORRIS_FACTS_FILE_NAME = "Chuck_Norris_Facts.txt";

	/**
	 * List of Chuck Norris facts.
	 */
	private List<String> chuckNorrisFacts;

	/**
	 * Generator for random numbers used to look up a Chuck Norris fact.
	 */
	private Random randomGenerator = new Random();

	/**
	 * Load facts about Chuck Norris from a file into memory.
	 * 
	 * @throws IOException
	 *             is thrown if the Chuck Norris facts could not be loaded.
	 */
	@PostConstruct
	public void loadChuckNorrisFacts() throws IOException {
		InputStream in = getClass().getClassLoader().getResource(CHUCK_NORRIS_FACTS_FILE_NAME).openStream();
		chuckNorrisFacts = IOUtils.readLines(in, "UTF-8");
	}

	/**
	 * Method for retrieving a fact about Chuck Norris.
	 * 
	 * @return Fact about Chuck Norris.
	 */
	@RequestMapping("/chucknorrisfact")
	public ChuckNorrisFact chuckNorrisFact() throws InterruptedException {

		// Simple way to slow down service for simulation purposes.
//		try {
//			Thread.sleep(15000);
//		} catch (InterruptedException e) {
//			// Do nothing.
//		}

		// Get a random fact and return it.
		int factIndex = getRandomInt(0, chuckNorrisFacts.size() - 1);
		String chuckNorrisFact = chuckNorrisFacts.get(factIndex);
		return new ChuckNorrisFact(factIndex, chuckNorrisFact);
	}

	/**
	 * Get a random {@link Integer} based on the give range.
	 * 
	 * @param start
	 *            Lowest random number.
	 * @param end
	 *            Highest random number
	 * @return Random number within the given range.
	 */
	public int getRandomInt(int start, int end) {
		if (start > end) {
			throw new IllegalArgumentException("Start cannot exceed End.");
		}

		// get the range, casting to long to avoid overflow problems
		long range = (long) end - (long) start + 1;
		// compute a fraction of the range, 0 <= frac < range
		long fraction = (long) (range * randomGenerator.nextDouble());
		int randomNumber = (int) (fraction + start);

		return randomNumber;
	}
}
