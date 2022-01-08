package main;

import checker.Checker;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import common.Constants;
import data.AnnualChildren;
import data.Children;
import data.Gift;
import data.Result;
import data.AnnualChanges;
import data.Input;
import data.InputLoader;
import data.SantaDb;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Class used to run the code
 */
public final class Main {

    private Main() {
        ///constructor for checkstyle
    }
    /**
     * This method is used to call the checker which calculates the score
     * @param args
     *          the arguments used to call the main method
     */
    public static void main(final String[] args) throws IOException {
        File file;
        Path path = Paths.get("output/");
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }
        File outputDirectory = new File(Constants.OUTPUT_PATH);

        for (int i = 1; i < Constants.NO_TESTS; i++) {
            String name = Constants.TEST_PATH + i + Constants.FILE_EXTENSION;
            String name2 = Constants.OUTPUT_PATH + i + Constants.FILE_EXTENSION;
            file  = new File(name);
            String filepath = name2;
            File out = new File(filepath);

            action(name, filepath);

        }

        Checker.calculateScore();
    }
    /**
     * @param filePath1 for input file
     * @param filePath2 for output file
     * @throws IOException in case of exceptions to reading / writing
     */
    public static void action(final String filePath1, final String filePath2) throws IOException {
        InputLoader inputLoader = new InputLoader(filePath1);
        Input input = inputLoader.readData();
        ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

        int numberOfYears = input.getNumberOfYears();
        double santaBudget = input.getSantaBudget();
        List<Children> children = input.getChildren();
        List<Gift> gifts = input.getGifts();
        List<AnnualChanges> annualChanges = input.getAnnualChanges();

        SantaDb santaDb = new SantaDb(numberOfYears, santaBudget, children, gifts, annualChanges);
        List<AnnualChildren> finalChildren = new ArrayList<>();
        Result result = null;
        List<Children> annualChildren = null;
        for (int i = 0; i <= numberOfYears; i++) {

            if (i == 0) {
                List<Children> tempChildren = new ArrayList<>();
                annualChildren = santaDb.roundZero();
                for (Children child: annualChildren) {
                    Children children1 = new Children(child);
                    tempChildren.add(children1);
                }
                finalChildren.add(new AnnualChildren(tempChildren));
                result = new Result(finalChildren);
            } else {
                List<Children> tempChildren = santaDb.rounds(i, annualChildren);
                annualChildren = new ArrayList<>();
                List<Children> tempChildren1 = new ArrayList<>();
                for (Children child : tempChildren) {
                    Children children1 = new Children(child);
                    tempChildren1.add(children1);
                }
                annualChildren = tempChildren;
                finalChildren.add(new AnnualChildren(tempChildren1));
                result = new Result(finalChildren);
            }
        }
        objectMapper.writeValue(new File(filePath2), result);

    }
}
