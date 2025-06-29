/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.json;

import java.io.Reader;
import java.io.StringReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XMLParserConfiguration;
import org.json.XMLTokener;
import org.json.XMLXsiTypeConverter;

public class XML {
    public static final Character AMP = Character.valueOf('&');
    public static final Character APOS = Character.valueOf('\'');
    public static final Character BANG = Character.valueOf('!');
    public static final Character EQ = Character.valueOf('=');
    public static final Character GT = Character.valueOf('>');
    public static final Character LT = Character.valueOf('<');
    public static final Character QUEST = Character.valueOf('?');
    public static final Character QUOT = Character.valueOf('\"');
    public static final Character SLASH = Character.valueOf('/');
    public static final String NULL_ATTR = "xsi:nil";
    public static final String TYPE_ATTR = "xsi:type";

    private static Iterable<Integer> codePointIterator(final String string) {
        return new Iterable<Integer>(){

            @Override
            public Iterator<Integer> iterator() {
                return new Iterator<Integer>(){
                    private int nextIndex = 0;
                    private int length;
                    {
                        this.length = string.length();
                    }

                    @Override
                    public boolean hasNext() {
                        return this.nextIndex < this.length;
                    }

                    @Override
                    public Integer next() {
                        int result = string.codePointAt(this.nextIndex);
                        this.nextIndex += Character.charCount(result);
                        return result;
                    }

                    @Override
                    public void remove() {
                        throw new UnsupportedOperationException();
                    }
                };
            }
        };
    }

    public static String escape(String string) {
        StringBuilder sb = new StringBuilder(string.length());
        block7: for (int cp : XML.codePointIterator(string)) {
            switch (cp) {
                case 38: {
                    sb.append("&amp;");
                    continue block7;
                }
                case 60: {
                    sb.append("&lt;");
                    continue block7;
                }
                case 62: {
                    sb.append("&gt;");
                    continue block7;
                }
                case 34: {
                    sb.append("&quot;");
                    continue block7;
                }
                case 39: {
                    sb.append("&apos;");
                    continue block7;
                }
            }
            if (XML.mustEscape(cp)) {
                sb.append("&#x");
                sb.append(Integer.toHexString(cp));
                sb.append(';');
                continue;
            }
            sb.appendCodePoint(cp);
        }
        return sb.toString();
    }

    private static boolean mustEscape(int cp) {
        return Character.isISOControl(cp) && cp != 9 && cp != 10 && cp != 13 || (cp < 32 || cp > 55295) && (cp < 57344 || cp > 65533) && (cp < 65536 || cp > 0x10FFFF);
    }

    public static String unescape(String string) {
        StringBuilder sb = new StringBuilder(string.length());
        int length = string.length();
        for (int i2 = 0; i2 < length; ++i2) {
            char c2 = string.charAt(i2);
            if (c2 == '&') {
                int semic = string.indexOf(59, i2);
                if (semic > i2) {
                    String entity = string.substring(i2 + 1, semic);
                    sb.append(XMLTokener.unescapeEntity(entity));
                    i2 += entity.length() + 1;
                    continue;
                }
                sb.append(c2);
                continue;
            }
            sb.append(c2);
        }
        return sb.toString();
    }

    public static void noSpace(String string) throws JSONException {
        int length = string.length();
        if (length == 0) {
            throw new JSONException("Empty string.");
        }
        for (int i2 = 0; i2 < length; ++i2) {
            if (!Character.isWhitespace(string.charAt(i2))) continue;
            throw new JSONException("'" + string + "' contains a space character.");
        }
    }

    private static boolean parse(XMLTokener x2, JSONObject context, String name, XMLParserConfiguration config, int currentNestingDepth) throws JSONException {
        String string;
        JSONObject jsonObject = null;
        Object token = x2.nextToken();
        if (token == BANG) {
            char c2 = x2.next();
            if (c2 == '-') {
                if (x2.next() == '-') {
                    x2.skipPast("-->");
                    return false;
                }
                x2.back();
            } else if (c2 == '[') {
                token = x2.nextToken();
                if ("CDATA".equals(token) && x2.next() == '[') {
                    String string2 = x2.nextCDATA();
                    if (string2.length() > 0) {
                        context.accumulate(config.getcDataTagName(), string2);
                    }
                    return false;
                }
                throw x2.syntaxError("Expected 'CDATA['");
            }
            int i2 = 1;
            do {
                if ((token = x2.nextMeta()) == null) {
                    throw x2.syntaxError("Missing '>' after '<!'.");
                }
                if (token == LT) {
                    ++i2;
                    continue;
                }
                if (token != GT) continue;
                --i2;
            } while (i2 > 0);
            return false;
        }
        if (token == QUEST) {
            x2.skipPast("?>");
            return false;
        }
        if (token == SLASH) {
            token = x2.nextToken();
            if (name == null) {
                throw x2.syntaxError("Mismatched close tag " + token);
            }
            if (!token.equals(name)) {
                throw x2.syntaxError("Mismatched " + name + " and " + token);
            }
            if (x2.nextToken() != GT) {
                throw x2.syntaxError("Misshaped close tag");
            }
            return true;
        }
        if (token instanceof Character) {
            throw x2.syntaxError("Misshaped tag");
        }
        String tagName = (String)token;
        token = null;
        jsonObject = new JSONObject();
        boolean nilAttributeFound = false;
        XMLXsiTypeConverter<?> xmlXsiTypeConverter = null;
        while (true) {
            if (token == null) {
                token = x2.nextToken();
            }
            if (!(token instanceof String)) break;
            string = (String)token;
            token = x2.nextToken();
            if (token == EQ) {
                token = x2.nextToken();
                if (!(token instanceof String)) {
                    throw x2.syntaxError("Missing value");
                }
                if (config.isConvertNilAttributeToNull() && NULL_ATTR.equals(string) && Boolean.parseBoolean((String)token)) {
                    nilAttributeFound = true;
                } else if (config.getXsiTypeMap() != null && !config.getXsiTypeMap().isEmpty() && TYPE_ATTR.equals(string)) {
                    xmlXsiTypeConverter = config.getXsiTypeMap().get(token);
                } else if (!nilAttributeFound) {
                    jsonObject.accumulate(string, config.isKeepStrings() ? (String)token : XML.stringToValue((String)token));
                }
                token = null;
                continue;
            }
            jsonObject.accumulate(string, "");
        }
        if (token == SLASH) {
            if (x2.nextToken() != GT) {
                throw x2.syntaxError("Misshaped tag");
            }
            if (config.getForceList().contains(tagName)) {
                if (nilAttributeFound) {
                    context.append(tagName, JSONObject.NULL);
                } else if (jsonObject.length() > 0) {
                    context.append(tagName, jsonObject);
                } else {
                    context.put(tagName, new JSONArray());
                }
            } else if (nilAttributeFound) {
                context.accumulate(tagName, JSONObject.NULL);
            } else if (jsonObject.length() > 0) {
                context.accumulate(tagName, jsonObject);
            } else {
                context.accumulate(tagName, "");
            }
            return false;
        }
        if (token == GT) {
            while (true) {
                if ((token = x2.nextContent()) == null) {
                    if (tagName != null) {
                        throw x2.syntaxError("Unclosed tag " + tagName);
                    }
                    return false;
                }
                if (token instanceof String) {
                    string = (String)token;
                    if (string.length() <= 0) continue;
                    if (xmlXsiTypeConverter != null) {
                        jsonObject.accumulate(config.getcDataTagName(), XML.stringToValue(string, xmlXsiTypeConverter));
                        continue;
                    }
                    jsonObject.accumulate(config.getcDataTagName(), config.isKeepStrings() ? string : XML.stringToValue(string));
                    continue;
                }
                if (token != LT) continue;
                if (currentNestingDepth == config.getMaxNestingDepth()) {
                    throw x2.syntaxError("Maximum nesting depth of " + config.getMaxNestingDepth() + " reached");
                }
                if (XML.parse(x2, jsonObject, tagName, config, currentNestingDepth + 1)) break;
            }
            if (config.getForceList().contains(tagName)) {
                if (jsonObject.length() == 0) {
                    context.put(tagName, new JSONArray());
                } else if (jsonObject.length() == 1 && jsonObject.opt(config.getcDataTagName()) != null) {
                    context.append(tagName, jsonObject.opt(config.getcDataTagName()));
                } else {
                    context.append(tagName, jsonObject);
                }
            } else if (jsonObject.length() == 0) {
                context.accumulate(tagName, "");
            } else if (jsonObject.length() == 1 && jsonObject.opt(config.getcDataTagName()) != null) {
                context.accumulate(tagName, jsonObject.opt(config.getcDataTagName()));
            } else {
                context.accumulate(tagName, jsonObject);
            }
            return false;
        }
        throw x2.syntaxError("Misshaped tag");
    }

    public static Object stringToValue(String string, XMLXsiTypeConverter<?> typeConverter) {
        if (typeConverter != null) {
            return typeConverter.convert(string);
        }
        return XML.stringToValue(string);
    }

    public static Object stringToValue(String string) {
        if ("".equals(string)) {
            return string;
        }
        if ("true".equalsIgnoreCase(string)) {
            return Boolean.TRUE;
        }
        if ("false".equalsIgnoreCase(string)) {
            return Boolean.FALSE;
        }
        if ("null".equalsIgnoreCase(string)) {
            return JSONObject.NULL;
        }
        char initial = string.charAt(0);
        if (initial >= '0' && initial <= '9' || initial == '-') {
            try {
                return XML.stringToNumber(string);
            } catch (Exception exception) {
                // empty catch block
            }
        }
        return string;
    }

    private static Number stringToNumber(String val) throws NumberFormatException {
        char initial = val.charAt(0);
        if (initial >= '0' && initial <= '9' || initial == '-') {
            BigInteger bi;
            char at1;
            if (XML.isDecimalNotation(val)) {
                try {
                    BigDecimal bd = new BigDecimal(val);
                    if (initial == '-' && BigDecimal.ZERO.compareTo(bd) == 0) {
                        return -0.0;
                    }
                    return bd;
                } catch (NumberFormatException retryAsDouble) {
                    try {
                        Double d2 = Double.valueOf(val);
                        if (d2.isNaN() || d2.isInfinite()) {
                            throw new NumberFormatException("val [" + val + "] is not a valid number.");
                        }
                        return d2;
                    } catch (NumberFormatException ignore) {
                        throw new NumberFormatException("val [" + val + "] is not a valid number.");
                    }
                }
            }
            if (initial == '0' && val.length() > 1) {
                at1 = val.charAt(1);
                if (at1 >= '0' && at1 <= '9') {
                    throw new NumberFormatException("val [" + val + "] is not a valid number.");
                }
            } else if (initial == '-' && val.length() > 2) {
                at1 = val.charAt(1);
                char at2 = val.charAt(2);
                if (at1 == '0' && at2 >= '0' && at2 <= '9') {
                    throw new NumberFormatException("val [" + val + "] is not a valid number.");
                }
            }
            if ((bi = new BigInteger(val)).bitLength() <= 31) {
                return bi.intValue();
            }
            if (bi.bitLength() <= 63) {
                return bi.longValue();
            }
            return bi;
        }
        throw new NumberFormatException("val [" + val + "] is not a valid number.");
    }

    private static boolean isDecimalNotation(String val) {
        return val.indexOf(46) > -1 || val.indexOf(101) > -1 || val.indexOf(69) > -1 || "-0".equals(val);
    }

    public static JSONObject toJSONObject(String string) throws JSONException {
        return XML.toJSONObject(string, XMLParserConfiguration.ORIGINAL);
    }

    public static JSONObject toJSONObject(Reader reader) throws JSONException {
        return XML.toJSONObject(reader, XMLParserConfiguration.ORIGINAL);
    }

    public static JSONObject toJSONObject(Reader reader, boolean keepStrings) throws JSONException {
        if (keepStrings) {
            return XML.toJSONObject(reader, XMLParserConfiguration.KEEP_STRINGS);
        }
        return XML.toJSONObject(reader, XMLParserConfiguration.ORIGINAL);
    }

    public static JSONObject toJSONObject(Reader reader, XMLParserConfiguration config) throws JSONException {
        JSONObject jo = new JSONObject();
        XMLTokener x2 = new XMLTokener(reader);
        while (x2.more()) {
            x2.skipPast("<");
            if (!x2.more()) continue;
            XML.parse(x2, jo, null, config, 0);
        }
        return jo;
    }

    public static JSONObject toJSONObject(String string, boolean keepStrings) throws JSONException {
        return XML.toJSONObject((Reader)new StringReader(string), keepStrings);
    }

    public static JSONObject toJSONObject(String string, XMLParserConfiguration config) throws JSONException {
        return XML.toJSONObject((Reader)new StringReader(string), config);
    }

    public static String toString(Object object) throws JSONException {
        return XML.toString(object, null, XMLParserConfiguration.ORIGINAL);
    }

    public static String toString(Object object, String tagName) {
        return XML.toString(object, tagName, XMLParserConfiguration.ORIGINAL);
    }

    public static String toString(Object object, String tagName, XMLParserConfiguration config) throws JSONException {
        return XML.toString(object, tagName, config, 0, 0);
    }

    private static String toString(Object object, String tagName, XMLParserConfiguration config, int indentFactor, int indent) throws JSONException {
        String string;
        StringBuilder sb = new StringBuilder();
        if (object instanceof JSONObject) {
            if (tagName != null) {
                sb.append(XML.indent(indent));
                sb.append('<');
                sb.append(tagName);
                sb.append('>');
                if (indentFactor > 0) {
                    sb.append("\n");
                    indent += indentFactor;
                }
            }
            JSONObject jo = (JSONObject)object;
            for (String key : jo.keySet()) {
                Object val;
                int i2;
                int jaLength;
                JSONArray ja2;
                Object value = jo.opt(key);
                if (value == null) {
                    value = "";
                } else if (value.getClass().isArray()) {
                    value = new JSONArray(value);
                }
                if (key.equals(config.getcDataTagName())) {
                    if (value instanceof JSONArray) {
                        ja2 = (JSONArray)value;
                        jaLength = ja2.length();
                        for (i2 = 0; i2 < jaLength; ++i2) {
                            if (i2 > 0) {
                                sb.append('\n');
                            }
                            val = ja2.opt(i2);
                            sb.append(XML.escape(val.toString()));
                        }
                        continue;
                    }
                    sb.append(XML.escape(value.toString()));
                    continue;
                }
                if (value instanceof JSONArray) {
                    ja2 = (JSONArray)value;
                    jaLength = ja2.length();
                    for (i2 = 0; i2 < jaLength; ++i2) {
                        val = ja2.opt(i2);
                        if (val instanceof JSONArray) {
                            sb.append('<');
                            sb.append(key);
                            sb.append('>');
                            sb.append(XML.toString(val, null, config, indentFactor, indent));
                            sb.append("</");
                            sb.append(key);
                            sb.append('>');
                            continue;
                        }
                        sb.append(XML.toString(val, key, config, indentFactor, indent));
                    }
                    continue;
                }
                if ("".equals(value)) {
                    sb.append(XML.indent(indent));
                    sb.append('<');
                    sb.append(key);
                    sb.append("/>");
                    if (indentFactor <= 0) continue;
                    sb.append("\n");
                    continue;
                }
                sb.append(XML.toString(value, key, config, indentFactor, indent));
            }
            if (tagName != null) {
                sb.append(XML.indent(indent - indentFactor));
                sb.append("</");
                sb.append(tagName);
                sb.append('>');
                if (indentFactor > 0) {
                    sb.append("\n");
                }
            }
            return sb.toString();
        }
        if (object != null && (object instanceof JSONArray || object.getClass().isArray())) {
            JSONArray ja3 = object.getClass().isArray() ? new JSONArray(object) : (JSONArray)object;
            int jaLength = ja3.length();
            for (int i3 = 0; i3 < jaLength; ++i3) {
                Object val = ja3.opt(i3);
                sb.append(XML.toString(val, tagName == null ? "array" : tagName, config, indentFactor, indent));
            }
            return sb.toString();
        }
        String string2 = string = object == null ? "null" : XML.escape(object.toString());
        if (tagName == null) {
            return XML.indent(indent) + "\"" + string + "\"" + (indentFactor > 0 ? "\n" : "");
        }
        if (string.length() == 0) {
            return XML.indent(indent) + "<" + tagName + "/>" + (indentFactor > 0 ? "\n" : "");
        }
        return XML.indent(indent) + "<" + tagName + ">" + string + "</" + tagName + ">" + (indentFactor > 0 ? "\n" : "");
    }

    public static String toString(Object object, int indentFactor) {
        return XML.toString(object, null, XMLParserConfiguration.ORIGINAL, indentFactor);
    }

    public static String toString(Object object, String tagName, int indentFactor) {
        return XML.toString(object, tagName, XMLParserConfiguration.ORIGINAL, indentFactor);
    }

    public static String toString(Object object, String tagName, XMLParserConfiguration config, int indentFactor) throws JSONException {
        return XML.toString(object, tagName, config, indentFactor, 0);
    }

    private static final String indent(int indent) {
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < indent; ++i2) {
            sb.append(' ');
        }
        return sb.toString();
    }
}

