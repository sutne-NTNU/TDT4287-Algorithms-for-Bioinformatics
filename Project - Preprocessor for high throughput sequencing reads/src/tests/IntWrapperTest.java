package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.Test;
import algorithms.suffixtree.IntWrapper;





public class IntWrapperTest
{

    @Test
    public void incrementTest()
    {
        IntWrapper wrapper = new IntWrapper(0);
        assertEquals(0, wrapper.get());
        wrapper.increment();
        assertEquals(1, wrapper.get());
        wrapper.increment();
        assertEquals(2, wrapper.get());
    }
}
