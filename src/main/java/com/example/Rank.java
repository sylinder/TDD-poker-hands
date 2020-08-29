package com.example;

public enum Rank {
  HIGH_CARD(1),
  PAIR(2),
  TWO_PAIR(3);

  private final int rank;

  Rank(int rank) {
    this.rank = rank;
  }

  public int getRank() {
    return rank;
  }
}
