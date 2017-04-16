// License: GPL. For details, see LICENSE file.
package org.openstreetmap.josm.data.osm.event;

import java.util.Collection;

import org.openstreetmap.josm.data.osm.DataSet;
import org.openstreetmap.josm.data.osm.OsmPrimitive;

/**
 * Base class of all dataset change events.
 * @since 2622
 */
public abstract class AbstractDatasetChangedEvent {

    /**
     * Type of dataset changed event, returned by {@link AbstractDatasetChangedEvent#getType}.
     */
    public enum DatasetEventType {
        DATA_CHANGED,
        NODE_MOVED,
        PRIMITIVES_ADDED,
        PRIMITIVES_REMOVED,
        RELATION_MEMBERS_CHANGED,
        TAGS_CHANGED,
        WAY_NODES_CHANGED,
        CHANGESET_ID_CHANGED,
        PRIMITIVE_FLAGS_CHANGED,
    }

    /**
     * The dataset from which the event came from.
     */
    protected final DataSet dataSet;

    /**
     * Constructs a new {@code AbstractDatasetChangedEvent}.
     * @param dataSet the dataset from which the event came from
     */
    protected AbstractDatasetChangedEvent(DataSet dataSet) {
        this.dataSet = dataSet;
    }

    /**
     * Calls the appropriate method of the listener for this event.
     * @param listener dataset listener to notify about this event
     */
    public abstract void fire(DataSetListener listener);

    /**
     * Returns list of primitives modified by this event.
     * <br>
     * <strong>WARNING</strong> This value might be incorrect in case
     * of {@link DataChangedEvent}. It returns all primitives in the dataset
     * when this method is called (live list), not list of primitives when
     * the event was created
     * @return List of modified primitives
     */
    public abstract Collection<? extends OsmPrimitive> getPrimitives();

    /**
     * Returns the dataset from which the event came from.
     * @return the dataset from which the event came from
     */
    public DataSet getDataset() {
        return dataSet;
    }

    /**
     * Returns the type of dataset changed event.
     * @return the type of dataset changed event
     */
    public abstract DatasetEventType getType();

    @Override
    public String toString() {
        return getType().toString();
    }

}
