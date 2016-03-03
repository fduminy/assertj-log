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

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.assertj.log.AbstractLogAssertRuleIT;

import static org.assertj.log4j.Log4jLoggerFacade.LOG4J;

/**
 * @author Fabien DUMINY
 */
public class Log4jLogAssertRuleIT extends AbstractLogAssertRuleIT {
    public Log4jLogAssertRuleIT() {
        super(Log4jIntegration.class);
    }

    public static class Log4jIntegration extends AbstractIntegration<Level, Logger, Log4jListAppender> {
        public Log4jIntegration() {
            super(Log4jListAppender.LEVELS, Log4jListAppenderTest.getLogMethodMap(), Logger.getLogger(Log4jLogAssertRuleIT.class.getName()), LOG4J);
        }
    }
}
