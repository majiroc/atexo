package com.atexo.game.model;

import java.util.ArrayList;
import java.util.List;

public class Hand implements Cloneable{


  private List<Card>  cards;

  private List<Color>  categoryOrder;

  private List<Value>  valueOrder;
  
  
  
  
	public List<Card> getCards() {
		return cards;
	}

	public void setCards(List<Card> cards) {
		this.cards = cards;
	}

	public List<Color> getCategoryOrder() {
		return categoryOrder;
	}

	public void setCategoryOrder(List<Color> categoryOrder) {
		this.categoryOrder = categoryOrder;
	}

	public List<Value> getValueOrder() {
		return valueOrder;
	}

	public void setValueOrder(List<Value> valueOrder) {
		this.valueOrder = valueOrder;
	}

	@Override
	public String toString() {
		return "Main [cards=" + cards + ", categoryOrder=" + categoryOrder + ", valueOrder=" + valueOrder + "]";
	}

	public Object clone() {
		Hand hand = null;
		try {
			hand = (Hand) super.clone();
		} catch(CloneNotSupportedException e) {
			e.printStackTrace();
		}
		
		hand.cards = new ArrayList<Card>(this.cards.size());
		for(Card card : this.cards)
			hand.cards.add((Card) card.clone());

		return hand;
	}
  
	
}
