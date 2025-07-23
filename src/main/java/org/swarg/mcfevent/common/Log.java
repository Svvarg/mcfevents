package org.swarg.mcfevent.common;

import java.io.PrintStream;

/**
 * Простейшая обёртка для журналирования для отладки
 * Идея: включать режим debug в runtime из консольных комад, чтобы затем
 * проверять например работу вызова методов, добавленых в игру через патчи.
 *
 * 22-07-2025
 * @author Swarg
 */
public class Log {
    public static boolean debug = false;
    public static PrintStream out = System.out;


    public static void logDebug(String msg, Object p1) {
        if (debug && out != null) {
            out.println(fmt(msg, new Object[]{p1}));
        }
    }

    public static void logDebug(String msg, Object p1, Object p2) {
        if (debug && out != null) {
            out.println(fmt(msg, new Object[]{p1, p2}));
        }
    }

    public static void logDebug(String msg, Object p1, Object p2, Object p3) {
        if (debug && out != null) {
            out.println(fmt(msg, new Object[]{p1, p2, p3}));
        }
    }

    public static String fmt(String fmsg, Object... params) {
        try {
            return String.format(fmsg, params);
        }
        catch (Exception e) {
            StringBuilder sb = new StringBuilder();
            sb.append(fmsg);
            for (Object param : params) {
                sb.append(' ').append(param);
            }
            return sb.toString();
        }
    }

}
