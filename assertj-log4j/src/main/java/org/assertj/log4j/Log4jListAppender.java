/**
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright 2012-2015 the original author or authors.
 */
package org.assertj.log4j;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.Level;
import org.apache.log4j.spi.LoggingEvent;
import org.assertj.log.Appender;
import org.assertj.log.LogLevelMap;

/**
 * @author Fabien DUMINY
 */
class Log4jListAppender extends AppenderSkeleton {
    static final LogLevelMap<Level> LEVELS = new LogLevelMap<>(Level.FATAL, Level.ERROR, Level.WARN, Level.INFO, Level.DEBUG, Level.TRACE);

    private final Appender logs;

    Log4jListAppender(Appender logs) {
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
