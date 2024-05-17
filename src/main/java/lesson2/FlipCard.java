package lesson2;

import java.util.Objects;

public class FlipCard {

	public FlipCard(String nativeWord, String translationWord) {
		this.nativeWord = nativeWord;
		this.translationWord = translationWord;
	}

	private int id;
	private String nativeWord;
	private String translationWord;
	private int incorrectTries = 0;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNativeWord() {
		return nativeWord;
	}

	public void setNativeWord(String nativeWord) {
		this.nativeWord = nativeWord;
	}

	public String getTranslationWord() {
		return translationWord;
	}

	public void setTranslationWord(String translationWord) {
		this.translationWord = translationWord;
	}

	public int getIncorrectTries() {
		return incorrectTries;
	}

	public void setIncorrectTries(int incorrectTries) {
		this.incorrectTries = incorrectTries;
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
