package org.example;

class PlayerInfoRemoteService {
    private final String url;
    private final String apiKey;
    private final int timeOut;

    public PlayerInfoRemoteService(String url, String apiKey, int timeOut) {
        this.url = url;
        this.apiKey = apiKey;
        this.timeOut = timeOut;
    }

    public PlayerInfo getPlayerInfoById(String playerId) {
        // simulate remote call
        try {
            Thread.sleep(100);
            return new PlayerInfo(playerId, 0);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
