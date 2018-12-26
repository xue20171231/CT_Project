package kv.key;

import kv.base.BaseDimension;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Objects;

public class ComDimension extends BaseDimension {
    //时间维度
    private DateDimension dataDimen =  new DateDimension();
    //联系人维度
    private ContactDimension contactDimen = new ContactDimension();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComDimension that = (ComDimension) o;
        return Objects.equals(dataDimen, that.dataDimen) &&
                Objects.equals(contactDimen, that.contactDimen);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dataDimen, contactDimen);
    }

    @Override
    public int compareTo(BaseDimension o) {
        //先时间后联系人
        ComDimension anotherComDimension = (ComDimension) o;
        int result = this.dataDimen.compareTo(anotherComDimension.dataDimen);
        if (result != 0) return result;

        result = this.contactDimen.compareTo(anotherComDimension.contactDimen);
        return result;
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataDimen.write(dataOutput);
        contactDimen.write(dataOutput);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        dataDimen.readFields(dataInput);
        contactDimen.readFields(dataInput);
    }

    public DateDimension getDataDimen() {
        return dataDimen;
    }

    public void setDataDimen(DateDimension dataDimen) {
        this.dataDimen = dataDimen;
    }

    public ContactDimension getContactDimen() {
        return contactDimen;
    }

    public void setContactDimen(ContactDimension contactDimen) {
        this.contactDimen = contactDimen;
    }
}
