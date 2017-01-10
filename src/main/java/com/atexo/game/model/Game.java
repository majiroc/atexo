package com.atexo.game.model;

import java.util.Date;

public class Game implements Cloneable{

	private String exerciceId;

	private Date dateCreation;

	private User candidate;


	private Hand data;

	private String name;

	public String getExerciceId() {
		return exerciceId;
	}

	public void setExerciceId(String exerciceId) {
		this.exerciceId = exerciceId;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public User getCandidate() {
		return candidate;
	}

	public void setCandidate(User candidate) {
		this.candidate = candidate;
	}

	public Hand getData() {
		return data;
	}

	public void setData(Hand data) {
		this.data = data;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Jeu [exerciceId=" + exerciceId + ", dateCreation=" + dateCreation + ", candidate=" + candidate + ", data="
				+ data + ", name=" + name + "]";
	}

	public Object clone() {
	    Game game = null;
	    try {
	    	game = (Game) super.clone();
	    } catch(CloneNotSupportedException e) {
	      	e.printStackTrace();
	    }
	    
	    game.candidate = (User) candidate.clone();
	    game.data = (Hand) data.clone();
	    
	    return game;
	}	  
}