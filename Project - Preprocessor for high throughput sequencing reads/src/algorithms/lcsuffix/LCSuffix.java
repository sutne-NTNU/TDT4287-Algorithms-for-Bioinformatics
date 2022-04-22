package algorithms.lcsuffix;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;





/**
 * Class to find the longest common suffix in a set of strings.
 */
public class LCSuffix
{
    private ArrayList<int[]> counts;
    private char[] alphabet;
    private Map<Character, Integer> indexMap;
    private Map<Integer, Character> charMap;

    public LCSuffix(char[] alphabet)
    {
        counts = new ArrayList<>();
        this.alphabet = alphabet;
        indexMap = new HashMap<>();
        charMap = new HashMap<>();
        for (int i = 0; i < alphabet.length; i++)
        {
            indexMap.put(this.alphabet[i], i);
            charMap.put(i, this.alphabet[i]);
        }
    }



    /**
     * Goes through the string and updates the counts for each char at each corresponding index
     * 
     * @param sequence
     */
    public void process(String sequence)
    {
        char[] chars = sequence.toCharArray();
        for (int i = 0; i < chars.length; i++)
        {
            if (counts.size() < i + 1) counts.add(new int[alphabet.length]);
            // Start from the back of the sequence (suffix comes first in counts)
            char c = chars[(chars.length - 1) - i];

            counts.get(i)[indexMap.get(c)] += 1;
        }
    }



    public void process(ArrayList<String> sequences)
    {
        for (String sequence : sequences)
        {
            process(sequence);
        }
    }



    private char getMostLikely(int index)
    {
        int highestCount = 0;
        int highestIndex = 0;
        int[] thisIndex = counts.get(index);
        for (int i = 0; i < thisIndex.length; i++)
        {
            int count = thisIndex[i];
            if (highestCount < count)
            {
                highestCount = count;
                highestIndex = i;
            }
        }
        return charMap.get(highestIndex);
    }



    public String getResult()
    {
        char[] result = new char[counts.size()];
        for (int i = 0; i < result.length; i++)
        {
            result[i] = getMostLikely(i);
        }
        StringBuilder sb = new StringBuilder();
        // Reverse chars back to be in correct order
        for (int i = result.length - 1; i >= 0; i--)
        {
            sb.append(result[i]);
        }
        return sb.toString();
    }
}
