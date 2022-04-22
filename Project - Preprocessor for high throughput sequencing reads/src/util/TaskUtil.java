package util;

import java.util.ArrayList;
import java.util.Random;
import algorithms.suffixtree.SuffixTree;





/**
 * Just to prevent as much duplicate code as possible, and make it easier to make appropiate changes
 * for both tasks (since they are very similar)
 */
public class TaskUtil
{
    static FileHandler fileHandler = new FileHandler();

    /**
     * Develop an algorithm that identifies all the sequences in S that contain suffixes that
     * perfectly match a prefix of a.
     */
    public static ArrayList<String> findAndRemoveSuffixPrefixMatch(String A, ArrayList<String> S,
            int error, boolean allowInDel)
    {
        ArrayList<String> withoutAdapterFragments = new ArrayList<>();
        /*
         * To find the sequences in S that have a suffix that perfectly match a prefix of the
         * adapter, i reverse both strings, that way i only need to create 1 suffixtree. (finding a
         * prefix in reversed "S" that matches the suffix in reverse "a")
         */
        // Create prefixtree of adapter
        SuffixTree prefixTree = new SuffixTree(Util.reverse(A));
        ProgressBar progress = new ProgressBar("Finding and removing suffixes", S.size(), true);
        for (String sequence : S)
        {
            if (error <= 0)
            {
                int lengthOfMatch = prefixTree.lengthOfPrefix(Util.reverse(sequence));
                // length of reversed sequence prefix match = length of normal sequence suffix match
                withoutAdapterFragments.add(Util.removeSuffix(sequence, lengthOfMatch));
            }
            else if (allowInDel) // Allow insertions and deletions
            {
                int lengthOfMatch =
                        prefixTree.lengthOfPrefixWithInDel(Util.reverse(sequence), error);
                withoutAdapterFragments.add(Util.removeSuffix(sequence, lengthOfMatch));
            }
            else // only allow mismatches
            {
                int lengthOfMatch = prefixTree.lengthOfPrefix(Util.reverse(sequence), error);
                withoutAdapterFragments.add(Util.removeSuffix(sequence, lengthOfMatch));
            }
            progress.increment();
        }
        progress.done();
        return withoutAdapterFragments;
    }



    public static ArrayList<String> findAndRemoveSuffixPrefixMatch(String A, ArrayList<String> S)
    {
        return findAndRemoveSuffixPrefixMatch(A, S, 0, false);
    }



    /** How many such sequences do you find? */
    public static void printNumberOfSequencesWithAMatch(ArrayList<String> sequences)
    {
        int longest = 0;
        for (String sequence : sequences)
        {
            int length = sequence.length();
            if (longest < length) longest = length;
        }
        int count = 0;
        for (String sequence : sequences)
        {
            if (sequence.length() < longest) count++;
        }
        System.out.println("Sequences with Suffixes mathing the Adapters Prefix: " + count);
    }



    /**
     * What is the length distribution of the sequences that remain after you have removed these
     * perfectly matching adapter fragments?
     */
    public static void writeLengthDistriutionsToFile(ArrayList<String> sequences, String filename)
    {
        // Find Longest remaining sequence
        int longest = 0;
        for (String sequence : sequences)
        {
            int length = sequence.length();
            if (longest < length) longest = length;
        }

        // Count length of each sequence and increase counter in list
        int[] occurences = new int[longest + 1];
        for (String sequence : sequences)
        {
            occurences[sequence.length()] += 1;
        }

        // Write the distribution to file
        fileHandler.openWriter(filename);
        for (int nr : occurences)
        {
            fileHandler.writeLineToWriter("" + nr);
        }
        fileHandler.closeWriter();
    }



    /** Task 1 */
    public static void measureRunningTimes_Task1(String filename)
    {
        int start = 1000;
        int stop = 100000;
        int runs = 200;
        measureRunningTimes(filename, start, stop, runs, 0, false);
    }



    /** Task 2 */
    public static void measureRunningTimes_Task2(String filename, int error, boolean allowInDel)
    {
        int start, stop, runs;
        if (allowInDel)
        {
            start = 10;
            stop = 100;
            runs = 200;
        }
        else
        {
            start = 100;
            stop = 5000;
            runs = 200;
        }
        measureRunningTimes(filename, start, stop, runs, error, allowInDel);
    }



    /**
     * What is the asymptotic (and practical) running time of your algorithm?
     * 
     * @param filename - Outputfile of times
     * @param startLength - length of the first string
     * @param stopLength - length of the last string
     * @param numberOfRuns - number of datapoints bewtween the two lengths (accuracy)
     * @param error - The error permitted by task 2, task 1 will have 0
     */
    private static void measureRunningTimes(String filename, int startLength, int stopLength,
            int numberOfRuns, int error, boolean allowInDel)
    {
        System.out.println("Measuring running times...");
        final int sequencesPerRun = 10000;
        double[] times = new double[numberOfRuns];
        long start, end;
        ProgressBar progress = new ProgressBar("Measuring Time", numberOfRuns, true);
        for (int runNr = 0; runNr < times.length; runNr++)
        {
            // Create the adapter and sequences to compare
            int stringLength = startLength + runNr * stopLength / numberOfRuns;
            String adapter = createRandomDNAString(stringLength);
            String[] sequences = new String[sequencesPerRun];
            for (int i = 0; i < sequences.length; i++)
                sequences[i] = createRandomDNAString(stringLength);

            // Runn appropriate functions for each task
            if (error <= 0)
            {
                start = System.currentTimeMillis();
                {
                    SuffixTree prefixAdapter = new SuffixTree(Util.reverse(adapter));
                    for (int i = 0; i < sequencesPerRun; i++)
                        prefixAdapter.lengthOfPrefix(Util.reverse(sequences[i]));
                }
                end = System.currentTimeMillis();
            }
            else if (allowInDel)
            {
                start = System.currentTimeMillis();
                {
                    SuffixTree prefixAdapter = new SuffixTree(Util.reverse(adapter));
                    for (int i = 0; i < sequencesPerRun; i++)
                        prefixAdapter.lengthOfPrefixWithInDel(Util.reverse(sequences[i]), error);
                }
                end = System.currentTimeMillis();
            }
            else
            {
                start = System.currentTimeMillis();
                {
                    SuffixTree prefixAdapter = new SuffixTree(Util.reverse(adapter));
                    for (int i = 0; i < sequencesPerRun; i++)
                        prefixAdapter.lengthOfPrefix(Util.reverse(sequences[i]), error);
                }
                end = System.currentTimeMillis();
            }
            float milliseconds = (float) (end - start) / sequencesPerRun;
            times[runNr] = milliseconds;
            progress.increment();
        }
        progress.done();

        // Write the times to file for plotting in python
        fileHandler.openWriter(filename);
        fileHandler.writeLineToWriter(startLength + ":" + numberOfRuns + ":" + stopLength);
        for (double time : times)
        {
            fileHandler.writeLineToWriter("" + time);
        }
        fileHandler.closeWriter();
    }



    public static String createRandomDNAString(int length)
    {
        char[] alphabet = {'A' , 'C' , 'T' , 'G' , 'N'};
        StringBuilder str = new StringBuilder();
        Random rand = new Random();
        for (int i = 0; i < length; i++)
            str.append(alphabet[rand.nextInt(alphabet.length)]);
        return str.toString();
    }



    /**
     * Create a new file with the difference between two distributions (A - B)
     * 
     * @param filenameA
     * @param filenameB
     * @param filenameOut
     */
    public static void compareDistributions(String filenameA, String filenameB, String filenameOut)
    {
        ArrayList<String> distributionA = FileHandler.readLinesFromFile(filenameA);
        ArrayList<String> distributionB = FileHandler.readLinesFromFile(filenameB);

        ArrayList<Integer> differences = new ArrayList<>();

        for (int i = 0; i < Math.max(distributionA.size(), distributionB.size()); i++)
        {
            int numA = i >= distributionA.size() ? 0 : Integer.parseInt(distributionA.get(i));
            int numB = i >= distributionB.size() ? 0 : Integer.parseInt(distributionB.get(i));
            differences.add(numA - numB);
        }
        fileHandler.openWriter(filenameOut);
        for (int diff : differences)
        {
            fileHandler.writeLineToWriter("" + diff);
        }
        fileHandler.closeWriter();
    }
}
