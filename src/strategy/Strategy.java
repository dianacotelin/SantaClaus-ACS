package strategy;

import java.util.HashMap;


public interface Strategy {
    /**
     * This method returns a HashMap with the children sorted
     */
    HashMap<String, Double> sortChildren();
}
