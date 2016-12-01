package br.com.amazoniasistemas.agenda.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.Exclude;


public class Contato implements Parcelable {

    private String key;
    private String name;
    private String address;
    private double salary;

    public Contato() {
    }

    public Contato(String key, String name, String address, double salary) {
        this.key = key;
        this.name = name;
        this.address = address;
        this.salary = salary;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Exclude
    public String getKey() {
        return key;
    }

    public boolean compare(String key) {
         return this.key.equals(key);
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return this.name;
    }

    private Contato(Parcel in) {
        key = in.readString();
        name = in.readString();
        address = in.readString();
        salary = in.readDouble();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(key);
        dest.writeString(name);
        dest.writeString(address);
        dest.writeDouble(salary);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Contato> CREATOR = new Parcelable.Creator<Contato>() {
        @Override
        public Contato createFromParcel(Parcel in) {
            return new Contato(in);
        }

        @Override
        public Contato[] newArray(int size) {
            return new Contato[size];
        }
    };


}
