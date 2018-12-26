package kv.key;

import kv.base.BaseDimension;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Objects;

public class ContactDimension extends BaseDimension {

    private String telephone;
    private String name;

    public ContactDimension() {

    }

    public ContactDimension(String telephone, String name) {
        this.telephone = telephone;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactDimension that = (ContactDimension) o;
        return Objects.equals(telephone, that.telephone) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(telephone, name);
    }

    @Override
    public int compareTo(BaseDimension o) {
        //先比名后比电话号
        ContactDimension anotherContactDimension = (ContactDimension) o;
        int result = this.name.compareTo(anotherContactDimension.name);
        if (result != 0) return result;

        result = this.telephone.compareTo(anotherContactDimension.telephone);
        return result;
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(this.telephone);
        dataOutput.writeUTF(this.name);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        this.telephone = dataInput.readUTF();
        this.name = dataInput.readUTF();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
