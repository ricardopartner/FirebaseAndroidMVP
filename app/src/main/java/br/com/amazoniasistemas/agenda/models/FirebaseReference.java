package br.com.amazoniasistemas.agenda.models;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

class FirebaseReference {
    private FirebaseDatabase database = FirebaseDatabase.getInstance();

    private DatabaseReference myRefContacts = database.getReference("contatos");

    DatabaseReference getContacts() {
        return myRefContacts;
    }
}
