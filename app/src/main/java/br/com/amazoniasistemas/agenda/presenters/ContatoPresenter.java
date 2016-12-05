package br.com.amazoniasistemas.agenda.presenters;


import android.content.Intent;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import br.com.amazoniasistemas.agenda.R;
import br.com.amazoniasistemas.agenda.models.Cidade;
import br.com.amazoniasistemas.agenda.models.CidadeDao;
import br.com.amazoniasistemas.agenda.models.Contato;
import br.com.amazoniasistemas.agenda.models.ContatoService;
import br.com.amazoniasistemas.agenda.views.ContatoActivity;

import static android.content.ContentValues.TAG;

public class ContatoPresenter implements Observer {

    private final EditText fieldName;
    private final EditText fieldAddress;
    private final AppCompatAutoCompleteTextView fieldCidade;
    private final ContatoActivity contatoActivity;
    private Contato contato;
    private CidadeDao cidadeDao = null;

    public ContatoPresenter(ContatoActivity contatoActivity) {
//        this.cidadeDao = new CidadeDao();
//        this.cidadeDao.addObserver(this);

        Calendar c1 = Calendar.getInstance();
        long seconds1 = c1.getTimeInMillis();
        Log.d(TAG, "===inicio=============>: " + seconds1);

        this.contatoActivity = contatoActivity;

        this.fieldName = (EditText) contatoActivity.findViewById(R.id.contato_field_name);
        this.fieldAddress = (EditText) contatoActivity.findViewById(R.id.contato_field_address);

        fieldCidade = (AppCompatAutoCompleteTextView) contatoActivity.findViewById(R.id.contato_field_cidade);

        fieldCidade();

        Init();


    }


    private void fieldCidade() {
//        Log.d(TAG, "fieldCidade(): ====>>>>" );
//        ArrayAdapter<Cidade> adapter = new ArrayAdapter<>(contatoActivity, R.layout.support_simple_spinner_dropdown_item, cidadeDao.cities);
//        fieldCidade.setAdapter(adapter);
//        adapter.notifyDataSetChanged();
//
//                    fieldCidade.setAdapter(adapter);

        DatabaseReference db = FirebaseDatabase.getInstance().getReference("cities");

        //db.keepSynced(true);

        final List<Cidade> cidades = new ArrayList<>();

        if (fieldCidade != null) {
            db.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(final DataSnapshot dataSnapshot) {

                    Runnable runnable = new Runnable() {
                        @Override
                        public void run() {

//                            GenericTypeIndicator<HashMap<String, Cidade>> t = new GenericTypeIndicator<HashMap<String, Cidade>>() {
//                            };
//                            final HashMap<String, Cidade> hashMap = dataSnapshot.getValue(t);
//                            for (Map.Entry<String, Cidade> entry : hashMap.entrySet()) {
//                                cidades.add(new Cidade(entry.getKey(), entry.getValue().nome, entry.getValue().uf));
//                            }

                            for (DataSnapshot i : dataSnapshot.getChildren()) {
                                Cidade c = i.getValue(Cidade.class);
                                c.key = i.getKey();
                                cidades.add(c);
                            }

                            Calendar c2 = Calendar.getInstance();
                            long seconds2 = c2.getTimeInMillis();
                            Log.d(TAG, "===fim================>: " + seconds2);

                        }
                    };

                    new Thread(runnable).start();

                    ArrayAdapter<Cidade> adapter = new ArrayAdapter<>(contatoActivity, R.layout.support_simple_spinner_dropdown_item, cidades);

                    fieldCidade.setAdapter(adapter);


                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
    }


    private void Init() {
        Intent intent = contatoActivity.getIntent();

        this.contato = intent.getParcelableExtra("contato");

        if (contato != null) {
            this.fieldName.setText(contato.name);
            this.fieldAddress.setText(contato.address);
        } else {
            this.contato = new Contato();
        }
    }


    public void merge() {
        this.contato.name = this.fieldName.getText().toString();
        this.contato.address = this.fieldAddress.getText().toString();
        this.contato.city = this.fieldAddress.getText().toString();

        new ContatoService(this.contato).merge();
    }

    @Override
    public void update(Observable observable, Object o) {
        fieldCidade();
    }
}
