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

package org.eclipse.ecsp.entities;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * The Configuration class represents a configuration entity in the device shadow system.
 * It contains information about the device ID, upload timestamp, payload, and deletion status.
 */
@Getter
@Setter
@SuppressWarnings("rawtypes")
@Entity(value = "deviceshadow", useDiscriminator = true,
    discriminator = "org.eclipse.ecsp.devicemessage.entities.DeviceShadow")
public class Configuration extends AbstractIgniteEntity {

    private static final int TIMESTAMP_32 = 32;
    private static final int ISDELETED1231 = 1231;
    private static final int ISDELETED1237 = 1237;

    /**
     * mongo _id.
     */
    @Id
    private String id;

    /**
     * device id.
     */

    private String pdid;

    /**
     * upload TimeStamp.
     */

    private long uploadTimeStamp;

    /**
     * upload timestamp.
     */

    private boolean isDeleted;

    /**
     * payload.
     */

    private Map payload = new HashMap();

    /**
     * Represents a configuration for a device.
     */
    public Configuration() {
        super();
    }

    /**
     * Represents a configuration object.
     */
    public Configuration(String pdid, long uploadTimeStamp, Map payload) {
        super();
        this.pdid = pdid;
        this.uploadTimeStamp = uploadTimeStamp;
        this.payload = payload;
    }

    /**
        * Returns a hash code value for the object. This method is used by the hashing
        * algorithms, such as HashMap, HashSet, etc., to determine the bucket index for
        * storing the object.
        *
        * @return the hash code value for the object.
        */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + (isDeleted ? ISDELETED1231 : ISDELETED1237);
        result = prime * result + ((payload == null) ? 0 : payload.hashCode());
        result = prime * result + ((pdid == null) ? 0 : pdid.hashCode());
        result = prime * result + (int) (uploadTimeStamp ^ (uploadTimeStamp >>> TIMESTAMP_32));
        return result;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param obj the reference object with which to compare
     * @return {@code true} if this object is the same as the obj argument; {@code false} otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Configuration other = (Configuration) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        if (isDeleted != other.isDeleted) {
            return false;
        }
        if (payload == null) {
            if (other.payload != null) {
                return false;
            }
        } else if (!payload.equals(other.payload)) {
            return false;
        }
        if (pdid == null) {
            if (other.pdid != null) {
                return false;
            }
        } else if (!pdid.equals(other.pdid)) {
            return false;
        }
        return uploadTimeStamp == other.uploadTimeStamp;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Configuration [id=" + id + ", pdid=" + pdid + ", uploadTimeStamp=" + uploadTimeStamp + ", isDeleted="
            + isDeleted + ", payload=" + payload + "]";
    }

}
