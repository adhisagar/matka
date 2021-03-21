package com.example.matkamasthi.model;

public class WalletHistoryRecyclerModel {

    private String gameType,status,playAmount,playedDate,walletPoint,walletFieldWin,gameComments;

    public WalletHistoryRecyclerModel(String gameType, String status, String playAmount, String playedDate, String walletPoint, String walletFieldWin,String gameComments) {
        this.gameType = gameType;
        this.status = status;
        this.playAmount = playAmount;
        this.playedDate = playedDate;
        this.walletPoint = walletPoint;
        this.walletFieldWin = walletFieldWin;
        this.gameComments=gameComments;
    }


    public String getGameType() {
        return gameType;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPlayAmount() {
        return playAmount;
    }

    public void setPlayAmount(String playAmount) {
        this.playAmount = playAmount;
    }

    public String getPlayedDate() {
        return playedDate;
    }

    public void setPlayedDate(String playedDate) {
        this.playedDate = playedDate;
    }

    public String getWalletPoint() {
        return walletPoint;
    }

    public void setWalletPoint(String walletPoint) {
        this.walletPoint = walletPoint;
    }

    public String getWalletFieldWin() {
        return walletFieldWin;
    }

    public void setWalletFieldWin(String walletFieldWin) {
        this.walletFieldWin = walletFieldWin;
    }

    public String getGameComments() {
        return gameComments;
    }

    public void setGameComments(String gameComments) {
        this.gameComments = gameComments;
    }
}
