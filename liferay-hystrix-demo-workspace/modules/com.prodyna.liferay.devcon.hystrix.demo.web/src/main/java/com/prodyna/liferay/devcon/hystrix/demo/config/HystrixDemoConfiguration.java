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
package com.prodyna.liferay.devcon.hystrix.demo.config;

import aQute.bnd.annotation.metatype.Meta;
import aQute.bnd.annotation.metatype.Meta.Type;

/**
 * Configuration for Hystrix demo.
 * 
 * @author Florian Assmus, PRODYNA AG
 *
 */
@Meta.OCD(id = "com.prodyna.liferay.devcon.hystrix.demo.config.HystrixDemoConfiguration")
public interface HystrixDemoConfiguration {

	/**
	 * Indicates if Hystrix should be used or not.
	 * 
	 * @return <code>true</code> if backend service should be called via
	 *         Hystrix, else <code>false</code>.
	 */
	@Meta.AD(type = Type.Boolean, deflt = "true", required = false)
	public Boolean userHystrix();

}
