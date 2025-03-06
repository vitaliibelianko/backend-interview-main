package org.example;

class PlayerInfo {
    private String playerName;
    private int balance;

    public PlayerInfo(String playerName, int balance) {
        this.playerName = playerName;
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }

    public String getPlayerName() {
        return playerName;
    }
}
