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
package org.assertj.log4j2;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.assertj.log.Appender;
import org.assertj.log.LogLevelMap;

/**
 * @author Fabien DUMINY
 */
class Log4j2ListAppender extends AbstractAppender {
    static final LogLevelMap<Level> LEVELS = new LogLevelMap<>(Level.FATAL, Level.ERROR, Level.WARN, Level.INFO, Level.DEBUG, Level.TRACE);

    private final Appender logs;

    Log4j2ListAppender(Appender logs) {
        super(Log4j2ListAppender.class.getSimpleName(), null, null);
        this.logs = logs;
    }

    @SuppressWarnings("ThrowableResultOfMethodCallIgnored")
    @Override
    public void append(LogEvent event) {
        logs.append(event.getLoggerName(), LEVELS.get(event.getLevel()), event.getMessage(), event.getThrown());
    }
}
