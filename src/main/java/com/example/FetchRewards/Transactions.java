package com.example.FetchRewards;


import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public class Transactions {

    private String payer;
    private int points;
    private LocalDateTime timestamp;

    private int updatedPoints ;

    public Transactions() {
    }

    public Transactions(String payer, int points) {
        this.payer = payer;
        this.points = points;
    }

    public Transactions(String payer, int points, LocalDateTime timestamp, int updatedPoints) {
        this.payer = payer;
        this.points = points;
        this.timestamp = timestamp;
        this.updatedPoints = updatedPoints;
    }

    public String getPayer() {
        return payer;
    }

    public void setPayer(String payer) {
        this.payer = payer;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public int getUpdatedPoints() {
        return updatedPoints;
    }

    public void setUpdatedPoints(int updatedPoints) {
        this.updatedPoints = updatedPoints;
    }

    @Override
    public String toString() {
        return "Transactions{" +
                "payer='" + payer + '\'' +
                ", points=" + points +
                ", timestamp=" + timestamp +
                ", updatedPoints=" + updatedPoints +
                '}';
    }
}
