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

package org.eclipse.ecsp.commons.dao;

import org.eclipse.ecsp.entities.Configuration;
import org.eclipse.ecsp.nosqldao.IgniteBaseDAO;

/**
 * The DeviceShadowDao interface represents a data access object for managing device shadow configurations.
 * It extends the IgniteBaseDAO interface and provides methods for CRUD operations on device shadow configurations.
 *
 * @param <String>         the type of the key used for device shadow configurations
 * @param <Configuration>  the type of the device shadow configuration
 */
public interface DeviceShadowDao extends IgniteBaseDAO<String, Configuration> {
}