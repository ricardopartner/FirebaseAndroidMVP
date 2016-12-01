package br.com.amazoniasistemas.agenda.models;

import android.util.Log;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;


import static android.content.ContentValues.TAG;

class CidadeDao extends Observable {

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference("cidades");
    private List<Cidade> cidades = new ArrayList<>();
    private Cidade cidade;

    //protected Set<Observer> observers;

    public List<Cidade> getCidades() {
        return cidades;
    }


//    private void setCidades(Cidade cidade) {
//        this.cidades.add(cidade);
//
//    }

    public CidadeDao() {
        myRef.addChildEventListener(childEventListener);
        this.cidades = new ArrayList<>();
        Log.d(TAG, "===========> CidadeDao");
    }

//    @Override
//    public void addObserver(Observer o) {
//        observers.add(o);
//        super.addObserver(o);
//    }

//    @Override
//    public void notifyObservers() {
//        observers.notify();
//        super.notifyObservers();
//    }

//    @Override
//    public synchronized void deleteObserver(Observer o) {
//        observers.remove(o);
//        super.deleteObserver(o);
//    }



    private ChildEventListener childEventListener = new ChildEventListener() {
        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
            Log.d(TAG, "onChildAdded:" + dataSnapshot.getValue());
            Cidade cidade = dataSnapshot.getValue(Cidade.class);
            notifyObservers();
            //setCidades(cidade);

            // A new comment has been added, add it to the displayed list
            // Comment comment = dataSnapshot.getValue(Comment.class);

            // ...
        }

        @Override
        public void onChildChanged(DataSnapshot dataSnapshot, String previousChildName) {
            Log.d(TAG, "onChildChanged:" + dataSnapshot.getKey());

            // A comment has changed, use the key to determine if we are displaying this
            // comment and if so displayed the changed comment.
            //   Comment newComment = dataSnapshot.getValue(Comment.class);
            String commentKey = dataSnapshot.getKey();

            // ...
        }

        @Override
        public void onChildRemoved(DataSnapshot dataSnapshot) {
            Log.d(TAG, "onChildRemoved:" + dataSnapshot.getKey());

            // A comment has changed, use the key to determine if we are displaying this
            // comment and if so remove it.
            String commentKey = dataSnapshot.getKey();

            // ...
        }

        @Override
        public void onChildMoved(DataSnapshot dataSnapshot, String previousChildName) {
            Log.d(TAG, "onChildMoved:" + dataSnapshot.getKey());

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


//
//    public Cidade get(String cidadeId) {
//        myRef.child(cidadeId).addListenerForSingleValueEvent(new ValueEventListener() {
//
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                Log.d(TAG, "onChildAddedxx:" + dataSnapshot.getValue());
//                cidade = dataSnapshot.getValue(Cidade.class);
//
//                cidades.add(cidade);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//
//        return (Cidade) cid;
//    }

}
