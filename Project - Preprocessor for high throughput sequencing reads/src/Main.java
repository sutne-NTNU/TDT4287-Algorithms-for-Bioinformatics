
public class Main
{
    public static void main(String[] args)
    {

        boolean measureTime = false; // This takes like 3 Hours+
        boolean performExpensive = true; // Not as bas as timing, but still takes like ~30 min

        Task_01.main(measureTime);
        Task_02.main(measureTime, performExpensive);
        Task_03.main();
        Task_04.main(measureTime);
        Task_05.main();
    }
}
