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
package org.assertj.jul;

import org.assertj.log.*;
import org.junit.Test;

import java.util.logging.Level;
import java.util.logging.Logger;

import static java.util.logging.Level.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

/**
 * @author Fabien DUMINY
 */
public class JulListAppenderTest extends AbstractListAppenderTest<JulListAppender, Logger> {
    @Test
    public void testConstructor() {
        JulListAppender appender = new JulListAppender(mock(Appender.class));

        assertThat(appender.getLevel()).as("initial level").isSameAs(Level.ALL);
    }

    @Override
    protected LogLevelMap<LogMethod<Logger>> createLogMethodMap() {
        return new LogLevelMap<>(new NullLogMethod<>(), method(SEVERE), method(WARNING), method(INFO), method(FINE), method(FINER));
    }

    @Override
    protected JulListAppender createAppender(Appender logs) {
        JulListAppender appender = new JulListAppender(logs);
        final Logger rootLogger = JulLoggerFacade.getRootLogger();
        rootLogger.setLevel(Level.ALL);
        rootLogger.addHandler(appender);
        return appender;
    }

    @Override
    protected void removeAppender(JulListAppender appender) {
        JulLoggerFacade.getRootLogger().removeHandler(appender);
    }

    @Override
    protected Logger getLogger(String loggerName) {
        return Logger.getLogger(loggerName);
    }

    private LogMethod<Logger> method(Level level) {
        return (logger, message, throwable) -> logger.log(level, message, throwable);
    }
}
