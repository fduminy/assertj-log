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

import org.apache.log4j.Logger;
import org.assertj.log.Appender;
import org.junit.After;
import org.junit.Test;

import java.util.Enumeration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

/**
 * @author Fabien DUMINY
 */
public class Log4jLoggerFacadeTest {
    @After
    public void tearDown() {
        Logger.getRootLogger().removeAllAppenders();
    }

    @Test
    public void testInit() throws Exception {
        new Log4jLoggerFacade();

        // TODO add support of Enumeration in AbstractListAppender.asList()
        assertThat(countElementsOfType(Logger.getRootLogger().getAllAppenders(), ListAppender.class)).isEqualTo(0);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testSetUp() throws Exception {
        Log4jLoggerFacade facade = new Log4jLoggerFacade();

        ListAppender appender = facade.setUp(mock(Appender.class));

        assertThat(appender).isNotNull();
        assertThat(countElementsOfType(Logger.getRootLogger().getAllAppenders(), ListAppender.class)).isEqualTo(1);
        assertThat(Logger.getRootLogger().getAllAppenders().nextElement()).isSameAs(appender);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testTearDown() throws Exception {
        Log4jLoggerFacade facade = new Log4jLoggerFacade();
        ListAppender appender = facade.setUp(mock(Appender.class));

        facade.tearDown(appender);

        assertThat(countElementsOfType(Logger.getRootLogger().getAllAppenders(), ListAppender.class)).isEqualTo(0);
    }

    public static int countElementsOfType(Enumeration<?> appenders, Class<?> clazz) {
        int count = 0;
        while (appenders.hasMoreElements()) {
            if (clazz.isInstance(appenders.nextElement())) {
                count++;
            }
        }
        return count;
    }
}