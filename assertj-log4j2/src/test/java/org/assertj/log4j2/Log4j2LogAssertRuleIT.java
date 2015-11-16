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
package org.assertj.log4j2;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.log.AbstractLogAssertRuleIT;

/**
 * @author Fabien DUMINY
 */
public class Log4j2LogAssertRuleIT extends AbstractLogAssertRuleIT {
    public Log4j2LogAssertRuleIT() {
        super(Log4jIntegration.class);
    }

    public static class Log4jIntegration extends AbstractIntegration<Level, Logger, Log4j2ListAppender> {
        public Log4jIntegration() {
            super(Log4j2ListAppender.LEVELS, Log4J2ListAppenderTest.getLogMethodMap(), LogManager.getLogger(Log4j2LogAssertRuleIT.class.getName()), new Log4j2LoggerFacade());
        }
    }
}
