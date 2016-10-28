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
package com.prodyna.liferay.devcon.hystrix.demo.portlet;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.prodyna.liferay.devcon.hystrix.demo.config.HystrixDemoConfiguration;

import java.io.IOException;
import java.util.Map;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;

import aQute.bnd.annotation.metatype.Configurable;

/**
 * Portlet for demonstraing Hystrix.
 * 
 * @author Florian Assmus, PRDOYNA AG.
 *
 */
@Component(configurationPid = "com.prodyna.liferay.devcon.hystrix.demo.config.HystrixDemoConfiguration", immediate = true, property = {
		"com.liferay.portlet.display-category=category.sample", "com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=Hystrix Demo Portlet",
		"javax.portlet.init-param.template-path=/hystrix-demo-portlet/",
		"javax.portlet.init-param.view-template=/hystrix-demo-portlet/view.jsp",
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
public class HystrixDemoPortlet extends MVCPortlet {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet#doView(javax.
	 * portlet.RenderRequest, javax.portlet.RenderResponse)
	 */
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {

		// Inject configuration into render request.
		renderRequest.setAttribute(HystrixDemoConfiguration.class.getName(), _configuration);

		super.doView(renderRequest, renderResponse);
	}

	/**
	 * Getter for flag indicating if Hystrix should be used or not.
	 * 
	 * @return <code>true</code> if Hystrix should be used, else
	 *         <code>false</code>.
	 */
	public boolean getUserHystrix() {
		return _configuration.userHystrix();
	}

	/**
	 * Component lifecycle callback for reading configuration.
	 * 
	 * @param properties
	 *            Map of configurations.
	 */
	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		_configuration = Configurable.createConfigurable(HystrixDemoConfiguration.class, properties);
	}

	/**
	 * Hystrix demo configuration.
	 */
	private volatile HystrixDemoConfiguration _configuration;
}