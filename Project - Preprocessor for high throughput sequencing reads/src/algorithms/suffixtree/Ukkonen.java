package algorithms.suffixtree;

/**
 * Ukkonen's Algorithm for creating a suffix tree.
 */
class Ukkonen
{
    /** The string we are creating the tree for. */
    private static char[] text;
    /** Root of the tree. */
    private static Node root;

    /** last index (exclusive) in original string we are currently checking. */
    private static IntWrapper position;
    /** Previous node if we perform multiple insertions in same step, will be null otherwise */
    private static Node waitingForSuffixLink;

    // Active Point
    /** Amount of suffixes we currently need to add. */
    private static int remainder;
    /** Position where we are inserting a suffix. */
    private static Node activeNode;
    /** Current edge (key) we have selected from activeNode.children. */
    private static char activeEdge;
    /** Remainder on current edge. */
    private static int activeLength;


    /**
     * Keep track of how many leaves are created, because all leaves are created in the order they
     * appear as suffixes the leafcounter actually gives an accurate index of where each leafs
     * suffix begins in the original string. Example leafCounter = 3 means suffix that ends in that
     * leaf start at index 3 in the original string.
     */
    private static int leafNodeCounter;
    /** Gives ID to internal nodes for debugging */
    private static int internalNodeCounter;

    /** Current Index in string we are at, accessible in entire class */
    private static int currentIndex;
    /** Current Character in string we are at, accessible in entire class */
    private static char currentChar;



    /**
     * Creates a suffix tree using Ukkonen's Algorithm.
     *
     * @param string - The string to create a suffix tree for
     * @return Root of the tree
     */
    static Node createTree(final String string)
    {
        text = string.toCharArray();
        root = Node.ROOT();

        leafNodeCounter = 0;
        internalNodeCounter = 0;

        position = new IntWrapper(0);
        activeNode = root;
        activeEdge = '\0';
        activeLength = 0;
        remainder = 0;

        for (currentIndex = 0; currentIndex < text.length; currentIndex++)
        {
            currentChar = text[currentIndex];
            waitingForSuffixLink = null;

            position.increment();
            remainder++;

            while (remainder > 0)
            {
                if (activeLength == 0) activeEdge = currentChar;

                /** The edge we are currently heading down */
                Node currentEdge = activeNode.children.get(activeEdge);

                if (currentEdge == null)
                {
                    insertLeaf(activeNode);
                    addSuffixLink(activeNode);
                }
                else
                {
                    if (activeNodeIsMoved(currentEdge)) continue;
                    if (text[currentEdge.start + activeLength] == currentChar)
                    {
                        // Dont need to change tree, suffix already exists
                        activeLength++;
                        addSuffixLink(activeNode);
                        break;
                    }
                    splitNode(currentEdge);
                }

                remainder--;

                if (activeNode == root && activeLength > 0)
                {
                    int nextCharIndex = currentIndex - remainder + 1;
                    activeEdge = text[nextCharIndex];
                    activeLength--;
                }
                else if (activeNode.suffixLink != null)
                {
                    activeNode = activeNode.suffixLink;
                }
                else
                {
                    activeNode = root;
                }
            }
        }
        return root;
    }



    /**
     * Insert a new Leaf Node as child of {@code parent}.
     * 
     * In the tree it will appear like this (--- is the rest of the tree):
     * 
     * <pre>
     * ---  ParentNode
     *          NewLeafNode
     * </pre>
     */
    private static void insertLeaf(Node parent)
    {
        Node leaf = Node.LEAF(leafNodeCounter, currentIndex, position);
        parent.addChild(currentChar, leaf);
        leafNodeCounter++;
    }



    /**
     * Split the {@code originalNode} into three parts:
     * 
     * <ul>
     * <li>A new Internal Node</li>
     * <li>The Original (updated) Node</li>
     * <li>New Leaf Node</li>
     * </ul>
     * 
     * In the tree it will appear like this (--- is the rest of the tree):
     * 
     * <pre>
     * ---  ActiveNode
     *          NewInternalNode
     *              OriginalNode ---
     *              NewLeafNode
     * </pre>
     */
    private static void splitNode(Node originalNode)
    {
        // Create new internal node and add that as child to activeNode
        Node newInternalNode = Node.INTERNAL(originalNode.start, originalNode.start + activeLength);
        activeNode.addChild(activeEdge, newInternalNode);

        // Create new leaf from the new internal node
        insertLeaf(newInternalNode);

        // offset original start to account for its new parent
        originalNode.start += newInternalNode.length();
        // add old node as child to the new internal node
        newInternalNode.addChild(text[originalNode.start], originalNode);

        addSuffixLink(newInternalNode);

        // Update node ID
        newInternalNode.originIndex = internalNodeCounter;
        internalNodeCounter++;
    }



    /**
     * Check if there is a node waiting for a suffix link, if there is, link that node to
     * {@code node}. If there isn't, set {@code node} to be waiting for a suffix link.
     */
    private static void addSuffixLink(Node node)
    {
        if (waitingForSuffixLink != null) waitingForSuffixLink.suffixLink = node;
        waitingForSuffixLink = node;
    }



    /**
     * In the event where our current edge is too short to account for an entire suffix we have to
     * move our active node down the tree. Example: if activeLength is 4 but the edge only has a
     * length of two, we have to pass atleast one internal node. This is done by changing our
     * activevNode and decreasing the activeLength + setting activeEdge
     * 
     * @return {@code true} if the actieNode is moved. If this happens the iteration of adding that
     *         suffix must restart (to account for and verify the new values)
     */
    private static boolean activeNodeIsMoved(Node currentEdgeNode)
    {
        if (currentEdgeNode.length() > activeLength) return false;

        // Reached the end of our current activeNode, so walk down the tree and "reset" values
        activeEdge = text[activeNode.start + currentEdgeNode.length()];
        activeLength -= currentEdgeNode.length();
        activeNode = currentEdgeNode;
        return true;
    }
}
