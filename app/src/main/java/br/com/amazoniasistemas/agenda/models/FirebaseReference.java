package br.com.amazoniasistemas.agenda.models;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseReference {
    private FirebaseDatabase database = FirebaseDatabase.getInstance();

    private DatabaseReference myRefContacts = database.getReference("contatos");

    public DatabaseReference getContacts() {
        return myRefContacts;
    }
}
