package com.atexo.game.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.atexo.game.model.Game;
import com.atexo.game.service.GameService;

@Controller
@RequestMapping("/")
public class GameController {

	@Autowired
	GameService gameService;  

	private Game gameNotSorted;

	@RequestMapping(value = { "/"}, method = RequestMethod.GET)
	public String homePage() {
		return "home";
	}


	@RequestMapping(value = { "/getCards"}, method = RequestMethod.GET)
	public String getCards(ModelMap model) {

		String gameJson=gameService.getGameJson();
		gameNotSorted = gameService.getGameObject(gameJson);

		model.addAttribute("gameNotSorted", gameNotSorted);

		return "getCards";
	}


	@RequestMapping(value = { "/sortCards"}, method = RequestMethod.GET)
	public String sortCards(ModelMap model) {

	
			if(gameNotSorted==null)
			{
				model.addAttribute("error", "emptyhand");
				return "sortedCards";
			}

			model.addAttribute("gameSorted", gameNotSorted);

			//cloner un objet game pour trier ses cartes tout en gardant l'originale pour afficher les deux
			Game gameSorted = (Game) gameNotSorted.clone();

			gameService.sortCards(gameSorted);
			model.addAttribute("gameSorted", gameSorted);

			
			//Convertir l'objet trié en json
			String cardsToJson=gameService.getJsonFromGame(gameSorted);
			
			//recuperer la reponse du serveur en cas d'erreur recuperer le code de celle-ci
			String response=gameService.verifySolution(gameSorted.getExerciceId(),cardsToJson);
			
			model.addAttribute("response", response);



		return "sortedCards";
	}


	@ModelAttribute("gameNotSorted")
	public Game getGameNotSorted() {
		return gameNotSorted;
	}


	public void setGameNotSorted(Game gameNotSorted) {
		this.gameNotSorted = gameNotSorted;
	}	



}
