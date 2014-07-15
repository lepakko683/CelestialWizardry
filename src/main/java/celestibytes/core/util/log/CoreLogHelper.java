package celestibytes.core.util.log;

import celestibytes.core.CelestiCore;
import celestibytes.core.mod.IMod;

import cpw.mods.fml.common.FMLLog;
import org.apache.logging.log4j.Level;

public class CoreLogHelper
{
    protected static void log(String targetLog, Level logLevel, Object object)
    {
        FMLLog.log(targetLog, logLevel, String.valueOf(object));
    }

    protected static void log(IMod mod, Level logLevel, Object object)
    {
        log(mod.getTargetLog(), logLevel, object);
    }

    protected static void logApi(IMod mod, Level logLevel, Object object)
    {
        log(mod.getTargetLog() + "API", logLevel, object);
    }

    private static void log(Level logLevel, Object object, boolean api)
    {
        if (api)
        {
            logApi(CelestiCore.instance, logLevel, String.valueOf(object));
        }
        else
        {
            log(CelestiCore.instance, logLevel, String.valueOf(object));
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
            CoreLogHelper.log(logLevel, String.valueOf(object), true);
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
