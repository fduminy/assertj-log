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
package org.assertj.jul;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.assertj.log.Appender;
import org.assertj.log.LoggerFacade;

/**
 * @author Fabien DUMINY
 */
public class JulLoggerFacade implements LoggerFacade<JulListAppender> {
    public static final JulLoggerFacade JUL = new JulLoggerFacade();

    private JulLoggerFacade() {
    }

    @Override
    public JulListAppender setUp(Appender events) {
        JulListAppender appender = new JulListAppender(events);
        getRootLogger().addHandler(appender);
        getRootLogger().setLevel(Level.ALL);
        return appender;
    }

    @Override
    public void tearDown(JulListAppender appender) {
        getRootLogger().removeHandler(appender);
    }

    static Logger getRootLogger() {
        return Logger.getLogger("");
    }
}
