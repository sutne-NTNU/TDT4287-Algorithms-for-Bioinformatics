package algorithms.suffixtree;

import java.util.HashMap;
import java.util.Map;





/** A Node for the SuffixTree */
public class Node
{
    /** Index of starting letter in original string for this node */
    public int start;
    /**
     * Index of last letter in original string for this node.
     * 
     * using AtomicInteger here because we need to be able to pass `end` as a reference to an int,
     * and using the normal `int` primitive only creates a copy
     */
    IntWrapper end;
    /** Wether this node is ROOT, INTERNAL or LEAF */
    public NodeType type;

    /** Link to node while creating the tree */
    public Node suffixLink;
    /**
     * All of the nodes connected to this node, we use a HashMap to be able to get a specific child
     * in O(1) time. Altnerative would be an array and loop through it every time we need a specific
     * node.
     */
    public Map<Character, Node> children;
    /**
     * The index of which a leaf node's suffix starts in the original String, is also used to debug
     * suffix links for internal nodes
     */
    public int originIndex;

    /** Private Constructor (has to use static factory constructors) */
    private Node(int originIndex, int start, IntWrapper end, NodeType type)
    {
        this.originIndex = originIndex;
        this.start = start;
        this.end = end;
        this.type = type;
        this.children = new HashMap<Character, Node>();
    }



    /** Create a new Root Node */
    public static Node ROOT()
    {
        return new Node(-99, 0, new IntWrapper(0), NodeType.ROOT);
    }



    /**
     * Create a new Internal Node
     * 
     * @param start start index of original string for this node
     * @param end end index of original string for this node
     */
    public static Node INTERNAL(int start, int end)
    {
        return new Node(-1, start, new IntWrapper(end), NodeType.INTERNAL);
    }



    /**
     * Create a new Leaf Node
     * 
     * @param origin index in original string suffix that ends here starts from
     * @param start start index of original string for this node
     * @param end end index of original string for this node
     */
    public static Node LEAF(int originIndex, int start, IntWrapper end)
    {
        return new Node(originIndex, start, end, NodeType.LEAF);
    }



    /** Adds a child to this nodes children */
    public void addChild(char key, Node child)
    {
        children.put(key, child);
    }



    /** Length of the substring contained in this node */
    public int length()
    {
        return end.get() - start;
    }



    /**
     * Get the characters in `str` from `node.start` to `node.end`.
     * 
     * node.end is non inclusive, which means that:
     * 
     * <blockquote>
     * 
     * <pre>
     * // node.start = 2, node.end = 5;
     * text = ['0', '1', '2', '3', '4', '5']
     * node.getSubstringFrom(text) returns "234"
     * </pre>
     * 
     * </blockquote>
     * 
     * @return The substring of `str`
     */
    public String getSubstringFrom(char[] text)
    {
        StringBuilder b = new StringBuilder();
        for (char c : text)
            b.append(c);
        return getSubstringFrom(b.toString());
    }



    /**
     * Get the characters in `str` from `node.start` to `node.end`.
     * 
     * node.end is non inclusive, which means that:
     * 
     * <blockquote>
     * 
     * <pre>
     * // node.start = 2, node.end = 5;
     * node.getSubstringFrom("012345") returns "234"
     * </pre>
     * 
     * </blockquote>
     * 
     * @return The substring of `str`
     */
    public String getSubstringFrom(String str)
    {
        if (str.isEmpty()) return "";
        int start = this.start;
        int end = this.end.get();
        if (start < 0) start = 0;
        if (end > str.length()) end = str.length();
        return str.substring(start, end);
    }



    @Override
    public String toString()
    {
        return "Node: start=%d, end=%d".formatted(start, end.get());
    }



    @Override
    public boolean equals(Object other)
    {
        if (other == null) return false;
        if (!(other instanceof Node)) return false;
        if (this == other) return true;
        Node node = (Node) other;
        if (this.start != node.start) return false;
        if (this.end.get() != node.end.get()) return false;
        return true;
    }
}
