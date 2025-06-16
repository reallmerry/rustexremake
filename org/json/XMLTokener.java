/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.json;

import java.io.Reader;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONTokener;
import org.json.XML;

public class XMLTokener
extends JSONTokener {
    public static final HashMap<String, Character> entity = new HashMap(8);

    public XMLTokener(Reader r2) {
        super(r2);
    }

    public XMLTokener(String s2) {
        super(s2);
    }

    public String nextCDATA() throws JSONException {
        StringBuilder sb = new StringBuilder();
        while (this.more()) {
            char c2 = this.next();
            sb.append(c2);
            int i2 = sb.length() - 3;
            if (i2 < 0 || sb.charAt(i2) != ']' || sb.charAt(i2 + 1) != ']' || sb.charAt(i2 + 2) != '>') continue;
            sb.setLength(i2);
            return sb.toString();
        }
        throw this.syntaxError("Unclosed CDATA");
    }

    public Object nextContent() throws JSONException {
        char c2;
        while (Character.isWhitespace(c2 = this.next())) {
        }
        if (c2 == '\u0000') {
            return null;
        }
        if (c2 == '<') {
            return XML.LT;
        }
        StringBuilder sb = new StringBuilder();
        while (c2 != '\u0000') {
            if (c2 == '<') {
                this.back();
                return sb.toString().trim();
            }
            if (c2 == '&') {
                sb.append(this.nextEntity(c2));
            } else {
                sb.append(c2);
            }
            c2 = this.next();
        }
        return sb.toString().trim();
    }

    public Object nextEntity(char ampersand) throws JSONException {
        char c2;
        StringBuilder sb = new StringBuilder();
        while (Character.isLetterOrDigit(c2 = this.next()) || c2 == '#') {
            sb.append(Character.toLowerCase(c2));
        }
        if (c2 != ';') {
            throw this.syntaxError("Missing ';' in XML entity: &" + sb);
        }
        String string = sb.toString();
        return XMLTokener.unescapeEntity(string);
    }

    static String unescapeEntity(String e2) {
        if (e2 == null || e2.isEmpty()) {
            return "";
        }
        if (e2.charAt(0) == '#') {
            int cp = e2.charAt(1) == 'x' || e2.charAt(1) == 'X' ? Integer.parseInt(e2.substring(2), 16) : Integer.parseInt(e2.substring(1));
            return new String(new int[]{cp}, 0, 1);
        }
        Character knownEntity = entity.get(e2);
        if (knownEntity == null) {
            return '&' + e2 + ';';
        }
        return knownEntity.toString();
    }

    public Object nextMeta() throws JSONException {
        char c2;
        while (Character.isWhitespace(c2 = this.next())) {
        }
        switch (c2) {
            case '\u0000': {
                throw this.syntaxError("Misshaped meta tag");
            }
            case '<': {
                return XML.LT;
            }
            case '>': {
                return XML.GT;
            }
            case '/': {
                return XML.SLASH;
            }
            case '=': {
                return XML.EQ;
            }
            case '!': {
                return XML.BANG;
            }
            case '?': {
                return XML.QUEST;
            }
            case '\"': 
            case '\'': {
                char q2 = c2;
                do {
                    if ((c2 = this.next()) != '\u0000') continue;
                    throw this.syntaxError("Unterminated string");
                } while (c2 != q2);
                return Boolean.TRUE;
            }
        }
        while (!Character.isWhitespace(c2 = this.next())) {
            switch (c2) {
                case '\u0000': {
                    throw this.syntaxError("Unterminated string");
                }
                case '!': 
                case '\"': 
                case '\'': 
                case '/': 
                case '<': 
                case '=': 
                case '>': 
                case '?': {
                    this.back();
                    return Boolean.TRUE;
                }
            }
        }
        return Boolean.TRUE;
    }

    public Object nextToken() throws JSONException {
        char c2;
        while (Character.isWhitespace(c2 = this.next())) {
        }
        switch (c2) {
            case '\u0000': {
                throw this.syntaxError("Misshaped element");
            }
            case '<': {
                throw this.syntaxError("Misplaced '<'");
            }
            case '>': {
                return XML.GT;
            }
            case '/': {
                return XML.SLASH;
            }
            case '=': {
                return XML.EQ;
            }
            case '!': {
                return XML.BANG;
            }
            case '?': {
                return XML.QUEST;
            }
            case '\"': 
            case '\'': {
                char q2 = c2;
                StringBuilder sb = new StringBuilder();
                while (true) {
                    if ((c2 = this.next()) == '\u0000') {
                        throw this.syntaxError("Unterminated string");
                    }
                    if (c2 == q2) {
                        return sb.toString();
                    }
                    if (c2 == '&') {
                        sb.append(this.nextEntity(c2));
                        continue;
                    }
                    sb.append(c2);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        while (true) {
            sb.append(c2);
            c2 = this.next();
            if (Character.isWhitespace(c2)) {
                return sb.toString();
            }
            switch (c2) {
                case '\u0000': {
                    return sb.toString();
                }
                case '!': 
                case '/': 
                case '=': 
                case '>': 
                case '?': 
                case '[': 
                case ']': {
                    this.back();
                    return sb.toString();
                }
                case '\"': 
                case '\'': 
                case '<': {
                    throw this.syntaxError("Bad character in a name");
                }
            }
        }
    }

    public void skipPast(String to) {
        char c2;
        int i2;
        int offset = 0;
        int length = to.length();
        char[] circle = new char[length];
        for (i2 = 0; i2 < length; ++i2) {
            c2 = this.next();
            if (c2 == '\u0000') {
                return;
            }
            circle[i2] = c2;
        }
        while (true) {
            int j2 = offset;
            boolean b2 = true;
            for (i2 = 0; i2 < length; ++i2) {
                if (circle[j2] != to.charAt(i2)) {
                    b2 = false;
                    break;
                }
                if (++j2 < length) continue;
                j2 -= length;
            }
            if (b2) {
                return;
            }
            c2 = this.next();
            if (c2 == '\u0000') {
                return;
            }
            circle[offset] = c2;
            if (++offset < length) continue;
            offset -= length;
        }
    }

    static {
        entity.put("amp", XML.AMP);
        entity.put("apos", XML.APOS);
        entity.put("gt", XML.GT);
        entity.put("lt", XML.LT);
        entity.put("quot", XML.QUOT);
    }
}

