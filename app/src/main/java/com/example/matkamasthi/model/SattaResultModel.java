package com.example.matkamasthi.model;

public class SattaResultModel {

    String startTime;
    String endTime;
    String gameName;
    String gameResult;

    public SattaResultModel(String startTime, String endTime, String gameName) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.gameName = gameName;
    }

    public SattaResultModel(String gameResult) {
        this.gameResult = gameResult;
    }

    public String getGameResult() {
        return gameResult;
    }

    public void setGameResult(String gameResult) {
        this.gameResult = gameResult;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }
}
