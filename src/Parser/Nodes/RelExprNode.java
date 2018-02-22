package Parser.Nodes;

import Parser.Operators.RelOp;
import Tokenizer.Token;
import Tokenizer.TokenReader;

public class RelExprNode extends ParseNode {
    private RelExprNode relExprNode;
    private RelOp relOp;
    private SimpleExprNode simpleExprNode;

    public RelExprNode() {
        this.relExprNode = null;
        this.relOp = null;
        this.simpleExprNode = null;
    }

    public void setRelExprNode(RelExprNode relExprNode) {
        this.relExprNode = relExprNode;
    }

    public void setRelOp(RelOp relOp) {
        this.relOp = relOp;
    }

    public void setSimpleExprNode(SimpleExprNode simpleExprNode) {
        this.simpleExprNode = simpleExprNode;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("");
        if (simpleExprNode != null) {
            str.append(simpleExprNode);
            if (relOp != null && relExprNode != null) {
                str.append(relOp);
                str.append(relExprNode);
            }
        }
        return str.toString();
    }

    public static RelExprNode parse(TokenReader tr) {
        SimpleExprNode simpleExprNode = SimpleExprNode.parse(tr);
        if (simpleExprNode != null) {
            Token token;
            RelExprNode relExprNode = new RelExprNode();
            relExprNode.setSimpleExprNode(simpleExprNode);
            token = tr.peek();
            if (RelOp.isOp(token)) {
                relExprNode.setRelOp(new RelOp(tr.read()));
                RelExprNode relExprNode1 = RelExprNode.parse(tr);
                if (relExprNode1 != null) {
                    relExprNode.setRelExprNode(relExprNode1);
                    return relExprNode;
                }
                return null;
            }
            return relExprNode;
        }
        return null;
    }
}
