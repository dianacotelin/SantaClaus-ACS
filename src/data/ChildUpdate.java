package data;

import java.util.ArrayList;

public final class ChildUpdate {
    private int id;
    private double niceScore;
    private ArrayList<String> giftsPreferences;
    private String elf;

    public ChildUpdate(final int id, final double niceScore,
                       final ArrayList<String> giftsPreferences, final String elf) {
        this.id = id;
        this.niceScore = niceScore;
        this.giftsPreferences = giftsPreferences;
        this.elf = elf;
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

    public String getElf() {
        return elf;
    }

    public void setElf(final String elf) {
        this.elf = elf;
    }

    public ChildUpdate(final ChildUpdate otherChildUpdate) {
        this.id = new Integer(otherChildUpdate.id);
        this.niceScore = new Double(otherChildUpdate.niceScore);
        this.giftsPreferences = new ArrayList<String>(otherChildUpdate.giftsPreferences);
        this.elf = new String(otherChildUpdate.elf);
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
