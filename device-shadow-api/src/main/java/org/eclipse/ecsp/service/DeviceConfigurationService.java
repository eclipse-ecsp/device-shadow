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

package org.eclipse.ecsp.service;

import org.eclipse.ecsp.commons.dao.impl.DeviceShadowDaoImpl;
import org.eclipse.ecsp.entities.Configuration;
import org.eclipse.ecsp.utils.logger.IgniteLogger;
import org.eclipse.ecsp.utils.logger.IgniteLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * This class represents a service for retrieving device configurations.
 * It provides methods to fetch configurations based on ID and timestamp.
 */
@Service
public class DeviceConfigurationService {

    private static final IgniteLogger LOGGER = IgniteLoggerFactory.getLogger(DeviceConfigurationService.class);

    @Autowired
    private DeviceShadowDaoImpl deviceShadowDaoImpl;

    @Value("${fetch.data.by.timestamp.greaterThan:false}")
    private boolean fetchDataByTimestampGreaterThan;

    /**
     * Retrieves the configuration data for a specific device based on the provided ID and timestamp.
     *
     * @param id        The ID of the device.
     * @param timeStamp The timestamp indicating the point in time from which to retrieve the configuration data.
     * @return A list of configuration data maps for the device.
     */
    @SuppressWarnings("rawtypes")
    public List<Map> getConfig(String id, long timeStamp) {
        List<Configuration> configList = null;
        LOGGER.info("in getConfig with flag fetchDataByTimestampGreaterThan:{}", fetchDataByTimestampGreaterThan);
        if (fetchDataByTimestampGreaterThan) {
            configList = deviceShadowDaoImpl.findByPdidAndPayloadTimeStampGreaterThan(id, timeStamp);
        } else {
            configList = deviceShadowDaoImpl.findByPdidAndUploadTimeStampGreaterThanEqual(id, timeStamp);
        }
        List<Map> dataList = new ArrayList<>();
        if (configList != null && !configList.isEmpty()) {
            configList.forEach(data -> dataList.add(data.getPayload()));
        }
        return dataList;
    }
}