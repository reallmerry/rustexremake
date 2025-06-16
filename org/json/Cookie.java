/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.json;

import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class Cookie {
    public static String escape(String string) {
        String s2 = string.trim();
        int length = s2.length();
        StringBuilder sb = new StringBuilder(length);
        for (int i2 = 0; i2 < length; ++i2) {
            char c2 = s2.charAt(i2);
            if (c2 < ' ' || c2 == '+' || c2 == '%' || c2 == '=' || c2 == ';') {
                sb.append('%');
                sb.append(Character.forDigit((char)(c2 >>> 4 & 0xF), 16));
                sb.append(Character.forDigit((char)(c2 & 0xF), 16));
                continue;
            }
            sb.append(c2);
        }
        return sb.toString();
    }

    public static JSONObject toJSONObject(String string) {
        JSONObject jo = new JSONObject();
        JSONTokener x2 = new JSONTokener(string);
        String name = Cookie.unescape(x2.nextTo('=').trim());
        if ("".equals(name)) {
            throw new JSONException("Cookies must have a 'name'");
        }
        jo.put("name", name);
        x2.next('=');
        jo.put("value", Cookie.unescape(x2.nextTo(';')).trim());
        x2.next();
        while (x2.more()) {
            Object value;
            name = Cookie.unescape(x2.nextTo("=;")).trim().toLowerCase(Locale.ROOT);
            if ("name".equalsIgnoreCase(name)) {
                throw new JSONException("Illegal attribute name: 'name'");
            }
            if ("value".equalsIgnoreCase(name)) {
                throw new JSONException("Illegal attribute name: 'value'");
            }
            if (x2.next() != '=') {
                value = Boolean.TRUE;
            } else {
                value = Cookie.unescape(x2.nextTo(';')).trim();
                x2.next();
            }
            if ("".equals(name) || "".equals(value)) continue;
            jo.put(name, value);
        }
        return jo;
    }

    public static String toString(JSONObject jo) throws JSONException {
        StringBuilder sb = new StringBuilder();
        String name = null;
        Object value = null;
        for (String key : jo.keySet()) {
            if ("name".equalsIgnoreCase(key)) {
                name = jo.getString(key).trim();
            }
            if ("value".equalsIgnoreCase(key)) {
                value = jo.getString(key).trim();
            }
            if (name == null || value == null) continue;
            break;
        }
        if (name == null || "".equals(name.trim())) {
            throw new JSONException("Cookie does not have a name");
        }
        if (value == null) {
            value = "";
        }
        sb.append(Cookie.escape(name));
        sb.append("=");
        sb.append(Cookie.escape((String)value));
        for (String key : jo.keySet()) {
            if ("name".equalsIgnoreCase(key) || "value".equalsIgnoreCase(key)) continue;
            value = jo.opt(key);
            if (value instanceof Boolean) {
                if (!Boolean.TRUE.equals(value)) continue;
                sb.append(';').append(Cookie.escape(key));
                continue;
            }
            sb.append(';').append(Cookie.escape(key)).append('=').append(Cookie.escape(value.toString()));
        }
        return sb.toString();
    }

    public static String unescape(String string) {
        int length = string.length();
        StringBuilder sb = new StringBuilder(length);
        for (int i2 = 0; i2 < length; ++i2) {
            char c2 = string.charAt(i2);
            if (c2 == '+') {
                c2 = ' ';
            } else if (c2 == '%' && i2 + 2 < length) {
                int d2 = JSONTokener.dehexchar(string.charAt(i2 + 1));
                int e2 = JSONTokener.dehexchar(string.charAt(i2 + 2));
                if (d2 >= 0 && e2 >= 0) {
                    c2 = (char)(d2 * 16 + e2);
                    i2 += 2;
                }
            }
            sb.append(c2);
        }
        return sb.toString();
    }
}

