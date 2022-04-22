package consts;

public final class Files
{
    static final String RESULTS = "src/results/";
    static final String DATA = "src/data/";

    public class Input
    {
        public static final String S_3_SEQUENCE = DATA + "s_3_sequence_1M.txt";
        public static final String UNKOWN_ADAPTER = DATA + "tdt4287-unknown-adapter.txt";
        public static final String SEQSET3 = DATA + "seqset3.txt";
        public static final String MULTIPLEXED = DATA + "MultiplexedSamples.txt";
    }

    public class Task1
    {
        public static final String DISTRIB = RESULTS + "task1-length-distribution.txt";
        public static final String TIMES = RESULTS + "task1-times.txt";
    }

    public class Task2
    {
        static final String TASK_2 = RESULTS + "task2-";
        static final String D = TASK_2 + "length-distribution";

        public static final String D_10 = D + "-10.txt";
        public static final String D_25 = D + "-25.txt";

        public static final String D_10_INDEL = D + "-10-with-indel.txt";
        public static final String D_25_INDEL = D + "-25-with-indel.txt";

        public static final String TIMES = TASK_2 + "times.txt";
        public static final String TIMES_INDEL = TASK_2 + "times-with-indel.txt";
    }


    public class Task3
    {
        static final String TASK_3 = RESULTS + "task3-";

        public static final String COMP_T2_10_T1 = TASK_3 + "task2-10-vs-task1.txt";
        public static final String COMP_T2_25_T1 = TASK_3 + "task2-25-vs-task1.txt";
        public static final String COMP_T2_25_T2_10 = TASK_3 + "task2-25-vs-task2-10.txt";
        public static final String COMP_T2_10_INDEL = TASK_3 + "10-indel-vs-10.txt";
        public static final String COMP_T2_25_INDEL = TASK_3 + "25-indel-vs-25.txt";
        public static final String COMP_T2_10_INDEL_T1 = TASK_3 + "10-indel-vs-t1.txt";
        public static final String COMP_T2_25_INDEL_T1 = TASK_3 + "25-indel-vs-t1.txt";
    }

    public class Task4
    {
        static final String TASK_4 = RESULTS + "task4-";

        public static final String ORIGINAL_LENGTH = TASK_4 + "original-length.txt";
        public static final String NEW_LENGTH = TASK_4 + "new-length.txt";
        public static final String COMP_OLD_NEW = TASK_4 + "length-comparison.txt";
        public static final String TIMES = TASK_4 + "times.txt";
    }
}
