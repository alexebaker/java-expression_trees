package Parser.Nodes;

import Tokenizer.Token;
import Tokenizer.TokenReader;

import java.util.Vector;

public class ExprNode extends ParseNode {
    private Vector<AsgnExprNode> asgnExprs;

    public ExprNode() {
        this.asgnExprs = new Vector<>();
    }

    public void addAsgnExpr(AsgnExprNode asgnExpr) {
        asgnExprs.add(asgnExpr);
    }

    public Vector<AsgnExprNode> getAsgnExprs() {
        return asgnExprs;
    }

    /**
     * Function to be called to parse an Expression as defined in the grammar.
     *
     * @return true if the parse was successful, false otherwise
     */
    public static ExprNode parse(TokenReader tr) {
        //System.out.println("Parsing Expression...");
        Token token;
        ExprNode exprNode = new ExprNode();

        while (true) {
            AsgnExprNode asgnExprNode = AsgnExprNode.parse(tr);
            if (asgnExprNode != null) {
                //System.out.println("Good Asgn Expr...");
                exprNode.addAsgnExpr(asgnExprNode);
                token = tr.peek();
                if (token.getValue().equals(",")) {
                    tr.read();
                    continue;
                }
                return exprNode;
            }
            break;
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("");
        for (AsgnExprNode asgnExpr : getAsgnExprs()) {
            str.append(asgnExpr.toString());
        }
        return str.toString();
    }
}
