package data;

import java.util.ArrayList;

public final class ChildUpdate {
    private int id;
    private double niceScore;
    private ArrayList<String> giftsPreferences;

    public ChildUpdate(final int id, final double niceScore,
                       final ArrayList<String> giftsPreferences) {
        this.id = id;
        this.niceScore = niceScore;
        this.giftsPreferences = giftsPreferences;
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public double getNiceScore() {
        return niceScore;
    }

    public void setNiceScore(final double niceScore) {
        this.niceScore = niceScore;
    }

    public ArrayList<String> getGiftsPreferences() {
        return giftsPreferences;
    }

    public void setGiftsPreferences(final ArrayList<String> giftsPreferences) {
        this.giftsPreferences = giftsPreferences;
    }
    public ChildUpdate(final ChildUpdate otherChildUpdate) {
        this.id = new Integer(otherChildUpdate.id);
        this.niceScore = new Double(otherChildUpdate.niceScore);
        this.giftsPreferences = new ArrayList<String>(otherChildUpdate.giftsPreferences);
    }

    @Override
    public String toString() {
        return "ChildUpdate{"
                +
                "id=" + id
                +
                ", niceScore=" + niceScore
                +
                ", giftsPreferences=" + giftsPreferences
                +
                '}';
    }
}
