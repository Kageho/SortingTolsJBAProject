package sorting;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

/*
 * class template for four types of arguments
 * strings, words, numbers*/
abstract class Skeleton {
    Scanner scanner;
    private final String type;
    private final boolean isNaturalSorting;
    private final String outputPath;
    private final boolean isScannerFile;
    private final String inputPath;

    public Skeleton(String type, boolean isNaturalSorting, String inputPath, String outPath) {
        this.type = type;
        this.scanner = new Scanner(System.in);
        this.inputPath = inputPath;
        if (Objects.equals("", inputPath)) {
            this.isScannerFile = false;
        } else {
            this.isScannerFile = true;
        }
        this.outputPath = outPath;
        this.isNaturalSorting = isNaturalSorting;
    }

    public boolean isNaturalSorting() {
        return isNaturalSorting;
    }

    //abstract method for getting data
    abstract void read();

    //    check is scanner a file set Scanner
    abstract void ifScannerIsAFile();

    public String getInputPath() {
        return inputPath;
    }

    public String getType() {
        return type;
    }

    public String getOutputPath() {
        return outputPath;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public void writeAnswerToFile(String answer) {
        try (PrintWriter printWriter = new PrintWriter(new File(outputPath))) {
            printWriter.println(answer);
        } catch (FileNotFoundException fnf) {
            System.out.println("File for output is not found");
        }
    }

    //method for print answer in natural sort view
    static <E> String getAnswerAsString(ArrayList<E> arrayList, int size, String typeOfArgument) {
        String result = "";
        result += "Total " + typeOfArgument + ": " + size + ".\n";
        result += "Sorted data: ";
        if (Objects.equals(typeOfArgument, "lines")) {
            result += "\n";
            for (var i : arrayList) {
                result += i + "\n";
            }
        } else {
            for (var i : arrayList) {
                result += i + " ";
            }
        }
        return result;
    }

    // method for print answer in sorted by count view
    static <E> String getAnswerAsString(List<Integer> keys, Map<Integer, ArrayList<E>> mapK_V, int totalNumber, String type) {
        String result = "Total " + type + ": " + totalNumber + ".\n";
        for (var i : keys) {
            ArrayList<E> arrayList = mapK_V.get(i);
            for (var j : arrayList) {
                result += j + ": " + i + " time(s), " + Math.round(i * 1.0 / totalNumber * 100) + "%\n";
            }
        }
        return result;
    }

    public boolean isScannerFile() {
        return isScannerFile;
    }

    //generic method for sorting strings
    List<Integer> sortByCountStrings(ArrayList<String> initialData, Map<Integer, ArrayList<String>> integerArrayListMap) {
        List<Integer> keys = new ArrayList<>();
        Map<String, Integer> dataTypeCount = new HashMap<>();
        for (var i : initialData) {
            if (dataTypeCount.get(i) == null) {
                dataTypeCount.put(i, 1);
            } else {
                dataTypeCount.put(i, dataTypeCount.get(i) + 1);
            }
        }
        for (var i : dataTypeCount.entrySet()) {
            if (!keys.contains(i.getValue())) {
                keys.add(i.getValue());
            }
            if (integerArrayListMap.containsKey(i.getValue())) {
                ArrayList<String> strings = integerArrayListMap.get(i.getValue());
                strings.add(i.getKey());
                Collections.sort(strings);
                integerArrayListMap.put(i.getValue(), strings);
            } else {
                integerArrayListMap.put(i.getValue(), new ArrayList<>(Collections.singletonList(i.getKey())));
            }
        }
        Collections.sort(keys);
        return keys;
    }
}
