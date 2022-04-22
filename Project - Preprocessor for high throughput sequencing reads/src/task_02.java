
import java.util.ArrayList;
import consts.Files;
import util.FileHandler;
import util.TaskUtil;





/*
 * Imperfectly matching adapter fragments
 * 
 * Because of sequencing errors, not all suffixes will perfectly match the adapter prefix, but
 * contain one or several mismatches to the adapter.
 * 
 * Develop an algorithm that identifies all the sequences in S that contain suffixes that match a
 * prefix of a and where this suffix can contain up to a given percentage of mismatches to the
 * prefix of a.
 * 
 * How many such sequences do you find if you apply your algorithm to S and a from Task 1, given
 * that the maximum percentage of mismatches is 10%?
 * 
 * What is the length distribution of the sequences that remain after you have removed these
 * imperfectly matching adapter fragments?
 * 
 * What are the answers to the previous two questions if you set the maximum percentage of
 * mismatches to 25%?
 * 
 * What is the answer to the previous three questions if you allow insertions and deletions?
 * 
 * What is the asymptotic (and practical) running time of your algorithm?
 */
class Task_02
{


    public static void main(String[] args)
    {
        main(false, false);
    }

    static String A = "TGGAATTCTCGGGTGCCAAGGAACTCCAGTCACACAGTGATCTCGTATGCCGTCTTCTGCTTG";
    static ArrayList<String> S;

    static void init()
    {
        S = FileHandler.readLinesFromFile(Files.Input.S_3_SEQUENCE);
    }



    static void main(boolean measureTime, boolean performExpensive)
    {
        System.out.println("\nTASK 2");

        init();

        ArrayList<String> sequences;

        System.out.println("\nCalculating result with just mismathcing");
        sequences = TaskUtil.findAndRemoveSuffixPrefixMatch(A, S, 10, false);
        TaskUtil.printNumberOfSequencesWithAMatch(sequences);
        TaskUtil.writeLengthDistriutionsToFile(sequences, Files.Task2.D_10);

        sequences = TaskUtil.findAndRemoveSuffixPrefixMatch(A, S, 25, false);
        TaskUtil.printNumberOfSequencesWithAMatch(sequences);
        TaskUtil.writeLengthDistriutionsToFile(sequences, Files.Task2.D_25);

        if (performExpensive)
        {
            System.out.println("\nCalculating result when allowing insertions and deletions");
            sequences = TaskUtil.findAndRemoveSuffixPrefixMatch(A, S, 10, true);
            TaskUtil.printNumberOfSequencesWithAMatch(sequences);
            TaskUtil.writeLengthDistriutionsToFile(sequences, Files.Task2.D_10_INDEL);

            sequences = TaskUtil.findAndRemoveSuffixPrefixMatch(A, S, 25, true);
            TaskUtil.printNumberOfSequencesWithAMatch(sequences);
            TaskUtil.writeLengthDistriutionsToFile(sequences, Files.Task2.D_25_INDEL);
        }
        if (measureTime)
        {
            TaskUtil.measureRunningTimes_Task2(Files.Task2.TIMES, 25, false);
            TaskUtil.measureRunningTimes_Task2(Files.Task2.TIMES_INDEL, 25, true);
        }
    }
}
