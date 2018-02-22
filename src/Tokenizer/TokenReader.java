package Tokenizer;

import java.nio.BufferOverflowException;
import java.nio.CharBuffer;

import Compiler.CompilerState;
import Compiler.CompilerIO;

public class TokenReader {
    private CompilerState cs;
    private boolean hasBufferedToken;
    private Token bufferedToken;
    private int bufferCapacity = 1024;

    public TokenReader(CompilerState cs) {
        this.cs = cs;
        this.hasBufferedToken = false;
        this.bufferedToken = null;
    }

    public Token read() {
        if (hasBufferedToken) {
            hasBufferedToken = !hasBufferedToken;
            return bufferedToken;
        }
        return getNextToken();
    }

    public Token peek() {
        if (!hasBufferedToken) {
            bufferedToken = getNextToken();
            hasBufferedToken = !hasBufferedToken;
        }
        return bufferedToken;
    }

    /**
     * Gets the next token from the input based on what is read from the compiler state
     * and returns the token.
     */
    private Token getNextToken() {
        int ch;
        CharBuffer charBuffer = CharBuffer.allocate(bufferCapacity);

        while (true) {
            ch = cs.getIO().read();

            // First, determine special cases that to not need to be tokenized
            if (EOFToken.isToken(ch)) {
                return new EOFToken(cs);
            }
            else if (Character.isWhitespace(ch)) {
                continue;
            }

            try {
                charBuffer.append((char) ch);
            }
            catch (BufferOverflowException ex) {
                System.err.println("Token exceeded " + bufferCapacity + " bytes.");
                ex.printStackTrace();
                cs.getIO().close();
                System.exit(1);
            }

            String buf = new String(charBuffer.array()).trim();
            int nextCh = cs.getIO().peek();
            String nextToken = Character.toString((char) nextCh);

            // Determine if the token in valid. The inside if statements determines
            // if the token needs to be written to the output stream
            if (isComment(buf)) {
            }
            else if (LiteralToken.isToken(buf)) {
                // The last check for a number token handles the case when a '.' is a literal token and not part of a number
                if (isDelim(nextCh) || (!LiteralToken.isToken(buf+nextToken) && !isComment(buf+nextToken)) || NumberToken.isToken(nextToken)) {
                    return new LiteralToken(buf, cs);
                }
            }
            else if (KeywordToken.isToken(buf)) {
                if (isDelim(nextCh) || LiteralToken.isToken(nextToken)) {
                    return new KeywordToken(buf, cs);
                }
            }
            else if (IdentifierToken.isToken(buf)) {
                if (isDelim(nextCh) || LiteralToken.isToken(nextToken)) {
                    return new IdentifierToken(buf, cs);
                }
            }
            else if (NumberToken.isToken(buf)) {
                // The last check catches numbers which would have more than one '.' in it. If the next token is a '.',
                // Then is will print out this number and treat the next '.' as a literal token
                if (isDelim(nextCh) || isNumDelim(nextToken) || !NumberToken.isToken(buf+nextToken)) {
                    return new NumberToken(buf, cs);
                }
            }
            else {
                return new IllChrToken(buf, cs);
            }
        }
    }

    public void handleError() {
        while (true) {
            if (EOFToken.isToken(peek())) {
                return;
            }
            else if (read().getValue().equals(";")) {
                return;
            }
        }
    }

    private void handleComment(CompilerIO io) {
        while (true) {
            if (io.peek() == -1) {
                return;
            }
            else if (io.read() == '\n') {
                return;
            }
        }
    }

    /**
     * Basic delimiter check for all tokens
     *
     * @param ch Character to check if it is a delimiter or not
     * @return true if ch is a delimiter, false otherwise
     */
    private boolean isDelim(int ch) {
        return EOFToken.isToken(ch) || Character.isWhitespace(ch) || IllChrToken.isToken(Character.toString((char) ch));
    }

    /**
     * Special delimiter cases for numbers.
     *
     * @param str String to check if it deliminates a number
     * @return true if str is a number delimiter, false otherwise
     */
    private boolean isNumDelim(String str) {
        return !str.equals(".") || LiteralToken.isToken(str) || KeywordToken.isToken(str) || IdentifierToken.isToken(str);
    }

    /**
     * Checks for comments
     *
     * @param str string to check if it is a comment token
     * @return true if str is a comment token, false otherwise
     */
    private boolean isComment(String str) {
        return str.equals("//");
    }
}
