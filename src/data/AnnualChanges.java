package data;

import java.util.ArrayList;
import java.util.List;

public final class AnnualChanges {
    /**
     * Santa's Budget
     */
    private double newSantaBudget;
    /**
     * Gifts
     */
    private List<Gift> gifts = new ArrayList<>();
    /**
     * new children list
     */
    private List<Children> newChildren = new ArrayList<>();
    /**
     * children udates
     */
    private List<ChildUpdate> childrenUpdates = new ArrayList<>();

    public AnnualChanges(final double newSantaBudget, final List<Gift> gifts,
                         final List<Children> newChildren,
                         final ArrayList<ChildUpdate> childrenUpdates) {
        this.newSantaBudget = newSantaBudget;
        this.gifts = gifts;
        this.childrenUpdates = childrenUpdates;
        this.newChildren = newChildren;
    }

    public double getNewSantaBudget() {
        return newSantaBudget;
    }

    public void setNewSantaBudget(final double newSantaBudget) {
        this.newSantaBudget = newSantaBudget;
    }

    public List<Children> getNewChildren() {
        return newChildren;
    }

    public void setNewChildren(final List<Children> newChildren) {
        this.newChildren = newChildren;
    }

    public List<Gift> getGifts() {
        return gifts;
    }

    public void setGifts(final List<Gift> gifts) {
        this.gifts = gifts;
    }

    public List<ChildUpdate> getChildrenUpdates() {
        return childrenUpdates;
    }

    public void setChildrenUpdates(final List<ChildUpdate> childrenUpdates) {
        this.childrenUpdates = childrenUpdates;
    }

    @Override
    public String toString() {
        return "AnnualChanges{"
                +
                "newSantaBudget=" + newSantaBudget
                +
                ", gifts=" + gifts
                +
                ", children=" + newChildren
                +
                ", childrenUpdates=" + childrenUpdates
                +
                '}';
    }
}
