package com.atexo.game.service;


import com.atexo.game.model.Game;
import com.atexo.game.model.Hand;


public interface GameService {


	String getGameJson();//C'est la fonction qui consomme le service web offrant un jeu de carte
	Game getGameObject(String gameJson);//Convertir le resultat json en objet Game
	void sortCards(Game game);//Trier les cartes obtenues
	void affecterMainAuxCartes(Hand data);//affecter la main aux cartes pour assurer la relation bidirectionnelle 
	String verifySolution(String exerciceId,String cardsToJson);//C'est la fonction qui consomme le service web qui test le tri
	String getJsonFromGame(Game gameSorted);//convertir un objet Game en json 
}