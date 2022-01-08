package data;

import java.util.List;

public final class Input {
    private final int numberOfYears;
    private final double santaBudget;
    private final List<Children> children;
    private final  List<Gift> gifts;
    private final List<AnnualChanges> annualChanges;

    public Input(final int numberOfYears, final double santaBudget, final List<Children> children,
                 final List<Gift> gifts, final List<AnnualChanges> annualChanges) {
        this.numberOfYears = numberOfYears;
        this.santaBudget = santaBudget;
        this.children = children;
        this.gifts = gifts;
        this.annualChanges = annualChanges;
    }
    public Input() {
        this.numberOfYears = 0;
        this.santaBudget = 0.0;
        this.children = null;
        this.gifts = null;
        this.annualChanges = null;
    }

    public int getNumberOfYears() {
        return numberOfYears;
    }

    public double getSantaBudget() {
        return santaBudget;
    }

    public List<Children> getChildren() {
        return children;
    }

    public List<Gift> getGifts() {
        return gifts;
    }

    public List<AnnualChanges> getAnnualChanges() {
        return annualChanges;
    }
}
