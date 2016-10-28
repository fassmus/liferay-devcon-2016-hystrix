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

/**
 * Chuck Norris fact bean.
 * 
 * @author Florian Assmus, PRODYNA AG
 *
 */
public class ChuckNorrisFact {

	/**
	 * ID of fact.
	 */
	private final long id;

	/**
	 * Chuck Norris fact.
	 */
	private final String fact;

	/**
	 * Create a Chuck Norris fact.
	 * 
	 * @param id
	 *            ID of fact.
	 * @param fact
	 *            Fact about Chuck Norris.
	 */
	public ChuckNorrisFact(long id, String fact) {
		this.id = id;
		this.fact = fact;
	}

	/**
	 * Getter for the facts ID.
	 * 
	 * @return ID of fact.
	 */
	public long getId() {
		return id;
	}

	/**
	 * Getter for the fact.
	 * 
	 * @return The fact.
	 */
	public String getFact() {
		return fact;
	}
}
