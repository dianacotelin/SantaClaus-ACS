package data;

import java.util.ArrayList;
import java.util.List;

public final class Database {

    private static final Database INSTANCE = new Database();
    private Database() {

    }

    public static Database getDatabase() {
        return INSTANCE;
    }


    private int numberOfYears = 0;
    private double santaBudget = 0;
    private List<Children> childrenList = new ArrayList<>();
    private List<Gift> giftList = new ArrayList<>();
    private List<AnnualChanges> annualChangesList = new ArrayList<>();
    /**
     * Destroy Database after each round
     */
    public void destroyDatabase() {
        this.numberOfYears = 0;
        this.santaBudget = 0;
        this.childrenList = new ArrayList<>();
        this.giftList = new ArrayList<>();
        this.annualChangesList = new ArrayList<>();
    }


    public int getNumberOfYears() {
        return numberOfYears;
    }

    public void setNumberOfYears(final int numberOfYears) {
        this.numberOfYears = numberOfYears;
    }

    public double getSantaBudget() {
        return santaBudget;
    }

    public void setSantaBudget(final double santaBudget) {
        this.santaBudget = santaBudget;
    }

    public List<Children> getChildrenList() {
        return childrenList;
    }

    public void setChildrenList(final List<Children> childrenList) {
        this.childrenList = childrenList;
    }

    public List<Gift> getGiftList() {
        return giftList;
    }

    public void setGiftList(final List<Gift> giftList) {
        this.giftList = giftList;
    }

    public List<AnnualChanges> getAnnualChangesList() {
        return annualChangesList;
    }

    public void setAnnualChangesList(final List<AnnualChanges> annualChangesList) {
        this.annualChangesList = annualChangesList;
    }
}
