package com.atexo.game.service;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;





@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader=AnnotationConfigContextLoader.class,classes=TestConfig.class)
public class FirstRestServiceTest extends AbstractJUnit4SpringContextTests {
	public  final String REST_SERVICE_GET_CARDS_URI = "https://recrutement.local-trust.com/test/cards/586f7478975adeb8520a4b96";
	public  final String RESULTAT = "{\"exerciceId2\":\"58717407975adeb8520a4c8b\",\"dateCreation\":1483830279863,\"candidate\":{\"candidateId\":\"57187b7c975adeb8520a283c\",\"firstName\":\"Othmane\",\"lastName\":\"QABLAOUI\"},\"data\":{\"cards\":[{\"category\":\"HEART\",\"value\":\"TEN\"},{\"category\":\"DIAMOND\",\"value\":\"FIVE\"},{\"category\":\"CLUB\",\"value\":\"SEVEN\"},{\"category\":\"CLUB\",\"value\":\"FIVE\"},{\"category\":\"DIAMOND\",\"value\":\"JACK\"},{\"category\":\"DIAMOND\",\"value\":\"KING\"},{\"category\":\"HEART\",\"value\":\"FOUR\"},{\"category\":\"CLUB\",\"value\":\"EIGHT\"},{\"category\":\"DIAMOND\",\"value\":\"SIX\"},{\"category\":\"SPADE\",\"value\":\"NINE\"}],\"categoryOrder\":[\"DIAMOND\",\"HEART\",\"SPADE\",\"CLUB\"],\"valueOrder\":[\"ACE\",\"TWO\",\"THREE\",\"FOUR\",\"FIVE\",\"SIX\",\"SEVEN\",\"EIGHT\",\"NINE\",\"TEN\",\"JACK\",\"QUEEN\",\"KING\"]},\"name\":\"cards\"}";


	@Autowired
	private GameService gameService;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	ObjectMapper mapper;
	
	private MockRestServiceServer mockServer;

	@Before
	public void setUp() {

		mockServer = MockRestServiceServer.createServer(restTemplate);
		mockServer
		.expect(requestTo(REST_SERVICE_GET_CARDS_URI))
		.andExpect(method(HttpMethod.GET))
		.andRespond(withSuccess(RESULTAT, MediaType.APPLICATION_JSON));

	}

	@Test
	public void testGetGameJson() {

		String gameJson = gameService.getGameJson();
		assertNotNull("Erreur recupération fichier json du service web",gameJson);
		mockServer.verify();
	}



	
}
