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

package org.eclipse.ecsp.commons.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;


/**
 * JsonUtil class provides json utility methods for JSON to object conversion.
 */
public class JsonUtil {

    /**
     * ObjectMapper ref.
     */
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    /**
     * private default constructor.
     */
    private JsonUtil() {
    }

    /**
     * Converts a JSON file to a JsonNode object.
     *
     * @param fileName - the name of the JSON file.
     * @return - the JsonNode object representing the JSON file.
     * @throws IOException - if an I/O error occurs while reading the file.
     */
    public static JsonNode convertToJsonNode(String fileName) throws IOException {
        return OBJECT_MAPPER.readTree(IOUtils.toString(JsonUtil.class.getResourceAsStream(fileName), StandardCharsets.UTF_8));
    }

    /**
     * Converts a JSON file to a Map object.
     *
     * @param fileName - the name of the JSON file.
     * @return - the Map object representing the JSON file.
     * @throws IOException - if an I/O error occurs while reading the file.
     */
    @SuppressWarnings("rawtypes")
    public static Map convertToMap(String fileName) throws IOException {
        return OBJECT_MAPPER.readValue(IOUtils.toString(JsonUtil.class.getResourceAsStream(fileName), StandardCharsets.UTF_8),
            HashMap.class);
    }
}