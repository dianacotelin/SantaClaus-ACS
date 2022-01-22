package strategy;

import java.util.HashMap;
import java.util.List;

import common.Constants;
import data.Children;
public class GiftFactory {
    /**
     * This method is used to get the children sorted
     */
    public HashMap<String, Double> createStrategy(final String strategy,
                                                  final List<Children> children) {
        HashMap<String, Double> sortChildren = new HashMap<>();
        if (strategy.equals(Constants.ID)) {
            sortChildren = new IdStrategy(children).sortChildren();
        } else if (strategy.equals(Constants.NICE_SCORE)) {
            sortChildren = new NiceScoreStrategy(children).sortChildren();
        } else {
            sortChildren = new NiceScoreCityStrategy(children).sortChildren();
        }
        return sortChildren;
    }
}
