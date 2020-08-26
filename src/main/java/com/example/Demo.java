package com.example;

import java.util.Arrays;
import java.util.HashMap;

public class Demo {

    public String run() {
        return "ABC";
    }

    public String compareHighCard(String input) {
        Cards blackCards = new Cards();
        Cards whiteCards = new Cards();
        handleInput(input, blackCards, whiteCards);
        int[] blackNumbers = blackCards.getCardNumber();
        int[] whiteNumbers = whiteCards.getCardNumber();
        for (int index = blackNumbers.length - 1; index >= 0; index--) {
            if (blackNumbers[index] > whiteNumbers[index]) {
                String str = convertNumberToString(blackNumbers[index]);
                return "Black wins. - with high card: " + str;
            }
            if (whiteNumbers[index] > blackNumbers[index]) {
                String str = convertNumberToString(whiteNumbers[index]);
                return "White wins. - with high card: " + str;
            }
        }
        return "Tie";
    }


    public String comparePair(String input) {
        Cards blackCards = new Cards();
        Cards whiteCards = new Cards();
        handleInput(input, blackCards, whiteCards);
        int[] blackNumbers = blackCards.getCardNumber();
        int[] whiteNumbers = whiteCards.getCardNumber();

        int pairNumberOfBlack = -1;
        int pairNumberOfWhite = -1;
        for (int i = 0; i < blackNumbers.length - 1; i++) {
            if (blackNumbers[i] == blackNumbers[i + 1]) {
                pairNumberOfBlack = blackNumbers[i];
            }
        }

        for (int i = 0; i < whiteNumbers.length - 1; i++) {
            if (whiteNumbers[i] == whiteNumbers[i + 1]) {
                pairNumberOfWhite = whiteNumbers[i];
            }
        }

        if (pairNumberOfBlack == -1 && pairNumberOfWhite == -1) {
            return null;
        } else if( pairNumberOfBlack > pairNumberOfWhite) {
            return "Black wins. - with pair of " + convertNumberToString(pairNumberOfBlack);
        } else if (pairNumberOfBlack < pairNumberOfWhite) {
            return "White wins. - with pair of " + convertNumberToString(pairNumberOfWhite);
        }
        return null;

    }


    public int convertCharToNum(char ch) {
        if (ch == 'T') {
            return 10;
        }
        if (ch == 'J') {
            return 11;
        }
        if (ch == 'Q') {
            return 12;
        }
        if (ch == 'K') {
            return 13;
        }
        if (ch == 'A') {
            return 14;
        }
        return ch - '0';
    }

    public String convertNumberToString(int number) {
        if (number == 14) {
            return "Ace";
        }
        if (number == 13) {
            return "King";
        }
        if (number == 12) {
            return "Queen";
        }
        if (number == 11) {
            return "Jack";
        }
        return String.valueOf(number);
    }


    public void handleInput(String input, Cards blackCards, Cards whiteCards) {
        String[] black = new String[5];
        String[] white = new String[5];
        String[] strings = input.split(" ");
        for (int i = 0; i < 5; i++) {
            black[i] = strings[i];
        }
        for (int i = 5; i < strings.length; i++) {
            white[i - 5] = strings[i];
        }

        int[] blackNumbers = new int[5];
        char[] blackChars = new char[5];
        int[] whiteNumbers = new int[5];
        char[] whiteChars = new char[5];
        for (int i = 0; i < black.length; i++) {
            blackNumbers[i] = convertCharToNum(black[i].charAt(0));
            blackChars[i] = black[i].charAt(1);
        }
        for (int i = 0; i < black.length; i++) {
            whiteNumbers[i] = convertCharToNum(white[i].charAt(0));
            whiteChars[i] = white[i].charAt(1);
        }

        Arrays.sort(blackNumbers);
        Arrays.sort(whiteNumbers);
        blackCards.setCardNumber(blackNumbers);
        blackCards.setCardChar(blackChars);
        whiteCards.setCardNumber(whiteNumbers);
        whiteCards.setCardChar(whiteChars);
    }


}
