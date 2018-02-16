package Parser;

import Tokenizer.TokenReader;

public class TokenParser {
    TokenReader tr;

    public TokenParser(TokenReader tr) {
        this.tr = tr;
    }

    public ParseTree getParseTree() {
        return new ParseTree(new ParseNode(tr.read()));
    }
}
