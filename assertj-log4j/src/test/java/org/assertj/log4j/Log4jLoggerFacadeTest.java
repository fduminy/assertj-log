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

import org.apache.log4j.Appender;
import org.apache.log4j.Logger;
import org.assertj.log.AbstractLoggerFacadeTest;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * @author Fabien DUMINY
 */
public class Log4jLoggerFacadeTest extends AbstractLoggerFacadeTest<ListAppender, Log4jLoggerFacade> {
    public Log4jLoggerFacadeTest() {
        super(ListAppender.class);
    }

    protected Log4jLoggerFacade createLoggerFacade() {
        return new Log4jLoggerFacade();
    }

    @SuppressWarnings("unchecked")
    protected List<ListAppender> getAllAppenders(Class<ListAppender> clazz) {
        List<ListAppender> appenders = new ArrayList<>();
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
