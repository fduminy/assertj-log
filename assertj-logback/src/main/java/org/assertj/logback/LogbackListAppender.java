/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright 2012-2018 the original author or authors.
 */
package org.assertj.logback;

import org.assertj.log.Appender;
import org.assertj.log.LogLevelMap;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.ThrowableProxy;
import ch.qos.logback.core.AppenderBase;

/**
 * @author Fabien DUMINY
 */
class LogbackListAppender extends AppenderBase<ILoggingEvent> {
    static final LogLevelMap<Level> LEVELS = new LogLevelMap<>(null, Level.ERROR, Level.WARN, Level.INFO, Level.DEBUG, Level.TRACE);

    private final Appender logs;

    LogbackListAppender(Appender logs) {
        this.logs = logs;
    }

    @SuppressWarnings("ThrowableResultOfMethodCallIgnored")
    @Override
    protected void append(ILoggingEvent iLoggingEvent) {
        final Throwable throwable = (iLoggingEvent.getThrowableProxy() == null) ? null : ((ThrowableProxy) iLoggingEvent.getThrowableProxy()).getThrowable();
        logs.append(iLoggingEvent.getLoggerName(), LEVELS.get(iLoggingEvent.getLevel()), iLoggingEvent.getMessage(), throwable);
    }
}
