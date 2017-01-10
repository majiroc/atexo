package com.atexo.game.model;


public class OrderCardByColor extends OrderCard  {



	@Override
	public int compare(Card c1, Card c2) {
		// TODO Auto-generated method stub
		if(c1.getMyHand().getCategoryOrder().indexOf(c1.getCategory()) >= c2.getMyHand().getCategoryOrder().indexOf(c2.getCategory()))
			return 1;
		return -1;
	}
}