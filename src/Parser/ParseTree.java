package Parser;

public class ParseTree {
    private ParseNode root;

    public ParseTree(ParseNode root) {
        this.root = root;
    }

    @Override
    public String toString() {
        return root.toString();
    }
}
