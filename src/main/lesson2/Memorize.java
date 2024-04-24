package main.lesson2;

import main.lesson2.telegrambot.MemorizeBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Memorize {
	public static void main(String[] args) {
		Database db = new Database();
		TelegramBotsApi telegramBotsApi = null;
		try {
			telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
			telegramBotsApi.registerBot(new MemorizeBot(new MemorizeService(db)));
		} catch (TelegramApiException e) {
			throw new RuntimeException(e);
		}


		//		FlipCard card1 = new FlipCard("река", "Der Fluss");
		//		FlipCard card2 = new FlipCard("популярный", "beliebt");
		//		FlipCard card3 = new FlipCard("актер", "Schauspieler");
		//
		//		MemorizeService service = new MemorizeService(new Database());
		//		service.addFlipCard(card1);
		//		service.addFlipCard(card2);
		//		service.addFlipCard(card3);
		//		System.out.println(service.getRandomCard());
	}
}
