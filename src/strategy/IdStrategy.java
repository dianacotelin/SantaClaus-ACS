package strategy;

import data.Children;

import java.util.HashMap;
import java.util.Map;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;

public class IdStrategy implements Strategy {

    private List<Children> children;

    public IdStrategy(final List<Children> children) {
        this.children = children;
    }
    /**
     * This method sort children by id using a HashMap with id
     * and score
     */
    @Override
    public HashMap<String, Double> sortChildren() {
        HashMap<String, Double> idSort = new HashMap<>();
        for (Children child: children) {
            idSort.put(String.valueOf(child.getId()), (double) child.getId());
        }
        HashMap<String, Double> childrenSorted;
        childrenSorted = sortById(idSort);
        return childrenSorted;
    }
    /**
     * This method sort a HashMap of id (as a string) and id as double
     */
    public HashMap<String, Double> sortById(final HashMap<String, Double> hm) {
        // Creez o lista cu elementele HashMap-ului
        List<Map.Entry<String, Double>> list =
                new LinkedList<Map.Entry<String, Double>>(hm.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<String, Double>>() {
            @Override
            public int compare(final Map.Entry<String, Double> o1,
                               final Map.Entry<String, Double> o2) {
                if (o1.getValue().compareTo(o2.getValue()) == 0) {
                    return o2.getKey().compareTo(o1.getKey());
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
