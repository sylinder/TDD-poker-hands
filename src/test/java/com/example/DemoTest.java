package com.example;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DemoTest {
    private static Demo demo;

    @BeforeAll
    public static void setUp() throws Exception {
        demo = new Demo();
    }

    @Test
    public void test01() {
        String result = demo.run();
        assertThat(result).isEqualTo("ABC");
    }

    @Test
    void should_return_white_win_when_compare_high_card_given_black_2H3D5S9CKD_and_white_2C3H4S8CAH() {
        //given
        String input = "2H 3D 5S 9C KD 2C 3H 4S 8C AH";

        //when compare high card
        String result = demo.compareHighCard(input);

        //then
        assertThat(result).isEqualTo("White wins. - with high card: Ace");
    }

    @Test
    void should_return_white_win_when_compare_high_card_unsorted_given_black_KH3D5S9C2D_and_white_8CAH4S3C2H() {
        //given
        String input = "KH 3D 5S 9C 2D 8C AH 4S 3C 2H";

        //when compare high card
        String result = demo.compareHighCard(input);

        //then
        assertThat(result).isEqualTo("White wins. - with high card: Ace");
    }

    @Test
    void should_return_black_win_when_compare_pair_given_black_2H2D5S9CKD_and_white_2C3H4S8CAH() {
        //given
        String input = "2H 2D 5S 9C KD 2C 3H 4S 8C AH";

        //when compare high card
        String result = demo.comparePair(input);

        //then
        assertThat(result).isEqualTo("Black wins. - with pair of 2");
    }
}