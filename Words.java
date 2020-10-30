package sorting;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

class Words extends Skeleton {
    private final ArrayList<String> myWordsList = new ArrayList<>();

    public Words(boolean isNaturalSort, String input, String out) {
        super("words", isNaturalSort, input, out);
        ifScannerIsAFile();
    }

    @Override
    void read() {
        while (scanner.hasNext()) {
            myWordsList.add(scanner.next());
        }
        if (!myWordsList.isEmpty()) {
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
        String answer;
        answer = myWordsList.get(0);
        for (int i = 1; i < myWordsList.size(); i++) {
            int length = myWordsList.get(i).length();
            String temp = myWordsList.get(i);
            if (answer.length() < length) {
                answer = temp;
            } else if (answer.length() == length) {
                if (answer.compareTo(temp) < 0) {
                    answer = temp;
                }
            }
        }

        if (isNaturalSorting()) {
            answer = getAnswerAsString(myWordsList, myWordsList.size(), getType());
        } else {
            Map<Integer, ArrayList<String>> sortedData = new HashMap<>();
            List<Integer> keys = sortByCountStrings(myWordsList, sortedData);
            answer = getAnswerAsString(keys, sortedData, myWordsList.size(), getType());
        }
        if (Objects.equals("", getOutputPath())) {
            System.out.println(answer);
        } else {
            writeAnswerToFile(answer);
        }
    }
}
