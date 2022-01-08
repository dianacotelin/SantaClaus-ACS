package child;

import common.Constants;

import java.util.List;

public class StrategyFactory {
    /**
     * This method is used to get the niceScore for a child
     */
    public double createStrategy(final int age, final List<Double> scores) {
        double score;
        if (age < Constants.BABY_AGE) {
            score = new Baby(scores).getNiceScore();
            return score;
        } else if (age < Constants.KID_AGE) {
            score = new Kid(scores).getNiceScore();
            return score;
        } else if (age <= Constants.TEEN_AGE) {
            score = new Teen(scores).getNiceScore();
            return score;
        } else {
            return -1;
        }
    }
}
