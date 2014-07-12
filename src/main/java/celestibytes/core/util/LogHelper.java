package celestibytes.core.util;

import cpw.mods.fml.common.FMLLog;

import celestibytes.core.reference.Reference;
import org.apache.logging.log4j.Level;

public class LogHelper
{
    private static void log(Level logLevel, Object object, boolean api)
    {
        if (api)
        {
            FMLLog.log(Reference.MOD_NAME + "Api", logLevel, String.valueOf(object));
        }
        else
        {
            FMLLog.log(Reference.MOD_NAME, logLevel, String.valueOf(object));
        }
    }

    private static void log(Level logLevel, Object object)
    {
        log(logLevel, object, false);
    }

    /**
     * No events will be logged.
     */
    public static void off(Object object)
    {
        log(Level.OFF, object);
    }

    /**
     * A severe error that will prevent the application from continuing.
     */
    public static void fatal(Object object)
    {
        log(Level.FATAL, object);
    }

    /**
     * An error in the application, possibly recoverable.
     */
    public static void error(Object object)
    {
        log(Level.ERROR, object);
    }

    /**
     * An error in the application, possibly recoverable.
     */
    public static void err(Object object)
    {
        error(object);
    }

    /**
     * An event that might possible lead to an error.
     */
    public static void warn(Object object)
    {
        log(Level.WARN, object);
    }

    /**
     * An event that might possible lead to an error.
     */
    public static void warning(Object object)
    {
        warn(object);
    }

    /**
     * An event for informational purposes.
     */
    public static void info(Object object)
    {
        log(Level.INFO, object);
    }

    /**
     * A general debugging event.
     */
    public static void debug(Object object)
    {
        log(Level.DEBUG, object);
    }

    /**
     * A general debugging event.
     */
    public static void fine(Object object)
    {
        debug(object);
    }

    /**
     * A fine-grained debug message, typically capturing the flow through the application.
     */
    public static void trace(Object object)
    {
        log(Level.TRACE, object);
    }

    /**
     * A fine-grained debug message, typically capturing the flow through the application.
     */
    public static void finer(Object object)
    {
        trace(object);
    }

    /**
     * All events should be logged.
     */
    public static void all(Object object)
    {
        log(Level.ALL, object);
    }

    public static class Api
    {
        private static void log(Level logLevel, Object object)
        {
            LogHelper.log(logLevel, String.valueOf(object), true);
        }

        /**
         * No events will be logged.
         */
        public static void off(Object object)
        {
            log(Level.OFF, object);
        }

        /**
         * A severe error that will prevent the application from continuing.
         */
        public static void fatal(Object object)
        {
            log(Level.FATAL, object);
        }

        /**
         * An error in the application, possibly recoverable.
         */
        public static void error(Object object)
        {
            log(Level.ERROR, object);
        }

        /**
         * An error in the application, possibly recoverable.
         */
        public static void err(Object object)
        {
            error(object);
        }

        /**
         * An event that might possible lead to an error.
         */
        public static void warn(Object object)
        {
            log(Level.WARN, object);
        }

        /**
         * An event that might possible lead to an error.
         */
        public static void warning(Object object)
        {
            warn(object);
        }

        /**
         * An event for informational purposes.
         */
        public static void info(Object object)
        {
            log(Level.INFO, object);
        }

        /**
         * A general debugging event.
         */
        public static void debug(Object object)
        {
            log(Level.DEBUG, object);
        }

        /**
         * A general debugging event.
         */
        public static void fine(Object object)
        {
            debug(object);
        }

        /**
         * A fine-grained debug message, typically capturing the flow through the application.
         */
        public static void trace(Object object)
        {
            log(Level.TRACE, object);
        }

        /**
         * A fine-grained debug message, typically capturing the flow through the application.
         */
        public static void finer(Object object)
        {
            trace(object);
        }

        /**
         * All events should be logged.
         */
        public static void all(Object object)
        {
            log(Level.ALL, object);
        }
    }
}
