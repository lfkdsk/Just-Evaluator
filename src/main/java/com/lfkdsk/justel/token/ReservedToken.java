package com.lfkdsk.justel.token;

import java.util.HashSet;
import java.util.Set;

/**
 * Reserved Token
 *
 * @author liufengkai
 *         Created by liufengkai on 2017/7/26.
 */
public class ReservedToken extends Token {

    private String token;

    private static Set<String> reservedToken = new HashSet<>();

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

    public static boolean addReservedToken(String token) {
        return reservedToken.add(token);
    }

    public static boolean containsReservedToken(String token) {
        return reservedToken.contains(token);
    }
}
