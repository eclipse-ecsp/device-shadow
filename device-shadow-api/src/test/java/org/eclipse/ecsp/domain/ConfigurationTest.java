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

package org.eclipse.ecsp.domain;

import org.eclipse.ecsp.commons.util.JsonUtil;
import org.eclipse.ecsp.entities.Configuration;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

/**
 * This class contains unit tests for the Configuration class.
 * It tests the equality and hash code methods of the Configuration class.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = {"spring.profiles.active = default"})
public class ConfigurationTest {

    private static final int UPLOAD_TIMESTAMP = 1497007356;
    private static final String PDID = "HU0SCIUKMYXFG9";

    /**
     * Test method to verify the equality of Configuration objects.
     * 
     * @throws IOException if an I/O error occurs during the test
     */
    @Test
    public void testConfigurationEquality() throws IOException {

        Configuration configuration1 =
            new Configuration(PDID, UPLOAD_TIMESTAMP, JsonUtil.convertToMap("/testData-Geofence.json"));
        Configuration configuration2 =
            new Configuration(PDID, UPLOAD_TIMESTAMP, JsonUtil.convertToMap("/testData-Geofence.json"));
        Configuration configuration3 =
            new Configuration(PDID, UPLOAD_TIMESTAMP, JsonUtil.convertToMap("/testData-Geofence.json"));
        Configuration configuration4 =
            new Configuration(PDID, UPLOAD_TIMESTAMP, JsonUtil.convertToMap("/testdataset.json"));
        Assert.assertEquals(configuration1, configuration2);
        Assert.assertNotEquals(true, configuration1.equals(configuration3));
        Assert.assertNotEquals(true, configuration1.equals(configuration4));


        Assert.assertEquals(configuration1.hashCode(), configuration2.hashCode());
        Assert.assertNotEquals(true, configuration1.hashCode() == configuration3.hashCode());
        Assert.assertNotEquals(true, configuration1.hashCode() == configuration4.hashCode());

        Assert.assertNotEquals(true, configuration1 == null);

        configuration4.setId(PDID);
        Assert.assertNotEquals(true, configuration1.equals(configuration4));
        configuration1.toString();
        configuration3.setPdid(null);
        Assert.assertNotEquals(true, configuration3.equals(configuration1));
    }
}