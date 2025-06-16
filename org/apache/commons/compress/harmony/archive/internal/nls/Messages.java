/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.harmony.archive.internal.nls;

import java.security.AccessController;
import java.util.Arrays;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.Objects;
import java.util.ResourceBundle;

public class Messages {
    private static ResourceBundle bundle = null;

    public static String format(String format, Object[] args) {
        StringBuilder answer = new StringBuilder(format.length() + args.length * 20);
        String[] argStrings = new String[args.length];
        Arrays.setAll(argStrings, i2 -> Objects.toString(args[i2], "<null>"));
        int lastI = 0;
        int i3 = format.indexOf(123, 0);
        while (i3 >= 0) {
            if (i3 != 0 && format.charAt(i3 - 1) == '\\') {
                if (i3 != 1) {
                    answer.append(format.substring(lastI, i3 - 1));
                }
                answer.append('{');
                lastI = i3 + 1;
            } else if (i3 > format.length() - 3) {
                answer.append(format.substring(lastI));
                lastI = format.length();
            } else {
                byte argnum = (byte)Character.digit(format.charAt(i3 + 1), 10);
                if (argnum < 0 || format.charAt(i3 + 2) != '}') {
                    answer.append(format.substring(lastI, i3 + 1));
                    lastI = i3 + 1;
                } else {
                    answer.append(format.substring(lastI, i3));
                    if (argnum >= argStrings.length) {
                        answer.append("<missing argument>");
                    } else {
                        answer.append(argStrings[argnum]);
                    }
                    lastI = i3 + 3;
                }
            }
            i3 = format.indexOf(123, lastI);
        }
        if (lastI < format.length()) {
            answer.append(format.substring(lastI));
        }
        return answer.toString();
    }

    public static String getString(String msg) {
        if (bundle == null) {
            return msg;
        }
        try {
            return bundle.getString(msg);
        } catch (MissingResourceException e2) {
            return "Missing message: " + msg;
        }
    }

    public static String getString(String msg, char arg) {
        return Messages.getString(msg, new Object[]{String.valueOf(arg)});
    }

    public static String getString(String msg, int arg) {
        return Messages.getString(msg, new Object[]{Integer.toString(arg)});
    }

    public static String getString(String msg, Object arg) {
        return Messages.getString(msg, new Object[]{arg});
    }

    public static String getString(String msg, Object arg1, Object arg2) {
        return Messages.getString(msg, new Object[]{arg1, arg2});
    }

    public static String getString(String msg, Object[] args) {
        String format = msg;
        if (bundle != null) {
            try {
                format = bundle.getString(msg);
            } catch (MissingResourceException missingResourceException) {
                // empty catch block
            }
        }
        return Messages.format(format, args);
    }

    public static ResourceBundle setLocale(Locale locale, String resource) {
        try {
            ClassLoader loader = null;
            return (ResourceBundle)AccessController.doPrivileged(() -> ResourceBundle.getBundle(resource, locale, loader != null ? loader : ClassLoader.getSystemClassLoader()));
        } catch (MissingResourceException missingResourceException) {
            return null;
        }
    }

    static {
        try {
            bundle = Messages.setLocale(Locale.getDefault(), "org.apache.commons.compress.harmony.archive.internal.nls.messages");
        } catch (Throwable e2) {
            e2.printStackTrace();
        }
    }
}

