package com.atexo.game.model;


public class OrderCardByValue extends OrderCard {

	@Override
	public int compare(Card c1, Card c2) {
		// TODO Auto-generated method stub
		if(c1.getMyHand().getValueOrder().indexOf(c1.getValue()) >= c2.getMyHand().getValueOrder().indexOf(c2.getValue()))
			return 1;
		return -1;
	}


}