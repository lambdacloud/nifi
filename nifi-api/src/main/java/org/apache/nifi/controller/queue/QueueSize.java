/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.nifi.controller.queue;

import java.text.NumberFormat;

/**
 *
 */
public class QueueSize {

    private final int objectCount;
    private final long totalSizeBytes;

    public QueueSize(final int numberObjects, final long totalSizeBytes) {
        if (numberObjects < 0 || totalSizeBytes < 0) {
            throw new IllegalArgumentException();
        }
        objectCount = numberObjects;
        this.totalSizeBytes = totalSizeBytes;
    }

    /**
     * @return number of objects present on the queue
     */
    public int getObjectCount() {
        return objectCount;
    }

    /**
     * @return total size in bytes of the content for the data on the queue
     */
    public long getByteCount() {
        return totalSizeBytes;
    }

    /**
     * Returns a new QueueSize that is the sum of this QueueSize and the provided QueueSize
     *
     * @param other the other QueueSize to add to this QueueSize
     * @return a new QueueSize that is the sum of this QueueSize and the provided QueueSize
     */
    public QueueSize add(final QueueSize other) {
        if (other == null) {
            return new QueueSize(objectCount, totalSizeBytes);
        }

        return new QueueSize(objectCount + other.getObjectCount(), totalSizeBytes + other.getByteCount());
    }

    @Override
    public String toString() {
        return "QueueSize[FlowFiles=" + objectCount + ", ContentSize=" + NumberFormat.getNumberInstance().format(totalSizeBytes) + " Bytes]";
    }
}