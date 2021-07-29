package com.bae.Lego;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.bae.Lego.data.Lego;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc

@Sql(scripts = { "classpath:Lego-schema.sql",
		"classpath:Lego-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)

@ActiveProfiles("test")

public class LegoControllerIntegrationTest {

	@Autowired
	private MockMvc mockMVC;

	@Autowired
	private ObjectMapper mapper;

	@Test
	void testCreate() throws Exception {

		Lego testLeg = new Lego("Disney", 1234, "Cinderella's Castle", 2019);

		String testLegAsJSON = this.mapper.writeValueAsString(testLeg);

		System.out.println(testLeg);
		System.out.println(testLegAsJSON);

		RequestBuilder request = post("/CreateLegoKit").contentType(MediaType.APPLICATION_JSON).content(testLegAsJSON);

		ResultMatcher checkStatus = status().is(201);

		Lego testCreatedLeg = new Lego("Disney", 1234, "Cinderella's Castle", 2019);
		testCreatedLeg.setId(2);
		String testCreatedLegAsJSON = this.mapper.writeValueAsString(testCreatedLeg);

		ResultMatcher checkBody = content().json(testCreatedLegAsJSON);

		this.mockMVC.perform(request).andExpect(checkStatus).andExpect(checkBody);
	}

	@Test
	void testUpdate() throws Exception {
		int id = 1;
		Lego newLego = new Lego(id, "City", 1357, "Fire Engine Cat Rescue", 2020);
		String newLegoAsJSON = this.mapper.writeValueAsString(newLego);

		RequestBuilder req = put("/replaceLegoKit/1").contentType(MediaType.APPLICATION_JSON).content(newLegoAsJSON);

		ResultMatcher checkStatus = status().isAccepted();

		ResultMatcher checkBody = content().json(newLegoAsJSON);

		this.mockMVC.perform(req).andExpect(checkStatus).andExpect(checkBody);

	}

	@Test
	void findById() throws Exception {
		RequestBuilder req = get("/getLegoKit/1");

		ResultMatcher checkStatus = status().isOk();

		Lego testLego = new Lego(1, "City", 1357, "Fire Engine Cat Rescue", 2020);

		String testLegoAsJSON = this.mapper.writeValueAsString(testLego);

		ResultMatcher checkBody = content().json(testLegoAsJSON);

		this.mockMVC.perform(req).andExpect(checkStatus).andExpect(checkBody);

	}

	@Test
	void testFindByName() throws Exception {
		RequestBuilder request = get("/getByKitName/Fire Engine Cat Rescue");

		ResultMatcher checkStatus = status().isOk();

		List<Lego> testLego = List.of(new Lego(1, "City", 1357, "Fire Engine Cat Rescue", 2020));

		String testLegoAsJSON = this.mapper.writeValueAsString(testLego);

		ResultMatcher checkBody = content().json(testLegoAsJSON);

		this.mockMVC.perform(request).andExpect(checkStatus).andExpect(checkBody);

	}

	@Test
	void testDelete() throws Exception {

		RequestBuilder request = delete("/deleteLegoKit/1");

		ResultMatcher checkStatus = status().is(204);
		ResultMatcher checkBody = content().string("Deleted: 1");

		this.mockMVC.perform(request).andExpect(checkStatus).andExpect(checkBody);
	}
}