package lw03;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class main {
    private static File resolveFile(String fileName) {
        List<String> candidates = Arrays.asList(
                fileName,
                "src/lw03/" + fileName,
                "dsa-5026231043/src/lw03/" + fileName
        );

        for (String path : candidates) {
            File file = new File(path);
            if (file.exists()) {
                return file;
            }
        }

        return new File("src/lw03/" + fileName);
    }

    private static List<String> readLines(String fileName) throws FileNotFoundException {
        List<String> lines = new ArrayList<>();
        try (Scanner scanner = new Scanner(resolveFile(fileName))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (!line.isEmpty()) {
                    lines.add(line);
                }
            }
        }
        return lines;
    }

    private static void problem1() throws FileNotFoundException {
        List<String> playlist = new ArrayList<>();

        for (String line : readLines("playlist.txt")) {
            if (line.startsWith("ADD ")) {
                playlist.add(line.substring(4).trim());
            } else if (line.startsWith("INSERT ")) {
                String rest = line.substring("INSERT ".length()).trim();
                String[] parts = rest.split("\\s+", 2);
                int index = Integer.parseInt(parts[0]);
                String song = parts[1];

                if (index < 0) {
                    index = 0;
                }
                if (index > playlist.size()) {
                    index = playlist.size();
                }
                playlist.add(index, song);
            } else if (line.startsWith("REMOVE ")) {
                String song = line.substring("REMOVE ".length()).trim();
                int index = playlist.indexOf(song);
                if (index != -1) {
                    playlist.remove(index);
                }
            }
        }

        System.out.println("===== Problem 1 =====");
        System.out.println("Total songs: " + playlist.size());
        for (int i = 0; i < playlist.size(); i++) {
            System.out.println((i + 1) + ": " + playlist.get(i));
        }
    }

    private static void problem2() throws FileNotFoundException {
        Set<String> participants = new LinkedHashSet<>();
        int duplicateRegistrations = 0;

        for (String name : readLines("participants.txt")) {
            if (!participants.add(name)) {
                duplicateRegistrations++;
            }
        }

        System.out.println("===== Problem 2 =====");
        System.out.println("Unique participants: " + participants.size());
        int counter = 1;
        for (String participant : participants) {
            System.out.println(counter + ". " + participant);
            counter++;
        }
        System.out.println("Duplicate registrations: " + duplicateRegistrations);
    }

    private static void problem3() throws FileNotFoundException {
        Map<String, Integer> inventory = new LinkedHashMap<>();
        int failedSales = 0;

        for (String line : readLines("inventory.txt")) {
            String[] parts = line.split("\\s+");
            String type = parts[0];
            String product = parts[1];
            int quantity = Integer.parseInt(parts[2]);

            if (type.equals("ADD")) {
                inventory.put(product, inventory.getOrDefault(product, 0) + quantity);
            } else if (type.equals("SELL")) {
                int current = inventory.getOrDefault(product, 0);
                if (current >= quantity) {
                    inventory.put(product, current - quantity);
                } else {
                    failedSales++;
                }
            }
        }

        System.out.println("===== Problem 3 =====");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        System.out.println("Failed sales: " + failedSales);
    }

    public static void main(String[] args) {
        try {
            problem1();
            problem2();
            problem3();
        } catch (FileNotFoundException e) {
            System.out.println("Input file not found.");
        }
    }
}
