import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.Scanner;

public class Main {

    static final int MAX_BORROW = 2;

    public static void main(String[] args) {
        LinkedList<String[]> requests = new LinkedList<>();

        LinkedList<String[]> books = new LinkedList<>();

        LinkedList<String[]> members = new LinkedList<>();

        Queue<String[]> queue = new LinkedList<>();

        Stack<String[]> failedRequests = new Stack<>();

        LinkedList<String[]> successfulRequests = new LinkedList<>();

        try {
            Scanner scanner = new Scanner(new File("borrowing.txt"));

            while (scanner.hasNextLine()) {

                String line = scanner.nextLine();

                Scanner lineScanner = new Scanner(line);

                String name = lineScanner.next();
                String bookTitle = lineScanner.next();

                String[] request = {name, bookTitle};

                requests.add(request);

                boolean memberExists = false;

                for (int i = 0; i < members.size(); i++) {
                    if (members.get(i)[0].equals(name)) {
                        memberExists = true;
                        break;
                    }
                }

                if (!memberExists) {
                    String[] member = {name, "0"};
                    members.add(member);
                }

                lineScanner.close();
            }

            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("borrowing.txt not found.");
            return;
        }


        books.add(new String[]{"Kalkulus", "2"});
        books.add(new String[]{"Fisika", "1"});
        books.add(new String[]{"Statistika", "2"});


        while (!requests.isEmpty()) {
            queue.add(requests.removeFirst());
        }


        while (!queue.isEmpty()) {

            String[] request = queue.poll();

            String name = request[0];
            String bookTitle = request[1];

            int bookIndex = -1;
            int memberIndex = -1;


            for (int i = 0; i < books.size(); i++) {
                if (books.get(i)[0].equals(bookTitle)) {
                    bookIndex = i;
                    break;
                }
            }


            for (int i = 0; i < members.size(); i++) {
                if (members.get(i)[0].equals(name)) {
                    memberIndex = i;
                    break;
                }
            }


            int stock = Integer.parseInt(books.get(bookIndex)[1]);
            int borrowed = Integer.parseInt(members.get(memberIndex)[1]);


            if (stock > 0 && borrowed < MAX_BORROW) {

                stock--;
                borrowed++;

                books.get(bookIndex)[1] = String.valueOf(stock);
                members.get(memberIndex)[1] = String.valueOf(borrowed);

                successfulRequests.add(request);

            } else {

                failedRequests.push(request);
            }
        }


        System.out.println("=== Successfully Processed Requests ===");

        for (int i = 0; i < successfulRequests.size(); i++) {

            String[] request = successfulRequests.get(i);

            System.out.println(request[0] + " " + request[1]);
        }


        System.out.println();
        System.out.println("=== Remaining Book Stock ===");

        for (int i = 0; i < books.size(); i++) {

            String[] book = books.get(i);

            System.out.println(book[0] + " : " + book[1]);
        }


        System.out.println();
        System.out.println("=== Failed Requests ===");

        while (!failedRequests.empty()) {

            String[] request = failedRequests.pop();

            System.out.println(request[0] + " " + request[1]);
        }
    }
}