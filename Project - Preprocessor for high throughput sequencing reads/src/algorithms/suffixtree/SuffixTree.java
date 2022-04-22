package algorithms.suffixtree;

import algorithms.editdistance.Levenshtein;
import util.Util;





public class SuffixTree
{
    private final String text;
    public final Node root;

    public SuffixTree(String str)
    {
        text = (str + "$");
        root = Ukkonen.createTree(text);
    }



    ////////////////////////////////////////////////////////////////////////////
    // ------------------------- PERFECT MATCHING---------------------------- //
    ////////////////////////////////////////////////////////////////////////////



    /**
     * Finds the length of the longest prefix in the provides string, that perfectly matches a
     * suffix in our tree.
     * 
     * <blockquote> Example:
     * 
     * <pre>
     * // tree has string: "abcdefg";
     * tree.lenghtOfPrefixMatch("efgxxx") returns 3
     * </pre>
     * 
     * </blockquote>
     */
    public int lengthOfPrefix(String prefix)
    {
        if (prefix.equals("")) return 0;
        Node node = root;
        int longestPrefix = 0;
        int length = 0;
        while ((node = node.children.get(prefix.charAt(length))) != null)
        {
            length++; // node is not null, so there was a match at "length"
            int index = node.start + 1; // find index we are at in original text

            // Check each character in the current node
            while (true)
            {
                if (isSuffix(prefix, length, node, index)) longestPrefix = length;
                if (index == node.end.get()) break; // end of this node
                // End of string we are checking (without it being a valid suffix in our tree)
                if (length == prefix.length()) return longestPrefix;
                if (text.charAt(index) != prefix.charAt(length)) return longestPrefix;

                length++;
                index++;
            }
        }
        return longestPrefix;
    }



    /**
     * Is the length of the prefix an actual suffix (this is a last minute hack because i got some
     * wrong results, shouldnt have to create and compare the suffix and prefix directly)
     */
    private boolean isSuffix(String fullPrefix, int length, Node node, int index)
    {
        // the current char is the suffix character then yes, suffix is valid
        if (text.charAt(index) == '$') return true;
        // If we are at the end of our node, and one of the children is the suffix character, then
        // this is also a valid suffix
        // if (index == node.end.get() && node.children.get('$') != null) return true;
        // return false;
        String prefix = fullPrefix.substring(0, length);
        String suffix = text.substring(text.length() - 1 - length, text.length() - 1);
        return prefix.equals(suffix);
    }



    ////////////////////////////////////////////////////////////////////////////
    // ------------------------- IMPERFECT MATCHING-------------------------- //
    ////////////////////////////////////////////////////////////////////////////



    /**
     * When we encounter an error this means that error could have any other valid value, we
     * therefor must go down all branches of the tree. In other words we just perform a BFS (Breadth
     * First Search), keeping track of the errors we make along the way down the tree. When
     * accounting for errors, it is important to realise that the number of errors depends on how
     * long the prefix we are returning are, we therefor need to go through the entire tree to the
     * leaes, and then finally check if the errors are too many
     * 
     * <pre>
     *  
     *  While there are more charatcers in this node
     *      increase indexes
     *      if error
     *         increase numErrors
     *  Move down the three in all directions from this node
     *      perform the same for node.children (recursive) 
     *      keep track of the longest prefix (with not too many errors) of the children
     *  return the longest prefix
     * 
     * </pre>
     * 
     * @return The longest correct suffix from this node.
     */
    private int lengthOfPrefix(Node node, String prefix, int longest, int length, int errors,
            double errorLimit, boolean withInDel)
    {
        int index = node.start;
        // Go through and compare all chars in this node
        while (true)
        {
            if (isSuffix(prefix, length, errors, errorLimit, withInDel)) longest = length;
            if (index == node.end.get()) break;
            if (length == prefix.length()) return longest;
            // new error along ths path, but keep going
            if (text.charAt(index) != prefix.charAt(length)) errors++;

            length++;
            index++;
        }
        // Got through all of this nodes children, and not at the end yet. see if any child can
        // improve the length
        for (Node childNode : node.children.values())
        {
            int childLength = lengthOfPrefix(childNode, prefix, longest, length, errors, errorLimit,
                    withInDel);
            if (longest < childLength) longest = childLength;
        }
        return longest;
    }



    /**
     * Finds the length of the longest prefix in `prefixString` that matches one of the tree's
     * suffixes with the errorPercent. This measn that the matching suffix and prefix has to match
     * with at most {@code errorPercent * length} mistakes. If errorPercent is 50%, and the method
     * returns length=10, that means that up to 5 of the characters in the prefix doesnt match.
     * <blockquote> Example:
     * 
     * <pre>
     * // tree has string: "abcdefg";
     * tree.lengthOfPrefixMatch("bcdefg-----",  0) returns 6 -> "bcdefg"
     * tree.lengthOfPrefixMatch("-c-e-g-----", 50) returns 6 -> "-c-e-g"
     * </pre>
     * 
     * </blockquote>
     */
    public int lengthOfPrefix(String prefix, int errorPercent)
    {
        if (prefix.equals("")) return 0;
        return lengthOfPrefix(root, prefix, 0, 0, 0, (double) errorPercent, false);
    }



    /**
     * Same as lengthOfPrefix() but now allows insertions and deletions (not only mismath errors)
     */
    public int lengthOfPrefixWithInDel(String prefix, int errorPercent)
    {
        if (prefix.equals("")) return 0;
        return lengthOfPrefix(root, prefix, 0, 0, 0, (double) errorPercent, true);
    }



    /**
     * <blockquote> THIS WORKS, BUT IS ABSOLUTELY USELESS, DO NOT DO THIS </bloackquote>
     * 
     * don't believe me? just look at the execution times
     * 
     * Compares using Levenshtein (EditDistance) to allow insertions and deletions, as is this a
     * HORRIBLE way to at because we are doing the oposite of dynamic programming (we recalculate
     * the same values basically every call). So an alternative would be passing the EditDistance
     * matrix, that way we could just extend the matrix as we explore further down the tree (this
     * takes a lot of space though), this could maybe also have been used to solve the enitre
     * problem but, you know, i have gotten too far now to make such drastic changes
     */
    private boolean isSuffix(String fullPrefix, int length, int errors, double errorLimit,
            boolean withInDel)
    {
        if (text.length() - 1 < length) return false;
        String prefix = fullPrefix.substring(0, length);
        String suffix = text.substring(text.length() - 1 - length, text.length() - 1);
        if (withInDel)
        {
            errors = Levenshtein.editDistance(prefix, suffix);
        }
        else
        {
            errors = 0;
            for (int i = 0; i < length; i++)
                if (prefix.charAt(i) != suffix.charAt(i)) errors++;
        }
        double percent = 100.0 * (double) errors / length;
        return percent <= errorLimit;
    }



    ////////////////////////////////////////////////////////////////////////////
    // ------------------------------ NOT USED ------------------------------ //
    ////////////////////////////////////////////////////////////////////////////



    @Override
    public String toString()
    {
        return Util.formatTree(root, text);
    }



    /**
     * Return the starting index in the original string where we find the substring
     * 
     * @param substring
     * @return Index of original str where substring starts, -1 if no match is found
     */
    public int findSubstring(String substring)
    {
        if (substring.equals("")) return -1;
        Node node = root;
        int substringIndex = 0;
        while ((node = node.children.get(substring.charAt(substringIndex))) != null)
        {
            substringIndex++;
            int textIndex = node.start + 1;
            // Go through all chars for this node (except the first because it is already a match)
            while (textIndex < node.end.get() && substringIndex < substring.length())
            {
                if (substring.charAt(substringIndex) != text.charAt(textIndex)) return -1;
                substringIndex++;
                textIndex++;
            }
            // Gone through entire substring without finding mistake?
            if (substringIndex == substring.length()) return getMatchIndex(node);
        }
        // Ran out of nodes that match the substring
        return -1;
    }



    // Returns the first index (smallest) from all of the leaves below this node.
    private int getMatchIndex(Node node)
    {
        if (node.type == NodeType.LEAF) return node.originIndex;
        int smallest = text.length() + 1;
        for (Node child : node.children.values())
        {
            int childIndex = getMatchIndex(child);
            if (childIndex < smallest) smallest = childIndex;
        }
        return smallest;
    }



    public boolean contains(String substring)
    {
        return findSubstring(substring) != -1;
    }



    /** Find Starting index of suffix if it matches. {@code -1} otherwise */
    public int findSuffix(String suffix)
    {
        return findSubstring(suffix + "$");
    }
}
