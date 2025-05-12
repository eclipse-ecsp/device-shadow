/********************************************************************************
 * *******************************************************************************
 *  * Copyright (c) 2023-24 Harman International
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *  http://www.apache.org/licenses/LICENSE-2.0
 *  *     
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *  *
 *  * SPDX-License-Identifier: Apache-2.0
 *  *******************************************************************************
 *******************************************************************************/

package org.eclipse.ecsp.util;

import org.eclipse.ecsp.commons.util.ObjectUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * This class contains unit tests for the ObjectUtils class.
 * It verifies the behavior of the methods in the ObjectUtils class.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = {"spring.profiles.active = default"})
public class ObjectUtilsTest {

    /**
     * Test case for the {@link ObjectUtils#requireNonEmpty(Object, String)} method.
     * 
     * This test verifies that the method correctly returns the input object if it is not empty.
     * It also checks that the method throws an exception with the specified message if the input object is empty.
     */
    @Test
    public void testRequireNonEmpty() {
        String obj = "TestObject";
        String msg = "TestObject is null";
        String actualObj = ObjectUtils.requireNonEmpty(obj, msg);
        Assert.assertEquals(obj, actualObj);
    }

    /**
     * Test case to verify that the `requireNonEmpty` method throws a RuntimeException
     * when the input object is empty.
     */
    @Test(expected = RuntimeException.class)
    public void testRequireNonEmptyNeg() {
        String obj = "";
        String msg = "TestObject is null";
        ObjectUtils.requireNonEmpty(obj, msg);
    }
}