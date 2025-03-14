import java.io.*;
import java.util.Scanner;
import java.util.Random;

public class BubbleSort {
    public static int[] createRandomArray(int arrayLength) {
        Random random = new Random();
        int[] array = new int[arrayLength];
        for (inti = 0; i<arrayLength; i++) {
            array[i] = random.nextInt(101);
        }
        return array;
    }
    public static void writeArrayToFile(int[] array, String filename) {
        try {
            FileWriter writer = new FileWriter(filename);
            for (int i = 0; i < array.length; i++) {
                writer.write(String.valueOf(array[i]));
                writer.write("\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public static int[] readFileToArray(String filename) {
        int[] array = null;
        try{
            Scanner scanner = new Scanner(new File(filename));
            int arrayLength = 0;
            while (scanner.hasNextLine()) {
                arrayLength++;
                scanner.nextLine();
            }
            scanner.close();
            array = new int[arrayLength];
            Scanner arrayScanner = new Scanner(new File(filename));
            int i = 0;
            while (arrayScanner.hasNextLine()) {
                array[i++] = Integer.parseInt(arrayScanner.nextLine());
            }
            arrayScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }
        return array;
    }
    public static void bubbleSort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
            }
        }
    }
        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter 1 to generate an array of random integers, and store as a file.");
            System.out.println("Enter '2' ro read an existing file, and sort the integers into an array");
            int option = sc.nextInt();
            if (option == 1) {
                System.out.println("Enter the length of the array.");
                int arrayLength = sc.nextInt();
                int[] array = createRandomArray(arrayLength);
                System.out.println("Enter the file name");
                String filename = sc.next();
                writeArrayToFile(array, filename);
                System.out.println("Array has been written to the file.");
            } else if (option == 2) {
                System.out.println("Enter file name.");
                String filename = sc.next();
                int[] array = readFileToArray(filename);
                if (array != null) {
                    bubbleSort(array);
                    System.out.println("Enter the file name to store the new array.");
                    String outputFilename = sc.next();
                    writeArrayToFile(array, outputFilename);
                    System.out.println("Sorted aray has been written to the file.");
                }
            } else {
                System.out.println("Invalid input.");
            }
            sc.close();
        }

}