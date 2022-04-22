package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.Test;
import algorithms.suffixtree.IntWrapper;
import algorithms.suffixtree.Node;





public class NodeTest
{

    @Test
    public void getSubstringTest()
    {
        Node node = Node.LEAF(0, 1, new IntWrapper(4));
        assertEquals("", node.getSubstringFrom(""));
        assertEquals("", node.getSubstringFrom("0"));
        assertEquals("1", node.getSubstringFrom("01"));
        assertEquals("123", node.getSubstringFrom("01234"));
        node = Node.LEAF(0, 0, new IntWrapper(0));
        assertEquals("", node.getSubstringFrom("01234"));
        node = Node.LEAF(0, 3, new IntWrapper(3));
        assertEquals("", node.getSubstringFrom("01234"));
        node = Node.LEAF(0, 3, new IntWrapper(4));
        assertEquals("3", node.getSubstringFrom("01234"));
    }



    @Test
    public void lengthTest()
    {
        Node node = Node.LEAF(0, 0, new IntWrapper(2));
        assertEquals(2, node.length());
        node = Node.LEAF(0, 2, new IntWrapper(3));
        assertEquals(1, node.length());
    }



    @Test
    public void equalsTest()
    {
        int start = 1;
        int end = 3;
        Node node1 = Node.LEAF(0, start, new IntWrapper(end));
        assertTrue(node1.equals(node1));
        Node node2 = Node.LEAF(0, start, new IntWrapper(end));
        assertTrue(node1.equals(node2));
        Node node3 = Node.LEAF(0, start + 1, new IntWrapper(end));
        assertFalse(node1.equals(node3));
    }
}
