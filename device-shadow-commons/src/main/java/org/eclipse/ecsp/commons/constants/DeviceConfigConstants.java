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

package org.eclipse.ecsp.commons.constants;

/**
 * The `DeviceConfigConstants` class contains constants related to device configuration.
 * It provides constant values for various properties and messages used in the application.
 */
public class DeviceConfigConstants {

    /**
     * Unsupported api version message constant.
     */
    public static final String UNSUPPORTED_API_VERSION_MSG = "Unsupported api version received in URI";
    /**
     * Device role constant.
     */
    public static final String DEVICE_ROLE = "ROLE_DEVICE";
    /**
     * Invalid device id message constant.
     */
    public static final String INVALID_DEVICE_ID_MSG = "Received invalid device id in URI";
    /**
     * Dynamo constant.
     */
    public static final String DYNAMO = "dynamo";
    /**
     * Api repository selector constant.
     */
    public static final String REPOSITORYSELECTOR = "API_REPOSITORY_SELECTOR";
    /**
     * Api execution env constant.
     */
    public static final String API_EXECUTION_ENV_PROP = "API_EXECUTION_ENV";
    /**
     * Lambda constant.
     */
    public static final String LAMBDA = "lambda";
    /**
     * haa prefix constant.
     */
    public static final String HAA_PREFIX = "haa-";
    /**
     * test case execution prefix constant.
     */
    public static final String TEST_CASE_EXECUTION_PREFIX = "test-";
    public static final String DEFAULT = "default";
    public static final String HYPHEN = "-";
    public static final String DOT = "\\.";
    public static final String UNDERSCORE = "_";
    public static final String HIDDEN_PASS = "********";
    public static final String DEVCIE_AUTHTOKEN_KEY = "tenant_env_auth_key";

    public static final String PDID = "pdid";
    public static final String UPLOADTIMESTAMP = "uploadTimeStamp";
    public static final String PAYLOAD_TIMESTAMP = "payload.timestamp";

    /**
     * private default constructor.
     */
    private DeviceConfigConstants() {
    }
}
