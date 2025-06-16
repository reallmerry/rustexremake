/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.json;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONMLParserConfiguration;
import org.json.JSONObject;
import org.json.XML;
import org.json.XMLTokener;

public class JSONML {
    private static Object parse(XMLTokener x2, boolean arrayForm, JSONArray ja2, boolean keepStrings, int currentNestingDepth) throws JSONException {
        return JSONML.parse(x2, arrayForm, ja2, keepStrings ? JSONMLParserConfiguration.KEEP_STRINGS : JSONMLParserConfiguration.ORIGINAL, currentNestingDepth);
    }

    private static Object parse(XMLTokener x2, boolean arrayForm, JSONArray ja2, JSONMLParserConfiguration config, int currentNestingDepth) throws JSONException {
        String closeTag = null;
        JSONArray newja = null;
        JSONObject newjo = null;
        String tagName = null;
        while (true) {
            if (!x2.more()) {
                throw x2.syntaxError("Bad XML");
            }
            Object token = x2.nextContent();
            if (token == XML.LT) {
                token = x2.nextToken();
                if (token instanceof Character) {
                    if (token == XML.SLASH) {
                        token = x2.nextToken();
                        if (!(token instanceof String)) {
                            throw new JSONException("Expected a closing name instead of '" + token + "'.");
                        }
                        if (x2.nextToken() != XML.GT) {
                            throw x2.syntaxError("Misshaped close tag");
                        }
                        return token;
                    }
                    if (token == XML.BANG) {
                        char c2 = x2.next();
                        if (c2 == '-') {
                            if (x2.next() == '-') {
                                x2.skipPast("-->");
                                continue;
                            }
                            x2.back();
                            continue;
                        }
                        if (c2 == '[') {
                            token = x2.nextToken();
                            if (token.equals("CDATA") && x2.next() == '[') {
                                if (ja2 == null) continue;
                                ja2.put(x2.nextCDATA());
                                continue;
                            }
                            throw x2.syntaxError("Expected 'CDATA['");
                        }
                        int i2 = 1;
                        do {
                            if ((token = x2.nextMeta()) == null) {
                                throw x2.syntaxError("Missing '>' after '<!'.");
                            }
                            if (token == XML.LT) {
                                ++i2;
                                continue;
                            }
                            if (token != XML.GT) continue;
                            --i2;
                        } while (i2 > 0);
                        continue;
                    }
                    if (token == XML.QUEST) {
                        x2.skipPast("?>");
                        continue;
                    }
                    throw x2.syntaxError("Misshaped tag");
                }
                if (!(token instanceof String)) {
                    throw x2.syntaxError("Bad tagName '" + token + "'.");
                }
                tagName = (String)token;
                newja = new JSONArray();
                newjo = new JSONObject();
                if (arrayForm) {
                    newja.put(tagName);
                    if (ja2 != null) {
                        ja2.put(newja);
                    }
                } else {
                    newjo.put("tagName", tagName);
                    if (ja2 != null) {
                        ja2.put(newjo);
                    }
                }
                token = null;
                while (true) {
                    if (token == null) {
                        token = x2.nextToken();
                    }
                    if (token == null) {
                        throw x2.syntaxError("Misshaped tag");
                    }
                    if (!(token instanceof String)) break;
                    String attribute = (String)token;
                    if (!arrayForm && ("tagName".equals(attribute) || "childNode".equals(attribute))) {
                        throw x2.syntaxError("Reserved attribute.");
                    }
                    token = x2.nextToken();
                    if (token == XML.EQ) {
                        token = x2.nextToken();
                        if (!(token instanceof String)) {
                            throw x2.syntaxError("Missing value");
                        }
                        newjo.accumulate(attribute, config.isKeepStrings() ? (String)token : XML.stringToValue((String)token));
                        token = null;
                        continue;
                    }
                    newjo.accumulate(attribute, "");
                }
                if (arrayForm && newjo.length() > 0) {
                    newja.put(newjo);
                }
                if (token == XML.SLASH) {
                    if (x2.nextToken() != XML.GT) {
                        throw x2.syntaxError("Misshaped tag");
                    }
                    if (ja2 != null) continue;
                    if (arrayForm) {
                        return newja;
                    }
                    return newjo;
                }
                if (token != XML.GT) {
                    throw x2.syntaxError("Misshaped tag");
                }
                if (currentNestingDepth == config.getMaxNestingDepth()) {
                    throw x2.syntaxError("Maximum nesting depth of " + config.getMaxNestingDepth() + " reached");
                }
                closeTag = (String)JSONML.parse(x2, arrayForm, newja, config, currentNestingDepth + 1);
                if (closeTag == null) continue;
                if (!closeTag.equals(tagName)) {
                    throw x2.syntaxError("Mismatched '" + tagName + "' and '" + closeTag + "'");
                }
                tagName = null;
                if (!arrayForm && newja.length() > 0) {
                    newjo.put("childNodes", newja);
                }
                if (ja2 != null) continue;
                if (arrayForm) {
                    return newja;
                }
                return newjo;
            }
            if (ja2 == null) continue;
            ja2.put(token instanceof String ? (config.isKeepStrings() ? XML.unescape((String)token) : XML.stringToValue((String)token)) : token);
        }
    }

    public static JSONArray toJSONArray(String string) throws JSONException {
        return (JSONArray)JSONML.parse(new XMLTokener(string), true, null, JSONMLParserConfiguration.ORIGINAL, 0);
    }

    public static JSONArray toJSONArray(String string, boolean keepStrings) throws JSONException {
        return (JSONArray)JSONML.parse(new XMLTokener(string), true, null, keepStrings, 0);
    }

    public static JSONArray toJSONArray(String string, JSONMLParserConfiguration config) throws JSONException {
        return (JSONArray)JSONML.parse(new XMLTokener(string), true, null, config, 0);
    }

    public static JSONArray toJSONArray(XMLTokener x2, JSONMLParserConfiguration config) throws JSONException {
        return (JSONArray)JSONML.parse(x2, true, null, config, 0);
    }

    public static JSONArray toJSONArray(XMLTokener x2, boolean keepStrings) throws JSONException {
        return (JSONArray)JSONML.parse(x2, true, null, keepStrings, 0);
    }

    public static JSONArray toJSONArray(XMLTokener x2) throws JSONException {
        return (JSONArray)JSONML.parse(x2, true, null, false, 0);
    }

    public static JSONObject toJSONObject(String string) throws JSONException {
        return (JSONObject)JSONML.parse(new XMLTokener(string), false, null, false, 0);
    }

    public static JSONObject toJSONObject(String string, boolean keepStrings) throws JSONException {
        return (JSONObject)JSONML.parse(new XMLTokener(string), false, null, keepStrings, 0);
    }

    public static JSONObject toJSONObject(String string, JSONMLParserConfiguration config) throws JSONException {
        return (JSONObject)JSONML.parse(new XMLTokener(string), false, null, config, 0);
    }

    public static JSONObject toJSONObject(XMLTokener x2) throws JSONException {
        return (JSONObject)JSONML.parse(x2, false, null, false, 0);
    }

    public static JSONObject toJSONObject(XMLTokener x2, boolean keepStrings) throws JSONException {
        return (JSONObject)JSONML.parse(x2, false, null, keepStrings, 0);
    }

    public static JSONObject toJSONObject(XMLTokener x2, JSONMLParserConfiguration config) throws JSONException {
        return (JSONObject)JSONML.parse(x2, false, null, config, 0);
    }

    public static String toString(JSONArray ja2) throws JSONException {
        int length;
        int i2;
        StringBuilder sb = new StringBuilder();
        String tagName = ja2.getString(0);
        XML.noSpace(tagName);
        tagName = XML.escape(tagName);
        sb.append('<');
        sb.append(tagName);
        Object object = ja2.opt(1);
        if (object instanceof JSONObject) {
            i2 = 2;
            JSONObject jo = (JSONObject)object;
            for (String key : jo.keySet()) {
                Object value = jo.opt(key);
                XML.noSpace(key);
                if (value == null) continue;
                sb.append(' ');
                sb.append(XML.escape(key));
                sb.append('=');
                sb.append('\"');
                sb.append(XML.escape(value.toString()));
                sb.append('\"');
            }
        } else {
            i2 = 1;
        }
        if (i2 >= (length = ja2.length())) {
            sb.append('/');
            sb.append('>');
        } else {
            sb.append('>');
            do {
                object = ja2.get(i2);
                ++i2;
                if (object == null) continue;
                if (object instanceof String) {
                    sb.append(XML.escape(object.toString()));
                    continue;
                }
                if (object instanceof JSONObject) {
                    sb.append(JSONML.toString((JSONObject)object));
                    continue;
                }
                if (object instanceof JSONArray) {
                    sb.append(JSONML.toString((JSONArray)object));
                    continue;
                }
                sb.append(object.toString());
            } while (i2 < length);
            sb.append('<');
            sb.append('/');
            sb.append(tagName);
            sb.append('>');
        }
        return sb.toString();
    }

    public static String toString(JSONObject jo) throws JSONException {
        StringBuilder sb = new StringBuilder();
        String tagName = jo.optString("tagName");
        if (tagName == null) {
            return XML.escape(jo.toString());
        }
        XML.noSpace(tagName);
        tagName = XML.escape(tagName);
        sb.append('<');
        sb.append(tagName);
        for (String key : jo.keySet()) {
            if ("tagName".equals(key) || "childNodes".equals(key)) continue;
            XML.noSpace(key);
            Object value = jo.opt(key);
            if (value == null) continue;
            sb.append(' ');
            sb.append(XML.escape(key));
            sb.append('=');
            sb.append('\"');
            sb.append(XML.escape(value.toString()));
            sb.append('\"');
        }
        JSONArray ja2 = jo.optJSONArray("childNodes");
        if (ja2 == null) {
            sb.append('/');
            sb.append('>');
        } else {
            sb.append('>');
            int length = ja2.length();
            for (int i2 = 0; i2 < length; ++i2) {
                Object object = ja2.get(i2);
                if (object == null) continue;
                if (object instanceof String) {
                    sb.append(XML.escape(object.toString()));
                    continue;
                }
                if (object instanceof JSONObject) {
                    sb.append(JSONML.toString((JSONObject)object));
                    continue;
                }
                if (object instanceof JSONArray) {
                    sb.append(JSONML.toString((JSONArray)object));
                    continue;
                }
                sb.append(object.toString());
            }
            sb.append('<');
            sb.append('/');
            sb.append(tagName);
            sb.append('>');
        }
        return sb.toString();
    }
}

