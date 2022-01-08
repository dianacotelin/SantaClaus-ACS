package child;

import java.util.List;

public class Kid implements NiceScoreStrategy {
    private List<Double> niceScores;
    public Kid(final List<Double> niceScores) {
        this.niceScores = niceScores;
    }

    /**
     * This method returns the niceScore of a Kid
     */
    @Override
    public double getNiceScore() {
        double average = 0;
        if (niceScores != null) {
            for (Double score : niceScores) {
                average += score;
            }

            return average / niceScores.size();
        } else {
            return 0;
        }
    }
}
