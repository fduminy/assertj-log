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
package org.assertj.logback;

import static org.assertj.logback.LogbackLoggerFacade.LOGBACK;

import org.assertj.log.AbstractLogAssertRuleIT;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;

/**
 * @author Fabien DUMINY
 */
public class LogbackLogAssertRuleIT extends AbstractLogAssertRuleIT {
    public LogbackLogAssertRuleIT() {
        super(LogbackIntegration.class);
    }

    public static class LogbackIntegration extends AbstractIntegration<Level, Logger, LogbackListAppender> {
        public LogbackIntegration() {
            super(LogbackListAppender.LEVELS, LogbackListAppenderTest.getLogMethodMap(),
                    (Logger) LoggerFactory.getLogger(LogbackLogAssertRuleIT.class.getName()), LOGBACK);
        }
    }
}
