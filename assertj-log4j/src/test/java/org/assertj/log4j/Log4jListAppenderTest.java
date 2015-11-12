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

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.assertj.log.AbstractListAppenderTest;
import org.assertj.log.Appender;
import org.assertj.log.LogLevelMap;
import org.assertj.log.LogMethod;

/**
 * @author Fabien DUMINY
 */
public class Log4jListAppenderTest extends AbstractListAppenderTest<Log4jListAppender, Logger> {
    @Override
    protected LogLevelMap<LogMethod<Logger>> createLogMethodMap() {
        return new LogLevelMap<>(Logger::fatal, Logger::error, Logger::warn, Logger::info, Logger::debug, Logger::trace);
    }

    @Override
    protected Log4jListAppender createAppender(Appender logs) {
        Log4jListAppender appender = new Log4jListAppender(logs);
        Logger.getRootLogger().addAppender(appender);
        return appender;
    }

    @Override
    protected void removeAppender(Log4jListAppender appender) {
        Logger.getRootLogger().removeAllAppenders();
    }

    @Override
    protected Logger getLogger(String loggerName) {
        final Logger logger = Logger.getLogger(loggerName);
        logger.setLevel(Level.ALL);
        return logger;
    }
}
