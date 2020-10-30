package sorting;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

class Numbers extends Skeleton {
    private final ArrayList<Long> myList = new ArrayList<>();

    public Numbers(boolean isNaturalSorting, String input, String out) {
        super("numbers", isNaturalSorting, input, out);
        ifScannerIsAFile();
    }

    @Override
    void read() {
        if (isScannerFile()) {

        }
        List<String> trashInput = new ArrayList<>();
        while (scanner.hasNext()) {
            String currentLong = scanner.next();
            if (currentLong.matches("[-+]?\\d+")) {
                myList.add(Long.parseLong(currentLong));
            } else {
                trashInput.add(currentLong);
            }
        }
        for (var i : trashInput) {
            System.out.println("\"" + i + "\"" + " isn't a long. It's skipped.");
        }
        if (!myList.isEmpty()) {
            sortAndPrintOrWriteAnswer();
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

    private void sortAndPrintOrWriteAnswer() {
        String answer;
        if (isNaturalSorting()) {
            Collections.sort(myList);
            answer = getAnswerAsString(myList, myList.size(), getType());
        } else {
            Map<Long, Integer> countToDataEntries = new HashMap<>();
            for (var i : myList) {
                if (countToDataEntries.get(i) == null) {
                    countToDataEntries.put(i, 1);
                } else {
                    countToDataEntries.put(i, countToDataEntries.get(i) + 1);
                }
            }
            Set<Integer> myHashSet = new HashSet<>();
            for (var i : countToDataEntries.entrySet()) {
                myHashSet.add(i.getValue());
            }
            ArrayList<Integer> integerArrayList = new ArrayList<>(myHashSet);
            Collections.sort(integerArrayList);
            Map<Integer, ArrayList<Long>> myCountSortMap = new HashMap<>();
            for (var i : myHashSet) {
                for (var j : countToDataEntries.entrySet()) {
                    if (i.equals(j.getValue())) {
                        if (myCountSortMap.get(i) == null) {
                            myCountSortMap.put(i, new ArrayList<>(Collections.singletonList(j.getKey())));
                        } else if (!myCountSortMap.get(i).contains(j.getKey())) {
                            ArrayList<Long> arrayList = myCountSortMap.get(i);
                            arrayList.add(j.getKey());
                            Collections.sort(arrayList);
                            myCountSortMap.put(i, arrayList);
                        }
                    }
                }
            }
            answer = getAnswerAsString(integerArrayList, myCountSortMap, myList.size(), getType());
        }
        if (Objects.equals("", getOutputPath())) {
            System.out.println(answer);
        } else {
            writeAnswerToFile(answer);
        }
    }
}
