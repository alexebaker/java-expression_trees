package Parser.Nodes;

import Tokenizer.Token;
import Tokenizer.TokenReader;

public class AsgnExprNode extends ParseNode {
    private AsgnExprNode asgnExprNode;
    private CondExprNode condExprNode;

    public AsgnExprNode() {
        this.asgnExprNode = null;
        this.condExprNode = null;
    }

    public void setAsgnExprNode(AsgnExprNode asgnExprNode) {
        this.asgnExprNode = asgnExprNode;
    }

    public void setCondExprNode(CondExprNode condExprNode) {
        this.condExprNode = condExprNode;
    }

    public static AsgnExprNode parse(TokenReader tr) {
        //System.out.println("Parsing Assign Expression...");
        CondExprNode condExprNode = CondExprNode.parse(tr);
        if (condExprNode != null) {
            Token token;
            AsgnExprNode asgnExprNode = new AsgnExprNode();
            asgnExprNode.setCondExprNode(condExprNode);
            token = tr.peek();
            if (token.getValue().equals("=")) {
                tr.read();
                AsgnExprNode asgnExprNode1 = AsgnExprNode.parse(tr);
                if (asgnExprNode1 != null) {
                    asgnExprNode.setAsgnExprNode(asgnExprNode1);
                }
            }
            return asgnExprNode;
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("");
        if (condExprNode != null) {
            str.append(condExprNode.toString());
            if (asgnExprNode != null) {
                str.append("=");
                str.append(asgnExprNode.toString());
            }
        }
        return str.toString();
    }
}
