package lesson2;

import java.util.HashSet;
import java.util.Set;

public class Database {
	private Set<FlipCard> flipCards = new HashSet<>();

	public Set<FlipCard> getFlipCards() {
		return flipCards;
	}

	public void setFlipCards(Set<FlipCard> flipCards) {
		this.flipCards = flipCards;
	}
}
