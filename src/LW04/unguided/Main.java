package LW04.unguided;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        File inputFile = new File("cards.txt");
        if (!inputFile.exists()) {
            inputFile = new File("src/LW04/unguided/cards.txt");
        }

        try (Scanner scanner = new Scanner(inputFile)) {
            int cardCount = scanner.nextInt();
            int[] cards = new int[cardCount];

            for (int i = 0; i < cardCount; i++) {
                cards[i] = scanner.nextInt();
            }

            int requestCount = scanner.nextInt();
            int[] requests = new int[requestCount];

            for (int i = 0; i < requestCount; i++) {
                requests[i] = scanner.nextInt();
            }

            sortCards(cards, 2);

            for (int i = 0; i < cards.length; i++) {
                if (i > 0) {
                    System.out.print(" ");
                }
                System.out.print(cards[i]);
            }
            System.out.println();

            for (int i = 0; i < requests.length; i++) {
                int index = findCard(cards, requests[i]);
                if (index >= 0) {
                    System.out.println(requests[i] + ": FOUND at index " + index);
                } else {
                    System.out.println(requests[i] + ": NOT FOUND");
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Could not find cards.txt in the working directory or src/LW04/unguided: "
                    + e.getMessage());
        }
    }

    private static void sortCards(int[] arr, int passes) {
        for (int i = 0; i < passes; i++) {
            int smallestIndex = i;

            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[smallestIndex]) {
                    smallestIndex = j;
                }
            }

            int temp = arr[i];
            arr[i] = arr[smallestIndex];
            arr[smallestIndex] = temp;
        }
    }

    private static int findCard(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i;
            }
        }

        return -1;
    }
}
