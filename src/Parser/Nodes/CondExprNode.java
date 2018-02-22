package Parser.Nodes;

import Tokenizer.Token;
import Tokenizer.TokenReader;

public class CondExprNode extends ParseNode {
    private LogOrExprNode logOrExprNode;
    private ExprNode exprNode;
    private CondExprNode condExprNode;

    public CondExprNode() {
        logOrExprNode = null;
        exprNode = null;
        condExprNode = null;
    }

    public void setLogOrExprNode(LogOrExprNode logOrExprNode) {
        this.logOrExprNode = logOrExprNode;
    }

    public void setCondExprNode(CondExprNode condExprNode) {
        this.condExprNode = condExprNode;
    }

    public void setExprNode(ExprNode exprNode) {
        this.exprNode = exprNode;
    }

    public static CondExprNode parse(TokenReader tr) {
        LogOrExprNode logOrExprNode = LogOrExprNode.parse(tr);
        if (logOrExprNode != null) {
            Token token;
            CondExprNode condExprNode = new CondExprNode();
            condExprNode.setLogOrExprNode(logOrExprNode);
            token = tr.peek();
            if (token.getValue().equals("?")) {
                tr.read();
                ExprNode exprNode = ExprNode.parse(tr);
                if (exprNode != null) {
                    condExprNode.setExprNode(exprNode);
                    token = tr.peek();
                    if (token.getValue().equals(":")) {
                        tr.read();
                        CondExprNode condExprNode1 = CondExprNode.parse(tr);
                        if (condExprNode1 != null) {
                            condExprNode.setCondExprNode(condExprNode1);
                            return condExprNode;
                        }
                    }
                }
                return null;
            }
            return condExprNode;
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("");
        if (logOrExprNode != null) {
            str.append(logOrExprNode.toString());
            if (exprNode != null && condExprNode != null) {
                str.append("?");
                str.append(exprNode.toString());
                str.append(":");
                str.append(condExprNode.toString());
            }
        }
        return str.toString();
    }
}
