package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.Test;
import algorithms.editdistance.Levenshtein;





public class LevenshteinTest
{

    @Test
    public void minTest()
    {
        assertEquals(0, Levenshtein.min(0, 1, 2));
        assertEquals(0, Levenshtein.min(0, 2, 1));
        assertEquals(0, Levenshtein.min(1, 0, 2));
        assertEquals(0, Levenshtein.min(2, 0, 1));
        assertEquals(0, Levenshtein.min(2, 1, 0));
        assertEquals(0, Levenshtein.min(1, 2, 0));
    }



    @Test
    public void costTest()
    {
        assertEquals(0, Levenshtein.editCost('b', 'b'));
        assertEquals(1, Levenshtein.editCost('a', 'b'));
        assertEquals(1, Levenshtein.editCost(' ', 'a'));
    }



    @Test
    public void editDistanceTest()
    {
        assertEquals(0, Levenshtein.editDistance("abcd", "abcd"));
        assertEquals(1, Levenshtein.editDistance("abcd", "dbcd"));
        assertEquals(2, Levenshtein.editDistance("abcd", "bcda"));
        assertEquals(2, Levenshtein.editDistance("abcd", "bc"));
        assertEquals(4, Levenshtein.editDistance("abcd", ""));
    }
}
