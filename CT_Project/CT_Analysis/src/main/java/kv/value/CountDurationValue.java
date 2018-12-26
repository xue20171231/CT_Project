package kv.value;

import kv.base.BaseValue;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Objects;

public class CountDurationValue extends BaseValue {
    private String callSum;
    private String callDurationSum;

    public CountDurationValue() {
    }

    public CountDurationValue(String callSum, String callDurationSum) {
        this.callSum = callSum;
        this.callDurationSum = callDurationSum;
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(callSum);
        dataOutput.writeUTF(callDurationSum);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        this.callSum = dataInput.readUTF();
        this.callDurationSum = dataInput.readUTF();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CountDurationValue that = (CountDurationValue) o;
        return Objects.equals(callSum, that.callSum) &&
                Objects.equals(callDurationSum, that.callDurationSum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(callSum, callDurationSum);
    }

    public String getCallSum() {
        return callSum;
    }

    public void setCallSum(String callSum) {
        this.callSum = callSum;
    }

    public String getCallDurationSum() {
        return callDurationSum;
    }

    public void setCallDurationSum(String callDurationSum) {
        this.callDurationSum = callDurationSum;
    }
}
