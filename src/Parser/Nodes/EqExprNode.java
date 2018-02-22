package Parser.Nodes;

import Parser.Operators.EqOp;
import Tokenizer.Token;
import Tokenizer.TokenReader;

public class EqExprNode extends ParseNode {
    private RelExprNode relExprNode;
    private EqOp eqOp;
    private EqExprNode eqExprNode;

    public EqExprNode() {
        this.relExprNode = null;
        this.eqOp = null;
        this.eqExprNode = null;
    }

    public void setEqExprNode(EqExprNode eqExprNode) {
        this.eqExprNode = eqExprNode;
    }

    public void setEqOp(EqOp eqOp) {
        this.eqOp = eqOp;
    }

    public void setRelExprNode(RelExprNode relExprNode) {
        this.relExprNode = relExprNode;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("");
        if (relExprNode != null) {
            str.append(relExprNode);
            if (eqOp != null && eqExprNode != null) {
                str.append(eqOp);
                str.append(eqExprNode);
            }
        }
        return str.toString();
    }

    public static EqExprNode parse(TokenReader tr) {
        RelExprNode relExprNode = RelExprNode.parse(tr);
        if (relExprNode != null) {
            Token token;
            EqExprNode eqExprNode = new EqExprNode();
            eqExprNode.setRelExprNode(relExprNode);
            token = tr.peek();
            if (EqOp.isOp(token)) {
                eqExprNode.setEqOp(new EqOp(tr.read()));
                EqExprNode eqExprNode1 = EqExprNode.parse(tr);
                if (eqExprNode1 != null) {
                    eqExprNode.setEqExprNode(eqExprNode1);
                    return eqExprNode;
                }
                return null;
            }
            return eqExprNode;
        }
        return null;
    }
}
