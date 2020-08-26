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
    void should_return_white_win_when_compare_given_black_2H3D5S9CKD_and_white_2C3H4S8CAH() {
        //given
        String input = "2H 3D 5S 9C KD 2C 3H 4S 8C AH";

        //when compare
        String result = demo.compareHighCard(input);

        //then
        assertThat(result).isEqualTo("White wins. - with high card: Ace");
    }
}