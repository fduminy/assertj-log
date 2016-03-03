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
package org.assertj.logback;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import org.assertj.log.Appender;
import org.assertj.log.LoggerFacade;
import org.slf4j.LoggerFactory;

/**
 * @author Fabien DUMINY
 */
public class LogbackLoggerFacade implements LoggerFacade<LogbackListAppender> {
    public static final LogbackLoggerFacade LOGBACK = new LogbackLoggerFacade();

    private LogbackLoggerFacade() {
    }

    @Override
    public LogbackListAppender setUp(Appender events) {
        LogbackListAppender appender = new LogbackListAppender(events);
        getRootLogger().addAppender(appender);
        getRootLogger().setLevel(Level.ALL);
        appender.start();
        return appender;
    }

    @Override
    public void tearDown(LogbackListAppender appender) {
        getRootLogger().detachAppender(appender);
    }

    static Logger getRootLogger() {
        return (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
    }
}
