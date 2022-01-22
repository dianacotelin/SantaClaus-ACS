package strategy;

import data.Children;

import java.util.HashMap;
import java.util.Map;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;


public class NiceScoreCityStrategy implements Strategy {

    private List<Children> children;

    public NiceScoreCityStrategy(final List<Children> children) {
        this.children = children;
    }
    /**
     * This method sort children by average niceScore of a city, using a HashMap with cities
     * and score
     */
    @Override
    public HashMap<String, Double> sortChildren() {
        HashMap<String, Double> citySort = new HashMap<>();
        // adaug doar orasele
        for (Children child : children) {
            citySort.put(child.getCity(), 0.0);
        }
        for (String city : citySort.keySet()) {
            double sum = 0;
            double count = 0;
            // adaug valori la fiecare oras
            for (Children child : children) {
                if (child.getCity().equals(city)) {
                    sum += child.getAverageScore();
                    count++;
                }
            }
            citySort.put(city, sum / count);
        }
        HashMap<String, Double> childrenSorted;
        childrenSorted = sortByCity(citySort);
        return childrenSorted;
    }
    /**
     * This method sort a HashMap of id and the average niceScore of a city
     */
    public HashMap<String, Double> sortByCity(final HashMap<String, Double> hm) {
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
