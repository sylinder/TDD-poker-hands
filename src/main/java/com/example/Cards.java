package com.example;

import java.util.Arrays;

public class Cards {
  private int[] cardNumber;
  private char[] cardChar;

  public Cards(int[] cardNumber, char[] cardChar) {
    this.cardNumber = cardNumber;
    this.cardChar = cardChar;
  }

  public Cards() {
  }

  public int[] getCardNumber() {
    return cardNumber;
  }

  public void setCardNumber(int[] cardNumber) {
    this.cardNumber = cardNumber;
  }

  public char[] getCardChar() {
    return cardChar;
  }

  public void setCardChar(char[] cardChar) {
    this.cardChar = cardChar;
  }

  @Override
  public String toString() {
    return "Cards{" +
        "cardNumber=" + Arrays.toString(cardNumber) +
        ", cardChar=" + Arrays.toString(cardChar) +
        '}';
  }
}

