package Compiler;

import Parser.ParseTree;
import Parser.TokenParser;
import Tokenizer.TokenReader;

public class LCC {
    CompilerState cs;

    public LCC(CompilerState cs) {
        this.cs = cs;
    }

    public void compile() {
        TokenParser tp = new TokenParser(new TokenReader(cs));
        ParseTree parseTree =  tp.getParseTree();
        this.cs.getIO().write(parseTree.toString());
    }
}
