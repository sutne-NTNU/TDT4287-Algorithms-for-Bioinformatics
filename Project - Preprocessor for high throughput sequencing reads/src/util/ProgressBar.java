package util;

/**
 * <pre>
 * Output
 * Progress:  74% |███████   |  740/1000  Time Remaining: 00:00:05 
 * Progress: 100% |██████████| 1000/1000  Finished In:    00:00:15
 * </pre>
 */
public class ProgressBar
{
    /** Length of the bar in number of chars */
    private final int increments = 100;
    /** Symbol used for percentage completed in bar */
    private final char DONE = '█';
    // private final char HALF_DONE = '▍';
    /** Symbol used for percentage not completed in bar */
    private final char NOT_DONE = ' ';

    /** Total number of iterations */
    private int total;
    /** Current iteration number */
    private int count;

    /** The message that prepends the bar */
    private final String description;

    /** Wether time should be included and calculated */
    private final boolean withTime;
    /** The time where the progressbar was created */
    private long startTime;
    /** The average execution time per iteration */
    private long averageTime;

    private Thread printer;

    /**
     * Starts a new progressbar and prints the empty bar. Make sure you are not printing anything
     * else before you call {@code <ProgressBar>.done()} if you don't want nasty prints.
     * 
     * <blockquote> Example:
     * 
     * <pre>
     * int iterations = 200;
     * boolean withTime = true;
     * ProgressBar bar = new ProgressBar("Testing", iterations, withTime);
     * for (int i = 0; i < iterations; i++)
     * {
     *     Thread.sleep(100);
     *     bar.increment();
     * }
     * bar.done();
     * </pre>
     */
    public ProgressBar(String description, int iterations, boolean withTime)
    {
        this.total = iterations;
        this.withTime = withTime;
        this.description = description;
        this.count = 0;
        printer = new Thread(new Printer());
        printer.run();
        if (withTime) this.startTime = System.currentTimeMillis();
    }



    /** Prints the current progress, should be called once every iteration. */
    public void increment()
    {
        count++;
        if (count % (total / increments) != 0) return; // no use in printing if it doesnt update
        printer.run();
        if (withTime) averageTime = elapsedTime() / count;
    }



    /** Prints the completed progressbar, should be called when all iterations are completed. */
    public void done()
    {
        count = total;
        printer.run();
        // Clear garbage left in System.out and end line
        System.out.print("                                          \n");
    }



    /** Get the total elapsed time since the prograssbar was created */
    private long elapsedTime()
    {
        return System.currentTimeMillis() - startTime;
    }



    /** Formats milliseconds to a clock in the form 00:00:00 (hours, minutes, seconds). */
    private String formatTime(long milliseconds)
    {
        long seconds = milliseconds / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;
        minutes = minutes % 60;
        seconds = seconds % 60;
        return "%02d:%02d:%02d".formatted(hours, minutes, seconds);
    }



    private class Printer implements Runnable
    {
        /** Prints the bar itself */
        @Override
        public void run()
        {
            StringBuilder bar = new StringBuilder(description);
            double percent_completed = 100 * count / (double) total;
            bar.append(": %.0f%%|".formatted(percent_completed));
            for (int i = 0; i < increments; i++)
            {
                double percent = (i / (double) increments) * 100;
                bar.append(percent < percent_completed ? DONE : NOT_DONE);
            }
            bar.append("| ").append(count).append("/").append(total);

            if (withTime)
            {
                if (0 < count && count < total)
                {
                    long timeLeft = (total - count) * averageTime;
                    bar.append(" \tTime remaining: ").append(formatTime(timeLeft));
                }
                else if (count == total)
                {
                    bar.append(" \tFinished in: ").append(formatTime(elapsedTime()));
                }
            }
            System.out.print("\r" + bar.toString());
        }
    }



    public static void main(String[] args)
    {
        try
        {
            int iterations = 10000;
            ProgressBar bar = new ProgressBar("Testing", iterations, true);
            for (int i = 0; i < iterations; i++)
            {
                Thread.sleep(1);
                bar.increment();
            }
            bar.done();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
