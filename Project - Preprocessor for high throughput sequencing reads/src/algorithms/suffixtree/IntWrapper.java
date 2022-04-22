package algorithms.suffixtree;

/**
 * A basic wrapper class for an int value, used to prevent copying of a primitive type, and intead
 * passing by reference
 */
public class IntWrapper
{
    private int value;

    public IntWrapper(int value)
    {
        this.value = value;
    }



    /** Increase value by One */
    public void increment()
    {
        value += 1;
    }



    /** Get the current value */
    public int get()
    {
        return value;
    }



    @Override
    public String toString()
    {
        return "" + value + "";
    }
}
