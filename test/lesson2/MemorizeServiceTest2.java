//package main.lesson2;
//
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.Set;
//
//@ExtendWith(MockitoExtension.class)
//class MemorizeServiceTest2 {
//	@Test
//	void addFlipCard_ShouldAddInstanceToDatabase() {
//		FlipCard flipCard = new FlipCard("word", "translation");
//		Database mockedDB = Mockito.mock(Database.class);
//
//		Assertions.assertTrue(mockedDB.getFlipCards().isEmpty());
//
//		Mockito.when(mockedDB.getFlipCards()).thenReturn(Set.of(flipCard));
//		Assertions.assertTrue(mockedDB.getFlipCards().contains(flipCard));
//	}
//}
