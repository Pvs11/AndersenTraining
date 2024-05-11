package lesson2;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class MemorizeServiceImpl implements MemorizeService {
	private final Database database = new Database();

	public MemorizeServiceImpl() {
	}

	@Override
	public Set<FlipCard> getFlipCards() {
		Set<FlipCard> flipCards = new HashSet<>();
		Connection connection = database.getNewConnection();
		String query = "select * from flipcards";
		try (Statement statement = connection.createStatement()) {
			ResultSet rs = statement.executeQuery(query);
			while (rs.next()) {
				flipCards.add(new FlipCard(rs.getString("native_word"), rs.getString("translation")));
			}
			connection.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return flipCards;
	}

	@Override
	public FlipCard findFlipCardById(int id) {
		Connection connection = database.getNewConnection();
		String query = "select * from flipcards where id=?";
		FlipCard flipCard;
		try (PreparedStatement ps = connection.prepareStatement(query)) {
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			flipCard = new FlipCard(rs.getString("native_word"), rs.getString("translation"));
			connection.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return flipCard;
	}

	@Override
	public FlipCard findFlipCardByNativeWord(String nativeWord) {
		Connection connection = database.getNewConnection();
		String query = "select * from flipcards where native_word=?";
		FlipCard flipCard;
		try (PreparedStatement ps = connection.prepareStatement(query)) {
			ps.setString(1, nativeWord);
			ResultSet rs = ps.executeQuery();
			flipCard = new FlipCard(rs.getString("native_word"), rs.getString("translation"));
			connection.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return flipCard;
	}

	@Override
	public boolean addFlipCard(FlipCard flipCard) {
		Connection connection = database.getNewConnection();
		String query = "insert into flipcards (native_word, translation) values (?,?)";
		if (!getFlipCards().contains(flipCard)) {
			try (PreparedStatement ps = connection.prepareStatement(query)) {
				ps.setString(1, flipCard.getNativeWord());
				ps.setString(2, flipCard.getTranslationWord());
				ps.execute();
				connection.close();
				return true;
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		} else return false;
	}
	//	@Override
	//	public boolean editFlipCard(FlipCard cardToEdit, FlipCard newFlipCard) {
	//		Connection connection = database.getNewConnection();
	//		String query = "select * from flipcards where native_word=? and translation=?";
	//		try (PreparedStatement ps = connection.prepareStatement(query)){
	//			ps.setString(1, cardToEdit.getNativeWord());
	//			ps.setString(2, cardToEdit.getTranslationWord());
	//			ResultSet rs = ps.executeQuery();
	//
	//		} catch (SQLException e) {
	//			throw new RuntimeException(e);
	//		}
	//		Set<FlipCard> flipCards = database.getFlipCards();
	//		flipCards.remove(cardToEdit);
	//		return flipCards.add(new FlipCard(newFlipCard.getNativeWord(), newFlipCard.getTranslationWord()));
	//	}
	//
	//	@Override
	//	public boolean removeFlipCard(FlipCard flipCard) {
	//		Set<FlipCard> flipCards = database.getFlipCards();
	//		if (flipCards.contains(flipCard)) {
	//			return database.getFlipCards().remove(flipCard);
	//		} else return false;
	//	}
	//
	//	@Override
	//	public FlipCard getRandomCard() {
	//		Set<FlipCard> flipCards = database.getFlipCards();
	//		if (flipCards.isEmpty()) {
	//			throw new FlipCardNotFoundException("Your database is empty");
	//		}
	//		int randomValue = new Random().nextInt(flipCards.size());
	//		int i = 0;
	//		FlipCard randomFlipCard = null;
	//		for (FlipCard card : flipCards) {
	//			if (i == randomValue) {
	//				randomFlipCard = card;
	//			}
	//			i++;
	//		}
	//		return randomFlipCard;
	//	}
}
