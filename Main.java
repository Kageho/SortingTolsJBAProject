package sorting;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Main {
    public static void main(final String[] args) {
       /* for(var i : args){
            System.out.print(i + " ");
        }
        System.out.println();*/
        String command = "word";
        boolean sortingTypeNatural = true;
        boolean doesProgramGetST = true;
        boolean doesProgramGetDT = true;
        String inputFile = "";
        String outputFile = "";
        for (var i = 0; i < args.length; i++) {
            if ("-dataType".equals(args[i])) {
                if (i + 1 < args.length) {
                    command = args[++i];
                } else {
                    doesProgramGetDT = false;
                }
            } else if ("-sortingType".equals(args[i]) && (i + 1) < args.length) {
                if (i + 1 < args.length) {
                    sortingTypeNatural = Objects.equals("natural", args[++i]);
                } else {
                    doesProgramGetST = false;
                }
            } else if (Objects.equals("-outputFile", args[i])) {
                if (i + 1 < args.length) {
                    outputFile = args[++i];
                }
            } else if (Objects.equals("-inputFile", args[i])) {
                if (i + 1 < args.length) {
                    inputFile = args[++i];
                }
            }
        }
        List<String> arguments = new ArrayList<>();
//        all available arguments
        arguments.addAll(Arrays.asList("-sortingType", "-dataType", "byCount", "long", "word", "line", "natural", "-inputFile", "-outputFile"));
        arguments.addAll(Arrays.asList(inputFile, outputFile));
        for (var i : args) {
            if (!arguments.contains(i)) {
                System.out.println("\"" + i + "\"" + " isn't a valid parameter. It's skipped.");
            }
        }
        if (!doesProgramGetST) {
            System.out.println("No sorting type defined!");
        } else if (!doesProgramGetDT) {
            System.out.println("No data type defined!");
        } else {
            switch (command) {
                case "long":
                    Numbers numbers = new Numbers(sortingTypeNatural, inputFile, outputFile);
                    break;
                case "word":
                    Words words = new Words(sortingTypeNatural, inputFile, outputFile);
                    break;
                case "line":
                    Line line = new Line(sortingTypeNatural, inputFile, outputFile);
                    break;
                default:
                    System.out.println("you miss, try again");
            }

        }

    }
}