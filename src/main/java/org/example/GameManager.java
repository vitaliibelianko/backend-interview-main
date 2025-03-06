package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.logging.Logger;

public class GameManager {
    private static final Logger LOGGER = Logger.getLogger(GameManager.class.getName());

    private String playerId;
    private int betAmount;
    private final Map<String, PlayerInfo> playerInfoCache = new HashMap<>();
    private final PlayerInfoRemoteService playerInfoRemoteService;
    private final Random random = new Random();

    public GameManager(int playerInfoRemoteServiceTimeOut, String playerInfoRemoteServiceUrl, String playerInfoRemoteServiceApiKey) {
        this.playerInfoRemoteService = new PlayerInfoRemoteService(playerInfoRemoteServiceUrl, playerInfoRemoteServiceApiKey, playerInfoRemoteServiceTimeOut);
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public void setBetAmount(int betAmount) {
        this.betAmount = betAmount;
    }

    public int getPlayerBalance() {
        PlayerInfo playerInfo = playerInfoCache.get(playerId);
        if (playerInfo == null) {
            LOGGER.info("Cache miss");
            playerInfo = playerInfoRemoteService.getPlayerInfoById(playerId);
            playerInfoCache.put(playerId, playerInfo);
        }
        return playerInfo.getBalance();
    }

    public int spin() {
        if (betAmount <= 0) {
            LOGGER.warning("Invalid bet amount.");
            return -1;
        }

        PlayerInfo playerInfo = playerInfoCache.get(playerId);
        if (playerInfo == null) {
            LOGGER.info("Cache miss");
            playerInfo = playerInfoRemoteService.getPlayerInfoById(playerId);
            playerInfoCache.put(playerId, playerInfo);
        }

        if (betAmount > playerInfo.getBalance()) {
            LOGGER.warning("Insufficient balance.");
            return -1;
        }
        // TODO freespins?

        int result = random.nextInt(100);
        return result;
    }

    public boolean processSpin() {
        int result;
        try {
            result = spin();
        } catch (Exception e) {
            LOGGER.severe("Spin failed: " + e.getMessage());
            return false;
        }
        LOGGER.info("Player " + playerId + " spun the reels and got: " + result);
        return true;
    }


}

