package child;

import common.Constants;

import java.util.List;

public class Baby implements NiceScoreStrategy {
    private List<Double> niceScores;
    public Baby(final List<Double> niceScores) {
        this.niceScores = niceScores;
    }

    /**
     * This method returns the niceScore of a Baby
     */
    @Override
    public double getNiceScore() {
        return Constants.BABY_SCORE;
    }
}
