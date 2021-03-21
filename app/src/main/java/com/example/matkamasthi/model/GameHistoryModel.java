package com.example.matkamasthi.model;

public class GameHistoryModel {

    String gameType,gameTypename,gameUsedAmount,gamePlayedDate;

    public GameHistoryModel(String gameType, String gameTypename, String gameUsedAmount, String gamePlayedDate) {
        this.gameType = gameType;
        this.gameTypename = gameTypename;
        this.gameUsedAmount = gameUsedAmount;
        this.gamePlayedDate = gamePlayedDate;
    }

    public String getGameType() {
        return gameType;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    public String getGameTypename() {
        return gameTypename;
    }

    public void setGameTypename(String gameTypename) {
        this.gameTypename = gameTypename;
    }

    public String getGameUsedAmount() {
        return gameUsedAmount;
    }

    public void setGameUsedAmount(String gameUsedAmount) {
        this.gameUsedAmount = gameUsedAmount;
    }

    public String getGamePlayedDate() {
        return gamePlayedDate;
    }

    public void setGamePlayedDate(String gamePlayedDate) {
        this.gamePlayedDate = gamePlayedDate;
    }
}
