package com.example.FetchRewards;

public class UserData {
    private String userName;
    private int totalPoints;

    public UserData() {
    }

    public UserData(String userName, int totalPoints) {
        this.userName = userName;
        this.totalPoints = totalPoints;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "userName='" + userName + '\'' +
                ", totalPoints=" + totalPoints +
                '}';
    }
}
