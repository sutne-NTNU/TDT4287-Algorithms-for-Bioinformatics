package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.Test;
import algorithms.suffixtree.Node;
import algorithms.suffixtree.NodeType;
import algorithms.suffixtree.SuffixTree;
import util.Util;





public class SuffixTreeTest
{
    String str = "abcabxabcd";
    SuffixTree tree = new SuffixTree(str);

    String revStr = Util.reverse("abcXbacXXX"); // XXXcabXcba
    SuffixTree revTree = new SuffixTree(revStr);

    @Test
    public void rootHasCorrectAmountOfChildrenTest()
    {
        assertEquals(6, tree.root.children.size());
        assertEquals(5, revTree.root.children.size());
    }



    @Test
    public void numberOfRootsTest()
    {
        assertEquals(1, getNumberOfNodeType(tree.root, NodeType.ROOT));
        assertEquals(1, getNumberOfNodeType(revTree.root, NodeType.ROOT));
    }



    @Test
    public void numberOfInternalNodesTest()
    {
        assertEquals(5, getNumberOfNodeType(tree.root, NodeType.INTERNAL));
        assertEquals(6, getNumberOfNodeType(revTree.root, NodeType.INTERNAL));
    }



    @Test
    public void numberOfLeavesTest()
    {
        assertEquals(str.length() + 1, getNumberOfNodeType(tree.root, NodeType.LEAF));
        assertEquals("abcXbacXXX".length() + 1, getNumberOfNodeType(revTree.root, NodeType.LEAF));
    }



    @Test
    public void containsTest()
    {
        assertTrue(tree.contains("abc"));
        assertTrue(tree.contains("bcd"));
        assertTrue(tree.contains("xabcd"));
        assertFalse(tree.contains("aba"));
        assertFalse(tree.contains("cad"));
        assertFalse(tree.contains("abce"));
    }



    @Test
    public void findSubstringTest()
    {
        assertEquals(0, tree.findSubstring("abc"));
        assertEquals(1, tree.findSubstring("bcabx"));
        assertEquals(5, tree.findSubstring("xabcd"));
        assertEquals(-1, tree.findSubstring(""));
        assertEquals(-1, tree.findSubstring("xbcd"));
        assertEquals(-1, tree.findSubstring("abcabc"));
    }



    @Test
    public void findSuffixTest()
    {
        assertEquals(9, tree.findSuffix("d"));
        assertEquals(5, tree.findSuffix("xabcd"));
        assertEquals(0, tree.findSuffix("abcabxabcd"));
        assertEquals(-1, tree.findSuffix("bc"));
        assertEquals(-1, tree.findSuffix("abcabxabc"));
        assertEquals(-1, tree.findSuffix("abcabxabcabxa"));
    }



    @Test
    public void lengthOfPrefixTest()
    {
        SuffixTree tree = new SuffixTree("abcdefg");
        assertEquals(0, tree.lengthOfPrefix("-bcdefg"));
        assertEquals(0, tree.lengthOfPrefix("abcdef-"));
        assertEquals(2, tree.lengthOfPrefix("fgadcbad"));
        assertEquals(7, tree.lengthOfPrefix("abcdefg"));

        tree = new SuffixTree("AGTTGTACGTCGTCGT");
        assertEquals(2, tree.lengthOfPrefix("GTTGTACTGCATCGAA"));

        tree = new SuffixTree("abxabyab");
        assertEquals(8, tree.lengthOfPrefix("abxabyab")); // bXcba-----
        assertEquals(2, tree.lengthOfPrefix("abxaby")); // bXcba-----

        // XXXcabXcba (Testing that reversing to find matching suffix instead works)
        assertEquals(4, revTree.lengthOfPrefix(Util.reverse("------abcX")));// Xcba------
        assertEquals(2, revTree.lengthOfPrefix(Util.reverse("-----ab"))); // ab-----
    }



    @Test
    public void lengthOfImperfectPrefixTest()
    {
        // From method description
        SuffixTree tree = new SuffixTree("abcdefg");
        assertEquals(6, tree.lengthOfPrefix("bcdefg-----", 0));
        assertEquals(6, tree.lengthOfPrefix("-c-e-g-----", 50));
        // Make sure it still works with 0 errors (same tests as above)
        assertEquals(0, tree.lengthOfPrefix("-bcdefg", 0));
        assertEquals(0, tree.lengthOfPrefix("abcdef-", 0));
        assertEquals(2, tree.lengthOfPrefix("fgadcbad", 0));
        assertEquals(7, tree.lengthOfPrefix("abcdefg", 0));
        tree = new SuffixTree("AGTTGTACGTCGTCGT");
        assertEquals(2, tree.lengthOfPrefix("GTTGTACTGCATCGAA", 0));
        tree = new SuffixTree("abxabyab");
        assertEquals(8, tree.lengthOfPrefix("abxabyab", 0));
        assertEquals(2, tree.lengthOfPrefix("abxaby", 0));
        assertEquals(4, revTree.lengthOfPrefix(Util.reverse("------abcX"), 0));
        assertEquals(2, revTree.lengthOfPrefix(Util.reverse("-----ab"), 0));

        // Test With Errors
        tree = new SuffixTree("abcdefghij");
        // The tests are marked with
        // "// x/y - z%"
        // where "x" is number of char wrong in the "y" long CORRECT suffix, "z" is the percentage
        // if "z" is greater than the accepted Error perdent, result should be zero, otherwise
        // should be "y"
        assertEquals(0, tree.lengthOfPrefix("---", 99)); // 3/3 = 100%
        assertEquals(3, tree.lengthOfPrefix("---", 100)); // 3/3 = 100% = OK
        assertEquals(0, tree.lengthOfPrefix("iij-------", 25)); // 1/3 = 33%
        assertEquals(3, tree.lengthOfPrefix("iij-------", 35)); // 1/3 = 33%
        assertEquals(1, tree.lengthOfPrefix("jefghij", 10)); // 0/1 = 0% , 1/7 = 14,3%
        assertEquals(7, tree.lengthOfPrefix("jefghij", 20)); // 1/7 = 14,3%
        assertEquals(0, tree.lengthOfPrefix("fgfij", 10)); // 1/5 = 20%
        assertEquals(5, tree.lengthOfPrefix("fgfij", 20)); // 1/5 = 20%
    }



    /** Helper method to count the number of a nodetype starting from node (recursively) */
    int getNumberOfNodeType(Node node, NodeType type)
    {
        int count = 0;
        if (node.type == type) count += 1;
        for (Node child : node.children.values())
            count += getNumberOfNodeType(child, type);
        return count;
    }
}
