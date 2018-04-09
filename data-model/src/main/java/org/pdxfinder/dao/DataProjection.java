package org.pdxfinder.dao;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

/**
 * DataProjection represents a node used to store a pre-computed view of the data.
 *
 * <p>Some queries can be computationally expensive to calculate and so the value can be pre-computed and
 * a DataProjection node may be used to cache the value in permanent storage, obviating the need
 * to perform the query at runtime.</p>
 *
 * <p>The value field can contain a serialized (e.g., JSON) representation of the result.</p>
 */
@NodeEntity
public class DataProjection {

    @GraphId
    private Long id;

    private String label;

    private String value;


    public DataProjection() {
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
