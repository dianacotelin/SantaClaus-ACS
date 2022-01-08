package data;

import child.StrategyFactory;
import common.Constants;


import java.util.ArrayList;
import java.util.List;

public final class SantaDb {
    private int numberOfYears;
    private double santaBudget;
    private List<Children> childrenList;
    private List<Gift> giftList;
    private List<AnnualChanges> annualChangesList;

    public SantaDb(final int numberOfYears, final double santaBudget,
                   final List<Children> childrenList,
                   final List<Gift> giftList,
                   final List<AnnualChanges> annualChangesList) {
        this.numberOfYears = numberOfYears;
        this.santaBudget = santaBudget;
        this.childrenList = childrenList;
        this.giftList = giftList;
        this.annualChangesList = annualChangesList;
    }
    /**
     * This method is used for Round 0
     */

    public List<Children> roundZero() {
        List<Children> annualChildren = new ArrayList<>();

        double averageSum = 0.0;
        for (Children child : childrenList) {
            if (child.getAge() <= Constants.TEEN_AGE) {

                List<Double> history = new ArrayList<>();
                history.add(child.getAverageScore());
                child.setNiceScoreHistory(history);

                double score;

                StrategyFactory strategyFactory = new StrategyFactory();
                score = strategyFactory.createStrategy(child.getAge(), child.getNiceScoreHistory());
                child.setAverageScore(score);
                annualChildren.add(child);
                averageSum += child.getAverageScore();

            }
        }
        double budgetUnit = santaBudget / averageSum;
        for (Children child : annualChildren) {
            child.setAssignedBudget(child.getAverageScore() * budgetUnit);
            double budget = child.getAssignedBudget();
            List<Gift> receivedGifts = new ArrayList<>();
            for (String preference : child.getGiftsPreferences()) {
                double price = 0.0;
                Gift giftFinal = null;
                for (Gift gift : giftList) {
                    if (gift.getCategory().equals(preference)) {
                        if (gift.getPrice() <= budget) {
                            if (price == 0.0) {
                                price = gift.getPrice();
                                giftFinal = gift;
                            } else {
                                /* Ma asigut va aleg cadoul cu pretul
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
                if (giftFinal != null) {
                    budget -= price;
                    receivedGifts.add(giftFinal);
                    if (child.getReceivedGifts() == null) {
                        child.setReceivedGifts(receivedGifts);
                    }
                }
            }
        }
        return annualChildren;

    }
    /**
     * This method is used for the rest of the rounds
     */
    public List<Children> rounds(final int i, List<Children> annualChildrenList) {
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

        if (annualChangesList != null) {
            AnnualChanges change = annualChangesList.get(i - 1);
            santaBudget = change.getNewSantaBudget();
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
                    giftList.add(gift);
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
                    }
                }
            }
            // Setez scorul de cumintenie
            double averageSum = 0.0;
            for (Children child: annualChildrenList) {
                double score;
                StrategyFactory strategyFactory = new StrategyFactory();
                score = strategyFactory.createStrategy(child.getAge(), child.getNiceScoreHistory());
                child.setAverageScore(score);
                averageSum += child.getAverageScore();
            }
            double budgetUnit = santaBudget / averageSum;
            for (Children child : annualChildrenList) {
                child.setAssignedBudget(child.getAverageScore() * budgetUnit);
                double budget = child.getAssignedBudget();
                boolean verify = false; // verific daca se adauga vreun cadou
                List<Gift> receivedGifts = new ArrayList<>();
                for (String preference : child.getGiftsPreferences()) {
                    double price = 0.0;
                    Gift giftFinal = null;
                    for (Gift gift : giftList) {
                        if (gift.getCategory().equals(preference)) {
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
                    if (giftFinal != null) {
                        budget -= price;
                        receivedGifts.add(giftFinal);
                        if (child.getReceivedGifts() == null) {
                            child.setReceivedGifts(receivedGifts);
                        } else {
                            child.setReceivedGifts(null);
                            child.setReceivedGifts(receivedGifts);
                        }
                    }
                }
                // daca nu s-a adaugat niciun cadou, fac lista de cadouri primite nula
                if (!verify) {
                    child.setReceivedGifts(null);
                }

            }
        }

        return annualChildrenList;

    }

}
