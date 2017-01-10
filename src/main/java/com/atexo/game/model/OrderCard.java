package com.atexo.game.model;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public abstract class OrderCard implements Comparator<Card>{
	
	 private OrderCard next;

	public void setNext(OrderCard next) {
		this.next = next;
	}

	
	public void sort(List<Card> cartes) {
		
		Collections.sort(cartes,this);
        if(next != null) 
             next.sort(cartes);
        
    }
	
}