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
package org.assertj.log;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * Test for class {@link LogAssertionsTest}.
 *
 * @author Fabien DUMINY
 */
@RunWith(MockitoJUnitRunner.class)
public class LogAssertionsTest {
    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Mock
    private LogAssertRule logs;

    @SuppressWarnings("ResultOfMethodCallIgnored") @Test
    public void testAssertThat() {
        LogAssert logAssert = LogAssertions.assertThat(logs);

        assertThat(logAssert).isNotNull();
        verify(logs).getEvents();
    }

    @Test
    public void testAssertThat_nullRule() {
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("logAssertRule");

        LogAssertions.assertThat((LogAssertRule) null);
    }
}
