package Leetcode.Merge_Tree_617;

/*
You are given two binary trees root1 and root2.

Imagine that when you put one of them to cover the other, some nodes of the two trees are overlapped while the others are not. You need to merge the two trees into a new binary tree. The merge rule is that if two nodes overlap, then sum node values up as the new value of the merged node. Otherwise, the NOT null node will be used as the node of the new tree.

Return the merged tree.

Note: The merging process must start from the root nodes of both trees.

주요 아이디어
1. recursive 를 이용해서 각 node 의 value 만 더해서 return 한다.

실패 분석
1. 기존의 풀이는 skewed tree 가 90개 정도 뻗을 시 idx 로 접근하기에 int 혹은 long 으로는 감당할 수 없다.
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

//public class Solution {
//
//    HashMap<Long, TreeNode> map;
//
//    void preorder(TreeNode node, long idx) {
//
//        if (node != null) {
//
//            if (!map.containsKey(idx)) map.put(idx, new TreeNode(0));
//            map.get(idx).val += node.val;
//            preorder(node.left, 2 * idx);
//            preorder(node.right, 2 * idx + 1);
//        }
//    }
//
//    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
//
//        map = new HashMap<>();
//
//        preorder(root1, 1);
//        preorder(root2, 1);
//
//        for (long idx : map.keySet()) {
//
//            if(idx == 1) continue;
//
//            long parentIdx = idx / 2;
//
//            if (parentIdx * 2 == idx) {
//
//                map.get(parentIdx).left = map.get(idx);
//            }
//
//            else if (parentIdx * 2 + 1 == idx) {
//
//                map.get(parentIdx).right = map.get(idx);
//            }
//        }
//
//        return map.get(1L);
//    }
//}

public class Solution {

    TreeNode merge(TreeNode node1, TreeNode node2) {

        if(node1 != null && node2 != null) {

            TreeNode node = new TreeNode(node1.val + node2.val);
            node.left = merge(node1.left, node2.left);
            node.right = merge(node1.right, node2.right);
            return node;
        }

        else if(node1 != null) {
            return node1;
        }

        else if(node2 != null){
            return node2;
        }

        else return null;

    }
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {

        return merge(root1, root2);
    }
}
