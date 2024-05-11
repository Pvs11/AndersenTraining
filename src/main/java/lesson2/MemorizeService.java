package lesson2;

import java.util.Set;

public interface MemorizeService {
	boolean addFlipCard(FlipCard flipCard);
//	boolean editFlipCard(FlipCard cardToEdit, FlipCard newFlipCard);
//	boolean removeFlipCard(FlipCard flipCard);
//	FlipCard getRandomCard();
	FlipCard findFlipCardByNativeWord(String nativeWord);
	Set<FlipCard> getFlipCards();

	FlipCard findFlipCardById(int id);
}
