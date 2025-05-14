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

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;

/**
 * This class contains unit tests for the DeviceShadowDaoImpl class.
 */
@RunWith(MockitoJUnitRunner.class)
public class DeviceShadowDaoImplTest {

    private static final long TIMESTAMP = 1683097568000L;
    @InjectMocks
    private DeviceShadowDaoImpl deviceShadowDaoImpl;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
        * Test case for the findByPdidAndPayloadTimeStampGreaterThan method.
        * Verifies that the method returns the expected result when searching for device shadows
        * with a given pdid and payload timestamp greater than a specified value.
        */
    @Test
    public void findByPdidAndPayloadTimeStampGreaterThanTest() {
        String pdid = "HUI5MQZKU15144";
        DeviceShadowDaoImpl test = new DeviceShadowDaoImpl();
        DeviceShadowDaoImpl spyTest = Mockito.spy(test);
        Mockito.doReturn(new ArrayList<>()).when(spyTest).find(Mockito.any());
        Assert.assertEquals(0, spyTest.findByPdidAndPayloadTimeStampGreaterThan(pdid, TIMESTAMP).size());
    }

    /**
        * Test case for the findByPdidAndUploadTimeStampGreaterThanEqual method.
        * It verifies the behavior of the method when searching for device shadows
        * with a given pdid and upload timestamp greater than or equal to a specified value.
        */
    @Test
    public void findByPdidAndUploadTimeStampGreaterThanEqualTest() {
        String pdid = "HUI5MQZKU15144";
        DeviceShadowDaoImpl test = new DeviceShadowDaoImpl();
        DeviceShadowDaoImpl spyTest = Mockito.spy(test);
        Mockito.doReturn(new ArrayList<>()).when(spyTest).find(Mockito.any());
        spyTest.findByPdidAndUploadTimeStampGreaterThanEqual(pdid, TIMESTAMP);
        Assert.assertEquals(0, spyTest.findByPdidAndPayloadTimeStampGreaterThan(pdid, TIMESTAMP).size());
    }
}