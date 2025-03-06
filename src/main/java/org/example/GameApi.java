package org.example;

import java.util.Map;

public class GameApi {

    private final GameManager gameManager;

    public GameApi(int playerInfoRemoteServiceTimeOut, String playerInfoRemoteServiceUrl, String playerInfoRemoteServiceApiKey) {
        this.gameManager = new GameManager(playerInfoRemoteServiceTimeOut, playerInfoRemoteServiceUrl, playerInfoRemoteServiceApiKey);
    }

    public Map<String, Object> spin(String playerId, int betAmount) {
        gameManager.setPlayerId(playerId);
        gameManager.setBetAmount(betAmount);

        boolean success = gameManager.processSpin();

        return Map.of("success", success);
    }

    public Map<String, Object> getPlayerBalance(String playerId) {
        gameManager.setPlayerId(playerId);

        int playerBalance = gameManager.getPlayerBalance();

        return Map.of("playerBalance", playerBalance);
    }
}
