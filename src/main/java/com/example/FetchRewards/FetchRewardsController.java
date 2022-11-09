package com.example.FetchRewards;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;


@RestController
@RequestMapping(path = "api/v1/rewards")
public class FetchRewardsController {
    Map<String, Integer> userMap = new HashMap<>();
    List<Transactions> transactionsList = new ArrayList<>();
    Map<String, List<Integer>> indexMap = new HashMap<>();

    int totalPoints = 0;
    int startPoint = 0;

    @PostMapping(path = "/addtransaction")
    void addTransaction(@RequestBody Transactions transaction){
        String payerName = transaction.getPayer();
        int points = transaction.getPoints();

        if(points > 0){
            if(userMap.containsKey(payerName)){
                int previousPoints = userMap.get(payerName);
                userMap.put(payerName, previousPoints + points );

            }

            else{
                userMap.put(payerName,  points );
                indexMap.put(payerName, new ArrayList<>());
            }
        }

        else if(points < 0){
            if(!userMap.containsKey(payerName)) {
                throw new IllegalStateException("Invalid Transaction - Deducting more points than expected");
            }

            else if(userMap.containsKey(payerName) && userMap.get(payerName) + points < 0){
                throw new IllegalStateException("Invalid Transaction - Deducting more points than expected");
            }

            else if(userMap.containsKey(payerName) && userMap.get(payerName) + points > 0){
                int previousPoints = userMap.get(payerName);
                userMap.put(payerName, previousPoints + points );

                List<Integer> userIndexList = indexMap.get(payerName);
                int high = userIndexList.size() -1;
                int curPoints = points;
                while(curPoints < 0) {
                    Transactions transObject = transactionsList.get(userIndexList.get(high));
                    if (transObject.getUpdatedPoints() < 0) {
                        high--;
                        continue;
                    }
                    System.out.println(transObject.getUpdatedPoints());
                    curPoints = transObject.getUpdatedPoints() + curPoints;
                    System.out.println(curPoints);
                    transObject.setUpdatedPoints(curPoints);
                    high--;
                }
            }
        }


        totalPoints += points;
        //Transactions transactionObject = new Transactions(payerName, points,)
        transaction.setTimestamp(LocalDateTime.now());
        transaction.setUpdatedPoints(points);
        transactionsList.add(transaction);
        indexMap.get(payerName).add(transactionsList.size() - 1);
    }

    @GetMapping(path = "/gettransactions")
    public List<Transactions> getTransactions(){
        return transactionsList;
    }

    @GetMapping(path = "/getuserdata")
    public List<UserData> getUserPoints(){
        List<UserData> userDataList = new ArrayList<>();

        for(String key : userMap.keySet()){

            int totalPoints = userMap.get(key);

            UserData userData = new UserData(key, totalPoints);
            userDataList.add(userData);
        }

        return userDataList;
    }

    @PutMapping(path = "/spendpoints/{points}")
    public List<UserData> spendPoints(@PathVariable("points") int points){
       List<UserData> result = new ArrayList<>();
       int spendPoints = points;
       if(totalPoints < spendPoints){
           throw new IllegalStateException("Spending more points than expected");
       }

       while(spendPoints > 0){
           Transactions trans = transactionsList.get(startPoint);
           if(trans.getUpdatedPoints() <= 0){
               startPoint++;
               continue;
           }
           int curPoints = spendPoints;
           int remainingPoints = (trans.getUpdatedPoints() - spendPoints) < 0 ? 0 : (trans.getUpdatedPoints() - spendPoints);
           spendPoints = (spendPoints - trans.getUpdatedPoints()) < 0 ? 0 : (spendPoints - trans.getUpdatedPoints());
           UserData userData = new UserData();
           userData.setUserName(trans.getPayer());
           if(remainingPoints == 0) {
               userData.setTotalPoints(trans.getUpdatedPoints());
               startPoint ++;
           }
           else
               userData.setTotalPoints(curPoints);

           trans.setUpdatedPoints(remainingPoints);
           userMap.put(trans.getPayer(), userMap.get(trans.getPayer()) - userData.getTotalPoints());
           result.add(userData);


       }
       totalPoints -= points;
       return result;
    }

}
