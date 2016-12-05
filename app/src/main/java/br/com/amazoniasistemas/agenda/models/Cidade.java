package br.com.amazoniasistemas.agenda.models;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Cidade implements Parcelable {
    @Exclude
    public String key;
    public String nome;
    public String uf;

    public Cidade(String key, String nome, String uf) {
        this.key = key;
        this.nome = nome;
        this.uf = uf;
    }

    public Cidade() {

    }

    @Override
    public String toString() {
        return nome + " - " + uf;
    }



    //region Parcelable
    private Cidade(Parcel in) {
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
    //endregion
}
