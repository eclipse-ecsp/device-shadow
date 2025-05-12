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

package org.eclipse.ecsp.commons.dao.impl;

import org.eclipse.ecsp.commons.constants.DeviceConfigConstants;
import org.eclipse.ecsp.commons.dao.DeviceShadowDao;
import org.eclipse.ecsp.entities.Configuration;
import org.eclipse.ecsp.nosqldao.IgniteCriteria;
import org.eclipse.ecsp.nosqldao.IgniteCriteriaGroup;
import org.eclipse.ecsp.nosqldao.IgniteQuery;
import org.eclipse.ecsp.nosqldao.Operator;
import org.eclipse.ecsp.nosqldao.mongodb.IgniteBaseDAOMongoImpl;
import org.eclipse.ecsp.utils.logger.IgniteLogger;
import org.eclipse.ecsp.utils.logger.IgniteLoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;


/**
 * Implementation of the DeviceShadowDao interface.
 * This class provides methods to retrieve configurations from the database based on certain criteria.
 */
@Repository
public class DeviceShadowDaoImpl extends IgniteBaseDAOMongoImpl<String, Configuration> implements DeviceShadowDao {

    private static final IgniteLogger LOGGER = IgniteLoggerFactory.getLogger(DeviceShadowDaoImpl.class);

    /**
     * Finds a list of Configuration objects by the specified pdid and upload timestamp greater than or equal
     * to the given value.
     *
     * @param pdid The pdid to search for.
     * @param uploadTimeStamp The upload timestamp to compare against.
     * @return A list of Configuration objects that match the specified criteria.
     */
    public List<Configuration> findByPdidAndUploadTimeStampGreaterThanEqual(String pdid, long uploadTimeStamp) {

        IgniteCriteria pdidCriteria = new IgniteCriteria(DeviceConfigConstants.PDID, Operator.EQ, pdid);
        IgniteCriteria uploadTimeStampCriteria =
            new IgniteCriteria(DeviceConfigConstants.UPLOADTIMESTAMP, Operator.GTE, uploadTimeStamp);
        IgniteQuery igniteQuery = new IgniteQuery(new IgniteCriteriaGroup(pdidCriteria).and(uploadTimeStampCriteria));
        return find(igniteQuery);
    }


    /**
     * Finds a list of configurations by the specified pdid and payload timestamp greater than the given value.
     *
     * @param pdid              the pdid to search for
     * @param payloadTimestamp  the payload timestamp to compare against
     * @return                  a list of configurations matching the criteria, or an empty list if none found
     */
    public List<Configuration> findByPdidAndPayloadTimeStampGreaterThan(String pdid, long payloadTimestamp) {
        try {
            IgniteCriteria pdidCriteria = new IgniteCriteria(DeviceConfigConstants.PDID, Operator.EQ, pdid);
            IgniteCriteria uploadTimeStampCriteria =
                new IgniteCriteria(DeviceConfigConstants.PAYLOAD_TIMESTAMP, Operator.GT, payloadTimestamp);
            IgniteQuery igniteQuery =
                new IgniteQuery(new IgniteCriteriaGroup(pdidCriteria).and(uploadTimeStampCriteria));
            return find(igniteQuery);
        } catch (Exception e) {
            LOGGER.error("Error in findByPdidAndPayloadTimeStampGreaterThan:{}", e);
        }
        return Collections.emptyList();
    }
}