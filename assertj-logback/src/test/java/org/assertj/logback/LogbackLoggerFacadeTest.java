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
import org.assertj.log.Appender;
import org.junit.After;
import org.junit.Test;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

/**
 * @author Fabien DUMINY
 */
public class LogbackLoggerFacadeTest {
    @After
    public void tearDown() {
        LogbackLoggerFacade.getRootLogger().detachAndStopAllAppenders();
    }

    @Test
    public void testInit() throws Exception {
        new LogbackLoggerFacade();

        assertThat(getAllAppenders(ListAppender.class).size()).isEqualTo(0);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testSetUp() throws Exception {
        LogbackLoggerFacade facade = new LogbackLoggerFacade();

        ListAppender appender = facade.setUp(mock(Appender.class));

        assertThat(appender).isNotNull();
        assertThat(getAllAppenders(ListAppender.class).size()).isEqualTo(1);
        assertThat(getAllAppenders(ListAppender.class).get(0)).isSameAs(appender);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testTearDown() throws Exception {
        LogbackLoggerFacade facade = new LogbackLoggerFacade();
        ListAppender appender = facade.setUp(mock(Appender.class));

        facade.tearDown(appender);

        assertThat(getAllAppenders(ListAppender.class).size()).isEqualTo(0);
    }

    private static List<ch.qos.logback.core.Appender<ILoggingEvent>> getAllAppenders(Class<?> clazz) {
        List<ch.qos.logback.core.Appender<ILoggingEvent>> appenders = new ArrayList<>();
        for (Logger logger : ((LoggerContext) LoggerFactory.getILoggerFactory()).getLoggerList()) {
            Iterator<ch.qos.logback.core.Appender<ILoggingEvent>> it = logger.iteratorForAppenders();
            while (it.hasNext()) {
                final ch.qos.logback.core.Appender<ILoggingEvent> appender = it.next();
                if (clazz.isInstance(appender)) {
                    appenders.add(appender);
                }
            }
        }
        return appenders;
    }
}