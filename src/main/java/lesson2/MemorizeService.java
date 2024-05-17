package lesson2;

import java.util.Set;

public interface MemorizeService {
	boolean addFlipCard(FlipCard flipCard);
	boolean editFlipCard(int oldId, FlipCard newFlipCard);
	boolean removeFlipCard(int id);
	FlipCard getRandomCard();
	FlipCard findFlipCardByNativeWord(String nativeWord);
	Set<FlipCard> getFlipCards();
	FlipCard findFlipCardById(int id);
}
