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
package org.assertj.jul;

import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;

import org.assertj.log.Appender;
import org.assertj.log.LogLevelMap;

/**
 * @author Fabien DUMINY
 */
class JulListAppender extends Handler {
    //TODO add Level.FINEST ?
    static final LogLevelMap<Level> LEVELS = new LogLevelMap<>(null, Level.SEVERE, Level.WARNING, Level.INFO, Level.FINE, Level.FINER);

    private final Appender logs;

    JulListAppender(Appender logs) {
        this.logs = logs;
        setLevel(Level.ALL);
    }

    @Override
    public void publish(LogRecord record) {
        logs.append(record.getLoggerName(), LEVELS.get(record.getLevel()), record.getMessage(), record.getThrown());
    }

    @Override
    public void flush() {
    }

    @Override
    public void close() throws SecurityException {
    }
}
