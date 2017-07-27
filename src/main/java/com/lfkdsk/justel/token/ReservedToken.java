package com.lfkdsk.justel.token;

import java.util.HashSet;
import java.util.Set;

/**
 * Reserved Token
 * Language reserved word
 *
 * @author liufengkai
 *         Created by liufengkai on 2017/7/26.
 * @see BoolToken
 */
public class ReservedToken extends Token {

    /**
     * reserved token str
     */
    protected String token;

    /**
     * reserved token in this set
     */
    public final static Set<String> reservedToken = new HashSet<>();

    public ReservedToken(int lineNumber, String token) {
        super(lineNumber, Token.RESERVED);
        this.token = token;
    }

    @Override
    public String getText() {
        return token;
    }

    @Override
    public boolean isIdentifier() {
        return true;
    }

}
