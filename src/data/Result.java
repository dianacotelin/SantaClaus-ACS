package data;

import java.util.List;

public final class Result {
    private List<AnnualChildren> children;
    public Result(final List<AnnualChildren> annualChildren) {
        this.children = annualChildren;
    }

    public List<AnnualChildren> getAnnualChildren() {
        return children;
    }

    public void setAnnualChildren(final List<AnnualChildren> annualChildren) {
        this.children = annualChildren;
    }

    @Override
    public String toString() {
        return "Result{"
                +
                "children=" + children
                +
                '}';
    }
}
