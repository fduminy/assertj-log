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
import ch.qos.logback.core.ContextBase;
import org.assertj.log.*;
import org.slf4j.LoggerFactory;

/**
 * @author Fabien DUMINY
 */
public class ListAppenderTest extends AbstractListAppenderTest<ListAppender, Logger> {
    @Override
    protected LogLevelMap<LogMethod<Logger>> createLogMethodMap() {
        return new LogLevelMap<>(new NullLogMethod<>(), Logger::error, Logger::warn, Logger::info, Logger::debug, Logger::trace);
    }

    @Override
    protected ListAppender createAppender(Appender logs) {
        ListAppender appender = new ListAppender(logs);
        appender.setContext(new ContextBase());
        appender.start();
        LogbackLoggerFacade.getRootLogger().addAppender(appender);
        return appender;
    }

    @Override
    protected void removeAppender(ListAppender appender) {
        LogbackLoggerFacade.getRootLogger().detachAppender(appender);
    }

    @Override
    protected Logger getLogger(String loggerName) {
        final Logger logger = (Logger) LoggerFactory.getLogger(loggerName);
        logger.setLevel(Level.ALL);
        logger.setAdditive(true);
        return logger;
    }
}
