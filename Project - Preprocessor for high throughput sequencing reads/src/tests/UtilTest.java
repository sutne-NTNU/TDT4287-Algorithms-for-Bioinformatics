package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.Test;
import util.Util;





public class UtilTest
{
    @Test
    public void removeSuffixMatchTest()
    {
        String original = "abcdefgh";
        assertEquals("abcdefgh", Util.removeSuffix(original, 0));
        assertEquals("abcde", Util.removeSuffix(original, 3));
        assertEquals("", Util.removeSuffix(original, 8));
    }



    @Test
    public void removePrefixMatchTest()
    {
        String original = "abcdefgh";
        assertEquals("abcdefgh", Util.removePrefix(original, 0));
        assertEquals("defgh", Util.removePrefix(original, 3));
        assertEquals("", Util.removePrefix(original, 8));
    }



    @Test
    public void reverseTest()
    {
        assertEquals("cba", Util.reverse("abc"));
        assertEquals("abcXcba", Util.reverse("abcXcba"));
        assertEquals("01234", Util.reverse("43210"));
    }
}
