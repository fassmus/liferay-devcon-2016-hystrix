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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Unit test for the Chuck Norris facts service.
 * 
 * @author Florian Assmus, PRODYNA AG
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ChuckNorrisFactControllerTests {

	/**
	 * Mock for String MVC.
	 */
	@Autowired
	private MockMvc mockMvc;

	/**
	 * Test the very simple Chuck Norris facts service.
	 * 
	 * @throws Exception
	 *             is thrown if something goes wrong.
	 */
	@Test
	public void noParamChuckNorrisFactShouldReturnFactAboutChuckNorris() throws Exception {
		this.mockMvc.perform(get("/chucknorrisfact")).andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$.fact").exists());
	}

}
