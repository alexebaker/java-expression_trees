package Parser.Nodes;

import Tokenizer.Token;
import Tokenizer.TokenReader;

public class ArraySpecNode extends ParseNode {
    private OptArraySizeNode optArraySizeNode;

    public ArraySpecNode() {
        this.optArraySizeNode = null;
    }

    public void setOptArraySizeNode(OptArraySizeNode optArraySizeNode) {
        this.optArraySizeNode = optArraySizeNode;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("");
        str.append("[");
        if (optArraySizeNode != null) {
            str.append(optArraySizeNode.toString());
        }
        str.append("]");
        return str.toString();
    }

    public static ArraySpecNode parse(TokenReader tr) {
        Token token = tr.peek();
        if (token.getValue().equals("[")) {
            tr.read();
            ArraySpecNode arraySpecNode = new ArraySpecNode();
            OptArraySizeNode optArraySizeNode = OptArraySizeNode.parse(tr);
            if (optArraySizeNode != null) {
                arraySpecNode.setOptArraySizeNode(optArraySizeNode);
                token = tr.peek();
                if (token.getValue().equals("]")) {
                    tr.read();
                    return arraySpecNode;
                }
            }
        }
        return null;
    }
}
