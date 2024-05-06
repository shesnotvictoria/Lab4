import java.io.*;

public class Main {
    public static void main(String[] args) {


        try {
            FileReader fileReader = new FileReader("Format_Download_File-eng.pdf");
            char nextChar;
            while ((nextChar = getNextValidChar(fileReader)) != '\0') {
                System.out.print(nextChar);
            }
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static char getNextValidChar(FileReader fileReader) throws IOException {
        int nextInt;
        while ((nextInt = fileReader.read()) != -1) {
            char nextChar = (char) nextInt;

            if (nextChar != '\r') {
                return nextChar;
            }
        }

        return '\0';
    }

    public static void countFrequency() {
        String inputFilePath = "Format_Download_File-eng.pdf";
        String outputFilePath = "frequency.txt";

        int[] frequencies = new int[52];

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {
            int nextChar;
            while ((nextChar = reader.read()) != -1) {
                char normalizedChar = normalize((char) nextChar);
                if (normalizedChar != '\0') {
                    frequencies[normalizedChar - 'A']++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {
            for (char c = 'A'; c <= 'Z'; c++) {
                writer.write(c + ": " + frequencies[c - 'A'] + "\n");
            }
            for (char c = 'a'; c <= 'z'; c++) {
                writer.write(c + ": " + frequencies[c - 'a' + 26] + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Character frequencies written to " + outputFilePath);
    }

    public static char normalize(char c) {
        if ((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z')) {
            return Character.toUpperCase(c);
        }
        return '\0';
    }

    public static void testCountMethod() {
        String testString = "Hello, World! How are you today?";

        // Test with StringReader
        StringReader stringReader = new StringReader(testString);
        count(stringReader);

        // Test with BufferedReader reading from a file
        try (BufferedReader fileReader = new BufferedReader(new FileReader("input.txt"))) {
            count(fileReader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void count(Reader reader) {
        int[] frequencies = new int[52];

        try (BufferedReader bufferedReader = new BufferedReader(reader)) {
            int nextChar;
            while ((nextChar = bufferedReader.read()) != -1) {
                char normalizedChar = normalize((char) nextChar);
                if (normalizedChar != '\0') {
                    frequencies[normalizedChar - 'A']++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        for (char c = 'A'; c <= 'Z'; c++) {
            System.out.println(c + ": " + frequencies[c - 'A']);
        }
        for (char c = 'a'; c <= 'z'; c++) {
            System.out.println(c + ": " + frequencies[c - 'a' + 26]);
        }
    }


    public static char findMostFrequentCharacter(int[] frequencies) {
        char mostFrequentChar = '\0'; // Initialize to null character
        int maxFrequency = 0;


        for (int i = 0; i < frequencies.length; i++) {
            char currentChar = (char) (i < 26 ? 'A' + i : 'a' + i - 26);
            int currentFrequency = frequencies[i];

            if (currentFrequency > maxFrequency) {
                maxFrequency = currentFrequency;
                mostFrequentChar = currentChar;
            }
        }

        return mostFrequentChar;
    }

    public static void testFindMostFrequentCharacter() {
        int[] frequencies = {3, 5, 2, 7, 1, 4, 6, 0, 2, 8, 5, 3, 2, 1, 6, 4, 5, 7, 2, 9, 0, 1, 3, 5, 2, 4,
                2, 3, 1, 6, 5, 4, 3, 0, 2, 5, 7, 1, 8, 6, 3, 2, 5, 4, 1, 3, 2, 0, 7, 8, 9};

        char mostFrequentChar = findMostFrequentCharacter(frequencies);
        System.out.println("Most frequent character: " + mostFrequentChar);
    }

    public static void outputHistogram(int[] frequencies) {
        for (int i = 0; i < frequencies.length; i++) {
            char currentChar = (char) (i < 26 ? 'A' + i : 'a' + i - 26); // Convert index to character
            int currentFrequency = frequencies[i];

            System.out.print(currentChar + " : ");
            for (int j = 0; j < currentFrequency; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }



}



