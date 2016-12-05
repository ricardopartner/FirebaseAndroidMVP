package br.com.amazoniasistemas.agenda.models;

import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Observable;

import static android.content.ContentValues.TAG;

public class ContatoDao extends Observable {

    private FirebaseReference myRef = null;

    private List<Contato> contatos = null;

    public ContatoDao() {
        this.contatos = new ArrayList<>();
        this.myRef = new FirebaseReference();
    }

    public List<Contato> getContatos() {
        return contatos;
    }

    void insert(Contato contato) {
        this.myRef.getContacts().push().setValue(contato);
    }

    void update(Contato contato) {
        this.myRef.getContacts().child(contato.key).setValue(contato);
    }

    private ChildEventListener childEventListener = new ChildEventListener() {
        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {

            Contato contato = dataSnapshot.getValue(Contato.class);
            contato.key = dataSnapshot.getKey();
            contatos.add(contato);
            setChanged();
            notifyObservers();

        }

        @Override
        public void onChildChanged(DataSnapshot dataSnapshot, String previousChildName) {
            Log.d(TAG, "onChildChanged:" + dataSnapshot.getKey());
            Contato contato = dataSnapshot.getValue(Contato.class);
            contato.key = dataSnapshot.getKey();

            for (int i = 0; i < contatos.size(); i++) {
                if (contatos.get(i).compare(contato.key)) {
                    contatos.set(i, contato);
                    setChanged();
                    notifyObservers();
                    break;
                }
            }
        }

        @Override
        public void onChildRemoved(DataSnapshot dataSnapshot) {
            Log.d(TAG, "onChildRemoved:" + dataSnapshot.getKey());
            String key = dataSnapshot.getKey();

            for (int i = 0; i < contatos.size(); i++) {
                if (contatos.get(i).compare(key)) {
                    contatos.remove(i);
                    setChanged();
                    notifyObservers();
                    break;
                }
            }
        }

        @Override
        public void onChildMoved(DataSnapshot dataSnapshot, String previousChildName) {
            //Log.d(TAG, "onChildMoved:" + dataSnapshot.getKey());

            // A comment has changed position, use the key to determine if we are
            // displaying this comment and if so move it.
            //Comment movedComment = dataSnapshot.getValue(Comment.class);
            String commentKey = dataSnapshot.getKey();

            // ...
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            Log.w(TAG, "postComments:onCancelled", databaseError.toException());
            // Toast.makeText(mContext, "Failed to load comments.",
            //  Toast.LENGTH_SHORT).show();
        }
    };

    public void stopListening() {
        this.myRef.getContacts().removeEventListener(childEventListener);
    }

    public void startListening() {
        this.myRef.getContacts().addChildEventListener(childEventListener);
    }

}
