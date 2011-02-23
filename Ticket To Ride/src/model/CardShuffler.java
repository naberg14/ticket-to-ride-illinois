package model;

import java.util.LinkedList;

public class CardShuffler {

	public LinkedList<Card> shuffleCards (Card[] arrayOfCards) {
		
		for (int i = arrayOfCards.length - 1; i > 0; i--) {
			int index = (int)Math.round(Math.random() * (double)(i + 1));
			Card temp = arrayOfCards[index];
			arrayOfCards[index] = arrayOfCards[i];
			arrayOfCards[i] = temp;
		}
		LinkedList<Card> shuffled = new LinkedList<Card>();
		for (int i = 0; i < arrayOfCards.length;  i++) {
			shuffled.add(arrayOfCards[i]);
		}
		return shuffled;
	}
	
	public LinkedList<Route> shuffleRoutes(Route[] arrayOfRoutes) {
		for (int i = arrayOfRoutes.length - 1; i > 0; i--) {
			int index = (int)Math.round(Math.random() * (double)(i + 1));
			Route temp = arrayOfRoutes[index];
			arrayOfRoutes[index] = arrayOfRoutes[i];
			arrayOfRoutes[i] = temp;
		}
		LinkedList<Route> shuffled = new LinkedList<Route>();
		for (int i = 0; i < arrayOfRoutes.length;  i++) {
			shuffled.add(arrayOfRoutes[i]);
		}
		return shuffled;
	}
}
