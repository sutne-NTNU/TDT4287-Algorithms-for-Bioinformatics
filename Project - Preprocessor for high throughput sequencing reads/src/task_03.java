


import consts.Files;
import util.TaskUtil;





/*
 * Sequencing errors and error distributions
 * 
 * Based on results from Task 2, can you estimate the rate of sequencing errors per sequence and per
 * nucleotide?
 * 
 * Are sequencing errors uniformly distributed across the sequences (i.e. is the frequency of
 * sequencing errors the same at the start of the sequence as at the end of the sequence, or
 * anywhere else)?
 */
class Task_03
{

    public static void main(String[] args)
    {
        main();
    }



    static void main()
    {
        System.out.println("\nTASK 3");

        System.out.println("Comparing and writing length distributions to file");
        TaskUtil.compareDistributions(Files.Task2.D_10, Files.Task1.DISTRIB,
                Files.Task3.COMP_T2_10_T1);
        TaskUtil.compareDistributions(Files.Task2.D_25, Files.Task1.DISTRIB,
                Files.Task3.COMP_T2_25_T1);
        TaskUtil.compareDistributions(Files.Task2.D_25, Files.Task2.D_10,
                Files.Task3.COMP_T2_25_T2_10);

        System.out.println("Comparing Insertions and Deletions");
        TaskUtil.compareDistributions(Files.Task2.D_10_INDEL, Files.Task1.DISTRIB,
                Files.Task3.COMP_T2_10_INDEL_T1);
        TaskUtil.compareDistributions(Files.Task2.D_25_INDEL, Files.Task1.DISTRIB,
                Files.Task3.COMP_T2_25_INDEL_T1);
        TaskUtil.compareDistributions(Files.Task2.D_10_INDEL, Files.Task2.D_10,
                Files.Task3.COMP_T2_10_INDEL);
        TaskUtil.compareDistributions(Files.Task2.D_25_INDEL, Files.Task2.D_25,
                Files.Task3.COMP_T2_25_INDEL);
    }
}
