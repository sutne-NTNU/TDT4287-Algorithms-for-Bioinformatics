package algorithms.editdistance;

public class Levenshtein
{

    public static int editCost(char a, char b)
    {
        return a == b ? 0 : 1;
    }



    public static int min(int a, int b, int c)
    {
        if (a < b) return Math.min(a, c);
        return Math.min(b, c);
    }



    /** Normal Levenshtein */
    public static int editDistance(String x, String y)
    {
        int[][] dp = new int[x.length() + 1][y.length() + 1];

        for (int i = 0; i <= x.length(); i++)
        {
            for (int j = 0; j <= y.length(); j++)
            {
                if (i == 0)
                {
                    dp[i][j] = j;
                }
                else if (j == 0)
                {
                    dp[i][j] = i;
                }
                else
                {
                    int a = dp[i - 1][j - 1] + editCost(x.charAt(i - 1), y.charAt(j - 1));
                    int b = dp[i - 1][j] + 1;
                    int c = dp[i][j - 1] + 1;
                    dp[i][j] = min(a, b, c);
                }
            }
        }
        return dp[x.length()][y.length()];
    }
}
