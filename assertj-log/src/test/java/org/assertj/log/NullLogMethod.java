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
package org.assertj.log;

import org.junit.Assume;

/**
 * @param <L> Class of logger.
 *
 * @author Fabien DUMINY
 */
public final class NullLogMethod<L> implements LogMethod<L> {
    @SuppressWarnings("ConstantConditions") @Override
    public void log(L logger, String message, Throwable throwable) {
        Assume.assumeTrue(false); // force leaving the test without failure/error
    }
}
