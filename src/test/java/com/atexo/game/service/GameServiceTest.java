package com.atexo.game.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import com.atexo.game.model.Game;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader=AnnotationConfigContextLoader.class,classes=TestConfig.class)
public class GameServiceTest extends AbstractJUnit4SpringContextTests {
	public  final String RESULTAT ="{\"exerciceId\":\"587404c2975adeb8520a4d59\",\"dateCreation\":1483998402046,\"candidate\":{\"candidateId\":\"586f7478975adeb8520a4b96\",\"firstName\":\"Abdelmjid\",\"lastName\":\"Elkihel\"},\"data\":{\"cards\":[{\"category\":\"CLUB\",\"value\":\"QUEEN\"},{\"category\":\"HEART\",\"value\":\"FOUR\"},{\"category\":\"SPADE\",\"value\":\"SIX\"},{\"category\":\"CLUB\",\"value\":\"EIGHT\"},{\"category\":\"HEART\",\"value\":\"TWO\"},{\"category\":\"CLUB\",\"value\":\"NINE\"},{\"category\":\"SPADE\",\"value\":\"KING\"},{\"category\":\"SPADE\",\"value\":\"FOUR\"},{\"category\":\"CLUB\",\"value\":\"ACE\"},{\"category\":\"SPADE\",\"value\":\"SEVEN\"}],\"categoryOrder\":[\"DIAMOND\",\"HEART\",\"SPADE\",\"CLUB\"],\"valueOrder\":[\"ACE\",\"TWO\",\"THREE\",\"FOUR\",\"FIVE\",\"SIX\",\"SEVEN\",\"EIGHT\",\"NINE\",\"TEN\",\"JACK\",\"QUEEN\",\"KING\"]},\"name\":\"cards\"}";
	public  final String JSON_CARDS_NOT_SORTED ="{\"exerciceId\":\"587404c2975adeb8520a4d59\",\"dateCreation\":1483998402046,\"candidate\":{\"candidateId\":\"586f7478975adeb8520a4b96\",\"firstName\":\"Abdelmjid\",\"lastName\":\"Elkihel\"},\"data\":{\"cards\":[{\"category\":\"CLUB\",\"value\":\"QUEEN\"},{\"category\":\"HEART\",\"value\":\"FOUR\"},{\"category\":\"SPADE\",\"value\":\"SIX\"},{\"category\":\"CLUB\",\"value\":\"EIGHT\"},{\"category\":\"HEART\",\"value\":\"TWO\"},{\"category\":\"CLUB\",\"value\":\"NINE\"},{\"category\":\"SPADE\",\"value\":\"KING\"},{\"category\":\"SPADE\",\"value\":\"FOUR\"},{\"category\":\"CLUB\",\"value\":\"ACE\"},{\"category\":\"SPADE\",\"value\":\"SEVEN\"}],\"categoryOrder\":[\"DIAMOND\",\"HEART\",\"SPADE\",\"CLUB\"],\"valueOrder\":[\"ACE\",\"TWO\",\"THREE\",\"FOUR\",\"FIVE\",\"SIX\",\"SEVEN\",\"EIGHT\",\"NINE\",\"TEN\",\"JACK\",\"QUEEN\",\"KING\"]},\"name\":\"cards\"}";
	public  final String JSON_CARDS_SORTED ="{\"exerciceId\":\"587404c2975adeb8520a4d59\",\"dateCreation\":1483998402046,\"candidate\":{\"candidateId\":\"586f7478975adeb8520a4b96\",\"firstName\":\"Abdelmjid\",\"lastName\":\"Elkihel\"},\"data\":{\"cards\":[{\"category\":\"HEART\",\"value\":\"TWO\"},{\"category\":\"HEART\",\"value\":\"FOUR\"},{\"category\":\"SPADE\",\"value\":\"FOUR\"},{\"category\":\"SPADE\",\"value\":\"SIX\"},{\"category\":\"SPADE\",\"value\":\"SEVEN\"},{\"category\":\"SPADE\",\"value\":\"KING\"},{\"category\":\"CLUB\",\"value\":\"ACE\"},{\"category\":\"CLUB\",\"value\":\"EIGHT\"},{\"category\":\"CLUB\",\"value\":\"NINE\"},{\"category\":\"CLUB\",\"value\":\"QUEEN\"}],\"categoryOrder\":[\"DIAMOND\",\"HEART\",\"SPADE\",\"CLUB\"],\"valueOrder\":[\"ACE\",\"TWO\",\"THREE\",\"FOUR\",\"FIVE\",\"SIX\",\"SEVEN\",\"EIGHT\",\"NINE\",\"TEN\",\"JACK\",\"QUEEN\",\"KING\"]},\"name\":\"cards\"}";


	@Autowired
	ObjectMapper mapper;

	@Autowired
	private GameService gameService;

	@Test
	public  void testGetGameObject() {

		Game game =gameService.getGameObject(RESULTAT);
		assertNotNull("Erreur mapping fichier json vers objet Game ",game);
	}

	@Test
	public  void testGetGameObjectCards() {

		Game game =gameService.getGameObject(RESULTAT);
		assertFalse("Erreur mapping des cartes ", game.getData().getCards().size()==0);
	}

	@Test
	public  void testSortCards() throws JsonParseException, JsonMappingException, IOException {
		
		Game gameNotSorted = mapper.readValue(JSON_CARDS_NOT_SORTED, Game.class);
		gameService.sortCards(gameNotSorted);
		String resultat = mapper.writeValueAsString(gameNotSorted);
		assertTrue("Erreur de tri des cartes ", JSON_CARDS_SORTED.equals(resultat));


	}

}
