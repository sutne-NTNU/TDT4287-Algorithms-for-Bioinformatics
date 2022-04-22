package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.Before;
import org.junit.Test;
import algorithms.lcsuffix.LCSuffix;





public class LCSuffixTest
{

    LCSuffix lcs;

    @Before
    public void init()
    {
        char[] alphabet = {'A' , 'T' , 'C' , 'G'};
        lcs = new LCSuffix(alphabet);
    }



    @Test
    public void getResultTest()
    {

        lcs.process("ATT");
        assertEquals("ATT", lcs.getResult());
        lcs.process("TGC");
        lcs.process("TCG");
        lcs.process("TCG");
        assertEquals("TCG", lcs.getResult());
        lcs.process("AACT");
        lcs.process("CATG");
        lcs.process("ATTG");
        lcs.process("CACG");
        assertEquals("AACG", lcs.getResult());
    }
}
