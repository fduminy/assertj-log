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
package org.assertj.log4j2;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.LoggerConfig;
import org.assertj.log.Appender;
import org.assertj.log.LoggerFacade;

/**
 * @author Fabien DUMINY
 */
public class Log4j2LoggerFacade implements LoggerFacade<Log4j2ListAppender> {
    @Override
    public Log4j2ListAppender setUp(Appender events) {
        final LoggerContext context = getContext();
        final Configuration config = context.getConfiguration();
        Log4j2ListAppender appender = new Log4j2ListAppender(events);
        config.getRootLogger().addAppender(appender, Level.ALL, null);
        config.getRootLogger().setLevel(Level.ALL);
        context.updateLoggers();
        return appender;
    }

    @Override
    public void tearDown(Log4j2ListAppender appender) {
        getRootLogger().removeAppender(appender.getName());
    }

    static LoggerConfig getRootLogger() {
        return getContext().getConfiguration().getRootLogger();
    }

    static LoggerContext getContext() {
        return (LoggerContext) LogManager.getContext(false);
    }
}
