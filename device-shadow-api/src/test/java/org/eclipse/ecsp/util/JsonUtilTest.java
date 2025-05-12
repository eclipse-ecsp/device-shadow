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

import com.fasterxml.jackson.databind.JsonNode;
import org.eclipse.ecsp.commons.util.JsonUtil;
import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Objects;

/**
 * This class contains unit tests for the JsonUtil class.
 * It tests the functionality of converting JSON data to JsonNode and Map objects.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = {"spring.profiles.active = default"})
public class JsonUtilTest {

    private static final int TEST_MAP_SIZE3 = 3;
    private static final int TEST_MAP_SIZE5 = 5;

    /**
     * Test case for the {@link JsonUtil#convertToJsonNode(String)} method.
     * It tests the conversion of a JSON file to a JsonNode object.
     *
     * @throws IOException   if an I/O error occurs while reading the JSON file.
     * @throws JSONException if there is an error in the JSON structure.
     */
    @Test
    public void testConvertToJsonNode() throws IOException, JSONException {
        final String actualData =
            IOUtils.toString(
                Objects.requireNonNull(JsonUtil.class.getResourceAsStream("/device-config-expected-result.json")),
                StandardCharsets.UTF_8);
        JsonNode jsonNode = JsonUtil.convertToJsonNode("/device-config-expected-result.json");
        Assert.assertNotNull(jsonNode);
        Assert.assertTrue(jsonNode.isArray());
        JSONAssert.assertEquals(actualData, jsonNode.toString(), false);
        Assert.assertEquals(TEST_MAP_SIZE3, jsonNode.size());
    }

    /**
     * Test case to verify the conversion of JSON data to a Map.
     *
     * @throws IOException   if there is an error reading the JSON file.
     * @throws JSONException if there is an error parsing the JSON data.
     */
    @Test
    public void testConvertToMap() throws IOException, JSONException {
        Map jsonMap = JsonUtil.convertToMap("/testData-Geofence.json");
        Assert.assertNotNull(jsonMap);
        Assert.assertEquals(TEST_MAP_SIZE5, jsonMap.size());
    }
}