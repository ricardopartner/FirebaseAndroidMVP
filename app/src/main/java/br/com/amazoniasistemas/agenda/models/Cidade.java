package br.com.amazoniasistemas.agenda.models;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.Exclude;

public class Cidade implements Parcelable {

    private String key;
    private String nome;
    private String uf;

    @Exclude
    public String getKey() {
        return key;
    }

    public String getNome() {
        return nome;
    }

    public String getUf() {
        return uf;
    }


    protected Cidade(Parcel in) {
        key = in.readString();
        nome = in.readString();
        uf = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(key);
        dest.writeString(nome);
        dest.writeString(uf);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Cidade> CREATOR = new Parcelable.Creator<Cidade>() {
        @Override
        public Cidade createFromParcel(Parcel in) {
            return new Cidade(in);
        }

        @Override
        public Cidade[] newArray(int size) {
            return new Cidade[size];
        }
    };
}
