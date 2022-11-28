package traverse.treetraverse;

public class TwoTree {
    public static void postOrderTraveral(TreeEntity node){
        if(node == null){
            return;
        }
        postOrderTraveral(node.left);
        System.out.print(node.value+" ");
        postOrderTraveral(node.right);
    }

    public static void main(String[] args) {
        TreeEntity root = new TreeEntity(0);
        TreeEntity left1 = new TreeEntity(1);
        TreeEntity right1 = new TreeEntity(2);
        TreeEntity left2 = new TreeEntity(3);
        TreeEntity right2 = new TreeEntity(4);
        root.setLeft(left1);
        root.setRight(right1);
        left1.setLeft(left2);
        left1.setRight(right2);
        postOrderTraveral(root);
    }

}
