package org.example;

public class Main {
    public static void main(String[] args) {
        var playerInfoRemoteServiceTimeOut = 3000;
        var playerInfoRemoteServiceUrl = "https://remoteserviceurl.com";
        var playerInfoRemoteServiceApiKey = System.getenv("playerInfoRemoteServiceApiKey");
        GameApi gameApi = new GameApi(playerInfoRemoteServiceTimeOut, playerInfoRemoteServiceUrl, playerInfoRemoteServiceApiKey);

    }
}
