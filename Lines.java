package sorting;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

class Line extends Skeleton {
    private final ArrayList<String> myLinesList = new ArrayList<>();

    public Line(boolean isNaturalSorting, String input, String out) {
        super("lines", isNaturalSorting, input, out);
        ifScannerIsAFile();
    }
    @Override
    void read() {
        while (scanner.hasNextLine()) {
            myLinesList.add(scanner.nextLine());
        }
        if (!myLinesList.isEmpty()) {
            sortAndPrintAnswer();
        }
    }

    @Override
    void ifScannerIsAFile() {
        if (isScannerFile()) {
            try (Scanner scanner1 = new Scanner(new File(getInputPath()))) {
                setScanner(scanner1);
                read();
            } catch (FileNotFoundException e) {
                System.out.println("File not found");
            }
        } else {
            read();
        }
    }

    private void sortAndPrintAnswer() {
        String longestLine = "";
        for (var i : myLinesList) {
            if (longestLine.length() < i.length()) {
                longestLine = i;
            } else if (longestLine.length() == i.length() && longestLine.compareTo(i) < 0) {
                longestLine = i;
            }
        }
        String answer;
        if (isNaturalSorting()) {
            answer = getAnswerAsString(myLinesList, myLinesList.size(), getType());
        } else {
            Map<Integer, ArrayList<String>> sortedData = new HashMap<>();
            List<Integer> keys = sortByCountStrings(myLinesList, sortedData);
            answer = getAnswerAsString(keys, sortedData, myLinesList.size(), getType());
        }
        if (Objects.equals("", getOutputPath())) {
            System.out.println(answer);
        } else {
            writeAnswerToFile(answer);
        }
    }
}
