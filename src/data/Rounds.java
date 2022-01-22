package data;

import child.StrategyFactory;
import common.Constants;
import strategy.GiftFactory;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public final class Rounds {

    private Rounds() {

    }
    /**
     * This method is used for Round 0
     */

    public static List<Children> roundZero() {
        List<Children> annualChildren = new ArrayList<>();

        double averageSum = 0.0;
        for (Children child : Database.getDatabase().getChildrenList()) {
            if (child.getAge() <= Constants.TEEN_AGE) {

                List<Double> history = new ArrayList<>();
                history.add(child.getAverageScore());
                child.setNiceScoreHistory(history);
                double score;

                StrategyFactory strategyFactory = new StrategyFactory();
                score = strategyFactory.createStrategy(child.getAge(), child.getNiceScoreHistory());
                if (child.getNiceScoreBonus() != 0) {
                    score += score * child.getNiceScoreBonus() / Constants.HUNDRED;
                    if (score > Constants.TEN) {
                        score = Constants.TEN;
                    }
                }
                child.setAverageScore(score);
                annualChildren.add(child);
                averageSum += child.getAverageScore();

            }
        }
        double budgetUnit = Database.getDatabase().getSantaBudget() / averageSum;
        for (Children child : annualChildren) {
            child.setAssignedBudget(child.getAverageScore() * budgetUnit);
            if (child.getElf().equals("pink")) {
                double newBudget = child.getAssignedBudget();
                newBudget += newBudget * Constants.PERCENT / Constants.HUNDRED;
                child.setAssignedBudget(newBudget);
            }
            if (child.getElf().equals("black")) {
                double newBudget = child.getAssignedBudget();
                newBudget -= newBudget * Constants.PERCENT / Constants.HUNDRED;
                child.setAssignedBudget(newBudget);
            }
            double budget = child.getAssignedBudget();
            List<Gift> receivedGifts = new ArrayList<>();
            for (String preference : child.getGiftsPreferences()) {
                double price = 0.0;
                Gift giftFinal = null;
                for (Gift gift : Database.getDatabase().getGiftList()) {
                    if (gift.getCategory().equals(preference)) {
                        if (gift.getQuantity() > 0) {
                            if (gift.getPrice() <= budget) {
                                if (price == 0.0) {
                                    price = gift.getPrice();
                                    giftFinal = gift;
                                } else {
                                /* Ma asigur ca aleg cadoul cu pretul
                                cel mai mic
                                 */
                                    if (price > gift.getPrice()) {
                                        price = gift.getPrice();
                                        giftFinal = gift;
                                    }
                                }
                            }
                        }
                    }
                }
                if (giftFinal != null) {
                    budget -= price;
                    receivedGifts.add(giftFinal);
                    if (child.getReceivedGifts() == null) {
                        child.setReceivedGifts(receivedGifts);
                    }
                    // Scad cantitatea cadoului primit
                    for (Gift gift : Database.getDatabase().getGiftList()) {
                        if (gift.getProductName().equals(giftFinal.getProductName())) {
                            gift.setQuantity(gift.getQuantity() - 1);
                        }
                    }
                }
            }
            if (receivedGifts.size() == 0) {
                if (child.getElf().equals("yellow")) {
                    String preferenceElf = child.getGiftsPreferences().get(0);
                    double priceGift = Constants.BIG_PRICE;
                    Gift giftElf = null;
                    for (Gift gift : Database.getDatabase().getGiftList()) {
                        if (gift.getCategory().equals(preferenceElf)) {
                            if (gift.getPrice() < priceGift) {
                                giftElf = gift;
                                priceGift = gift.getPrice();
                            }
                        }
                    }
                    if (giftElf != null) {
                        if (giftElf.getQuantity() != 0) {
                            receivedGifts.add(giftElf);
                            child.setReceivedGifts(receivedGifts);
                        }
                    }
                }
            }
        }
        return annualChildren;

    }
    /**
     * This method is used for the rest of the rounds
     */
    public static List<Children> rounds(final int i, List<Children> annualChildrenList) {
        // Incrementez varsta pentru copiii existenti
        if (annualChildrenList != null) {
            List<Children> tempChildren = new ArrayList<>();
            for (Children child : annualChildrenList) {
                if (child.getAge() < Constants.TEEN_AGE) {
                    child.setAge(child.getAge() + 1);
                    tempChildren.add(child);
                }
            }
            annualChildrenList = new ArrayList<>();
            annualChildrenList = tempChildren;
        }

        if (Database.getDatabase().getAnnualChangesList() != null) {
            AnnualChanges change = Database.getDatabase().getAnnualChangesList().get(i - 1);
            Database.getDatabase().setSantaBudget(change.getNewSantaBudget());
            // Adaug copiii noi
            for (Children child : change.getNewChildren()) {
                if (child.getAge() <= Constants.TEEN_AGE) {
                    annualChildrenList.add(child);
                }
            }
            // Adaug cadourile noi
            if (change.getGifts() != null) {
                List<Gift> newGifts = change.getGifts();
                for (Gift gift: newGifts) {
                    Database.getDatabase().getGiftList().add(gift);
                }
            }

            List<ChildUpdate> updates = change.getChildrenUpdates();

            for (ChildUpdate update : updates) {

                for (Children child : annualChildrenList) {
                    if (child.getId() == update.getId()) {
                        /* Modific niceScore-ul, in utils la convertJSONChildUpdate am pus
                         scorul -1 acolo unde era null
                         */
                        if (update.getNiceScore() >= 0) {
                            if (child.getNiceScoreHistory() != null) {
                                child.getNiceScoreHistory().add(update.getNiceScore());
                            } else {
                                List<Double> tempScore = new ArrayList<>();
                                tempScore.add(update.getNiceScore());
                                child.setNiceScoreHistory(tempScore);
                            }
                        }
                        // Modific lista de preferinte
                        if (update.getGiftsPreferences() != null) {
                            List<String> gifts = new ArrayList<>();
                            for (String gift : update.getGiftsPreferences()) {
                                if (gifts == null) {
                                    gifts.add(gift);
                                }
                                // Verific daca sunt dubluri in lista
                                if (!gifts.contains(gift)) {
                                    gifts.add(gift);
                                }
                            }
                            for (String gift : child.getGiftsPreferences()) {
                                int verify = 0;
                                // Verific daca exista preferinta in lista initiala
                                for (String gift2: gifts) {
                                    if (gift.equals(gift2)) {
                                        verify = 1;
                                        break;
                                    }
                                }
                                if (verify == 0) {
                                    gifts.add(gift);
                                }
                            }
                            child.setGiftsPreferences(null);
                            child.setGiftsPreferences((ArrayList<String>) gifts);
                        }
                        //modific elful
                        child.setElf(update.getElf());
                    }
                }
            }
            // Setez scorul de cumintenie
            double averageSum = 0.0;
            for (Children child: annualChildrenList) {
                double score;
                StrategyFactory strategyFactory = new StrategyFactory();
                score = strategyFactory.createStrategy(child.getAge(), child.getNiceScoreHistory());
                if (child.getNiceScoreBonus() != 0) {
                    score += score * child.getNiceScoreBonus() / Constants.HUNDRED;
                    if (score > Constants.TEN) {
                        score = Constants.TEN;
                    }
                }
                child.setAverageScore(score);
                averageSum += child.getAverageScore();
            }
            double budgetUnit = Database.getDatabase().getSantaBudget() / averageSum;
            GiftFactory giftStrategy = new GiftFactory();
            HashMap<String, Double> sortedChildren = giftStrategy.
                    createStrategy(change.getStrategy(), annualChildrenList);
            if (change.getStrategy().equals("niceScoreCity")) {
                // sortedChildren este un HashMap cu orase si niceScore-ul lor
                for (String key: sortedChildren.keySet()) {
                    for (Children child : annualChildrenList) {
                        if (child.getCity().equals(key)) {
                            assignGift(child, budgetUnit);
                        }

                    }
                }
            } else if (change.getStrategy().equals("niceScore")) {
                for (String key: sortedChildren.keySet()) {
                    // sortedChildren este un HashMap cu id-uri si niceScore-uri
                    for (Children child : annualChildrenList) {
                        if (String.valueOf(child.getId()).equals(key)) {
                            assignGift(child, budgetUnit);
                        }

                    }
                }
            } else {
                for (Children child : annualChildrenList) {
                    assignGift(child, budgetUnit);
                }
            }
        }

        return annualChildrenList;

    }
    /**
     * This method is used to assign gifts
     */
    public static void assignGift(final Children child, final Double budgetUnit) {
        child.setAssignedBudget(child.getAverageScore() * budgetUnit);
        if (child.getElf().equals("pink")) {
            double newBudget = child.getAssignedBudget();
            newBudget += newBudget * Constants.PERCENT / Constants.HUNDRED;
            child.setAssignedBudget(newBudget);
        }
        if (child.getElf().equals("black")) {
            double newBudget = child.getAssignedBudget();
            newBudget -= newBudget * Constants.PERCENT / Constants.HUNDRED;
            child.setAssignedBudget(newBudget);
        }
        double budget = child.getAssignedBudget();
        boolean verify = false; // verific daca se adauga vreun cadou
        List<Gift> receivedGifts = new ArrayList<>();
        for (String preference : child.getGiftsPreferences()) {
            double price = 0.0;
            Gift giftFinal = null;
            for (Gift gift : Database.getDatabase().getGiftList()) {
                if (gift.getCategory().equals(preference)) {
                    if (gift.getQuantity() > 0) {
                        if (gift.getPrice() <= budget) {
                            if (price == 0.0) {
                                price = gift.getPrice();
                                giftFinal = gift;
                                verify = true;
                            } else {
                                if (price > gift.getPrice()) {
                                    price = gift.getPrice();
                                    giftFinal = gift;
                                }
                            }
                        }
                    }
                }
            }
            if (giftFinal != null) {
                budget -= price;
                receivedGifts.add(giftFinal);
                if (child.getReceivedGifts() == null) {
                    child.setReceivedGifts(receivedGifts);
                } else {
                    child.setReceivedGifts(null);
                    child.setReceivedGifts(receivedGifts);
                }
                for (Gift gift : Database.getDatabase()
                        .getGiftList()) {
                    if (gift.getProductName()
                            .equals(giftFinal.getProductName())) {
                        gift.setQuantity(gift.getQuantity() - 1);
                    }
                }
            }
        }
        if (!verify) {
            if (child.getElf().equals("yellow")) {
                String preferenceElf = child.getGiftsPreferences().get(0);
                double priceGift = Constants.BIG_PRICE;
                Gift giftElf = null;
                for (Gift gift : Database.getDatabase().getGiftList()) {
                    if (gift.getCategory().equals(preferenceElf)) {
                        if (gift.getPrice() < priceGift) {
                            giftElf = gift;
                            priceGift = gift.getPrice();
                        }
                    }
                }
                if (giftElf != null) {
                    if (giftElf.getQuantity() != 0) {
                        receivedGifts.add(giftElf);
                        child.setReceivedGifts(receivedGifts);
                        verify = true;
                        for (Gift gift : Database.getDatabase().getGiftList()) {
                            if (gift.getProductName()
                                    .equals(giftElf.getProductName())) {
                                gift.setQuantity(gift.getQuantity() - 1);
                            }
                        }
                    }
                }
            }
        }
            // daca nu s-a adaugat niciun cadou, fac lista de cadouri primite nula
        if (!verify) {
            child.setReceivedGifts(null);
        }

    }


}
