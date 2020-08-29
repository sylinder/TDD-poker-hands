package com.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
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
        char[] cardChar = pokerCards.getCardChar();

        if (rank == Rank.FULL_HOUSE.getRank()) {
            int first = cardNumber[0];
            int last = cardNumber[cardNumber.length - 1];
            int diff = first == cardNumber[2] ? last : first;
            return "full house: " + cardNumber[2] + " over " + diff;
        }
        if (rank == Rank.FLUSH.getRank()) {
            return "flush: " + cardChar[0];
        }

        if (rank == Rank.STRAIGHT.getRank()) {
            return "straight: " + convertNumberToString(cardNumber[4]);
        }

        if (rank == Rank.THREE_KIND.getRank()) {
            return "three of a kind: " + convertNumberToString(cardNumber[2]);
        }

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
            return "two pairs: " + convertNumberToString(second);
        }
        if (rank == Rank.PAIR.getRank()) {
            int number = -1;
            for (int i = 0; i < cardNumber.length - 1; i++) {
                if (cardNumber[i] == cardNumber[i + 1]) {
                    number = cardNumber[i];
                }
            }
            return "pair of " + convertNumberToString(number);
        }
        return null;
    }

    public String handleEqualRank(int rank, PokerCards blackPokerCards, PokerCards whitePokerCards) {
        int[] blackNumbers = blackPokerCards.getCardNumber();
        int[] whiteNumbers = whitePokerCards.getCardNumber();

        if (rank == Rank.FULL_HOUSE.getRank()) {
            return compareFullHouse(blackNumbers, whiteNumbers);
        }

        if (rank == Rank.FLUSH.getRank()) {
            return compareFlush(blackNumbers, whiteNumbers);
        }

        if (rank == Rank.STRAIGHT.getRank()) {
            return compareStraight(blackNumbers, whiteNumbers);
        }

        if (rank == Rank.THREE_KIND.getRank()) {
            return compareThreeOfKind(blackNumbers, whiteNumbers);
        }
        
        if (rank == Rank.TWO_PAIR.getRank()) {
            return compareTwoPairs(blackNumbers, whiteNumbers);
        }

        if (rank == Rank.PAIR.getRank()) {
            return comparePair(blackNumbers, whiteNumbers);
        }

        return compareHighCard(blackNumbers, whiteNumbers);
    }

    private String compareFullHouse(int[] blackNumbers, int[] whiteNumbers) {
        if (blackNumbers[2] > whiteNumbers[2]) {
            return "Black wins. - with full house: " + blackNumbers[4] + " over " + blackNumbers[0];
        }
        return "White wins. - with full house: " + whiteNumbers[4] + " over " + whiteNumbers[0];
    }

    private String compareFlush(int[] blackNumbers, int[] whiteNumbers) {
        return blackNumbers[4] > whiteNumbers[4] ?
            "Black wins. - with flush: " + convertNumberToString(blackNumbers[4]) :
            "White wins. - with flush: " + convertNumberToString(whiteNumbers[4]);
    }

    private String compareStraight(int[] blackNumbers, int[] whiteNumbers) {
        return blackNumbers[4] > whiteNumbers[4] ?
            "Black wins. - with straight: " + convertNumberToString(blackNumbers[4]) :
            "White wins. - with straight: " + convertNumberToString(whiteNumbers[4]);
    }

    private String compareThreeOfKind(int[] blackNumbers, int[] whiteNumbers) {
        return blackNumbers[2] > whiteNumbers[2] ?
            "Black wins. - with three of a kind: " + convertNumberToString(blackNumbers[2]) :
            "White wins. - with three of a kind: " + convertNumberToString(whiteNumbers[2]);
    }

    public String compareHighCard(int[] blackNumbers, int[] whiteNumbers) {
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

    public String comparePair(int[] blackNumbers, int[] whiteNumbers) {
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
        return compareHighCard(blackNumbers, whiteNumbers);
    }

    public String compareTwoPairs(int[] blackNumbers, int[] whiteNumbers) {
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
        if (isFullHouse(pokerCards)) {
            return Rank.FULL_HOUSE.getRank();
        }
        if(isFlush(pokerCards)) {
            return Rank.FLUSH.getRank();
        }
        if (isStraight(pokerCards)) {
            return Rank.STRAIGHT.getRank();
        }
        if (isThreeOfKind(pokerCards)) {
            return Rank.THREE_KIND.getRank();
        }
        if (isTwoPair(pokerCards)) {
            return Rank.TWO_PAIR.getRank();
        }
        if (isPair(pokerCards)) {
            return Rank.PAIR.getRank();
        }
        return Rank.HIGH_CARD.getRank();
    }

    private boolean isFullHouse(PokerCards pokerCards) {
        int[] cardNumber = pokerCards.getCardNumber();
        Map<Integer, Integer> map = new HashMap<>();
        for (Integer item : cardNumber) {
            map.put(item, map.getOrDefault(item, 0) + 1);
        }
        Collection<Integer> values = map.values();
        if (values.contains(3) && values.contains(2)) {
            return true;
        }
        return false;
    }

    private boolean isFlush(PokerCards pokerCards) {
        char[] cardChar = pokerCards.getCardChar();
        Set<Character> set = new HashSet<>();
        for (Character ch : cardChar) {
            set.add(ch);
        }
        return set.size() == 1;
    }

    private boolean isStraight(PokerCards pokerCards) {
        int[] cardNumber = pokerCards.getCardNumber();
        for (int i = 0; i < cardNumber.length - 1; i++) {
            if (cardNumber[i + 1] - cardNumber[i] != 1) {
                return false;
            }
        }
        return true;
    }

    private boolean isThreeOfKind(PokerCards pokerCards) {
        int[] cardNumber = pokerCards.getCardNumber();
        Map<Integer, Integer> map = new HashMap<>();
        for (Integer item : cardNumber) {
            map.put(item, map.getOrDefault(item, 0) + 1);
        }
        int highest = 0;
        for (Integer item : map.values()) {
            if (item > highest) {
                highest = item;
            }
        }
        if (highest == 3) {
            return true;
        }
        return false;
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
