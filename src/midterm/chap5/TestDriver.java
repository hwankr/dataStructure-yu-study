package midterm.chap5;

import java.util.*;

public class TestDriver {
    public static void main(String[] args) {

        LinkedBinaryTree<String> tree = new LinkedBinaryTree<>();

        // 루트 생성
        TreeNode<String> A = tree.addRoot("A");

        // 레벨 1
        TreeNode<String> B = tree.addLeft(A, "B");
        TreeNode<String> C = tree.addRight(A, "C");

        // 레벨 2
        tree.addLeft(B, "D");
        tree.addRight(B, "E");
        tree.addRight(C, "F");

        // inorder 출력
        System.out.print("Inorder: ");
        for (TreeNode<String> node : tree.inorder()) {
            System.out.print(node.getElement() + " ");
        }
        System.out.println();
    }
}
