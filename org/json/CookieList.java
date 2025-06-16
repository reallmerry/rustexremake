/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.json;

import org.json.Cookie;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class CookieList {
    public static JSONObject toJSONObject(String string) throws JSONException {
        JSONObject jo = new JSONObject();
        JSONTokener x2 = new JSONTokener(string);
        while (x2.more()) {
            String name = Cookie.unescape(x2.nextTo('='));
            x2.next('=');
            jo.put(name, Cookie.unescape(x2.nextTo(';')));
            x2.next();
        }
        return jo;
    }

    public static String toString(JSONObject jo) throws JSONException {
        boolean b2 = false;
        StringBuilder sb = new StringBuilder();
        for (String key : jo.keySet()) {
            Object value = jo.opt(key);
            if (JSONObject.NULL.equals(value)) continue;
            if (b2) {
                sb.append(';');
            }
            sb.append(Cookie.escape(key));
            sb.append("=");
            sb.append(Cookie.escape(value.toString()));
            b2 = true;
        }
        return sb.toString();
    }
}

