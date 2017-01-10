package com.atexo.game.service;


import static org.junit.Assert.assertTrue;
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
public class SecondRestServiceTest extends AbstractJUnit4SpringContextTests {
	public  final String REST_SERVICE_TEST_SORT_URI = "https://recrutement.local-trust.com/test/";
	public  final String EXERCICE_ID = "587404c2975adeb8520a4d59";
	public  final String RESULTAT ="{\"cards\":[{\"category\":\"HEART\",\"value\":\"TWO\"},{\"category\":\"HEART\",\"value\":\"FOUR\"},{\"category\":\"SPADE\",\"value\":\"FOUR\"},{\"category\":\"SPADE\",\"value\":\"SIX\"},{\"category\":\"SPADE\",\"value\":\"SEVEN\"},{\"category\":\"SPADE\",\"value\":\"KING\"},{\"category\":\"CLUB\",\"value\":\"ACE\"},{\"category\":\"CLUB\",\"value\":\"EIGHT\"},{\"category\":\"CLUB\",\"value\":\"NINE\"},{\"category\":\"CLUB\",\"value\":\"QUEEN\"}],\"categoryOrder\":[\"DIAMOND\",\"HEART\",\"SPADE\",\"CLUB\"],\"valueOrder\":[\"ACE\",\"TWO\",\"THREE\",\"FOUR\",\"FIVE\",\"SIX\",\"SEVEN\",\"EIGHT\",\"NINE\",\"TEN\",\"JACK\",\"QUEEN\",\"KING\"]}";

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
		.expect(requestTo(REST_SERVICE_TEST_SORT_URI+EXERCICE_ID))
		.andExpect(method(HttpMethod.POST))
		.andRespond(withSuccess(RESULTAT, MediaType.APPLICATION_JSON));

	}

	@Test
	public void testVerifySolution() {

		String messageResponse = gameService.verifySolution(EXERCICE_ID,RESULTAT);
		assertTrue("resultat test erroné","200".equals(messageResponse));
		mockServer.verify();
	}

	
}
