package data;

import common.Constants;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import utils.Utils;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class InputLoader {
    /**
     * The path to the input file
     */
    private final String inputPath;

    public InputLoader(final String inputPath) {
        this.inputPath = inputPath;
    }

    public String getInputPath() {
        return inputPath;
    }

    /**
     * The method reads the initialData
     * @return an Input object
     */
    public Input readData() {
        JSONParser jsonParser = new JSONParser();
        int numberOfYears = 0;
        double santaBudget = 0.0;
        List<Children> children = new ArrayList<>();
        List<Gift> gifts = new ArrayList<>();
        List<AnnualChanges> annualChanges = new ArrayList<>();
        try {
            JSONObject jsonObject = (JSONObject) jsonParser
                    .parse(new FileReader(inputPath));
            numberOfYears = Integer.parseInt((jsonObject
                    .get(Constants.NUMBER_OF_YEARS).toString()));
            santaBudget = Double.parseDouble((jsonObject.get(Constants.SANTA_BUDGET).toString()));
            JSONObject initialData = (JSONObject) jsonObject.get(Constants.INITIAL_DATA);
            JSONArray jsonChildren = (JSONArray) initialData.get(Constants.CHILDREN);
            JSONArray jsonGifts = (JSONArray) initialData.get(Constants.SANTA_GIFT_LIST);
            JSONArray jsonChanges = (JSONArray) jsonObject.get(Constants.ANNUAL_CHANGES);

            if (jsonChildren != null) {
                for (Object jsonChild : jsonChildren) {
                    if (Double.parseDouble(((JSONObject) jsonChild)
                            .get(Constants.NICE_SCORE_BONUS).toString()) != 0) {
                        children.add(new Children.Builder(
                                Integer.parseInt(((JSONObject) jsonChild)
                                        .get(Constants.ID).toString()),
                                (String) ((JSONObject) jsonChild).get(Constants.LAST_NAME),
                                (String) ((JSONObject) jsonChild).get(Constants.FIRST_NAME),
                                (String) ((JSONObject) jsonChild).get(Constants.CITY),
                                Integer.parseInt(((JSONObject) jsonChild)
                                        .get(Constants.AGE).toString()),
                                Double.parseDouble(((JSONObject) jsonChild)
                                        .get(Constants.NICE_SCORE).toString()),
                                Utils.convertJSONArray((JSONArray) ((JSONObject) jsonChild)
                                        .get(Constants.GIFT_PREFERENCE)),
                                (String) ((JSONObject) jsonChild).get(Constants.ELF)).
                                scoreBonus(Double.parseDouble(((JSONObject) jsonChild)
                                .get(Constants.NICE_SCORE_BONUS).toString())).build()
                        );
                    } else {
                    children.add(new Children.Builder(
                            Integer.parseInt(((JSONObject) jsonChild).get(Constants.ID).toString()),
                            (String) ((JSONObject) jsonChild).get(Constants.LAST_NAME),
                            (String) ((JSONObject) jsonChild).get(Constants.FIRST_NAME),
                            (String) ((JSONObject) jsonChild).get(Constants.CITY),
                            Integer.parseInt(((JSONObject) jsonChild)
                                    .get(Constants.AGE).toString()),
                            Double.parseDouble(((JSONObject) jsonChild)
                                    .get(Constants.NICE_SCORE).toString()),
                            Utils.convertJSONArray((JSONArray) ((JSONObject) jsonChild)
                                    .get(Constants.GIFT_PREFERENCE)),
                            (String) ((JSONObject) jsonChild).get(Constants.ELF)).build());
                    }
                }
            }

            if (jsonGifts != null) {
                for (Object jsonGift : jsonGifts) {
                    gifts.add(new Gift((String) ((JSONObject) jsonGift)
                            .get(Constants.PRODUCT_NAME),
                            Double.parseDouble(((JSONObject) jsonGift)
                                    .get(Constants.PRICE).toString()),
                            (String) ((JSONObject) jsonGift).get(Constants.CATEGORY),
                            Integer.parseInt(((JSONObject) jsonGift)
                                    .get(Constants.QUANTITY).toString())
                            ));
                }
            }
            if (jsonChanges != null) {

                for (Object change : jsonChanges) {
                    List<ChildUpdate> childUpdate = new ArrayList<>();

                    JSONArray jsonChildrenUpdates = (JSONArray) ((JSONObject) change)
                            .get(Constants.CHILDREN_UPDATES);

                    List<Children> newChildren = new ArrayList<>();
                    JSONArray jsonNewChildren = (JSONArray) ((JSONObject) change)
                            .get(Constants.NEW_CHILDREN);

                    JSONArray jsonNewGifts = (JSONArray) ((JSONObject) change)
                            .get(Constants.NEW_GIFTS);
                    annualChanges.add(new AnnualChanges(Double.parseDouble(((JSONObject) change)
                            .get(Constants.NEW_SANTA_BUDGET).toString()),
                            Utils.convertJSONGifts(jsonNewGifts),
                            Utils.convertJSONChildren(jsonNewChildren),
                            Utils.convertJSONChildUpdate(jsonChildrenUpdates),
                            (String) ((JSONObject) change).get(Constants.STRATEGY)

                    ));
                }
            }

            if (jsonGifts == null) {
                gifts = null;
            }
            if (jsonChildren == null) {
                children = null;
            }
            if (jsonChanges == null) {
                annualChanges = null;
            }
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        return new Input(numberOfYears, santaBudget, children, gifts, annualChanges);
    }
}
