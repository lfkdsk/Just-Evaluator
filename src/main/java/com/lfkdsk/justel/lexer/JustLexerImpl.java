package com.lfkdsk.justel.lexer;

import com.lfkdsk.justel.exception.ParseException;
import com.lfkdsk.justel.token.*;
import com.lfkdsk.justel.utils.NumberUtils;
import com.lfkdsk.justel.utils.collection.ArrayQueue;

import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Reader;

import static com.lfkdsk.justel.token.ReservedToken.reservedToken;

/**
 * Lexer - Language Token Lexer
 * Just catch token this Engine supported.
 *
 * @author liufengkai
 *         Created by liufengkai on 2017/7/23.
 */
public class JustLexerImpl implements Lexer {

    /**
     * Token Queue
     */
    private ArrayQueue<Token> queue = new ArrayQueue<>();

    /**
     * has more tokens in Lexer
     */
    private boolean hasMore;

    private LineNumberReader reader;

    public JustLexerImpl(Reader reader) {
        this.reader = new LineNumberReader(reader);
        this.hasMore = true;
    }

    @Override
    public Token peek(int index) {
        if (fillQueue(index)) {
            return queue.get(index);
        } else {
            return Token.EOF;
        }
    }

    @Override
    public Token read() {
        if (fillQueue(0)) {
            return queue.poll();
        } else {
            return Token.EOF;
        }
    }

    /**
     * 填充队列
     *
     * @param index 指定num
     * @return 返回状态
     * @throws ParseException
     */
    private boolean fillQueue(int index) throws ParseException {
        while (index >= queue.size()) {
            if (hasMore) {
                readLine();
            } else {
                return false;
            }
        }
        return true;
    }


    /**
     * read one line
     *
     * @throws ParseException
     */
    private void readLine() throws ParseException {
        String line;

        try {
            line = reader.readLine();
        } catch (IOException e) {
            throw new ParseException(e);
        }

        if (line == null) {
            hasMore = false;
            return;
        }


        // init base message
        start = -1;
        end = line.length();
        currentReadString = line;
        lineNumber = reader.getLineNumber();

        if (end == 0) return;

        while (start < end) {
            scanToken();
        }

        addToken(SepToken.EOL_TOKEN);
    }

    /**
     * current peek char.
     */
    private char peekChar = ' ';

    /**
     * start : current index.
     * end   : end of line.
     * lineNumber: line number.
     */
    private int start = 0, end = 0, lineNumber = 0;

    /**
     * current read string.
     */
    private String currentReadString = "";

    /**
     * read next char => peekChar
     *
     * @return peekChar name
     */
    private char readChar() {
        start++;
        if (start >= end) {
            peekChar = ' ';
            // end of file
            return peekChar;
        }

        // read string
        peekChar = currentReadString.charAt(start);

        return peekChar;
    }

    /**
     * next char is equal this char?
     *
     * @param ch this char
     * @return next char is equal this char?
     */
    private boolean readChar(char ch) {
        return start <= end && currentReadString.charAt(start + 1) == ch;
    }

    private void scanToken() {
        // jump all blank chars
        skipBlank();

        // resolve symbols & operators
        if (resolveSymbol()) return;

        // resolve number
        if (resolveNumber()) return;

        // resolve string
        if (resolveString()) return;

        // resolve id
        if (resolveIDToken()) return;

        // add other symbol
        addToken(new SepToken(lineNumber, String.valueOf(peekChar)));
        readChar();
    }

    /**
     * Skip Blank
     */
    private void skipBlank() {
        for (; ; readChar()) {
            if (peekChar == ' ' || peekChar == '\t') {
                continue;
            } else if (peekChar == '\n')
                lineNumber = lineNumber + 1;
            else break;
        }
    }

    /**
     * resolve symbol token
     *
     * @return is symbol token?
     */
    private boolean resolveSymbol() {
        // current peek char
        switch (peekChar) {
            case '?': {
                addToken(SepToken.QUESTION_TOKEN);
                break;
            }

            case ':': {
                addToken(SepToken.COLON_TOKEN);
                break;
            }

            case '+': {
                addToken(SepToken.PLUS_TOKEN);
                break;
            }

            case '-': {
                addToken(SepToken.MINUS_TOKEN);
                break;
            }

            case '*': {
                addToken(SepToken.MULTIPLY_TOKEN);
                break;
            }

            case '/': {
                addToken(SepToken.DIVIDE_TOKEN);
                break;
            }

            case '%': {
                addToken(SepToken.MOD_TOKEN);
                break;
            }

            case '&': {
                if (readChar('&')) {
                    readChar();
                    addToken(SepToken.AND_TOKEN);
                } else {
                    addToken(SepToken.AMPERSAND_TOKEN);
                }
                break;
            }

            case '|': {
                if (readChar('|')) {
                    readChar();
                    addToken(SepToken.OR_TOKEN);
                } else {
                    addToken(SepToken.BAR_TOKEN);
                }
                break;
            }

            case '<': {
                if (readChar('=')) {
                    readChar();
                    addToken(SepToken.LTE_TOKEN);
                } else {
                    addToken(SepToken.LESS_TOKEN);
                }
                break;
            }

            case '>': {
                if (readChar('=')) {
                    readChar();
                    addToken(SepToken.GTE_TOKEN);
                } else {
                    addToken(SepToken.GREAT_TOKEN);
                }
                break;
            }

            case '!': {
                if (readChar('=')) {
                    readChar();
                    addToken(SepToken.NOT_EQUAL_TOKEN);
                } else {
                    addToken(SepToken.EXCLAM_TOKEN);
                }
                break;
            }

            case '.': {
                addToken(SepToken.DOT_TOKEN);
                break;
            }

            case '[': {
                addToken(SepToken.COLLECT_GET_LEFT_TOKEN);
                break;
            }

            case ']': {
                addToken(SepToken.COLLECT_GET_RIGHT_TOKEN);
                break;
            }

            case '=': {
                if (readChar('=')) {
                    readChar();
                    addToken(SepToken.EQUAL_TOKEN);
                }
                break;
            }

            default:
                return false;
        }

        readChar();
        return true;
    }

    /**
     * resolve number token
     *
     * @return is number token?
     */
    private boolean resolveNumber() {
        if (Character.isDigit(peekChar)) {
            // int name parser
            long v = 0;

            do {
                v = 10 * v + Character.digit(peekChar, 10);
                readChar();
            } while (Character.isDigit(peekChar));

            if (peekChar != '.') {
                // int name
                // check name type is long or integer.
                Number checkedNum = NumberUtils.parseNumber(v);
                int checkedType = Token.INTEGER;

                if (checkedNum instanceof Long) {
                    checkedType = Token.LONG;
                }

                addToken(new NumberToken(lineNumber, checkedType,
                        String.valueOf(checkedNum),
                        checkedNum));

                return true;

            } else {
                double x = v;
                double d = 10;

                for (; ; ) {

                    if (!Character.isDigit(readChar())) {
                        break;
                    }

                    x = x + Character.digit(peekChar, 10) / d;
                    d = d * 10;
                }

                // float or double name
                Number checkedNum = NumberUtils.parseNumber(x);
                int checkedType = Token.FLOAT;

                if (checkedNum instanceof Double) {
                    checkedType = Token.DOUBLE;
                }

                addToken(new NumberToken(lineNumber, checkedType,
                        String.valueOf(checkedNum), checkedNum));

                return true;
            }
        }

        return false;
    }

    /**
     * resolve string
     *
     * @return is string token?
     */
    private boolean resolveString() {
        if (peekChar == '\"') {
            boolean returnFlag = false;
            StringBuilder builder = new StringBuilder();
            char lastChar = ' ';
            for (readChar(); ; readChar()) {
                if (peekChar == '\"' && returnFlag
                        && lastChar != '\\') {
                    break;
                } else if (peekChar == '\n') {
                    lineNumber += 1;
                    continue;
                } else if (peekChar != '\"') {
                    builder.append(peekChar);
                }
                lastChar = peekChar;
                returnFlag = true;
            }

            addToken(new StringToken(lineNumber, builder.toString()));
            readChar();

            return true;
        }

        return false;
    }

    /**
     * resolve id token
     *
     * @return is id token?
     */
    private boolean resolveIDToken() {
        if (Character.isLetterOrDigit(peekChar) || peekChar == '_') {
            StringBuilder buffer = new StringBuilder();

            do {
                buffer.append(peekChar);
                readChar();
            } while (Character.isLetterOrDigit(peekChar) || peekChar == '_');

            // token word
            String token = buffer.toString();

            switch (token) {

                case "true":
                case "false": {
                    addToken(new BoolToken(lineNumber, BoolToken.booleanValue(token)));
                    break;
                }

                default: {
                    addToken(reservedToken.contains(token) ?
                            new ReservedToken(lineNumber, token) :
                            new IDToken(lineNumber, token));
                    break;
                }
            }

            return true;
        }

        return false;
    }

    /**
     * Add Token => Queue
     *
     * @param token Token
     */
    private void addToken(Token token) {
        queue.add(token);
    }
}
