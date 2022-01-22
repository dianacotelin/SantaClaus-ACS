package data;

import java.util.List;

public final class AnnualChildren {
    private List<Children> children;

    public AnnualChildren(final List<Children> children) {
        this.children = children;
    }

    public List<Children> getChildren() {
        return children;
    }
    public AnnualChildren(final AnnualChildren otherChildren) {
        this.children = otherChildren.children;
    }

    public void setChildren(final List<Children> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "children{"
                +
                "children{ " + children.toString()
                +
                '}';
    }
}
