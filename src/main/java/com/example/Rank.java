package com.example;

public enum Rank {
  STRAIGHT(5),
  THREE_KIND(4),
  TWO_PAIR(3),
  PAIR(2),
  HIGH_CARD(1);

  private final int rank;

  Rank(int rank) {
    this.rank = rank;
  }

  public int getRank() {
    return rank;
  }
}
