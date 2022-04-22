import java.util.ArrayList;
import algorithms.lcsuffix.LCSuffix;
import consts.Files;
import util.FileHandler;
import util.ProgressBar;
import util.TaskUtil;





/*
 * Finding the adapter sequence
 * 
 * For some datasets the actual adapter sequence could be unknown. However, the adapter sequence
 * could still potentially be inferred by identifying frequently occurring suffixes within the
 * sequence set S.
 * 
 * Develop an algorithm that given a sequence set S, identifies the most likely adapter sequence a
 * and use this algorithm to analyze the set found in the file tdt4287-unknown-adapter.txt.gz.
 * 
 * What is the most likely adapter sequence?
 * 
 * What is the length distribution of the sequences that remain after you have removed these adapter
 * fragments?
 * 
 * What is the running time of your algorithm?
 * 
 * Does the set contain any highly frequent sequences; i.e. what is the frequency distribution of
 * unique sequences in the set?
 * 
 * Does the unique set contain any other common (proper) suffix patterns? Such additional common
 * suffixes could indicate bias in the sequencing experiment.
 * 
 * Does the set in s_3_sequence_1M.txt.gz contain additional common suffix patterns?
 * 
 * What sequence does your algorithm return if you use your algorithm to analyze the files
 * s_3_sequence_1M.txt.gz and Seqset3.txt.gz?
 */
class Task_04
{
    public static void main(String[] args)
    {
        main(true);
    }



    static char[] alphabet = {'A' , 'C' , 'T' , 'G' , 'N'};

    static ArrayList<String> S;
    static String A;

    static ArrayList<String> controlS;
    static String controlA = "TGGAATTCTCGGGTGCCAAGGAACTCCAGTCACACAGTGATCTCGTATGCCGTCTTCTGCTTG";
    static ArrayList<String> S2;



    /**
     * Read from/load the necessary files for this task
     */
    static void init()
    {
        S = FileHandler.readLinesFromFile(Files.Input.UNKOWN_ADAPTER);
        TaskUtil.writeLengthDistriutionsToFile(S, Files.Task4.ORIGINAL_LENGTH);

        controlS = FileHandler.readLinesFromFile(Files.Input.S_3_SEQUENCE);
        S2 = FileHandler.readLinesFromFile(Files.Input.SEQSET3);
    }



    static void main(boolean measureTime)
    {
        System.out.println("\nTASK 4");

        init();

        System.out.println("\nBASELINE: Randomly guessing the adapter (≈25%)");
        compare(controlA, TaskUtil.createRandomDNAString(controlA.length()));

        System.out.println("\nCONTROL: Testing the algorithm for sequences with known adapter");
        LCSuffix control = new LCSuffix(alphabet);
        control.process(controlS);
        String controlResult = control.getResult();
        compare(controlA, controlResult);

        System.out
                .println("\nRESULT: Finding most likely adapter in 'tdt4287-unknown-adapter.txt'");
        LCSuffix lcSuffix = new LCSuffix(alphabet);
        lcSuffix.process(S);
        A = lcSuffix.getResult();
        System.out.println("Most likely Adapter Sequence is: \t" + A);

        System.out.println("Removing Perfect Adapter Fragments and writing lengths to file");
        ArrayList<String> withoutAdapterFragments = TaskUtil.findAndRemoveSuffixPrefixMatch(A, S);
        TaskUtil.writeLengthDistriutionsToFile(withoutAdapterFragments, Files.Task4.NEW_LENGTH);
        System.out.println("Comparing Old and New lengths");
        TaskUtil.compareDistributions(Files.Task4.NEW_LENGTH, Files.Task4.ORIGINAL_LENGTH,
                Files.Task4.COMP_OLD_NEW);

        System.out.println("\nRESULT: Finding sequence in Seqset3.txt");
        lcSuffix = new LCSuffix(alphabet);
        lcSuffix.process(S2);
        A = lcSuffix.getResult();
        System.out.println("Sequence found is: \t" + A);

        if (measureTime) measureTime(Files.Task4.TIMES);
    }



    public static void compare(String expected, String actual)
    {
        System.out.println("expected: \t" + expected);
        System.out.println("actual: \t" + actual);
        int correct = 0;
        int comparisonLength = Math.min(expected.length(), actual.length());
        System.out.print("matching: \t");
        for (int i = 0; i < comparisonLength; i++)
        {
            if (expected.charAt(i) == actual.charAt(i))
            {
                System.out.print(expected.charAt(i));
                correct++;
            }
            else
            {
                System.out.print("-");
            }
        }
        double percentCorrect = 100.0 * (double) correct / comparisonLength;
        System.out.printf("\t%.1f%% Correct\n", percentCorrect);
    }



    public static void measureTime(String filename)
    {
        System.out.println("Measuring running times...");
        final int numRuns = 200;
        int startNumber = 100000;
        int endNumber = 10000000;

        int increment = endNumber / numRuns;

        double[] times = new double[numRuns];
        ProgressBar progress = new ProgressBar("Measuring Time", numRuns, true);
        for (int runNr = 0; runNr < times.length; runNr++)
        {
            int numberOfSequencesToCheck = startNumber + runNr * increment;
            long start = System.currentTimeMillis();
            {
                LCSuffix lcs = new LCSuffix(alphabet);
                for (int i = 0; i < numberOfSequencesToCheck; i++)
                {
                    lcs.process(S.get(i % S.size()));
                }
                lcs.getResult();
            }
            long end = System.currentTimeMillis();
            times[runNr] = end - start;
            progress.increment();
        }
        progress.done();

        // Write the times to file for plotting in python
        FileHandler fh = new FileHandler();
        fh.openWriter(filename);
        fh.writeLineToWriter(startNumber + ":" + numRuns + ":" + endNumber);
        for (double time : times)
        {
            fh.writeLineToWriter("" + time);
        }
        fh.closeWriter();
    }
}
