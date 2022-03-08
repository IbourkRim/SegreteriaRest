package it.epicode.be;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.junit.jupiter.api.Assertions.*;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;

import it.epicode.be.model.CorsoDiLaurea;
import it.epicode.be.model.Studente;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTest {

	Studente s1;
	CorsoDiLaurea cl1;

	@Autowired
	private MockMvc mockMvc;

	@Test
	@WithAnonymousUser
	public void loginNoBody() throws Exception {
		this.mockMvc.perform(post("/auth/login")).andExpect(status().isBadRequest());
	}

	@Test
	@WithAnonymousUser
	public void getAllStudenti() throws Exception {

		this.mockMvc.perform(get("/api/studente")).andExpect(status().isUnauthorized());
	}

	@Test
	@WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
	public void listaStudentiWhenUtenteMockIsAuthenticated() throws Exception {
		this.mockMvc.perform(get("/api/studente")).andExpect(status().isOk());
	}

	@Test
	@WithMockUser(username = "m.rossi", password = "test", roles = "ADMIN")
	public void addNewStudent() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(s1);
		MvcResult result = mockMvc.perform(post("/api/studente").contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isOk()).andReturn();
		
		//TESTIAMO LA COLLECTION (I CORSI DELLO STUDENTE)
		JSONObject json_obj = new JSONObject(result.getResponse().getContentAsString());
		assertTrue(json_obj.has("nome"));
		assertTrue(json_obj.has("corsoDiLaurea"));
		
		//conferma che in "corsoDiLaurea è presente un valore con "A234"
		assertTrue(json_obj.getString("corsoDiLaurea").contains("A234"));
	}

	@BeforeEach
	public void initContext() {

		cl1 = new CorsoDiLaurea();
		cl1.setCodice("A234");
		cl1.setNome("Informatica");
		cl1.setIndirizzo("Tecnologie per il Web");
		cl1.setNumeroEsami(20);

		s1 = new Studente();
		s1.setMatricola("BA345");
		s1.setNome("Mario");
		s1.setCognome("Rossi");
		s1.setIndirizzo("Via Nazionale, 35");
		s1.setCitta("Roma");
		s1.setEmail("m.rossi@email.em");
		s1.setCorsoDiLaurea(cl1);
	}

}
