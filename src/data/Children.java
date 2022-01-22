package data;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

public final class Children {
    private int id;
    private String lastName;
    private String firstName;
    private String city;
    private int age;
    private ArrayList<String> giftsPreferences;
    private double averageScore;
    private List<Double> niceScoreHistory;
    private double assignedBudget;
    private List<Gift> receivedGifts;
    @JsonIgnore private double niceScoreBonus;
    @JsonIgnore private String elf;

    private Children(final Builder builder) {
        this.id = builder.id;
        this.lastName = builder.lastName;
        this.firstName = builder.firstName;
        this.city = builder.city;
        this.age = builder.age;
        this.averageScore = builder.averageScore;
        this.giftsPreferences = builder.giftsPreferences;
        this.niceScoreBonus = builder.niceScoreBonus;
        this.elf = builder.elf;
    }
    public static class Builder {
        private int id;
        private String lastName;
        private String firstName;
        private String city;
        private int age;
        private ArrayList<String> giftsPreferences;
        private double averageScore;
        private List<Double> niceScoreHistory;
        private double assignedBudget;
        private List<Gift> receivedGifts;
        private double niceScoreBonus = 0;
        private String elf;
        public Builder(final int id, final String lastName, final String firstName,
                        final String city, final int age,
                        final double niceScore, final ArrayList<String> giftsPreferences,
                         final String elf) {
            this.id = id;
            this.lastName = lastName;
            this.firstName = firstName;
            this.city = city;
            this.age = age;
            this.averageScore = niceScore;
            this.giftsPreferences = giftsPreferences;
            this.elf = elf;
        }
        /**
         * Builder for scoreBonus
         */
        public Builder scoreBonus(final double niceScoreBonus) {
            this.niceScoreBonus = niceScoreBonus;
            return this;
        }
        /**
         * Build children
         */
        public Children build() {
            return new Children(this);
        }
    }
    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(final int age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(final String city) {
        this.city = city;
    }

    public ArrayList<String> getGiftsPreferences() {
        return giftsPreferences;
    }

    public void setGiftsPreferences(final ArrayList<String> giftsPreferences) {
        this.giftsPreferences = giftsPreferences;
    }

    public double getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(final double averageScore) {
        this.averageScore = averageScore;
    }

    public List<Double> getNiceScoreHistory() {
        return niceScoreHistory;
    }

    public void setNiceScoreHistory(final List<Double> niceScoreHistory) {
        this.niceScoreHistory = niceScoreHistory;
    }

    public List<Gift> getReceivedGifts() {
        return receivedGifts;
    }

    public void setReceivedGifts(final List<Gift> receivedGifts) {
        this.receivedGifts = receivedGifts;
    }

    public double getAssignedBudget() {
        return assignedBudget;
    }

    public void setAssignedBudget(final double assignedBudget) {
        this.assignedBudget = assignedBudget;
    }

    public double getNiceScoreBonus() {
        return niceScoreBonus;
    }

    public void setNiceScoreBonus(final double niceScoreBonus) {
        this.niceScoreBonus = niceScoreBonus;
    }

    public String getElf() {
        return elf;
    }

    public void setElf(final String elf) {
        this.elf = elf;
    }

    public Children(final Children otherChildren) {
        this.id = new Integer(otherChildren.id);
        this.lastName = new String(otherChildren.lastName);
        this.firstName = new String(otherChildren.firstName);
        this.city = new String(otherChildren.city);
        this.age = new Integer(otherChildren.age);
        this.averageScore = new Double(otherChildren.averageScore);
        this.giftsPreferences = new ArrayList<String>(otherChildren.giftsPreferences);
        this.assignedBudget = new Double(otherChildren.assignedBudget);
        if (otherChildren.receivedGifts != null) {
            this.receivedGifts = new ArrayList<Gift>(otherChildren.receivedGifts);
        } else {
            this.receivedGifts = new ArrayList<>();
        }
        if (otherChildren.niceScoreHistory != null) {
            this.niceScoreHistory = new ArrayList<Double>(otherChildren.niceScoreHistory);
        } else {
            this.niceScoreHistory = new ArrayList<>();
        }
        this.niceScoreBonus = new Double(otherChildren.niceScoreBonus);
        this.elf = new String(otherChildren.elf);
    }

    @Override
    public String toString() {
        return "Children{"
                +
                "id=" + id
                +
                ", lastName='" + lastName + '\''
                +
                ", firstName='" + firstName + '\''
                +
                ", city='" + city + '\''
                +
                ", age=" + age
                +
                ", giftsPreferences=" + giftsPreferences
                +
                ", averageScore=" + averageScore
                +
                ", niceScoreHistory=" + niceScoreHistory
                +
                ", assignedBudget=" + assignedBudget
                +
                ", receivedGifts=" + receivedGifts
                +
                '}';
    }
}
