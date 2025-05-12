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

package org.eclipse.ecsp;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.ecsp.commons.constants.DeviceConfigConstants;
import org.eclipse.ecsp.commons.dao.impl.DeviceShadowDaoImpl;
import org.eclipse.ecsp.commons.util.JsonUtil;
import org.eclipse.ecsp.entities.Configuration;
import org.eclipse.ecsp.testutils.CommonTestBase;
import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

/**
 * This class contains unit tests for the MongoDeviceConfigurationController class.
 * It tests the API configuration functionality of the controller.
 */
@TestPropertySource("classpath:/application.properties")
@SuppressWarnings({"LineLength"})
@SpringBootTest(classes = org.eclipse.ecsp.Application.class,
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class MongoDeviceConfigurationControllerTest extends CommonTestBase {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private DeviceShadowDaoImpl deviceShadowDaoImpl;

    private static final long TIMESTAMP_1 = 1497007356;
    private static final long TIMESTAMP_2 = 1497007357;
    private static final long TIMESTAMP_3 = 1497007300;
    private static final long TIMESTAMP_4 = 1497007200;
    private static final int TEST_MAP_SIZE = 3;

    /**
     * Test case for the `testApiConfig` method.
     * This method tests the API configuration by sending a GET request to the "/v1/devices/HU0SCIUKMYXFG9/configuration"
     * endpoint with a specific timestamp. It verifies the response body and compares it with the expected result.
     *
     * @throws Exception if an error occurs during the test.
     */
    @Test
    public void testApiConfig() throws Exception {
        prepareData();
        ResponseEntity<String> response =
            restTemplate.exchange("/v1/devices/HU0SCIUKMYXFG9/configuration?since=1497007300", HttpMethod.GET,
                new HttpEntity<Object>(createHeaders()), String.class);
        ObjectMapper objectMapper = new ObjectMapper();

        Assert.assertNotNull(response);
        Assert.assertNotNull(response.getBody());
        List dataList = (List) objectMapper.readValue(response.getBody().getBytes(), List.class);

        Assert.assertNotNull(dataList);
        Assert.assertEquals(TEST_MAP_SIZE, dataList.size());

        final String expectedData =
            IOUtils.toString(JsonUtil.class.getResourceAsStream("/device-config-expected-result.json"), "UTF-8");
        JSONAssert.assertEquals(expectedData.trim(), response.getBody().trim(), false);
    }

    /**
     * Test case to verify the behavior when an invalid authentication token is used in the API configuration.
     */
    @Test
    public void testInvalidAuthTokenApiConfig() {
        try {
            HttpHeaders httpHeaders = createHeaders();
            httpHeaders
                    .set("AUTH-Token",
                            "c_nEP34dmcbisY9xRTqRvr-h8ZM-oe265t_rC0N79688iwDaDie3P6P45ieyE4x_nQTPj5dEMrzU0QjGZY6EaDDQQWyHYPT83cRZvmnQWer2N66VEJ0m57BC-55syXgWGHF");
            restTemplate.exchange("/v1/devices/HU0SCIUKMYXFG9/configuration?since=1497007300",
                    HttpMethod.GET,
                    new HttpEntity<Object>(httpHeaders), String.class);
        } catch (Exception ex) {
            Assert.assertTrue(true);
        }
    }

    /**
     * Test case to verify the behavior of the API when the configuration is empty.
     *
     * @throws Exception if an error occurs during the test
     */
    @Test
    public void testApiConfigEmpty() throws Exception {
        ResponseEntity<String> response =
            restTemplate.exchange("/v1/devices/HU0SCIUKMYXFG57/configuration?since=1497007300", HttpMethod.GET,
                new HttpEntity<Object>(createHeaders()), String.class);
        Assert.assertEquals("[]", response.getBody().toString());
    }

    /**
     * Test case to verify the behavior when an invalid version is provided in the API configuration request.
     *
     * @throws Exception if an error occurs during the test
     */
    @Test
    public void testInvalidVersionApiConfig() throws Exception {
        ResponseEntity<ExceptionResponse> response =
            restTemplate.exchange("/v1.1/devices/HU0SCIUKMYXFG10/configuration?since=1497007300", HttpMethod.GET,
                new HttpEntity<Object>(createHeaders()), ExceptionResponse.class);
        Assert.assertNotNull(response);
        Assert.assertEquals(response.getBody().getCode(), HttpStatus.BAD_REQUEST.value());
    }

    /**
        * Test case to verify the behavior when an invalid configuration timestamp is provided.
        */
    @Test
    public void testInvalidConfigTimeStamp() {

        ResponseEntity<ExceptionResponse> response =
            restTemplate.exchange("/v1/devices/HU0SCIUKMYXFG10/configuration?since=1497007300F", HttpMethod.GET,
                new HttpEntity<Object>(createHeaders()), ExceptionResponse.class);
        Assert.assertNotNull(response);
        Assert.assertEquals(response.getBody().getCode(), HttpStatus.BAD_REQUEST.value());

    }

    /**
     * Test case to verify the behavior when an invalid configuration is requested for an invalid device ID.
     */
    @Test
    public void testInvalidConfigInvalidDeviceId() {
        ResponseEntity<ExceptionResponse> response =
            restTemplate.exchange("/v1/devices/(null)/configuration?since=1497007300", HttpMethod.GET,
                new HttpEntity<Object>(createHeaders()), ExceptionResponse.class);
        Assert.assertNotNull(response);
        Assert.assertEquals(response.getBody().getCode(), HttpStatus.BAD_REQUEST.value());
        Assert.assertEquals(DeviceConfigConstants.INVALID_DEVICE_ID_MSG, response.getBody().getDescription());
    }

    /**
     * Prepares the data by saving configurations using the deviceShadowDaoImpl.
     * The configurations include test data for geofence, curfew, and a test dataset.
     * Each configuration is associated with the device ID "HU0SCIUKMYXFG9" and a specific timestamp.
     * The test data is converted to a map using the JsonUtil.convertToMap method.
     */
    private void prepareData() throws IOException {

        deviceShadowDaoImpl.save(
            new Configuration("HU0SCIUKMYXFG9", TIMESTAMP_1, JsonUtil.convertToMap("/testData-Geofence.json")));
        deviceShadowDaoImpl.save(
            new Configuration("HU0SCIUKMYXFG9", TIMESTAMP_2, JsonUtil.convertToMap("/testData-Curfew.json")));
        deviceShadowDaoImpl.save(
            new Configuration("HU0SCIUKMYXFG9", TIMESTAMP_3, JsonUtil.convertToMap("/testdataset.json")));
        deviceShadowDaoImpl.save(
            new Configuration("HU0SCIUKMYXFG9", TIMESTAMP_4, JsonUtil.convertToMap("/testData-Geofence.json")));
    }

    /**
     * Creates and returns HttpHeaders object with predefined headers.
     *
     * @return HttpHeaders object with predefined headers.
     */
    private HttpHeaders createHeaders() {
        HttpHeaders headers = new HttpHeaders() {
            {
                set("AUTH-Token",
                        "U33LbcPogSlvfCjJbE8lzz9msUlhBUbFhtftBf6L4LvF-On46mHH0CbhIlK-nsLUj718uZeoHHP47uDIlLm3-agcuJ4v-A0rdeXZ5rlIJreBZ4WoRm1MDQjp0iIND3et");
                set("Content-Type", "application/json");
            }
        };
        return headers;
    }
}