package strategy;

import data.Children;

import java.util.HashMap;
import java.util.Map;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;

public class NiceScoreStrategy implements Strategy {
    private List<Children> children;

    public NiceScoreStrategy(final List<Children> children) {
        this.children = children;
    }
    /**
     * This method sort children by average niceScore, using a HashMap with id
     * and niceScore
     */
    @Override
    public HashMap<String, Double> sortChildren() {
        HashMap<String, Double> niceScoreSort = new HashMap<>();
        for (Children child: children) {
            niceScoreSort.put(String.valueOf(child.getId()), child.getAverageScore());
        }
        HashMap<String, Double> childrenSorted;
        childrenSorted = sortByNiceScore(niceScoreSort);
        return childrenSorted;
    }
    /**
     * This method sort a HashMap of id and niceScore
     */
    public HashMap<String, Double> sortByNiceScore(final HashMap<String, Double> hm) {
        // Creez o lista cu elementele HashMap-ului
        List<Map.Entry<String, Double>> list =
                new LinkedList<Map.Entry<String, Double>>(hm.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<String, Double>>() {
            @Override
            public int compare(final Map.Entry<String, Double> o1,
                               final Map.Entry<String, Double> o2) {
                if (o1.getValue().compareTo(o2.getValue()) == 0) {
                    return o1.getKey().compareTo(o2.getKey());
                } else {
                    return o2.getValue().compareTo(o1.getValue());
                }

            }
        });

        HashMap<String, Double> temp = new LinkedHashMap<String, Double>();
        for (Map.Entry<String, Double> aa: list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }
}
