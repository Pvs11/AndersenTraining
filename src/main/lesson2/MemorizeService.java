package main.lesson2;

import java.util.Random;
import java.util.Set;

public class MemorizeService {
	private final Database database;

	public MemorizeService(Database database) {
		this.database = database;
	}

	public boolean addFlipCard(FlipCard flipCard) {
		Set<FlipCard> set = database.getFlipCards();
		if (!set.contains(flipCard)) {
			set.add(flipCard);
			return true;
		} else return false;
	}

	public boolean editFlipCard(FlipCard cardToEdit, FlipCard newFlipCard) {
		Set<FlipCard> flipCards = database.getFlipCards();
		flipCards.remove(cardToEdit);
		return flipCards.add(new FlipCard(newFlipCard.getNativeWord(), newFlipCard.getTranslationWords()));
	}

	public boolean removeFlipCard(FlipCard flipCard) {
		Set<FlipCard> flipCards = database.getFlipCards();
		if (flipCards.contains(flipCard)) {
			return database.getFlipCards().remove(flipCard);
		} else return false;
	}

	public FlipCard getRandomCard() {
		Set<FlipCard> flipCards = database.getFlipCards();
		if (flipCards.isEmpty()) {
			throw new FlipCardNotFoundException("Your database is empty");
		}
		int randomValue = new Random().nextInt(flipCards.size());
		int i = 0;
		FlipCard randomFlipCard = null;
		for (FlipCard card : flipCards) {
			if (i == randomValue) {
				randomFlipCard = card;
			}
			i++;
		}
		return randomFlipCard;
	}

	public FlipCard findFlipCardByNativeWord(String nativeWord) {
		return database.getFlipCards().stream().filter(fc -> fc.getNativeWord().equals(nativeWord))
				.findFirst().orElseThrow(() -> new FlipCardNotFoundException("could find flipcard by native word"));
	}
}
