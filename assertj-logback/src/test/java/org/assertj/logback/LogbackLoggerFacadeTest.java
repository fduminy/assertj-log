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

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.ILoggingEvent;
import org.assertj.log.AbstractLoggerFacadeTest;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Fabien DUMINY
 */
public class LogbackLoggerFacadeTest extends AbstractLoggerFacadeTest<ListAppender, LogbackLoggerFacade> {
    public LogbackLoggerFacadeTest() {
        super(ListAppender.class);
    }

    protected LogbackLoggerFacade createLoggerFacade() {
        return new LogbackLoggerFacade();
    }

    protected List<ListAppender> getAllAppenders(Class<ListAppender> clazz) {
        List<ListAppender> appenders = new ArrayList<>();
        for (Logger logger : ((LoggerContext) LoggerFactory.getILoggerFactory()).getLoggerList()) {
            Iterator<ch.qos.logback.core.Appender<ILoggingEvent>> it = logger.iteratorForAppenders();
            while (it.hasNext()) {
                final ch.qos.logback.core.Appender<ILoggingEvent> appender = it.next();
                if (clazz.isInstance(appender)) {
                    appenders.add(clazz.cast(appender));
                }
            }
        }
        return appenders;
    }

    protected void removeAllAppenders() {
        LogbackLoggerFacade.getRootLogger().detachAndStopAllAppenders();
    }
}
