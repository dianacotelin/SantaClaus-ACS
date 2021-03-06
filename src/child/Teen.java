package child;

import java.util.List;

public class Teen implements NiceScoreStrategy {
    private List<Double> niceScores;
    public Teen(final List<Double> niceScores) {
        this.niceScores = niceScores;
    }

    /**
     * This method returns the niceScore of a Baby
     */
    @Override
    public double getNiceScore() {
        if (niceScores != null) {
            double wAvg = 0.0;
            double weight = 0.0;
            int i = 1;
            for (Double score : niceScores) {
                weight += i;
                wAvg += score * i;
                i++;
            }
            return wAvg / weight;
        } else {
            return 0;
        }

    }
}
