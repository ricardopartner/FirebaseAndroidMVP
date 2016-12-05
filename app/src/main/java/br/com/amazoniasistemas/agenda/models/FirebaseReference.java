package br.com.amazoniasistemas.agenda.models;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

class FirebaseReference {
    private FirebaseDatabase database = FirebaseDatabase.getInstance();

    private DatabaseReference myRefContacts = database.getReference("contatos");
    private DatabaseReference myRefCities = database.getReference("cities");

    DatabaseReference getContacts() {
        return myRefContacts;
    }

    public DatabaseReference getmyRefCities() {
        return myRefCities;
    }
}
