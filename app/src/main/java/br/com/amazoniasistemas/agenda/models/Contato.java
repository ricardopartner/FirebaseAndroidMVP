package br.com.amazoniasistemas.agenda.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Contato implements Parcelable {
    @Exclude
    public String key;
    public String name;
    public String address;
    public double salary;
    public String city;


    public Contato() {
    }

    public Contato(String key, String name, String address, double salary, String city) {
        this.key = key;
        this.name = name;
        this.address = address;
        this.salary = salary;
        this.city = city;
    }


    boolean compare(String key) {
        return this.key.equals(key);
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
        city = in.readString();
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
        dest.writeString(city);
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
