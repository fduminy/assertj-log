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
package org.assertj.jul;

import org.assertj.log.AbstractLoggerFacadeTest;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.logging.Handler;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * @author Fabien DUMINY
 */
public class JulLoggerFacadeTest extends AbstractLoggerFacadeTest<JulListAppender, JulLoggerFacade> {
    public JulLoggerFacadeTest() {
        super(JulListAppender.class);
    }

    protected JulLoggerFacade createLoggerFacade() {
        return new JulLoggerFacade();
    }

    protected List<JulListAppender> getAllAppenders(Class<JulListAppender> clazz) {
        List<JulListAppender> appenders = new ArrayList<>();
        Enumeration<String> loggerNames = LogManager.getLogManager().getLoggerNames();
        while (loggerNames.hasMoreElements()) {
            final Logger logger = Logger.getLogger(loggerNames.nextElement());
            for (Handler handler : logger.getHandlers()) {
                if (clazz.isInstance(handler)) {
                    appenders.add(clazz.cast(handler));
                }
            }
        }
        return appenders;
    }

    protected void removeAllAppenders() {
        Enumeration<String> loggerNames = LogManager.getLogManager().getLoggerNames();
        while (loggerNames.hasMoreElements()) {
            final Logger logger = Logger.getLogger(loggerNames.nextElement());
            for (Handler handler : logger.getHandlers()) {
                logger.removeHandler(handler);
            }
        }
    }
}
