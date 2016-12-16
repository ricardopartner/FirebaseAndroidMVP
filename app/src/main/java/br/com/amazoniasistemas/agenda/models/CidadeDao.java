package br.com.amazoniasistemas.agenda.models;


import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import static android.content.ContentValues.TAG;


public class CidadeDao extends Observable {

    private FirebaseReference myRef = null;
    public List<Cidade> cities = null;

    public CidadeDao() {
        this.cities = new ArrayList<>();
        this.myRef = new FirebaseReference();
        this.myRef.getmyRefCities().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d(TAG, "onDataChange: ====>>>>" );
                GenericTypeIndicator<HashMap<String, Cidade>> t = new GenericTypeIndicator<HashMap<String, Cidade>>() {
                };
                final HashMap<String, Cidade> hashMap = dataSnapshot.getValue(t);


                for (Map.Entry<String, Cidade> entry : hashMap.entrySet()) {
                    cities.add(new Cidade(entry.getKey(), entry.getValue().nome, entry.getValue().uf));
                    Log.d(TAG, "onDataChange: " + entry.getKey());
                }
                setChanged();
                notifyObservers("cidadedao");
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());

            }
        });
    }


}
