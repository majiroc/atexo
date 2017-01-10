package com.atexo.game.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Card implements Cloneable{

	private Color  category;
	private Value  value;
	
	@JsonIgnore
	private Hand myHand;
	
	public Color getCategory() {
		return category;
	}
	public void setCategory(Color category) {
		this.category = category;
	}
	public Value getValue() {
		return value;
	}
	public void setValue(Value value) {
		this.value = value;
	}
	public Hand getMyHand() {
		return myHand;
	}
	public void setMyHand(Hand myHand) {
		this.myHand = myHand;
	}
	
	
	
	public Object clone() {
		Object o = null;
		try {
			o = super.clone();
		} catch(CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return o;
	}
}