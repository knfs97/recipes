import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            int numberOfGroceries = sc.nextInt();
            LocalTime timeOutOfOffice = LocalTime.of(19, 30).plusMinutes(30);
            sc.nextLine();
            List<String> openedGroceries = new ArrayList<>();
            while (numberOfGroceries > 0) {
                String[] grocery = sc.nextLine().split(" ");
                LocalTime timeWhenGroceryCloses = LocalTime.parse(grocery[1]);
                if (timeOutOfOffice.isBefore(timeWhenGroceryCloses)) openedGroceries.add(grocery[0]);
                numberOfGroceries--;
            }
            openedGroceries.forEach(System.out::println);
        }
    }
}