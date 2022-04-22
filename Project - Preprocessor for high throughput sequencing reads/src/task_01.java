import java.util.ArrayList;
import consts.Files;
import util.FileHandler;
import util.TaskUtil;





/*
 * Perfectly matching adapter fragments
 * 
 * The file s_3_sequence_1M.txt.gz contains a set S of sequences (note: one sequence per line; i.e.
 * sequences are newline ‘\n’ separated) from a high throughput sequencing experiment that used the
 * following 3’ adapter sequence:
 * 
 * adapter = “TGGAATTCTCGGGTGCCAAGGAACTCCAGTCACACAGTGATCTCGTATGCCGTCTTCTGCTTG”.
 */
class Task_01
{

    public static void main(String[] args)
    {
        main(false);
    }



    static String A = "TGGAATTCTCGGGTGCCAAGGAACTCCAGTCACACAGTGATCTCGTATGCCGTCTTCTGCTTG";
    static ArrayList<String> S;



    static void init()
    {
        S = FileHandler.readLinesFromFile(Files.Input.S_3_SEQUENCE);
    }



    static void main(boolean measureTime)
    {
        System.out.println("\nTASK 1");

        init();

        ArrayList<String> sequences = TaskUtil.findAndRemoveSuffixPrefixMatch(A, S);
        TaskUtil.printNumberOfSequencesWithAMatch(sequences);
        TaskUtil.writeLengthDistriutionsToFile(sequences, Files.Task1.DISTRIB);
        if (measureTime)
        {
            TaskUtil.measureRunningTimes_Task1(Files.Task1.TIMES);
        }
    }
}
