package lb.dmag.logging;


import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static lb.dmag.logging.LogInternalUtil.handleException;



/**
 * @author Leonid Bushuev
 **/
public interface Loggy {


  ////// METHODS THAT TO IMPLEMENT BY IMPLEMENTATION \\\\\\

  /**
   * Returns the current logging level.
   * @return the current logging level.
   */
  LogLevel getLevel();

  /**
   * Determines whether the system is in the debug mode.
   * @return whether the system is in the debug mode.
   */
  boolean isDebug();

  /**
   * Sends the message unconditionally (not depending on level).
   * @param level
   * @param text
   */
  void sendMessage(LogLevel level, String text);


  ////// USEFUL METHODS \\\\\\

  default void log(@NotNull LogLevel level, @Nullable String text) {
    if (getLevel().ordinal() >= level.ordinal() && text != null) {
      sendMessage(level, text);
    }
  }

  default void log(@NotNull LogLevel level, @Nullable String template, Object... params) {
    if (getLevel().ordinal() >= level.ordinal() && template != null) {
      sendMessage(level, String.format(template, params));
    }
  }

  default void log(@NotNull LogLevel level, @Nullable Throwable exception, @Nullable String text) {
    if (getLevel().ordinal() >= level.ordinal()) {
      String message;
      if (exception != null && text != null) {
        StringBuilder b = new StringBuilder();
        b.append(text).append('\n');
        b.append(handleException(exception, isDebug()));
        message = b.toString();
      }
      else if (exception != null) {
        message = handleException(exception, isDebug()).toString();
      }
      else if (text != null) {
        message = text;
      }
      else {
        return;
      }

      sendMessage(level, message);
    }
  }

  default void log(@NotNull LogLevel level, @Nullable Throwable exception, @Nullable String template, Object... params) {
    if (getLevel().ordinal() >= level.ordinal()) {
      String text = template != null ? String.format(template, params) : null;
      log(level, exception, text);
    }
  }


  // @formatter:off

  default void fatal(String text)                                               {log(LogLevel.Fatal, text);}
  default void fatal(String template, Object... params)                         {log(LogLevel.Fatal, template, params);}
  default void fatal(Throwable exception, String text)                          {log(LogLevel.Fatal, exception, text);}
  default void fatal(Throwable exception, String template, Object... params)    {log(LogLevel.Fatal, exception, template, params);}

  default void error(String text)                                               {log(LogLevel.Error, text);}
  default void error(String template, Object... params)                         {log(LogLevel.Error, template, params);}
  default void error(Throwable exception, String text)                          {log(LogLevel.Error, exception, text);}
  default void error(Throwable exception, String template, Object... params)    {log(LogLevel.Error, exception, template, params);}

  default void warn(String text)                                                {log(LogLevel.Warn, text);}
  default void warn(String template, Object... params)                          {log(LogLevel.Warn, template, params);}
  default void warn(Throwable exception, String text)                           {log(LogLevel.Warn, exception, text);}
  default void warn(Throwable exception, String template, Object... params)     {log(LogLevel.Warn, exception, template, params);}

  default void info(String text)                                                {log(LogLevel.Info, text);}
  default void info(String template, Object... params)                          {log(LogLevel.Info, template, params);}
  default void info(Throwable exception, String text)                           {log(LogLevel.Info, exception, text);}
  default void info(Throwable exception, String template, Object... params)     {log(LogLevel.Info, exception, template, params);}

  default void verbose(String text)                                             {log(LogLevel.Verbose, text);}
  default void verbose(String template, Object... params)                       {log(LogLevel.Verbose, template, params);}
  default void verbose(Throwable exception, String text)                        {log(LogLevel.Verbose, exception, text);}
  default void verbose(Throwable exception, String template, Object... params)  {log(LogLevel.Verbose, exception, template, params);}

  default void trace(String text)                                               {log(LogLevel.Trace, text);}
  default void trace(String template, Object... params)                         {log(LogLevel.Trace, template, params);}
  default void trace(Throwable exception, String text)                          {log(LogLevel.Trace, exception, text);}
  default void trace(Throwable exception, String template, Object... params)    {log(LogLevel.Trace, exception, template, params);}


}
