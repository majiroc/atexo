


package com.atexo.game.service;

import java.net.URI;
import java.util.Date;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import com.atexo.game.model.Card;
import com.atexo.game.model.Game;
import com.atexo.game.model.Hand;
import com.atexo.game.model.OrderCardByColor;
import com.atexo.game.model.OrderCardByValue;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;


@Service("gameService")
public class GameServiceImpl implements GameService{


	final static Logger logger = Logger.getLogger(GameServiceImpl.class);
	public static final String REST_SERVICE_GET_CARDS_URI = "https://recrutement.local-trust.com/test/cards/586f7478975adeb8520a4b96";
	public static final String REST_SERVICE_TEST_SORT_URI = "https://recrutement.local-trust.com/test/";
	@Autowired
	RestTemplate restTemplate;
	@Autowired
	ObjectMapper mapper;

	/* GET */

	//C'est la fonction qui consomme le service web offrant un jeu de carte
	@Override
	public  String getGameJson(){

		String resultat=null;

		try {


			//recupere le resultat json du service web
			resultat = restTemplate.getForObject(REST_SERVICE_GET_CARDS_URI, String.class);

		} catch (HttpStatusCodeException e) {
			logger.info("Error at : " + new Date());
			logger.error("FAILED with HttpStatusCode: " + e.getStatusCode() + "|" + e.getStatusText());

		} catch (Exception e) {
			logger.info("Error at : " + new Date());
			logger.error("C'est la fonction qui consomme le service web offrant un jeu de carte : " +  e.getMessage());

		}
		return resultat;

	}


	//Convertir le resultat json en objet Game
	@Override
	public  Game getGameObject(String gameJson){

		//si l'objet Game est null on sort de la fonction
		if(gameJson==null) return null;
		
		String error="";
		try {

			
			mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			Game game = mapper.readValue(gameJson, Game.class);
			return game;


		}  catch (Exception e) {
			error = "Conversion FAILED\n" + e.getStackTrace();
			logger.info("Error at : " + new Date());
			logger.error("Convertir le resultat json en objet Game : " + error);

		}
		return null;

	}

	//Trier les cartes obtenues
	@Override
	public  void sortCards(Game game){
		try {

			//le mapping n'affecte pas l'objet Hand à chaqune de ses carte pour cela on les affecte via cette fonction
			//et ça pour benificier de cet objet lors du tri
			affecterMainAuxCartes(game.getData());
			
			//appeler les maillons de tri selon le design pattern chaine de responsabilités
			OrderCardByColor orderByColor = new OrderCardByColor();
			OrderCardByValue orderByValue = new OrderCardByValue();
			orderByValue.setNext(orderByColor);
			orderByValue.sort(game.getData().getCards());


		} catch (Exception e) {
			logger.info("Error at : " + new Date());
			logger.error("Trier les cartes obtenues : " + e.getMessage());
		}
	}

	//affecter la main aux cartes pour assurer la relation bidirectionnelle
	@Override
	public void affecterMainAuxCartes(Hand data) {
		// TODO Auto-generated method stub
		for(Card carte :data.getCards() )
			carte.setMyHand(data);
	}
	
	//Convertir la liste des cartes en json
	@Override
	public String getJsonFromGame(Game gameSorted) {
		String cardsToJson="";
		try 
		{
			
			cardsToJson = mapper.writeValueAsString(gameSorted.getData());

		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			logger.info("Error at : " + new Date());
			logger.info("Convertir la liste des cartes en json :" + e.getMessage());

		}
		return cardsToJson;
	}
	
	//C'est la fonction qui consomme le service web qui test le tri
	@Override
	public String verifySolution(String exerciceId,String cardsToJson) {
		// TODO Auto-generated method stub

		ResponseEntity<Object> messageResponse=null;
		try {

			URI uri = new URI(REST_SERVICE_TEST_SORT_URI.concat(exerciceId )) ;

			//créer les headers de la requete à envoyer voir le Content-Type
			MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
			headers.add("Content-Type", "application/json");

			//créer une entité HTTP contenante le body et le header
			HttpEntity<String> request = new HttpEntity<String>(cardsToJson, headers);
			messageResponse=restTemplate.exchange(uri,HttpMethod.POST, request, Object.class);


		} catch (HttpClientErrorException e) 
		{
			logger.info("Error at : " + new Date());
			logger.error("C'est la fonction qui consomme le service web qui test le tri : " + e.getMessage());
			return e.getStatusCode().toString();
		}catch (Exception e) {
			// TODO: handle exception
			logger.info("Error at : " + new Date());
			logger.error("C'est la fonction qui consomme le service web qui test le tri : " + e.getMessage());
			return "";
		}

		return messageResponse.getStatusCode().toString();
	}


}