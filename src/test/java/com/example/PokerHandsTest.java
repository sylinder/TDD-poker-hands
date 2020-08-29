package com.example;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PokerHandsTest {
    private static PokerHands pokerHands;

    @BeforeAll
    public static void setUp() throws Exception {
        pokerHands = new PokerHands();
    }

    @Test
    public void test01() {
        String result = pokerHands.run();
        assertThat(result).isEqualTo("ABC");
    }

    @Test
    void should_return_white_win_when_compare_high_card_given_black_2H3D5S9CKD_and_white_2C3H4S8CAH() {
        //given
        String input = "2H 3D 5S 9C KD 2C 3H 4S 8C AH";

        //when compare high card
        String result = pokerHands.playGame(input);

        //then
        assertThat(result).isEqualTo("White wins. - with high card: Ace");
    }

    @Test
    void should_return_white_win_when_compare_high_card_unsorted_given_black_KH3D5S9C2D_and_white_8CAH4S3C2H() {
        //given
        String input = "KH 3D 5S 9C 2D 8C AH 4S 3C 2H";

        //when compare high card
        String result = pokerHands.playGame(input);

        //then
        assertThat(result).isEqualTo("White wins. - with high card: Ace");
    }

    @Test
    void should_return_black_win_when_compare_pair_given_black_2H2D5S9CKD_and_white_2C3H4S8CAH() {
        //given
        String input = "2H 2D 5S 9C KD 2C 3H 4S 8C AH";

        //when compare high card
        String result = pokerHands.playGame(input);

        //then
        assertThat(result).isEqualTo("Black wins. - with pair of 2");
    }

    @Test
    void should_return_black_win_when_compare_pair_given_black_2H2D5S9CKD_and_white_3C3H4S8CAH() {
        //given
        String input = "2H 2D 5S 9C KD 3C 3H 4S 8C AH";

        //when compare high card
        String result = pokerHands.playGame(input);

        //then
        assertThat(result).isEqualTo("White wins. - with pair of 3");
    }

    @Test
    void should_return_white_win_when_compare_pair_given_black_2H2DKS9C8D_and_white_2C2HAS8C7H() {
        //given
        String input = "2H 2D KS 9C 8D 2C 2H AS 8C 7H";

        //when compare high card
        String result = pokerHands.playGame(input);

        //then
        assertThat(result).isEqualTo("White wins. - with high card: Ace");
    }

    @Test
    void should_return_black_win_when_compare_two_pairs_given_black_3H3D5S9C5D_and_white_3H3D4S9C4D(){
        //given
        String input = "3H 3D 5S 9C 5D 3H 3D 4S 9C 4D";

        //when
        String result= pokerHands.playGame(input);

        //then
        assertThat(result).isEqualTo("Black wins. - with two pairs: 5");
    }

    @Test
    void should_return_black_win_when_compare_two_pairs_given_black_3H3D5S9C9D_and_white_3H3D5S9C6D(){
        //given
        String input = "3H 3D 5S 9C 9D 3H 3D 5S 9C 6D";

        //when
        String result = pokerHands.playGame(input);

        //then
        assertThat(result).isEqualTo("Black wins. - with two pairs: 9");
    }

    @Test
    void should_return_black_win_when_compare_two_pairs_given_black_3H3D5S2C5D_and_white_3H3D5S4C5D() {
        //given
        String input = "3H 3D 5S 2C 5D 3H 3D 5S 4C 5D";

        //when
        String result= pokerHands.playGame(input);

        //then
        assertThat(result).isEqualTo("White wins. - with two pairs: 4");
    }


    @Test
    void should_return_black_win_when_compare_two_pairs_given_black_3H3D5S9C5D_and_white_4H4D5S8C5D(){
        //given
        String input = "3H 3D 5S 9C 5D 4H 4D 5S 8C 5D";

        //when
        String result = pokerHands.playGame(input);

        //then
        assertThat(result).isEqualTo("White wins. - with two pairs: 4");
    }

    @Test
    void should_return_white_win_when_compare_three_of_a_kind_given_black_3H3D5S9C3D_and_white_4H4D5S9C6D(){
        //given
        String input = "3H 3D 5S 9C 3D 4H 4D 5S 9C 6D";

        //when
        String result= pokerHands.playGame(input);

        //then
        assertThat(result).isEqualTo("Black wins. - with three of a kind: 3");
    }
}