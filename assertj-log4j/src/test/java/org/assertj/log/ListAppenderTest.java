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

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;

/**
 * @author Fabien DUMINY
 */
@RunWith(Theories.class)
public class ListAppenderTest {
    private ListAppender appender;
    private Appender logs;

    @FunctionalInterface
    public interface LogMethod {
        void log(Logger logger, String message, java.lang.Throwable throwable);
    }

    private static final Map<LogLevel, LogMethod> LOG_METHODS = new HashMap<>();

    static {
        LOG_METHODS.put(LogLevel.FATAL, Logger::fatal);
        LOG_METHODS.put(LogLevel.ERROR, Logger::error);
        LOG_METHODS.put(LogLevel.WARN, Logger::warn);
        LOG_METHODS.put(LogLevel.INFO, Logger::info);
        LOG_METHODS.put(LogLevel.DEBUG, Logger::debug);
        LOG_METHODS.put(LogLevel.TRACE, Logger::trace);
    }

    @SuppressWarnings("unchecked")
    @Before
    public void setUp() {
        logs = mock(Appender.class);
        appender = new ListAppender(logs);
        Logger.getRootLogger().addAppender(appender);
    }

    @After
    public void tearDown() {
        Logger.getRootLogger().removeAllAppenders();
    }

    @Theory
    public void testLog(LogLevel level, Messages message, Throwables throwableEnum) throws Exception {
        System.out.println("testLog(" + level + ", " + message + ", " + throwableEnum + ")");
        final Logger logger = Logger.getLogger(getClass());
        logger.setLevel(Level.ALL);

        LOG_METHODS.get(level).log(logger, message.getMessage(), throwableEnum.getThrowable());

        verify(logs).append(eq(logger.getName()), eq(level), eq(message.getMessage()), eq(throwableEnum.getThrowable()));
        verifyNoMoreInteractions(logs);
    }
}
