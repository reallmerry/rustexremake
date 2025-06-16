/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.json;

import org.json.JSONException;
import org.json.JSONTokener;

public class HTTPTokener
extends JSONTokener {
    public HTTPTokener(String string) {
        super(string);
    }

    public String nextToken() throws JSONException {
        char c2;
        StringBuilder sb = new StringBuilder();
        while (Character.isWhitespace(c2 = this.next())) {
        }
        if (c2 == '\"' || c2 == '\'') {
            char q2 = c2;
            while (true) {
                if ((c2 = this.next()) < ' ') {
                    throw this.syntaxError("Unterminated string.");
                }
                if (c2 == q2) {
                    return sb.toString();
                }
                sb.append(c2);
            }
        }
        while (c2 != '\u0000' && !Character.isWhitespace(c2)) {
            sb.append(c2);
            c2 = this.next();
        }
        return sb.toString();
    }
}

