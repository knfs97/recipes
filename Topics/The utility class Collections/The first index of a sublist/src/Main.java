import java.util.Collections;
import java.util.List;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            List<String> seq1 = List.of(sc.nextLine().split(" "));
            List<String> seq2 = List.of(sc.nextLine().split(" "));

            String firstNumAtSeq1 = seq1.get(0);
            String firstNumAtSeq2 = seq2.get(0);

            int firstOccurrenceAtSeq1 = seq2.contains(firstNumAtSeq1) ? seq2.lastIndexOf(firstNumAtSeq1) : -1;
            int firstOccurrenceAtSeq2 = seq1.contains(firstNumAtSeq2) ? seq1.lastIndexOf(firstNumAtSeq2) : -1;

            System.out.println(firstOccurrenceAtSeq1 + " " + firstOccurrenceAtSeq2);
        }
    }
}