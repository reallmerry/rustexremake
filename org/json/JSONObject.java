/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.json;

import java.io.Closeable;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONPointer;
import org.json.JSONPointerException;
import org.json.JSONPropertyIgnore;
import org.json.JSONPropertyName;
import org.json.JSONString;
import org.json.JSONTokener;
import org.json.JSONWriter;

public class JSONObject {
    static final Pattern NUMBER_PATTERN = Pattern.compile("-?(?:0|[1-9]\\d*)(?:\\.\\d+)?(?:[eE][+-]?\\d+)?");
    private final Map<String, Object> map;
    public static final Object NULL = new Null();

    public Class<? extends Map> getMapType() {
        return this.map.getClass();
    }

    public JSONObject() {
        this.map = new HashMap<String, Object>();
    }

    public JSONObject(JSONObject jo, String ... names) {
        this(names.length);
        for (int i2 = 0; i2 < names.length; ++i2) {
            try {
                this.putOnce(names[i2], jo.opt(names[i2]));
                continue;
            } catch (Exception exception) {
                // empty catch block
            }
        }
    }

    public JSONObject(JSONTokener x2) throws JSONException {
        this();
        if (x2.nextClean() != '{') {
            throw x2.syntaxError("A JSONObject text must begin with '{'");
        }
        block9: while (true) {
            char prev = x2.getPrevious();
            char c2 = x2.nextClean();
            switch (c2) {
                case '\u0000': {
                    throw x2.syntaxError("A JSONObject text must end with '}'");
                }
                case '}': {
                    return;
                }
                case '[': 
                case '{': {
                    if (prev != '{') break;
                    throw x2.syntaxError("A JSON Object can not directly nest another JSON Object or JSON Array.");
                }
            }
            x2.back();
            String key = x2.nextValue().toString();
            c2 = x2.nextClean();
            if (c2 != ':') {
                throw x2.syntaxError("Expected a ':' after a key");
            }
            if (key != null) {
                if (this.opt(key) != null) {
                    throw x2.syntaxError("Duplicate key \"" + key + "\"");
                }
                Object value = x2.nextValue();
                if (value != null) {
                    this.put(key, value);
                }
            }
            switch (x2.nextClean()) {
                case ',': 
                case ';': {
                    if (x2.nextClean() == '}') {
                        return;
                    }
                    x2.back();
                    continue block9;
                }
                case '}': {
                    return;
                }
            }
            break;
        }
        throw x2.syntaxError("Expected a ',' or '}'");
    }

    public JSONObject(Map<?, ?> m2) {
        if (m2 == null) {
            this.map = new HashMap<String, Object>();
        } else {
            this.map = new HashMap<String, Object>(m2.size());
            for (Map.Entry<?, ?> e2 : m2.entrySet()) {
                if (e2.getKey() == null) {
                    throw new NullPointerException("Null key.");
                }
                Object value = e2.getValue();
                if (value == null) continue;
                this.map.put(String.valueOf(e2.getKey()), JSONObject.wrap(value));
            }
        }
    }

    public JSONObject(Object bean) {
        this();
        this.populateMap(bean);
    }

    private JSONObject(Object bean, Set<Object> objectsRecord) {
        this();
        this.populateMap(bean, objectsRecord);
    }

    public JSONObject(Object object, String ... names) {
        this(names.length);
        Class<?> c2 = object.getClass();
        for (int i2 = 0; i2 < names.length; ++i2) {
            String name = names[i2];
            try {
                this.putOpt(name, c2.getField(name).get(object));
                continue;
            } catch (Exception exception) {
                // empty catch block
            }
        }
    }

    public JSONObject(String source) throws JSONException {
        this(new JSONTokener(source));
    }

    public JSONObject(String baseName, Locale locale) throws JSONException {
        this();
        ResourceBundle bundle = ResourceBundle.getBundle(baseName, locale, Thread.currentThread().getContextClassLoader());
        Enumeration<String> keys = bundle.getKeys();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            if (key == null) continue;
            String[] path = key.split("\\.");
            int last = path.length - 1;
            JSONObject target = this;
            for (int i2 = 0; i2 < last; ++i2) {
                String segment = path[i2];
                JSONObject nextTarget = target.optJSONObject(segment);
                if (nextTarget == null) {
                    nextTarget = new JSONObject();
                    target.put(segment, nextTarget);
                }
                target = nextTarget;
            }
            target.put(path[last], bundle.getString(key));
        }
    }

    protected JSONObject(int initialCapacity) {
        this.map = new HashMap<String, Object>(initialCapacity);
    }

    public JSONObject accumulate(String key, Object value) throws JSONException {
        JSONObject.testValidity(value);
        Object object = this.opt(key);
        if (object == null) {
            this.put(key, value instanceof JSONArray ? new JSONArray().put(value) : value);
        } else if (object instanceof JSONArray) {
            ((JSONArray)object).put(value);
        } else {
            this.put(key, new JSONArray().put(object).put(value));
        }
        return this;
    }

    public JSONObject append(String key, Object value) throws JSONException {
        JSONObject.testValidity(value);
        Object object = this.opt(key);
        if (object == null) {
            this.put(key, new JSONArray().put(value));
        } else if (object instanceof JSONArray) {
            this.put(key, ((JSONArray)object).put(value));
        } else {
            throw JSONObject.wrongValueFormatException(key, "JSONArray", null, null);
        }
        return this;
    }

    public static String doubleToString(double d2) {
        if (Double.isInfinite(d2) || Double.isNaN(d2)) {
            return "null";
        }
        String string = Double.toString(d2);
        if (string.indexOf(46) > 0 && string.indexOf(101) < 0 && string.indexOf(69) < 0) {
            while (string.endsWith("0")) {
                string = string.substring(0, string.length() - 1);
            }
            if (string.endsWith(".")) {
                string = string.substring(0, string.length() - 1);
            }
        }
        return string;
    }

    public Object get(String key) throws JSONException {
        if (key == null) {
            throw new JSONException("Null key.");
        }
        Object object = this.opt(key);
        if (object == null) {
            throw new JSONException("JSONObject[" + JSONObject.quote(key) + "] not found.");
        }
        return object;
    }

    public <E extends Enum<E>> E getEnum(Class<E> clazz, String key) throws JSONException {
        E val = this.optEnum(clazz, key);
        if (val == null) {
            throw JSONObject.wrongValueFormatException(key, "enum of type " + JSONObject.quote(clazz.getSimpleName()), this.opt(key), null);
        }
        return val;
    }

    public boolean getBoolean(String key) throws JSONException {
        Object object = this.get(key);
        if (object.equals(Boolean.FALSE) || object instanceof String && ((String)object).equalsIgnoreCase("false")) {
            return false;
        }
        if (object.equals(Boolean.TRUE) || object instanceof String && ((String)object).equalsIgnoreCase("true")) {
            return true;
        }
        throw JSONObject.wrongValueFormatException(key, "Boolean", object, null);
    }

    public BigInteger getBigInteger(String key) throws JSONException {
        Object object = this.get(key);
        BigInteger ret = JSONObject.objectToBigInteger(object, null);
        if (ret != null) {
            return ret;
        }
        throw JSONObject.wrongValueFormatException(key, "BigInteger", object, null);
    }

    public BigDecimal getBigDecimal(String key) throws JSONException {
        Object object = this.get(key);
        BigDecimal ret = JSONObject.objectToBigDecimal(object, null);
        if (ret != null) {
            return ret;
        }
        throw JSONObject.wrongValueFormatException(key, "BigDecimal", object, null);
    }

    public double getDouble(String key) throws JSONException {
        Object object = this.get(key);
        if (object instanceof Number) {
            return ((Number)object).doubleValue();
        }
        try {
            return Double.parseDouble(object.toString());
        } catch (Exception e2) {
            throw JSONObject.wrongValueFormatException(key, "double", object, e2);
        }
    }

    public float getFloat(String key) throws JSONException {
        Object object = this.get(key);
        if (object instanceof Number) {
            return ((Number)object).floatValue();
        }
        try {
            return Float.parseFloat(object.toString());
        } catch (Exception e2) {
            throw JSONObject.wrongValueFormatException(key, "float", object, e2);
        }
    }

    public Number getNumber(String key) throws JSONException {
        Object object = this.get(key);
        try {
            if (object instanceof Number) {
                return (Number)object;
            }
            return JSONObject.stringToNumber(object.toString());
        } catch (Exception e2) {
            throw JSONObject.wrongValueFormatException(key, "number", object, e2);
        }
    }

    public int getInt(String key) throws JSONException {
        Object object = this.get(key);
        if (object instanceof Number) {
            return ((Number)object).intValue();
        }
        try {
            return Integer.parseInt(object.toString());
        } catch (Exception e2) {
            throw JSONObject.wrongValueFormatException(key, "int", object, e2);
        }
    }

    public JSONArray getJSONArray(String key) throws JSONException {
        Object object = this.get(key);
        if (object instanceof JSONArray) {
            return (JSONArray)object;
        }
        throw JSONObject.wrongValueFormatException(key, "JSONArray", object, null);
    }

    public JSONObject getJSONObject(String key) throws JSONException {
        Object object = this.get(key);
        if (object instanceof JSONObject) {
            return (JSONObject)object;
        }
        throw JSONObject.wrongValueFormatException(key, "JSONObject", object, null);
    }

    public long getLong(String key) throws JSONException {
        Object object = this.get(key);
        if (object instanceof Number) {
            return ((Number)object).longValue();
        }
        try {
            return Long.parseLong(object.toString());
        } catch (Exception e2) {
            throw JSONObject.wrongValueFormatException(key, "long", object, e2);
        }
    }

    public static String[] getNames(JSONObject jo) {
        if (jo.isEmpty()) {
            return null;
        }
        return jo.keySet().toArray(new String[jo.length()]);
    }

    public static String[] getNames(Object object) {
        if (object == null) {
            return null;
        }
        Class<?> klass = object.getClass();
        Field[] fields = klass.getFields();
        int length = fields.length;
        if (length == 0) {
            return null;
        }
        String[] names = new String[length];
        for (int i2 = 0; i2 < length; ++i2) {
            names[i2] = fields[i2].getName();
        }
        return names;
    }

    public String getString(String key) throws JSONException {
        Object object = this.get(key);
        if (object instanceof String) {
            return (String)object;
        }
        throw JSONObject.wrongValueFormatException(key, "string", object, null);
    }

    public boolean has(String key) {
        return this.map.containsKey(key);
    }

    public JSONObject increment(String key) throws JSONException {
        Object value = this.opt(key);
        if (value == null) {
            this.put(key, 1);
        } else if (value instanceof Integer) {
            this.put(key, (Integer)value + 1);
        } else if (value instanceof Long) {
            this.put(key, (Long)value + 1L);
        } else if (value instanceof BigInteger) {
            this.put(key, ((BigInteger)value).add(BigInteger.ONE));
        } else if (value instanceof Float) {
            this.put(key, ((Float)value).floatValue() + 1.0f);
        } else if (value instanceof Double) {
            this.put(key, (Double)value + 1.0);
        } else if (value instanceof BigDecimal) {
            this.put(key, ((BigDecimal)value).add(BigDecimal.ONE));
        } else {
            throw new JSONException("Unable to increment [" + JSONObject.quote(key) + "].");
        }
        return this;
    }

    public boolean isNull(String key) {
        return NULL.equals(this.opt(key));
    }

    public Iterator<String> keys() {
        return this.keySet().iterator();
    }

    public Set<String> keySet() {
        return this.map.keySet();
    }

    protected Set<Map.Entry<String, Object>> entrySet() {
        return this.map.entrySet();
    }

    public int length() {
        return this.map.size();
    }

    public void clear() {
        this.map.clear();
    }

    public boolean isEmpty() {
        return this.map.isEmpty();
    }

    public JSONArray names() {
        if (this.map.isEmpty()) {
            return null;
        }
        return new JSONArray((Collection<?>)this.map.keySet());
    }

    public static String numberToString(Number number) throws JSONException {
        if (number == null) {
            throw new JSONException("Null pointer");
        }
        JSONObject.testValidity(number);
        String string = number.toString();
        if (string.indexOf(46) > 0 && string.indexOf(101) < 0 && string.indexOf(69) < 0) {
            while (string.endsWith("0")) {
                string = string.substring(0, string.length() - 1);
            }
            if (string.endsWith(".")) {
                string = string.substring(0, string.length() - 1);
            }
        }
        return string;
    }

    public Object opt(String key) {
        return key == null ? null : this.map.get(key);
    }

    public <E extends Enum<E>> E optEnum(Class<E> clazz, String key) {
        return this.optEnum(clazz, key, null);
    }

    public <E extends Enum<E>> E optEnum(Class<E> clazz, String key, E defaultValue) {
        try {
            Object val = this.opt(key);
            if (NULL.equals(val)) {
                return defaultValue;
            }
            if (clazz.isAssignableFrom(val.getClass())) {
                Enum myE = (Enum)val;
                return (E)myE;
            }
            return Enum.valueOf(clazz, val.toString());
        } catch (IllegalArgumentException e2) {
            return defaultValue;
        } catch (NullPointerException e3) {
            return defaultValue;
        }
    }

    public boolean optBoolean(String key) {
        return this.optBoolean(key, false);
    }

    public boolean optBoolean(String key, boolean defaultValue) {
        Object val = this.opt(key);
        if (NULL.equals(val)) {
            return defaultValue;
        }
        if (val instanceof Boolean) {
            return (Boolean)val;
        }
        try {
            return this.getBoolean(key);
        } catch (Exception e2) {
            return defaultValue;
        }
    }

    public BigDecimal optBigDecimal(String key, BigDecimal defaultValue) {
        Object val = this.opt(key);
        return JSONObject.objectToBigDecimal(val, defaultValue);
    }

    static BigDecimal objectToBigDecimal(Object val, BigDecimal defaultValue) {
        return JSONObject.objectToBigDecimal(val, defaultValue, true);
    }

    static BigDecimal objectToBigDecimal(Object val, BigDecimal defaultValue, boolean exact) {
        if (NULL.equals(val)) {
            return defaultValue;
        }
        if (val instanceof BigDecimal) {
            return (BigDecimal)val;
        }
        if (val instanceof BigInteger) {
            return new BigDecimal((BigInteger)val);
        }
        if (val instanceof Double || val instanceof Float) {
            if (!JSONObject.numberIsFinite((Number)val)) {
                return defaultValue;
            }
            if (exact) {
                return new BigDecimal(((Number)val).doubleValue());
            }
            return new BigDecimal(val.toString());
        }
        if (val instanceof Long || val instanceof Integer || val instanceof Short || val instanceof Byte) {
            return new BigDecimal(((Number)val).longValue());
        }
        try {
            return new BigDecimal(val.toString());
        } catch (Exception e2) {
            return defaultValue;
        }
    }

    public BigInteger optBigInteger(String key, BigInteger defaultValue) {
        Object val = this.opt(key);
        return JSONObject.objectToBigInteger(val, defaultValue);
    }

    static BigInteger objectToBigInteger(Object val, BigInteger defaultValue) {
        if (NULL.equals(val)) {
            return defaultValue;
        }
        if (val instanceof BigInteger) {
            return (BigInteger)val;
        }
        if (val instanceof BigDecimal) {
            return ((BigDecimal)val).toBigInteger();
        }
        if (val instanceof Double || val instanceof Float) {
            if (!JSONObject.numberIsFinite((Number)val)) {
                return defaultValue;
            }
            return new BigDecimal(((Number)val).doubleValue()).toBigInteger();
        }
        if (val instanceof Long || val instanceof Integer || val instanceof Short || val instanceof Byte) {
            return BigInteger.valueOf(((Number)val).longValue());
        }
        try {
            String valStr = val.toString();
            if (JSONObject.isDecimalNotation(valStr)) {
                return new BigDecimal(valStr).toBigInteger();
            }
            return new BigInteger(valStr);
        } catch (Exception e2) {
            return defaultValue;
        }
    }

    public double optDouble(String key) {
        return this.optDouble(key, Double.NaN);
    }

    public double optDouble(String key, double defaultValue) {
        Number val = this.optNumber(key);
        if (val == null) {
            return defaultValue;
        }
        return val.doubleValue();
    }

    public float optFloat(String key) {
        return this.optFloat(key, Float.NaN);
    }

    public float optFloat(String key, float defaultValue) {
        Number val = this.optNumber(key);
        if (val == null) {
            return defaultValue;
        }
        float floatValue = val.floatValue();
        return floatValue;
    }

    public int optInt(String key) {
        return this.optInt(key, 0);
    }

    public int optInt(String key, int defaultValue) {
        Number val = this.optNumber(key, null);
        if (val == null) {
            return defaultValue;
        }
        return val.intValue();
    }

    public JSONArray optJSONArray(String key) {
        Object o2 = this.opt(key);
        return o2 instanceof JSONArray ? (JSONArray)o2 : null;
    }

    public JSONObject optJSONObject(String key) {
        return this.optJSONObject(key, null);
    }

    public JSONObject optJSONObject(String key, JSONObject defaultValue) {
        Object object = this.opt(key);
        return object instanceof JSONObject ? (JSONObject)object : defaultValue;
    }

    public long optLong(String key) {
        return this.optLong(key, 0L);
    }

    public long optLong(String key, long defaultValue) {
        Number val = this.optNumber(key, null);
        if (val == null) {
            return defaultValue;
        }
        return val.longValue();
    }

    public Number optNumber(String key) {
        return this.optNumber(key, null);
    }

    public Number optNumber(String key, Number defaultValue) {
        Object val = this.opt(key);
        if (NULL.equals(val)) {
            return defaultValue;
        }
        if (val instanceof Number) {
            return (Number)val;
        }
        try {
            return JSONObject.stringToNumber(val.toString());
        } catch (Exception e2) {
            return defaultValue;
        }
    }

    public String optString(String key) {
        return this.optString(key, "");
    }

    public String optString(String key, String defaultValue) {
        Object object = this.opt(key);
        return NULL.equals(object) ? defaultValue : object.toString();
    }

    private void populateMap(Object bean) {
        this.populateMap(bean, Collections.newSetFromMap(new IdentityHashMap()));
    }

    private void populateMap(Object bean, Set<Object> objectsRecord) {
        Method[] methods;
        Class<?> klass = bean.getClass();
        boolean includeSuperClass = klass.getClassLoader() != null;
        for (Method method : methods = includeSuperClass ? klass.getMethods() : klass.getDeclaredMethods()) {
            String key;
            int modifiers = method.getModifiers();
            if (!Modifier.isPublic(modifiers) || Modifier.isStatic(modifiers) || method.getParameterTypes().length != 0 || method.isBridge() || method.getReturnType() == Void.TYPE || !JSONObject.isValidMethodName(method.getName()) || (key = JSONObject.getKeyNameFromMethod(method)) == null || key.isEmpty()) continue;
            try {
                Object result = method.invoke(bean, new Object[0]);
                if (result == null) continue;
                if (objectsRecord.contains(result)) {
                    throw JSONObject.recursivelyDefinedObjectException(key);
                }
                objectsRecord.add(result);
                this.map.put(key, JSONObject.wrap(result, objectsRecord));
                objectsRecord.remove(result);
                if (!(result instanceof Closeable)) continue;
                try {
                    ((Closeable)result).close();
                } catch (IOException iOException) {}
            } catch (IllegalAccessException illegalAccessException) {
            } catch (IllegalArgumentException illegalArgumentException) {
            } catch (InvocationTargetException invocationTargetException) {
                // empty catch block
            }
        }
    }

    private static boolean isValidMethodName(String name) {
        return !"getClass".equals(name) && !"getDeclaringClass".equals(name);
    }

    private static String getKeyNameFromMethod(Method method) {
        String key;
        int forcedNameDepth;
        int ignoreDepth = JSONObject.getAnnotationDepth(method, JSONPropertyIgnore.class);
        if (ignoreDepth > 0 && ((forcedNameDepth = JSONObject.getAnnotationDepth(method, JSONPropertyName.class)) < 0 || ignoreDepth <= forcedNameDepth)) {
            return null;
        }
        JSONPropertyName annotation = JSONObject.getAnnotation(method, JSONPropertyName.class);
        if (annotation != null && annotation.value() != null && !annotation.value().isEmpty()) {
            return annotation.value();
        }
        String name = method.getName();
        if (name.startsWith("get") && name.length() > 3) {
            key = name.substring(3);
        } else if (name.startsWith("is") && name.length() > 2) {
            key = name.substring(2);
        } else {
            return null;
        }
        if (key.length() == 0 || Character.isLowerCase(key.charAt(0))) {
            return null;
        }
        if (key.length() == 1) {
            key = key.toLowerCase(Locale.ROOT);
        } else if (!Character.isUpperCase(key.charAt(1))) {
            key = key.substring(0, 1).toLowerCase(Locale.ROOT) + key.substring(1);
        }
        return key;
    }

    private static <A extends Annotation> A getAnnotation(Method m2, Class<A> annotationClass) {
        if (m2 == null || annotationClass == null) {
            return null;
        }
        if (m2.isAnnotationPresent(annotationClass)) {
            return m2.getAnnotation(annotationClass);
        }
        Class<?> c2 = m2.getDeclaringClass();
        if (c2.getSuperclass() == null) {
            return null;
        }
        for (Class<?> i2 : c2.getInterfaces()) {
            try {
                Method im = i2.getMethod(m2.getName(), m2.getParameterTypes());
                return JSONObject.getAnnotation(im, annotationClass);
            } catch (SecurityException ex) {
            } catch (NoSuchMethodException ex) {
                // empty catch block
            }
        }
        try {
            return JSONObject.getAnnotation(c2.getSuperclass().getMethod(m2.getName(), m2.getParameterTypes()), annotationClass);
        } catch (SecurityException ex) {
            return null;
        } catch (NoSuchMethodException ex) {
            return null;
        }
    }

    private static int getAnnotationDepth(Method m2, Class<? extends Annotation> annotationClass) {
        if (m2 == null || annotationClass == null) {
            return -1;
        }
        if (m2.isAnnotationPresent(annotationClass)) {
            return 1;
        }
        Class<?> c2 = m2.getDeclaringClass();
        if (c2.getSuperclass() == null) {
            return -1;
        }
        for (Class<?> i2 : c2.getInterfaces()) {
            try {
                Method im = i2.getMethod(m2.getName(), m2.getParameterTypes());
                int d2 = JSONObject.getAnnotationDepth(im, annotationClass);
                if (d2 <= 0) continue;
                return d2 + 1;
            } catch (SecurityException ex) {
            } catch (NoSuchMethodException ex) {
                // empty catch block
            }
        }
        try {
            int d3 = JSONObject.getAnnotationDepth(c2.getSuperclass().getMethod(m2.getName(), m2.getParameterTypes()), annotationClass);
            if (d3 > 0) {
                return d3 + 1;
            }
            return -1;
        } catch (SecurityException ex) {
            return -1;
        } catch (NoSuchMethodException ex) {
            return -1;
        }
    }

    public JSONObject put(String key, boolean value) throws JSONException {
        return this.put(key, value ? Boolean.TRUE : Boolean.FALSE);
    }

    public JSONObject put(String key, Collection<?> value) throws JSONException {
        return this.put(key, new JSONArray(value));
    }

    public JSONObject put(String key, double value) throws JSONException {
        return this.put(key, (Object)value);
    }

    public JSONObject put(String key, float value) throws JSONException {
        return this.put(key, Float.valueOf(value));
    }

    public JSONObject put(String key, int value) throws JSONException {
        return this.put(key, (Object)value);
    }

    public JSONObject put(String key, long value) throws JSONException {
        return this.put(key, (Object)value);
    }

    public JSONObject put(String key, Map<?, ?> value) throws JSONException {
        return this.put(key, new JSONObject(value));
    }

    public JSONObject put(String key, Object value) throws JSONException {
        if (key == null) {
            throw new NullPointerException("Null key.");
        }
        if (value != null) {
            JSONObject.testValidity(value);
            this.map.put(key, value);
        } else {
            this.remove(key);
        }
        return this;
    }

    public JSONObject putOnce(String key, Object value) throws JSONException {
        if (key != null && value != null) {
            if (this.opt(key) != null) {
                throw new JSONException("Duplicate key \"" + key + "\"");
            }
            return this.put(key, value);
        }
        return this;
    }

    public JSONObject putOpt(String key, Object value) throws JSONException {
        if (key != null && value != null) {
            return this.put(key, value);
        }
        return this;
    }

    public Object query(String jsonPointer) {
        return this.query(new JSONPointer(jsonPointer));
    }

    public Object query(JSONPointer jsonPointer) {
        return jsonPointer.queryFrom(this);
    }

    public Object optQuery(String jsonPointer) {
        return this.optQuery(new JSONPointer(jsonPointer));
    }

    public Object optQuery(JSONPointer jsonPointer) {
        try {
            return jsonPointer.queryFrom(this);
        } catch (JSONPointerException e2) {
            return null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static String quote(String string) {
        StringWriter sw = new StringWriter();
        StringBuffer stringBuffer = sw.getBuffer();
        synchronized (stringBuffer) {
            try {
                return JSONObject.quote(string, sw).toString();
            } catch (IOException ignored) {
                return "";
            }
        }
    }

    public static Writer quote(String string, Writer w2) throws IOException {
        if (string == null || string.isEmpty()) {
            w2.write("\"\"");
            return w2;
        }
        char c2 = '\u0000';
        int len = string.length();
        w2.write(34);
        block9: for (int i2 = 0; i2 < len; ++i2) {
            char b2 = c2;
            c2 = string.charAt(i2);
            switch (c2) {
                case '\"': 
                case '\\': {
                    w2.write(92);
                    w2.write(c2);
                    continue block9;
                }
                case '/': {
                    if (b2 == '<') {
                        w2.write(92);
                    }
                    w2.write(c2);
                    continue block9;
                }
                case '\b': {
                    w2.write("\\b");
                    continue block9;
                }
                case '\t': {
                    w2.write("\\t");
                    continue block9;
                }
                case '\n': {
                    w2.write("\\n");
                    continue block9;
                }
                case '\f': {
                    w2.write("\\f");
                    continue block9;
                }
                case '\r': {
                    w2.write("\\r");
                    continue block9;
                }
                default: {
                    if (c2 < ' ' || c2 >= '\u0080' && c2 < '\u00a0' || c2 >= '\u2000' && c2 < '\u2100') {
                        w2.write("\\u");
                        String hhhh = Integer.toHexString(c2);
                        w2.write("0000", 0, 4 - hhhh.length());
                        w2.write(hhhh);
                        continue block9;
                    }
                    w2.write(c2);
                }
            }
        }
        w2.write(34);
        return w2;
    }

    public Object remove(String key) {
        return this.map.remove(key);
    }

    public boolean similar(Object other) {
        try {
            if (!(other instanceof JSONObject)) {
                return false;
            }
            if (!this.keySet().equals(((JSONObject)other).keySet())) {
                return false;
            }
            for (Map.Entry<String, Object> entry : this.entrySet()) {
                Object valueOther;
                String name = entry.getKey();
                Object valueThis = entry.getValue();
                if (valueThis == (valueOther = ((JSONObject)other).get(name))) continue;
                if (valueThis == null) {
                    return false;
                }
                if (!(valueThis instanceof JSONObject ? !((JSONObject)valueThis).similar(valueOther) : (valueThis instanceof JSONArray ? !((JSONArray)valueThis).similar(valueOther) : (valueThis instanceof Number && valueOther instanceof Number ? !JSONObject.isNumberSimilar((Number)valueThis, (Number)valueOther) : (valueThis instanceof JSONString && valueOther instanceof JSONString ? !((JSONString)valueThis).toJSONString().equals(((JSONString)valueOther).toJSONString()) : !valueThis.equals(valueOther)))))) continue;
                return false;
            }
            return true;
        } catch (Throwable exception) {
            return false;
        }
    }

    static boolean isNumberSimilar(Number l2, Number r2) {
        if (!JSONObject.numberIsFinite(l2) || !JSONObject.numberIsFinite(r2)) {
            return false;
        }
        if (l2.getClass().equals(r2.getClass()) && l2 instanceof Comparable) {
            int compareTo = ((Comparable)((Object)l2)).compareTo(r2);
            return compareTo == 0;
        }
        BigDecimal lBigDecimal = JSONObject.objectToBigDecimal(l2, null, false);
        BigDecimal rBigDecimal = JSONObject.objectToBigDecimal(r2, null, false);
        if (lBigDecimal == null || rBigDecimal == null) {
            return false;
        }
        return lBigDecimal.compareTo(rBigDecimal) == 0;
    }

    private static boolean numberIsFinite(Number n2) {
        if (n2 instanceof Double && (((Double)n2).isInfinite() || ((Double)n2).isNaN())) {
            return false;
        }
        return !(n2 instanceof Float) || !((Float)n2).isInfinite() && !((Float)n2).isNaN();
    }

    protected static boolean isDecimalNotation(String val) {
        return val.indexOf(46) > -1 || val.indexOf(101) > -1 || val.indexOf(69) > -1 || "-0".equals(val);
    }

    protected static Number stringToNumber(String val) throws NumberFormatException {
        char initial = val.charAt(0);
        if (initial >= '0' && initial <= '9' || initial == '-') {
            BigInteger bi;
            char at1;
            if (JSONObject.isDecimalNotation(val)) {
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
            return NULL;
        }
        char initial = string.charAt(0);
        if (initial >= '0' && initial <= '9' || initial == '-') {
            try {
                return JSONObject.stringToNumber(string);
            } catch (Exception exception) {
                // empty catch block
            }
        }
        return string;
    }

    public static void testValidity(Object o2) throws JSONException {
        if (o2 instanceof Number && !JSONObject.numberIsFinite((Number)o2)) {
            throw new JSONException("JSON does not allow non-finite numbers.");
        }
    }

    public JSONArray toJSONArray(JSONArray names) throws JSONException {
        if (names == null || names.isEmpty()) {
            return null;
        }
        JSONArray ja2 = new JSONArray();
        for (int i2 = 0; i2 < names.length(); ++i2) {
            ja2.put(this.opt(names.getString(i2)));
        }
        return ja2;
    }

    public String toString() {
        try {
            return this.toString(0);
        } catch (Exception e2) {
            return null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public String toString(int indentFactor) throws JSONException {
        StringWriter w2 = new StringWriter();
        StringBuffer stringBuffer = w2.getBuffer();
        synchronized (stringBuffer) {
            return this.write(w2, indentFactor, 0).toString();
        }
    }

    public static String valueToString(Object value) throws JSONException {
        return JSONWriter.valueToString(value);
    }

    public static Object wrap(Object object) {
        return JSONObject.wrap(object, null);
    }

    private static Object wrap(Object object, Set<Object> objectsRecord) {
        try {
            String objectPackageName;
            if (NULL.equals(object)) {
                return NULL;
            }
            if (object instanceof JSONObject || object instanceof JSONArray || NULL.equals(object) || object instanceof JSONString || object instanceof Byte || object instanceof Character || object instanceof Short || object instanceof Integer || object instanceof Long || object instanceof Boolean || object instanceof Float || object instanceof Double || object instanceof String || object instanceof BigInteger || object instanceof BigDecimal || object instanceof Enum) {
                return object;
            }
            if (object instanceof Collection) {
                Collection coll = (Collection)object;
                return new JSONArray(coll);
            }
            if (object.getClass().isArray()) {
                return new JSONArray(object);
            }
            if (object instanceof Map) {
                Map map = (Map)object;
                return new JSONObject(map);
            }
            Package objectPackage = object.getClass().getPackage();
            String string = objectPackageName = objectPackage != null ? objectPackage.getName() : "";
            if (objectPackageName.startsWith("java.") || objectPackageName.startsWith("javax.") || object.getClass().getClassLoader() == null) {
                return object.toString();
            }
            if (objectsRecord != null) {
                return new JSONObject(object, objectsRecord);
            }
            return new JSONObject(object);
        } catch (JSONException exception) {
            throw exception;
        } catch (Exception exception) {
            return null;
        }
    }

    public Writer write(Writer writer) throws JSONException {
        return this.write(writer, 0, 0);
    }

    static final Writer writeValue(Writer writer, Object value, int indentFactor, int indent) throws JSONException, IOException {
        if (value == null || value.equals(null)) {
            writer.write("null");
        } else if (value instanceof JSONString) {
            String o2;
            try {
                o2 = ((JSONString)value).toJSONString();
            } catch (Exception e2) {
                throw new JSONException(e2);
            }
            writer.write(o2 != null ? o2.toString() : JSONObject.quote(value.toString()));
        } else if (value instanceof Number) {
            String numberAsString = JSONObject.numberToString((Number)value);
            if (NUMBER_PATTERN.matcher(numberAsString).matches()) {
                writer.write(numberAsString);
            } else {
                JSONObject.quote(numberAsString, writer);
            }
        } else if (value instanceof Boolean) {
            writer.write(value.toString());
        } else if (value instanceof Enum) {
            writer.write(JSONObject.quote(((Enum)value).name()));
        } else if (value instanceof JSONObject) {
            ((JSONObject)value).write(writer, indentFactor, indent);
        } else if (value instanceof JSONArray) {
            ((JSONArray)value).write(writer, indentFactor, indent);
        } else if (value instanceof Map) {
            Map map = (Map)value;
            new JSONObject(map).write(writer, indentFactor, indent);
        } else if (value instanceof Collection) {
            Collection coll = (Collection)value;
            new JSONArray(coll).write(writer, indentFactor, indent);
        } else if (value.getClass().isArray()) {
            new JSONArray(value).write(writer, indentFactor, indent);
        } else {
            JSONObject.quote(value.toString(), writer);
        }
        return writer;
    }

    static final void indent(Writer writer, int indent) throws IOException {
        for (int i2 = 0; i2 < indent; ++i2) {
            writer.write(32);
        }
    }

    public Writer write(Writer writer, int indentFactor, int indent) throws JSONException {
        try {
            boolean needsComma = false;
            int length = this.length();
            writer.write(123);
            if (length == 1) {
                Map.Entry<String, Object> entry = this.entrySet().iterator().next();
                String key = entry.getKey();
                writer.write(JSONObject.quote(key));
                writer.write(58);
                if (indentFactor > 0) {
                    writer.write(32);
                }
                try {
                    JSONObject.writeValue(writer, entry.getValue(), indentFactor, indent);
                } catch (Exception e2) {
                    throw new JSONException("Unable to write JSONObject value for key: " + key, e2);
                }
            }
            if (length != 0) {
                int newIndent = indent + indentFactor;
                for (Map.Entry<String, Object> entry : this.entrySet()) {
                    if (needsComma) {
                        writer.write(44);
                    }
                    if (indentFactor > 0) {
                        writer.write(10);
                    }
                    JSONObject.indent(writer, newIndent);
                    String key = entry.getKey();
                    writer.write(JSONObject.quote(key));
                    writer.write(58);
                    if (indentFactor > 0) {
                        writer.write(32);
                    }
                    try {
                        JSONObject.writeValue(writer, entry.getValue(), indentFactor, newIndent);
                    } catch (Exception e3) {
                        throw new JSONException("Unable to write JSONObject value for key: " + key, e3);
                    }
                    needsComma = true;
                }
                if (indentFactor > 0) {
                    writer.write(10);
                }
                JSONObject.indent(writer, indent);
            }
            writer.write(125);
            return writer;
        } catch (IOException exception) {
            throw new JSONException(exception);
        }
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> results = new HashMap<String, Object>();
        for (Map.Entry<String, Object> entry : this.entrySet()) {
            Object value = entry.getValue() == null || NULL.equals(entry.getValue()) ? null : (entry.getValue() instanceof JSONObject ? ((JSONObject)entry.getValue()).toMap() : (entry.getValue() instanceof JSONArray ? ((JSONArray)entry.getValue()).toList() : entry.getValue()));
            results.put(entry.getKey(), value);
        }
        return results;
    }

    private static JSONException wrongValueFormatException(String key, String valueType, Object value, Throwable cause) {
        if (value == null) {
            return new JSONException("JSONObject[" + JSONObject.quote(key) + "] is not a " + valueType + " (null).", cause);
        }
        if (value instanceof Map || value instanceof Iterable || value instanceof JSONObject) {
            return new JSONException("JSONObject[" + JSONObject.quote(key) + "] is not a " + valueType + " (" + value.getClass() + ").", cause);
        }
        return new JSONException("JSONObject[" + JSONObject.quote(key) + "] is not a " + valueType + " (" + value.getClass() + " : " + value + ").", cause);
    }

    private static JSONException recursivelyDefinedObjectException(String key) {
        return new JSONException("JavaBean object contains recursively defined member variable of key " + JSONObject.quote(key));
    }

    private static final class Null {
        private Null() {
        }

        protected final Object clone() {
            return this;
        }

        public boolean equals(Object object) {
            return object == null || object == this;
        }

        public int hashCode() {
            return 0;
        }

        public String toString() {
            return "null";
        }
    }
}

