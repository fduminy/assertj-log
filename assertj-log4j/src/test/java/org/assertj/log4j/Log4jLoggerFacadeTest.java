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
package org.assertj.log4j;

import static org.assertj.log4j.Log4jLoggerFacade.LOG4J;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.apache.log4j.Appender;
import org.apache.log4j.Logger;
import org.assertj.log.AbstractLoggerFacadeTest;

/**
 * @author Fabien DUMINY
 */
public class Log4jLoggerFacadeTest extends AbstractLoggerFacadeTest<Log4jListAppender, Log4jLoggerFacade> {
    public Log4jLoggerFacadeTest() {
        super(Log4jListAppender.class);
    }

    protected Log4jLoggerFacade createLoggerFacade() {
        return LOG4J;
    }

    @SuppressWarnings("unchecked")
    protected List<Log4jListAppender> getAllAppenders(Class<Log4jListAppender> clazz) {
        List<Log4jListAppender> appenders = new ArrayList<>();
        Enumeration<Appender> appenderEnum = Logger.getRootLogger().getAllAppenders();
        while (appenderEnum.hasMoreElements()) {
            final Appender appender = appenderEnum.nextElement();
            if (clazz.isInstance(appender)) {
                appenders.add(clazz.cast(appender));
            }
        }
        return appenders;
    }

    protected void removeAllAppenders() {
        Logger.getRootLogger().removeAllAppenders();
    }
}
