package celestialwizardry.api.util;

import celestialwizardry.api.CWApi;

import cpw.mods.fml.common.FMLLog;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ApiLogHelper
{
    private static final String UTIL_PACKAGE = CWApi.CW_PACKAGE + "util.";
    private static final String API = UTIL_PACKAGE + "LogHelper.Api";

    /**
     * No events will be logged.
     */
    public static void off(Object object)
    {
        try
        {
            Class<?> clazz = Class.forName(API);
            Class[] args = new Class[]{Object.class};
            Method method = clazz.getDeclaredMethod("off", args);
            method.invoke(null, object);
        }
        catch (ClassNotFoundException e)
        {
            FMLLog.warning("Failed to invoke method " + API + ".off");
            e.printStackTrace();
        }
        catch (IllegalAccessException e)
        {
            FMLLog.warning("Failed to invoke method " + API + ".off");
            e.printStackTrace();
        }
        catch (IllegalArgumentException e)
        {
            FMLLog.warning("Failed to invoke method " + API + ".off");
            e.printStackTrace();
        }
        catch (NoSuchMethodException e)
        {
            FMLLog.warning("Failed to invoke method " + API + ".off");
            e.printStackTrace();
        }
        catch (SecurityException e)
        {
            FMLLog.warning("Failed to invoke method " + API + ".off");
            e.printStackTrace();
        }
        catch (InvocationTargetException e)
        {
            FMLLog.warning("Failed to invoke method " + API + ".off");
            e.printStackTrace();
        }
    }

    /**
     * A severe error that will prevent the application from continuing.
     */
    public static void fatal(Object object)
    {
        try
        {
            Class<?> clazz = Class.forName(API);
            Class[] args = new Class[]{Object.class};
            Method method = clazz.getDeclaredMethod("fatal", args);
            method.invoke(null, object);
        }
        catch (ClassNotFoundException e)
        {
            FMLLog.warning("Failed to invoke method " + API + ".fatal");
            e.printStackTrace();
        }
        catch (IllegalAccessException e)
        {
            FMLLog.warning("Failed to invoke method " + API + ".fatal");
            e.printStackTrace();
        }
        catch (IllegalArgumentException e)
        {
            FMLLog.warning("Failed to invoke method " + API + ".fatal");
            e.printStackTrace();
        }
        catch (NoSuchMethodException e)
        {
            FMLLog.warning("Failed to invoke method " + API + ".fatal");
            e.printStackTrace();
        }
        catch (SecurityException e)
        {
            FMLLog.warning("Failed to invoke method " + API + ".fatal");
            e.printStackTrace();
        }
        catch (InvocationTargetException e)
        {
            FMLLog.warning("Failed to invoke method " + API + ".fatal");
            e.printStackTrace();
        }
    }

    /**
     * An error in the application, possibly recoverable.
     */
    public static void error(Object object)
    {
        try
        {
            Class<?> clazz = Class.forName(API);
            Class[] args = new Class[]{Object.class};
            Method method = clazz.getDeclaredMethod("error", args);
            method.invoke(null, object);
        }
        catch (ClassNotFoundException e)
        {
            FMLLog.warning("Failed to invoke method " + API + ".error");
            e.printStackTrace();
        }
        catch (IllegalAccessException e)
        {
            FMLLog.warning("Failed to invoke method " + API + ".error");
            e.printStackTrace();
        }
        catch (IllegalArgumentException e)
        {
            FMLLog.warning("Failed to invoke method " + API + ".error");
            e.printStackTrace();
        }
        catch (NoSuchMethodException e)
        {
            FMLLog.warning("Failed to invoke method " + API + ".error");
            e.printStackTrace();
        }
        catch (SecurityException e)
        {
            FMLLog.warning("Failed to invoke method " + API + ".error");
            e.printStackTrace();
        }
        catch (InvocationTargetException e)
        {
            FMLLog.warning("Failed to invoke method " + API + ".error");
            e.printStackTrace();
        }
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
        try
        {
            Class<?> clazz = Class.forName(API);
            Class[] args = new Class[]{Object.class};
            Method method = clazz.getDeclaredMethod("warn", args);
            method.invoke(null, object);
        }
        catch (ClassNotFoundException e)
        {
            FMLLog.warning("Failed to invoke method " + API + ".warn");
            e.printStackTrace();
        }
        catch (IllegalAccessException e)
        {
            FMLLog.warning("Failed to invoke method " + API + ".warn");
            e.printStackTrace();
        }
        catch (IllegalArgumentException e)
        {
            FMLLog.warning("Failed to invoke method " + API + ".warn");
            e.printStackTrace();
        }
        catch (NoSuchMethodException e)
        {
            FMLLog.warning("Failed to invoke method " + API + ".warn");
            e.printStackTrace();
        }
        catch (SecurityException e)
        {
            FMLLog.warning("Failed to invoke method " + API + ".warn");
            e.printStackTrace();
        }
        catch (InvocationTargetException e)
        {
            FMLLog.warning("Failed to invoke method " + API + ".warn");
            e.printStackTrace();
        }
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
        try
        {
            Class<?> clazz = Class.forName(API);
            Class[] args = new Class[]{Object.class};
            Method method = clazz.getDeclaredMethod("info", args);
            method.invoke(null, object);
        }
        catch (ClassNotFoundException e)
        {
            FMLLog.warning("Failed to invoke method " + API + ".info");
            e.printStackTrace();
        }
        catch (IllegalAccessException e)
        {
            FMLLog.warning("Failed to invoke method " + API + ".info");
            e.printStackTrace();
        }
        catch (IllegalArgumentException e)
        {
            FMLLog.warning("Failed to invoke method " + API + ".info");
            e.printStackTrace();
        }
        catch (NoSuchMethodException e)
        {
            FMLLog.warning("Failed to invoke method " + API + ".info");
            e.printStackTrace();
        }
        catch (SecurityException e)
        {
            FMLLog.warning("Failed to invoke method " + API + ".info");
            e.printStackTrace();
        }
        catch (InvocationTargetException e)
        {
            FMLLog.warning("Failed to invoke method " + API + ".info");
            e.printStackTrace();
        }
    }

    /**
     * A general debugging event.
     */
    public static void debug(Object object)
    {
        try
        {
            Class<?> clazz = Class.forName(API);
            Class[] args = new Class[]{Object.class};
            Method method = clazz.getDeclaredMethod("debug", args);
            method.invoke(null, object);
        }
        catch (ClassNotFoundException e)
        {
            FMLLog.warning("Failed to invoke method " + API + ".debug");
            e.printStackTrace();
        }
        catch (IllegalAccessException e)
        {
            FMLLog.warning("Failed to invoke method " + API + ".debug");
            e.printStackTrace();
        }
        catch (IllegalArgumentException e)
        {
            FMLLog.warning("Failed to invoke method " + API + ".debug");
            e.printStackTrace();
        }
        catch (NoSuchMethodException e)
        {
            FMLLog.warning("Failed to invoke method " + API + ".debug");
            e.printStackTrace();
        }
        catch (SecurityException e)
        {
            FMLLog.warning("Failed to invoke method " + API + ".debug");
            e.printStackTrace();
        }
        catch (InvocationTargetException e)
        {
            FMLLog.warning("Failed to invoke method " + API + ".debug");
            e.printStackTrace();
        }
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
        try
        {
            Class<?> clazz = Class.forName(API);
            Class[] args = new Class[]{Object.class};
            Method method = clazz.getDeclaredMethod("trace", args);
            method.invoke(null, object);
        }
        catch (ClassNotFoundException e)
        {
            FMLLog.warning("Failed to invoke method " + API + ".trace");
            e.printStackTrace();
        }
        catch (IllegalAccessException e)
        {
            FMLLog.warning("Failed to invoke method " + API + ".trace");
            e.printStackTrace();
        }
        catch (IllegalArgumentException e)
        {
            FMLLog.warning("Failed to invoke method " + API + ".trace");
            e.printStackTrace();
        }
        catch (NoSuchMethodException e)
        {
            FMLLog.warning("Failed to invoke method " + API + ".trace");
            e.printStackTrace();
        }
        catch (SecurityException e)
        {
            FMLLog.warning("Failed to invoke method " + API + ".trace");
            e.printStackTrace();
        }
        catch (InvocationTargetException e)
        {
            FMLLog.warning("Failed to invoke method " + API + ".trace");
            e.printStackTrace();
        }
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
        try
        {
            Class<?> clazz = Class.forName(API);
            Class[] args = new Class[]{Object.class};
            Method method = clazz.getDeclaredMethod("all", args);
            method.invoke(null, object);
        }
        catch (ClassNotFoundException e)
        {
            FMLLog.warning("Failed to invoke method " + API + ".all");
            e.printStackTrace();
        }
        catch (IllegalAccessException e)
        {
            FMLLog.warning("Failed to invoke method " + API + ".all");
            e.printStackTrace();
        }
        catch (IllegalArgumentException e)
        {
            FMLLog.warning("Failed to invoke method " + API + ".all");
            e.printStackTrace();
        }
        catch (NoSuchMethodException e)
        {
            FMLLog.warning("Failed to invoke method " + API + ".all");
            e.printStackTrace();
        }
        catch (SecurityException e)
        {
            FMLLog.warning("Failed to invoke method " + API + ".all");
            e.printStackTrace();
        }
        catch (InvocationTargetException e)
        {
            FMLLog.warning("Failed to invoke method " + API + ".all");
            e.printStackTrace();
        }
    }
}
