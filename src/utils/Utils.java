package utils;

import common.Constants;
import data.ChildUpdate;
import data.Children;
import data.Gift;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;


public final class Utils {
    /**
     * for coding style
     */
    private Utils() {
    }

    /**
     * Transforms an array of JSON's into an array of strings
     * @param array of JSONs
     * @return a list of strings
     */
    public static ArrayList<String> convertJSONArray(final JSONArray array) {
        if (array != null) {
            ArrayList<String> finalArray = new ArrayList<>();
            for (Object object : array) {
                finalArray.add((String) object);
            }
            return finalArray;
        } else {
            return null;
        }
    }
    /**
     * Transforms an array of JSON's into an array of children
     * @param array of JSONs
     * @return a list of children
     */
    public static ArrayList<Children> convertJSONChildren(final JSONArray array) {
        if (array != null) {
            ArrayList<Children> finalArray = new ArrayList<>();
            for (Object object : array) {
                if (Double.parseDouble(((JSONObject) object)
                        .get(Constants.NICE_SCORE_BONUS).toString()) != 0) {
                    Children temp = new Children.Builder(
                            Integer.parseInt(((JSONObject) object).get(Constants.ID).toString()),
                            (String) ((JSONObject) object).get(Constants.LAST_NAME),
                            (String) ((JSONObject) object).get(Constants.FIRST_NAME),
                            (String) ((JSONObject) object).get(Constants.CITY),
                            Integer.parseInt(((JSONObject) object)
                                    .get(Constants.AGE).toString()),
                            Double.parseDouble(((JSONObject) object)
                                    .get(Constants.NICE_SCORE).toString()),
                            Utils.convertJSONArray((JSONArray) ((JSONObject) object)
                                    .get(Constants.GIFT_PREFERENCE)),
                            (String) ((JSONObject) object).get(Constants.ELF)).
                            scoreBonus(Double.parseDouble(((JSONObject) object)
                            .get(Constants.NICE_SCORE_BONUS).toString())).build();

                    List<Double> scoreTmp = new ArrayList<>();
                    scoreTmp.add(temp.getAverageScore());
                    temp.setNiceScoreHistory(scoreTmp);
                    finalArray.add(temp);
                } else {
                    Children temp = new Children.Builder(
                            Integer.parseInt(((JSONObject) object).get(Constants.ID).toString()),
                            (String) ((JSONObject) object).get(Constants.LAST_NAME),
                            (String) ((JSONObject) object).get(Constants.FIRST_NAME),
                            (String) ((JSONObject) object).get(Constants.CITY),
                            Integer.parseInt(((JSONObject) object)
                                    .get(Constants.AGE).toString()),
                            Double.parseDouble(((JSONObject) object)
                                    .get(Constants.NICE_SCORE).toString()),
                            Utils.convertJSONArray((JSONArray) ((JSONObject) object)
                                    .get(Constants.GIFT_PREFERENCE)),
                            (String) ((JSONObject) object).get(Constants.ELF)).build();
                    List<Double> scoreTmp = new ArrayList<>();
                    scoreTmp.add(temp.getAverageScore());
                    temp.setNiceScoreHistory(scoreTmp);
                    finalArray.add(temp);
                }
            }
            return finalArray;
        } else {
            return null;
        }
    }
    /**
     * Transforms an array of JSON's into an array of gifts
     * @param array of JSONs
     * @return a list of gifts
     */
    public static ArrayList<Gift> convertJSONGifts(final JSONArray array) {
        if (array != null) {
            ArrayList<Gift> finalArray = new ArrayList<>();
            for (Object object : array) {
                Gift gift = new Gift((String) ((JSONObject) object)
                        .get(Constants.PRODUCT_NAME),
                        Double.parseDouble(((JSONObject) object)
                                .get(Constants.PRICE).toString()),
                        (String) ((JSONObject) object).get(Constants.CATEGORY),
                        Integer.parseInt(((JSONObject) object)
                                .get(Constants.QUANTITY).toString()));
                finalArray.add(gift);
            }
            return finalArray;
        } else {
            return null;
        }
    }
    /**
     * Transforms an array of JSON's into an array of childrenUpdate
     * @param array of JSONs
     * @return a list of childUpdate
     */
    public static ArrayList<ChildUpdate> convertJSONChildUpdate(final JSONArray array) {
        if (array != null) {
            ArrayList<ChildUpdate> finalArray = new ArrayList<>();
            for (Object object : array) {
                if (((JSONObject) object)
                        .get(Constants.NICE_SCORE) != null) {
                    ChildUpdate temp = new ChildUpdate(Integer.
                            parseInt(((JSONObject) object).
                                    get(Constants.ID).toString()),
                            Double.parseDouble(((JSONObject) object)
                                    .get(Constants.NICE_SCORE).toString()),
                            Utils.convertJSONArray((JSONArray)
                                    ((JSONObject) object)
                                            .get(Constants.GIFT_PREFERENCE)),
                            (String) ((JSONObject) object).get(Constants.ELF)
                    );
                    finalArray.add(temp);
                } else {
                    // Daca scorul este null, am pus valoarea -1 ca sa stiu sa il ignor
                    ChildUpdate temp = new ChildUpdate(Integer.
                            parseInt(((JSONObject) object).
                                    get(Constants.ID).toString()),
                            -1,
                            Utils.convertJSONArray((JSONArray)
                                    ((JSONObject) object)
                                            .get(Constants.GIFT_PREFERENCE)),
                    (String) ((JSONObject) object).get(Constants.ELF));
                    finalArray.add(temp);
                }
            }
            return finalArray;
        } else {
            return null;
        }
    }

}
