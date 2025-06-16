/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.json;

import java.util.Locale;
import org.json.HTTPTokener;
import org.json.JSONException;
import org.json.JSONObject;

public class HTTP {
    public static final String CRLF = "\r\n";

    public static JSONObject toJSONObject(String string) throws JSONException {
        JSONObject jo = new JSONObject();
        HTTPTokener x2 = new HTTPTokener(string);
        String token = x2.nextToken();
        if (token.toUpperCase(Locale.ROOT).startsWith("HTTP")) {
            jo.put("HTTP-Version", token);
            jo.put("Status-Code", x2.nextToken());
            jo.put("Reason-Phrase", x2.nextTo('\u0000'));
            x2.next();
        } else {
            jo.put("Method", token);
            jo.put("Request-URI", x2.nextToken());
            jo.put("HTTP-Version", x2.nextToken());
        }
        while (x2.more()) {
            String name = x2.nextTo(':');
            x2.next(':');
            jo.put(name, x2.nextTo('\u0000'));
            x2.next();
        }
        return jo;
    }

    public static String toString(JSONObject jo) throws JSONException {
        StringBuilder sb = new StringBuilder();
        if (jo.has("Status-Code") && jo.has("Reason-Phrase")) {
            sb.append(jo.getString("HTTP-Version"));
            sb.append(' ');
            sb.append(jo.getString("Status-Code"));
            sb.append(' ');
            sb.append(jo.getString("Reason-Phrase"));
        } else if (jo.has("Method") && jo.has("Request-URI")) {
            sb.append(jo.getString("Method"));
            sb.append(' ');
            sb.append('\"');
            sb.append(jo.getString("Request-URI"));
            sb.append('\"');
            sb.append(' ');
            sb.append(jo.getString("HTTP-Version"));
        } else {
            throw new JSONException("Not enough material for an HTTP header.");
        }
        sb.append(CRLF);
        for (String key : jo.keySet()) {
            String value = jo.optString(key);
            if ("HTTP-Version".equals(key) || "Status-Code".equals(key) || "Reason-Phrase".equals(key) || "Method".equals(key) || "Request-URI".equals(key) || JSONObject.NULL.equals(value)) continue;
            sb.append(key);
            sb.append(": ");
            sb.append(jo.optString(key));
            sb.append(CRLF);
        }
        sb.append(CRLF);
        return sb.toString();
    }
}

