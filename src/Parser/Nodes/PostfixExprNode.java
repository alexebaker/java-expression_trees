package Parser.Nodes;

import Parser.Operators.PostunOp;
import Tokenizer.Token;
import Tokenizer.TokenReader;

public class PostfixExprNode extends ParseNode {
    private PrimaryExprNode primaryExprNode;
    private PostfixExprNode postfixExprNode;
    private PostunOp postunOp;
    private ArraySpecNode arraySpecNode;

    public PostfixExprNode() {
        this.primaryExprNode = null;
        this.postfixExprNode = null;
        this.postunOp = null;
        this.arraySpecNode = null;
    }

    public void setPostfixExprNode(PostfixExprNode postfixExprNode) {
        this.postfixExprNode = postfixExprNode;
    }

    public void setArraySpecNode(ArraySpecNode arraySpecNode) {
        this.arraySpecNode = arraySpecNode;
    }

    public void setPostunOp(PostunOp postunOp) {
        this.postunOp = postunOp;
    }

    public void setPrimaryExprNode(PrimaryExprNode primaryExprNode) {
        this.primaryExprNode = primaryExprNode;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("");
        if (primaryExprNode != null) {
            str.append(primaryExprNode.toString());
        }
        else if (postunOp != null && postfixExprNode != null) {
            str.append("(");
            str.append(postfixExprNode.toString());
            str.append(postunOp.toString());
            str.append(")");
        }
        else if (arraySpecNode != null && postfixExprNode != null) {
            str.append(postfixExprNode.toString());
            str.append(arraySpecNode.toString());
        }
        return str.toString();
    }

    public static PostfixExprNode parse(TokenReader tr) {
        Token token = tr.peek();
        PostfixExprNode postfixExprNode = new PostfixExprNode();
        if (PostunOp.isOp(token)) {
            postfixExprNode.setPostunOp(new PostunOp(tr.read()));
            PostfixExprNode postfixExprNode1 = PostfixExprNode.parse(tr);
            if (postfixExprNode1 != null) {
                postfixExprNode.setPostfixExprNode(postfixExprNode1);
                return postfixExprNode;
            }
            return null;
        }
        else if (token.getValue().equals("[")) {
            ArraySpecNode arraySpecNode = ArraySpecNode.parse(tr);
            if (arraySpecNode != null) {
                postfixExprNode.setArraySpecNode(arraySpecNode);
                PostfixExprNode postfixExprNode1 = PostfixExprNode.parse(tr);
                if (postfixExprNode1 != null) {
                    postfixExprNode.setPostfixExprNode(postfixExprNode1);
                    return postfixExprNode;
                }
                return null;
            }

        }
        else if (IdentifierNode.isID(token) || NumberNode.isNum(token) || token.getValue().equals("(")) {
            PrimaryExprNode primaryExprNode = PrimaryExprNode.parse(tr);
            if (primaryExprNode != null) {
                postfixExprNode.setPrimaryExprNode(primaryExprNode);
                token = tr.peek();
                if (PostunOp.isOp(token) || token.getValue().equals("[")) {
                    PostfixExprNode postfixExprNode1 = PostfixExprNode.parse(tr);
                    if (postfixExprNode1 != null) {
                        postfixExprNode.setPostfixExprNode(postfixExprNode1);
                        return postfixExprNode;
                    }
                    return null;
                }
                return postfixExprNode;
            }
            return null;
        }
        return postfixExprNode;
    }
}
