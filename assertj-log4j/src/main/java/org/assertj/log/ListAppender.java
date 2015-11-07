/**
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 * <p>
 * Copyright 2012-2015 the original author or authors.
 */
package org.assertj.log;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.Level;
import org.apache.log4j.spi.LoggingEvent;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Fabien DUMINY
 */
class ListAppender extends AppenderSkeleton {
    private static final Map<Level, LogLevel> LEVELS = new HashMap<>(LogLevel.values().length);

    static {
        LEVELS.put(Level.FATAL, LogLevel.FATAL);
        LEVELS.put(Level.ERROR, LogLevel.ERROR);
        LEVELS.put(Level.WARN, LogLevel.WARN);
        LEVELS.put(Level.INFO, LogLevel.INFO);
        LEVELS.put(Level.DEBUG, LogLevel.DEBUG);
        LEVELS.put(Level.TRACE, LogLevel.TRACE);
    }

    private final Appender logs;

    ListAppender(Appender logs) {
        this.logs = logs;
    }

    @SuppressWarnings("ThrowableResultOfMethodCallIgnored")
    @Override
    protected void append(LoggingEvent event) {
        final Throwable throwable = (event.getThrowableInformation() == null) ? null : event.getThrowableInformation().getThrowable();
        logs.append(event.getLoggerName(), LEVELS.get(event.getLevel()), event.getMessage(), throwable);
    }

    @Override
    public boolean requiresLayout() {
        return false;
    }

    @Override
    public void close() {
    }
}
