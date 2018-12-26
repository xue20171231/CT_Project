package kv.base;

import org.apache.hadoop.io.WritableComparable;

public abstract class BaseDimension implements WritableComparable<BaseDimension> {
    public abstract int compareTo(BaseDimension o);
}
