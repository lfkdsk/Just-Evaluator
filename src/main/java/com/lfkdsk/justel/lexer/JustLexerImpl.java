package com.lfkdsk.justel.lexer;

import com.lfkdsk.justel.exception.ParseException;
import com.lfkdsk.justel.token.*;
import com.lfkdsk.justel.utils.GeneratedId;
import com.lfkdsk.justel.utils.NumberUtils;
import com.lfkdsk.justel.utils.collection.ArrayQueue;

import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static com.lfkdsk.justel.token.ReservedToken.reservedToken;


/**
 * Lexer - Language Token Lexer
 * Just catch token this Engine supported.
 *
 * @author liufengkai
 * Created by liufengkai on 2017/7/23.
 */
public class JustLexerImpl implements Lexer {

    /**
     * Token Queue
     */
    private ArrayQueue<Token> queue = new ArrayQueue<>();

    /**
     * Insert Symbol to Lexer
     */
    private Map<String, Token> insertSymbol = new HashMap<>();

    private Set<Character> avoidChar = new HashSet<>();

    /**
     * has more tokens in Lexer
     */
    private boolean hasMore;

    /**
     * Line Number Reader
     */
    private LineNumberReader reader;


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

    private boolean endOfLine = false;

    public JustLexerImpl() {
        this(new StringReader(""));
    }

    public JustLexerImpl(String expr) {
        this(new StringReader(expr));
    }

    public JustLexerImpl(Reader reader) {
        this.reader = new LineNumberReader(reader);
        this.hasMore = true;
        this.initialSymbols();
    }

    @Override
    public void reset(String expr) {
        this.start = 0;
        this.end = 0;
        this.lineNumber = 0;
        this.currentReadString = "";
        this.peekChar = ' ';
        this.endOfLine = false;
        this.hasMore = true;
        this.queue.clear();
        this.reader = new LineNumberReader(new StringReader(expr));
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
     * read next char => peekChar
     *
     * @return peekChar name
     */
    private char readChar() {
        start++;
        if (start >= end) {
            peekChar = ' ';
            // end of file
            endOfLine = true;
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
        if (skipBlank()) return;

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
    private boolean skipBlank() {
        for (; ; readChar()) {
            if (endOfLine) return true;

            if (peekChar == ' ' || peekChar == '\t') {
                continue;
            } else if (peekChar == '\n') {
                lineNumber = lineNumber + 1;
            } else break;
        }

        return false;
    }

    private boolean notLetterOrDigit(char peek) {
        return !Character.isLetterOrDigit(peek)
                && !avoidChar.contains(peek);
    }

    /**
     * resolve symbol token
     *
     * @return is symbol token?
     */
    private boolean resolveSymbol() {
        if (!notLetterOrDigit(peekChar)) return false;

        StringBuilder symbol = new StringBuilder();
        symbol.append(peekChar);

        char itemSymbol;
        while (notLetterOrDigit(itemSymbol = readChar())) {
            if (!insertSymbol.containsKey(symbol.toString() + itemSymbol))
                break;

            symbol.append(itemSymbol);
        }

        if (insertSymbol.containsKey(symbol.toString())) {
            addToken(insertSymbol.get(symbol.toString()));
            // read next line

            return true;
        } else {

            return false;
        }
    }

    private void initialSymbols() {
        // addToken(SepToken.QUESTION_TOKEN);
        insertSymbol("?", SepToken.QUESTION_TOKEN);
        // addToken(SepToken.COLON_TOKEN);
        insertSymbol(":", SepToken.COLON_TOKEN);
        // addToken(SepToken.PLUS_TOKEN);
        insertSymbol("+", SepToken.PLUS_TOKEN);
        // addToken(SepToken.MINUS_TOKEN);
        insertSymbol("-", SepToken.MINUS_TOKEN);
        // addToken(SepToken.MULTIPLY_TOKEN);
        insertSymbol("*", SepToken.MULTIPLY_TOKEN);
        // addToken(SepToken.DIVIDE_TOKEN);
        insertSymbol("/", SepToken.DIVIDE_TOKEN);
        // addToken(SepToken.MOD_TOKEN);
        insertSymbol("%", SepToken.MOD_TOKEN);
        // addToken(SepToken.AND_TOKEN);
        insertSymbol("&", SepToken.AMPERSAND_TOKEN);
        // addToken(SepToken.AMPERSAND_TOKEN);
        insertSymbol("&&", SepToken.AND_TOKEN);
        // addToken(SepToken.BAR_TOKEN);
        insertSymbol("|", SepToken.BAR_TOKEN);
        // addToken(SepToken.OR_TOKEN);
        insertSymbol("||", SepToken.OR_TOKEN);
        // addToken(SepToken.EQUAL_TOKEN);
        insertSymbol("==", SepToken.EQUAL_TOKEN);
        // addToken(SepToken.COLLECT_GET_LEFT_TOKEN);
        insertSymbol("[", SepToken.COLLECT_GET_LEFT_TOKEN);
        // addToken(SepToken.COLLECT_GET_RIGHT_TOKEN);
        insertSymbol("]", SepToken.COLLECT_GET_RIGHT_TOKEN);
        // addToken(SepToken.DOT_TOKEN);
        insertSymbol(".", SepToken.DOT_TOKEN);
        // addToken(SepToken.NOT_EQUAL_TOKEN);
        insertSymbol("!=", SepToken.NOT_EQUAL_TOKEN);
        // addToken(SepToken.EXCLAM_TOKEN);
        insertSymbol("!", SepToken.EXCLAM_TOKEN);
        // addToken(SepToken.GREAT_TOKEN);
        insertSymbol(">", SepToken.GREAT_TOKEN);
        // addToken(SepToken.GTE_TOKEN);
        insertSymbol(">=", SepToken.GTE_TOKEN);
        // addToken(SepToken.LESS_TOKEN);
        insertSymbol("<", SepToken.LESS_TOKEN);
        // addToken(SepToken.LTE_TOKEN);
        insertSymbol("<=", SepToken.LTE_TOKEN);

        avoidChar.add('(');
        avoidChar.add(')');
        avoidChar.add(' ');
        avoidChar.add(',');
        avoidChar.add('\"');
    }

    public void insertSymbol(final String symbol) {
        if (symbol.length() <= 0) return;

        SepToken token = new SepToken(GeneratedId.generateAtomId(), symbol);
        insertSymbol(symbol, token);
    }

    public void insertSymbol(final String symbol, final Token token) {
        insertSymbol.put(symbol, token);
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

                if (peekChar == 'l' || peekChar == 'L') {
                    checkedType = Token.LONG;
                    readChar();
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

                if (peekChar == 'd' || peekChar == 'D') {
                    checkedType = Token.DOUBLE;
                    readChar();
                } else if (peekChar == 'f' || peekChar == 'F') {
                    checkedType = Token.FLOAT;
                    readChar();
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

                if (endOfLine) {
                    break;
                }

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
