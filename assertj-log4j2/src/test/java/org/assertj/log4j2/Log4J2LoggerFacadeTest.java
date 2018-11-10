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

import static org.assertj.log4j2.Log4j2LoggerFacade.LOG4J2;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.config.LoggerConfig;
import org.assertj.log.AbstractLoggerFacadeTest;

/**
 * @author Fabien DUMINY
 */
public class Log4J2LoggerFacadeTest extends AbstractLoggerFacadeTest<Log4j2ListAppender, Log4j2LoggerFacade> {
    public Log4J2LoggerFacadeTest() {
        super(Log4j2ListAppender.class);
    }

    protected Log4j2LoggerFacade createLoggerFacade() {
        return LOG4J2;
    }

    @SuppressWarnings("unchecked")
    protected List<Log4j2ListAppender> getAllAppenders(Class<Log4j2ListAppender> clazz) {
        List<Log4j2ListAppender> appenders = new ArrayList<>();
        final LoggerConfig rootLogger = Log4j2LoggerFacade.getRootLogger();
        for (Appender appender : rootLogger.getAppenders().values()) {
            if (clazz.isInstance(appender)) {
                appenders.add(clazz.cast(appender));
            }
        }
        return appenders;
    }

    protected void removeAllAppenders() {
        final LoggerConfig rootLogger = Log4j2LoggerFacade.getRootLogger();
        for (String appender : rootLogger.getAppenders().keySet()) {
            rootLogger.removeAppender(appender);
        }
    }
}
