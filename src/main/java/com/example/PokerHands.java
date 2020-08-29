package com.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PokerHands {

    public String run() {
        return "ABC";
    }

    public String playGame(String input) {
        PokerCards blackPokerCards = new PokerCards();
        PokerCards whitePokerCards = new PokerCards();
        handleInput(input, blackPokerCards, whitePokerCards);

        int blackRank = getRank(blackPokerCards);
        int whiteRank = getRank(whitePokerCards);

        if (blackRank < whiteRank) {
            String result = "White wins. - with ";
            result += handleNoEqualRank(whiteRank, whitePokerCards);
            return result;
        }

        if (blackRank > whiteRank) {
            String result = "Black wins. - with ";
            result += handleNoEqualRank(blackRank, blackPokerCards);
            return result;
        }

        return handleEqualRank(blackRank, blackPokerCards, whitePokerCards);

    }


    public String handleNoEqualRank(int rank, PokerCards pokerCards) {
        int[] cardNumber = pokerCards.getCardNumber();
        if (rank == Rank.TWO_PAIR.getRank()) {
            int first = -1;
            int second = -1;
            boolean isFirst = true;
            for (int i = 0; i < cardNumber.length - 1; i++) {
                if (cardNumber[i] == cardNumber[i + 1] && isFirst) {
                    first = cardNumber[i];
                    isFirst = false;
                } else if (cardNumber[i] == cardNumber[i + 1]) {
                    second = cardNumber[i];
                }
            }
            return "two pairs: " + second;
        }
        if (rank == Rank.PAIR.getRank()) {
            int number = -1;
            for (int i = 0; i < cardNumber.length - 1; i++) {
                if (cardNumber[i] == cardNumber[i + 1]) {
                    number = cardNumber[i];
                }
            }
            return "pair of " + number;
        }
        return null;
    }

    public String handleEqualRank(int rank, PokerCards blackPokerCards, PokerCards whitePokerCards) {

        if (rank == Rank.TWO_PAIR.getRank()) {
            return compareTwoPairs(blackPokerCards, whitePokerCards);
        }

        if (rank == Rank.PAIR.getRank()) {
            return comparePair(blackPokerCards, whitePokerCards);
        }

        return compareHighCard(blackPokerCards, whitePokerCards);
    }

    public String compareHighCard(PokerCards blackPokerCards, PokerCards whitePokerCards) {
        int[] blackNumbers = blackPokerCards.getCardNumber();
        int[] whiteNumbers = whitePokerCards.getCardNumber();
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


    public String comparePair(PokerCards blackPokerCards, PokerCards whitePokerCards) {
        int[] blackNumbers = blackPokerCards.getCardNumber();
        int[] whiteNumbers = whitePokerCards.getCardNumber();

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
        } else if (pairNumberOfBlack > pairNumberOfWhite) {
            return "Black wins. - with pair of " + convertNumberToString(pairNumberOfBlack);
        } else if (pairNumberOfBlack < pairNumberOfWhite) {
            return "White wins. - with pair of " + convertNumberToString(pairNumberOfWhite);
        }
        return compareHighCard(blackPokerCards, whitePokerCards);
    }

    public String compareTwoPairs(PokerCards blackPokerCards, PokerCards whitePokerCards) {
        int blackRank = getRank(blackPokerCards);
        int whiteRank = getRank(whitePokerCards);
        if (blackRank == Rank.TWO_PAIR.getRank() && whiteRank == Rank.PAIR.getRank()) {
            return "Black wins. - with two pairs: " + convertNumberToString(blackPokerCards.getCardNumber()[4]);
        }
        int[] blackNumbers = blackPokerCards.getCardNumber();
        int[] whiteNumbers = whitePokerCards.getCardNumber();

        int blackFirstPair = -1, blackSecondPair = -1, blackSingleNumber = -1;
        int whiteFirstPair = -1, whiteSecondPair = -1, whiteSingleNumber = -1;
        int index = 0;
        boolean isFirstPair = true;
        while (index < blackNumbers.length - 1) {
            if (blackNumbers[index] == blackNumbers[index + 1] && isFirstPair) {
                blackFirstPair = blackNumbers[index];
                isFirstPair = false;
                index += 2;
                continue;
            }
            if (blackNumbers[index] == blackNumbers[index + 1]) {
                blackSecondPair = blackNumbers[index];
                index += 2;
                continue;
            }
            blackSingleNumber = blackNumbers[index];
            index++;
        }

        index = 0;
        isFirstPair = true;
        while (index < whiteNumbers.length - 1) {
            if (whiteNumbers[index] == whiteNumbers[index + 1] && isFirstPair) {
                whiteFirstPair = whiteNumbers[index];
                isFirstPair = false;
                index += 2;
                continue;
            }
            if (whiteNumbers[index] == whiteNumbers[index + 1]) {
                whiteSecondPair = whiteNumbers[index];
                index += 2;
                continue;
            }
            whiteSingleNumber = whiteNumbers[index];
            index++;
        }

        if (blackSecondPair > whiteSecondPair) {
            return "Black wins. - with two pairs: " + convertNumberToString(blackSecondPair);
        } else if (whiteSecondPair > blackSecondPair) {
            return "White wins. - with two pairs: " + convertNumberToString(whiteSecondPair);
        }
        if (blackFirstPair > whiteFirstPair) {
            return "Black wins. - with two pairs: " + convertNumberToString(blackFirstPair);
        } else if (whiteFirstPair > blackFirstPair) {
            return "White wins. - with two pairs: " + convertNumberToString(whiteFirstPair);
        }
        if (blackSingleNumber > whiteSingleNumber) {
            return "Black wins. - with two pairs: " + convertNumberToString(blackSingleNumber);
        } else if (whiteSingleNumber > blackSingleNumber) {
            return "White wins. - with two pairs: " + convertNumberToString(whiteSingleNumber);
        }
        return "Tie";
    }

    public int getRank(PokerCards pokerCards) {
        if (isTwoPair(pokerCards)) {
            return Rank.TWO_PAIR.getRank();
        }
        if (isPair(pokerCards)) {
            return Rank.PAIR.getRank();
        }
        return Rank.HIGH_CARD.getRank();
    }

    private boolean isPair(PokerCards pokerCards) {
        int[] cardNumber = pokerCards.getCardNumber();
        Set<Integer> set = new HashSet<>();
        for (Integer item : cardNumber) {
            set.add(item);
        }
        return set.size() == 4;
    }

    private boolean isTwoPair(PokerCards pokerCards) {
        int[] cardNumber = pokerCards.getCardNumber();
        Map<Integer, Integer> map = new HashMap<>();
        for (Integer item : cardNumber) {
            map.put(item, map.getOrDefault(item, 0) + 1);
        }
        if (map.keySet().size() != 3) {
            return false;
        }
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (Integer item : map.values()) {
            arrayList.add(item);
        }
        arrayList.sort((o1, o2) -> o1 - o2);
        if (arrayList.get(1) != 2 && arrayList.get(2) != 2) {
            return false;
        }
        return true;
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


    public void handleInput(String input, PokerCards blackPokerCards, PokerCards whitePokerCards) {
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
        blackPokerCards.setCardNumber(blackNumbers);
        blackPokerCards.setCardChar(blackChars);
        whitePokerCards.setCardNumber(whiteNumbers);
        whitePokerCards.setCardChar(whiteChars);
    }

}
