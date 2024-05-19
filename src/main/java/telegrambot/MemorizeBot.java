//package main.lesson2.telegrambot;
//
//import main.models.FlipCard;
//import main.service.MemorizeService;
//import org.apache.commons.lang3.StringUtils;
//import org.telegram.telegrambots.bots.TelegramLongPollingBot;
//import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
//import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
//import org.telegram.telegrambots.meta.api.objects.Chat;
//import org.telegram.telegrambots.meta.api.objects.Update;
//import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
//import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeChat;
//import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
//import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
//import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
//import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
//import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
//import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Objects;
//
//public class MemorizeBot extends TelegramLongPollingBot {
//	private final MemorizeService service;
//
//	public MemorizeBot(MemorizeService service) {
//		this.service = service;
//	}
//
//	@Override
//	public void onUpdateReceived(Update update) {
//		String message = update.getMessage().getText();
//		long chatId = update.getMessage().getChat().getId();
//		if (!Objects.equals(update.getMessage(), null) && !Objects.equals(update.getMessage().getText(), null)) {
//			switch (message) {
//				case "/start" -> greetUser(update);
//				case "/add" -> addNewFlipCard(chatId);
//				case "next" -> sendPartOfFlipCard(chatId);
//				default -> saveNewFlipCard(message);
//			}
//		}
//	}
//
//	private void saveNewFlipCard(String userText) {
//		String[] textInSingleQuotes = StringUtils.substringsBetween(userText, "'", "'");
//		FlipCard flipCard = new FlipCard(Arrays.asList(textInSingleQuotes).get(0), Arrays.asList(textInSingleQuotes).get(1));
//		service.addFlipCard(flipCard);
//	}
//
//	private void addNewFlipCard(long chatId) {
//		SendMessage sm = SendMessage.builder()
//				.chatId(chatId)
//				.text("please, enter native word and translation in following manner: 'word' - 'translation'")
//				.build();
//		executeSendMessage(sm);
//	}
//
//	private void sendPartOfFlipCard(long chatId) {
//		FlipCard flipCard = service.getRandomCard();
//		InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup();
//		List<InlineKeyboardButton> rows = new ArrayList<>();
//		InlineKeyboardButton check = new InlineKeyboardButton();
//		check.setText("check");
//		check.setCallbackData(flipCard.getNativeWord().toUpperCase());
//		rows.add(check);
//		List<List<InlineKeyboardButton>> severalRows = List.of(rows);
//		keyboard.setKeyboard(severalRows);
//		SendMessage sm = SendMessage.builder()
//				.text(flipCard.getNativeWord())
//				.chatId(String.valueOf(chatId))
//				.replyMarkup(keyboard)
//				.build();
//		executeSendMessage(sm);
//	}
//
//	private void greetUser(Update update) {
//		Chat chat = update.getMessage().getChat();
//		long chatId = chat.getId();
//		List<BotCommand> botCommands = new ArrayList<>();
//		botCommands.add(new BotCommand("/start", "starts this bot with basic info"));
//		botCommands.add(new BotCommand("/settings", "provides a settings"));
//		botCommands.add(new BotCommand("/add", "add new card"));
//		try {
//			execute(new SetMyCommands(botCommands, new BotCommandScopeChat(String.valueOf(chatId)), "en"));
//		} catch (TelegramApiException e) {
//			e.fillInStackTrace();
//		}
//		ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
//		KeyboardRow row = new KeyboardRow();
//		KeyboardButton postpone = new KeyboardButton("postpone");
//		KeyboardButton check = new KeyboardButton("check");
//		KeyboardButton next = new KeyboardButton("next");
//		row.add(postpone);
//		row.add(check);
//		row.add(next);
//		keyboardMarkup.setKeyboard(List.of(row));
//		keyboardMarkup.setResizeKeyboard(true);
//		keyboardMarkup.setOneTimeKeyboard(false);
//		String userName = chat.getFirstName();
//		String greetings = String.format("Hi, %s I am a MemorizeAndLearnBot, I will help you create your own dictionary and learn new words", userName);
//		SendMessage sendMessage = SendMessage.builder().chatId(String.valueOf(chatId)).text(greetings).replyMarkup(keyboardMarkup).build();
//		executeSendMessage(sendMessage);
//	}
//
//	private void executeSendMessage(SendMessage sendMessage) {
//		try {
//			execute(sendMessage);
//		} catch (TelegramApiException e) {
//			throw new RuntimeException(e);
//		}
//	}
//
//	@Override
//	public String getBotUsername() {
//		return "MemorizeAndLearnBot";
//	}
//
//	@Override
//	public String getBotToken() {
//		return "7176451140:AAGz0whpgaXLRZk5KxQFJGDGrg86MXS1A48";
//	}
//}
