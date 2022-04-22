package util;

import algorithms.suffixtree.Node;
import algorithms.suffixtree.NodeType;





public class Util
{
    /** Reverse the string "abc" > "cba" */
    public static String reverse(String str)
    {
        return new StringBuilder(str).reverse().toString();
    }



    /** Remove the suffix from original string */
    public static String removeSuffix(String original, int lengthOfSuffix)
    {
        return original.substring(0, original.length() - lengthOfSuffix);
    }



    /** Remove the prefix from original string */
    public static String removePrefix(String original, int lengthOfPrefix)
    {
        return original.substring(0 + lengthOfPrefix, original.length());
    }



    /** Format the tree for helpful printing from `root` */
    public static String formatTree(Node root, String text)
    {
        StringBuilder builder = new StringBuilder();
        Util.buildString(text, builder, root, "");
        return builder.toString();
    }



    /** recursivele append node and children to the stringbuilder */
    private static void buildString(String text, StringBuilder builder, Node node, String indent)
    {
        if (node.type == NodeType.ROOT)
        {
            builder.append("\nROOT");
        }
        else if (node.type == NodeType.INTERNAL)
        {
            boolean hasLink = node.suffixLink != null;
            builder.append("\n").append(indent).append(node.getSubstringFrom(text))
                    .append("\t(" + node.originIndex + ",")
                    .append(hasLink ? node.suffixLink.originIndex : " ").append(")");
        }
        else // NodeType == LEAF
        {
            builder.append("\n").append(indent).append(node.getSubstringFrom(text)).append("\t[")
                    .append(node.originIndex).append("]");

        }
        for (Node child : node.children.values())
            buildString(text, builder, child, indent + "\t\t\t");
    }
}
