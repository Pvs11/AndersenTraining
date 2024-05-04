package lesson2;

import java.util.Objects;

public class FlipCard {
	public FlipCard(String nativeWord, String translationWords) {
		this.nativeWord = nativeWord;
		this.translationWords = translationWords;
	}

	private String nativeWord;
	private String translationWords;
	private int incorrectTries = 0;

	public String getNativeWord() {
		return nativeWord;
	}

	public void setNativeWord(String nativeWord) {
		this.nativeWord = nativeWord;
	}

	public String getTranslationWords() {
		return translationWords;
	}

	public void setTranslationWords(String translationWords) {
		this.translationWords = translationWords;
	}

	public int getIncorrectTries() {
		return incorrectTries;
	}

	public void setIncorrectTries(int incorrectTries) {
		this.incorrectTries = incorrectTries;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof FlipCard flipCard)) return false;
		return Objects.equals(nativeWord, flipCard.nativeWord) && Objects.equals(translationWords, flipCard.translationWords);
	}

	@Override
	public int hashCode() {
		return Objects.hash(nativeWord);
	}

	@Override
	public String toString() {
		return 	nativeWord + " - " + translationWords;
	}
}
