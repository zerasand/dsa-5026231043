package lw03.unguided;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
	private static void main (String [] args) {
		List<String> courseCodes = new ArrayList<>();
		Map<String, Integer> enrollments = new HashMap<>();
		List<String> checkResults = new ArrayList<>();
		int rejectedOperations = 0;

		try (Scanner scanner = new Scanner(new File("src/lw03/unguided/enrollment.txt"))) {
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine().trim();
				if (!line.isEmpty()) {
					String[] parts = line.split("\\s+", 2);
					String operation = parts[0];
					String courseCode = parts[1];

					switch (operation) {
						case "ADD":
							if (!courseCodes.contains(courseCode)) {
								courseCodes.add(courseCode);
								enrollments.put(courseCode, 0);
							} else {
								rejectedOperations++;
							}
							break;
						case "REMOVE":
							if (courseCodes.contains(courseCode)) {
								courseCodes.remove(courseCode);
								enrollments.remove(courseCode);
							} else {
								rejectedOperations++;
							}
							break;
						case "ENROLL":
							if (courseCodes.contains(courseCode)) {
								enrollments.put(courseCode, enrollments.get(courseCode) + 1);
							} else {
								rejectedOperations++;
							}
							break;
						case "CHECK":
							if (courseCodes.contains(courseCode)) {
								checkResults.add("Course " + courseCode + " exists.");
							} else {
								checkResults.add("Course " + courseCode + " does not exist.");
							}
							break;
						default:
							rejectedOperations++;
					}
				}
			}
		} catch (FileNotFoundException e) {
			System.err.println("File not found: " + e.getMessage());
			return;
		}

	}
}