package ru.stqa.pft.telegrambot.entity;

public enum Currency {
  USD(431), EUR(451), RUB(456), BYN(0);

  private final int id;

  public int getId() {
    return id;
  }

  Currency(int id) {
    this.id = id;
  }
}
