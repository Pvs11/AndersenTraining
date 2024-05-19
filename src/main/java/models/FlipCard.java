package models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@NoArgsConstructor
@Table(name = "flipcards")
@Entity
public class FlipCard {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "native_word")
	private String nativeWord;
	@Column(name = "translation")
	private String translationWord;
	@Transient
	private int incorrectTries = 0;

	public FlipCard(String nativeWord, String translationWord) {
		this.nativeWord = nativeWord;
		this.translationWord = translationWord;
	}

	public boolean isEmpty() {
		return Objects.equals(this.getNativeWord(), "") || Objects.equals(this.getTranslationWord(), "");
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof FlipCard flipCard)) return false;
		return Objects.equals(nativeWord, flipCard.nativeWord) && Objects.equals(translationWord, flipCard.translationWord);
	}

	@Override
	public int hashCode() {
		return Objects.hash(nativeWord);
	}

	@Override
	public String toString() {
		return id + ": " + nativeWord + " - " + translationWord;
	}
}
