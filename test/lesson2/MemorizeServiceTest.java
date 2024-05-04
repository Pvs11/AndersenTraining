//package main.lesson2;
//
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.junit.jupiter.api.extension.Extensions;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.MethodSource;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.stream.Stream;
//
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//@ExtendWith(EnvironmentExtension.class)
//class MemorizeServiceTest {
//	private final Database db = new Database();
//	private final MemorizeService service = new MemorizeService(db);
//
//	@Test
//	@DisplayName("add new FlipCard to database and return true")
//	void givenFlipCard_whenAddFlipCardToDatabase_thenAddToDatabaseAndReturnTrue() throws InterruptedException {
//		FlipCard newFlipCard = new FlipCard("Das Gebäude", "здание");
//		Assertions.assertTrue(service.addFlipCard(newFlipCard), "method should return true if successful");
//
//	}
//
//	@Test
//	@DisplayName("update an existing FlipCard in database and return true")
//	void givenFlipCard_whenEditExistingFlipCard_thenUpdateFlipCardAndReturnTrue() throws InterruptedException {
//		FlipCard flipCardToEdit = new FlipCard("Das Gebäude", "здание");
//		FlipCard newFlipCard = new FlipCard("lecker", "вкусный");
//		service.addFlipCard(flipCardToEdit);
//		Assertions.assertAll(
//				() -> assertTrue(service.editFlipCard(flipCardToEdit, newFlipCard), " method should return true if FlipCard is updated"),
//				() -> assertTrue(db.getFlipCards().contains(newFlipCard), "should contain edited FlipCard"),
//				() -> assertFalse(db.getFlipCards().contains(flipCardToEdit), "should not contain previous FlipCard after editing")
//		);
//	}
//
//	@ParameterizedTest
//	@MethodSource("provideFlipCards")
//	@DisplayName("remove specified FlipCard if exists and return true, otherwise return false")
//	void givenDatabaseOfFlipCards_whenRemoveSpecifiedFlipCard_thenUpdateDatabaseAndReturnTrue(FlipCard flipCard) {
//		service.addFlipCard(flipCard);
//		Assertions.assertTrue(service.removeFlipCard(flipCard));
//		Assertions.assertFalse(db.getFlipCards().contains(flipCard));
//	}
//
//	@ParameterizedTest
//	@MethodSource("provideFlipCards")
//	@DisplayName("get random FlipCard from database if database is not empty")
//	void givenNotEmptyDatabase_whenGetRandomFlipCard_thenReturnFlipCard(FlipCard flipCard) {
//		Assertions.assertNotNull(flipCard);
//	}
//
//	@Test
//	@DisplayName("throw an exception if database is empty")
//	void givenEmptyDatabase_whenGetRandomFlipCard_thenThrowFlipCardNotFoundException() {
//		Assertions.assertThrows(FlipCardNotFoundException.class, service::getRandomCard);
//	}
//
//	@Test
//	@DisplayName("find FlipCard by native word")
//	void givenNativeWord_whenFindFlipCardByNativeWord_thenReturnFlipCard() {
//		FlipCard flipCard1 = new FlipCard("gesund", "здоровый");
//		service.addFlipCard(flipCard1);
//		Assertions.assertEquals(flipCard1, service.findFlipCardByNativeWord("gesund"));
//	}
//
//	private static Stream<FlipCard> provideFlipCards() {
//		return Stream.<FlipCard>builder()
//				.add(new FlipCard("gesund", "здоровый"))
//				.add(new FlipCard("krank", "больной"))
//				.add(new FlipCard("Berg", "гора"))
//				.add(new FlipCard("Schauspieler", "актер"))
//				.build();
//	}
//}