package lw01.prelab;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<PrintJob> jobs = new ArrayList<>();
        Scanner scanner = new Scanner(Main.class.getResourceAsStream("job.txt"));

        while (scanner.hasNext()) {
            String type = scanner.next();
            
            if (type.equals("MONO")) {
                jobs.add(new MonoPrint(scanner.next(), scanner.nextInt()));
            } else {
                jobs.add(new ColourPrint(scanner.next(), scanner.nextInt()));
            }
        }

        for (PrintJob job : jobs) System.out.println(job.summary());
    }
}
