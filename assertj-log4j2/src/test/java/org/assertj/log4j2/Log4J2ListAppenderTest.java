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

import static org.mockito.ArgumentMatchers.argThat;

import java.util.Objects;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.Message;
import org.assertj.log.AbstractListAppenderTest;
import org.assertj.log.Appender;
import org.assertj.log.LogLevelMap;
import org.assertj.log.LogMethod;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.mockito.internal.hamcrest.HamcrestArgumentMatcher;

/**
 * @author Fabien DUMINY
 */
public class Log4J2ListAppenderTest extends AbstractListAppenderTest<Log4j2ListAppender, Logger> {
    @Override
    protected LogLevelMap<LogMethod<Logger>> createLogMethodMap() {
        return getLogMethodMap();
    }

    static LogLevelMap<LogMethod<Logger>> getLogMethodMap() {
        return new LogLevelMap<>(Logger::fatal, Logger::error, Logger::warn, Logger::info, Logger::debug, Logger::trace);
    }

    @Override
    protected Log4j2ListAppender createAppender(Appender logs) {
        Log4j2ListAppender appender = new Log4j2ListAppender(logs);
        Log4j2LoggerFacade.getRootLogger().addAppender(appender, Level.ALL, null);
        return appender;
    }

    @Override
    protected void removeAppender(Log4j2ListAppender appender) {
        Log4j2LoggerFacade.getRootLogger().removeAppender(appender.getName());
    }

    @Override
    protected Logger getLogger(String loggerName) {
        final org.apache.logging.log4j.core.Logger logger = (org.apache.logging.log4j.core.Logger) LogManager.getLogger(loggerName);
        logger.setLevel(Level.ALL);
        return logger;
    }

    @Override
    protected String equalsMessage(String message) {
        return argThat(new HamcrestArgumentMatcher<>(new BaseMatcher<String>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("equals(").appendValue(message).appendText(")");
            }

            @Override
            public boolean matches(Object item) {
                if (!(item instanceof Message)) {
                    return false;
                }

                String actualMessage = ((Message) item).getFormattedMessage();
                if (Objects.equals(actualMessage, "null")) {
                    actualMessage = null;
                }
                return Objects.equals(actualMessage, message);
            }
        }));
    }
}
