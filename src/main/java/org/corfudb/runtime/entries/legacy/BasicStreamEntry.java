/**
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.corfudb.runtime.entries.legacy;


import org.corfudb.runtime.entries.IStreamEntry;
import org.corfudb.runtime.stream.*;

import java.io.Serializable;
import java.util.*;

public class BasicStreamEntry implements IStreamEntry, Serializable
{
    private ITimestamp logpos; //this doesn't have to be serialized, but leaving it in for debug purposes
    private Object payload;
    private List<UUID> streams;

    public Object getPayload() {
        return payload;
    }

    @Override
    public boolean containsStream(UUID streamid) {
        return getStreamIds().contains(streamid);
    }

    public List<UUID> getStreamIds() {
        return streams;
    }

    public BasicStreamEntry(Object tbs, ITimestamp position, Set<UUID> tstreams) {
        logpos = position;
        payload = tbs;
        streams = new ArrayList();
        streams.addAll(tstreams);
    }

    public BasicStreamEntry(Object tbs, ITimestamp position, List<UUID> tstreams) {
        logpos = position;
        payload = tbs;
        streams = tstreams;
    }

    public BasicStreamEntry(Object tbs, ITimestamp position, UUID tstreamid) {
        logpos = position;
        payload = tbs;
        streams = new ArrayList();
        streams.add(tstreamid);
    }

    /**
     * Gets the timestamp of the stream this entry belongs to.
     * @return The timestamp of the stream this entry belongs to.
     */
    public ITimestamp getTimestamp() { return logpos; }

    /**
     * Set the timestamp.
     * @param   ts  The timestamp of this entry.
     */
    public void setTimestamp(ITimestamp ts) { logpos = ts; }

}



