package lesson2;

import java.sql.*;
import java.util.*;

public class MemorizeServiceImpl implements MemorizeService {
	private final Database database = new Database();

	public MemorizeServiceImpl() {
	}

	@Override
	public boolean addFlipCard(FlipCard flipCard) {
		if (!getFlipCards().contains(flipCard) && !flipCard.isEmpty()) {
			Connection connection = database.getNewConnection();
			String query = "insert into flipcards (native_word, translation) values (?,?)";
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

	@Override
	public Set<FlipCard> getFlipCards() {
		Set<FlipCard> flipCards = new HashSet<>();
		Connection connection = database.getNewConnection();
		String query = "select * from flipcards";
		try (Statement statement = connection.createStatement()) {
			ResultSet rs = statement.executeQuery(query);
			while (rs.next()) {
				FlipCard newCard = new FlipCard(rs.getString("native_word"), rs.getString("translation"));
				newCard.setId(rs.getInt("id"));
				flipCards.add(newCard);
			}
			connection.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return flipCards;
	}

	@Override
	public FlipCard findFlipCardById(int id) {
		String query = "select * from flipcards where id=?";
		FlipCard flipCard = null;
		try (Connection connection = database.getNewConnection();
			 PreparedStatement ps = connection.prepareStatement(query)) {
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				flipCard = new FlipCard(rs.getString("native_word"), rs.getString("translation"));
			}
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
	public boolean editFlipCard(int idOfOldCard, FlipCard newFlipCard) {
		String query = "update flipcards set native_word=?, translation=? where id=?";
		try (Connection connection = database.getNewConnection();
			 PreparedStatement ps = connection.prepareStatement(query)) {
			ps.setString(1, newFlipCard.getNativeWord());
			ps.setString(2, newFlipCard.getTranslationWord());
			ps.setInt(3, idOfOldCard);
			int result = ps.executeUpdate();
			if (result == 1) {
				return true;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return false;
	}

	@Override
	public boolean removeFlipCard(int id) {
		String SQL = "delete from flipcards where id=?";
		try (Connection connection = database.getNewConnection();
			 PreparedStatement ps = connection.prepareStatement(SQL)) {
			ps.setInt(1, id);
			int result = ps.executeUpdate();
			if (Objects.equals(result, 1)) {
				return true;
			} else return false;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public FlipCard getRandomCard() {
		List<Integer> idList = new ArrayList<>();
		String countQuery = "select id from flipcards";
		String getRandomQuery = "select * from flipcards where id=?";
		try (Connection connection = database.getNewConnection();
			PreparedStatement ps = connection.prepareStatement(countQuery)){
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				idList.add(rs.getInt(1));
			}
			idList.sort(Comparator.comparingInt(Integer::intValue));
			int randomId = idList.get(new Random().nextInt(0, idList.size()));
			PreparedStatement ps2 = connection.prepareStatement(getRandomQuery);
			ps2.setInt(1, randomId);
			ResultSet rs2 = ps2.executeQuery();
			rs2.next();
			FlipCard randomFlipCard = new FlipCard(rs2.getString("native_word"), rs2.getString("translation"));
			ps2.close();
			return randomFlipCard;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
