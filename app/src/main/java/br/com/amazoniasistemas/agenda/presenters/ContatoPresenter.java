package br.com.amazoniasistemas.agenda.presenters;


import android.content.Intent;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
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
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import java.util.Observable;
import java.util.Observer;

import br.com.amazoniasistemas.agenda.R;
import br.com.amazoniasistemas.agenda.models.Cidade;
import br.com.amazoniasistemas.agenda.models.Contato;
import br.com.amazoniasistemas.agenda.models.ContatoService;
import br.com.amazoniasistemas.agenda.views.ContatoActivity;

public class ContatoPresenter {

    private final TextView fieldMessage;
    private final EditText fieldName;
    private final EditText fieldAddress;
    private final AppCompatAutoCompleteTextView fieldCidade;
    private final ContatoActivity contatoActivity;
    private Contato contato;

    public ContatoPresenter(ContatoActivity contatoActivity) {

        this.contatoActivity = contatoActivity;

        this.fieldMessage = (TextView) contatoActivity.findViewById(R.id.contato_message);

        this.fieldName = (EditText) contatoActivity.findViewById(R.id.contato_field_name);
        this.fieldAddress = (EditText) contatoActivity.findViewById(R.id.contato_field_address);

        fieldCidade = (AppCompatAutoCompleteTextView) contatoActivity.findViewById(R.id.contato_field_cidade);

        fieldCidade();

        Init();

    }


    private void fieldCidade() {
        DatabaseReference db = FirebaseDatabase.getInstance().getReference("cities");

        db.keepSynced(true);

        final List<Cidade> cidades = new ArrayList<>();

        if (fieldCidade != null) {
            db.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(final DataSnapshot dataSnapshot) {

                    Runnable runnable = new Runnable() {
                        @Override
                        public void run() {
                            for (DataSnapshot i : dataSnapshot.getChildren()) {
                                Cidade c = i.getValue(Cidade.class);
                                c.setKey(i.getKey());
                                cidades.add(c);
                            }

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
            this.fieldName.setText(contato.getName());
            this.fieldAddress.setText(contato.getAddress());
        } else {
            this.contato = new Contato();
        }
    }


    public void merge() {

        new ContatoService(
                new Contato(
                        null,
                        this.fieldName.getText().toString(),
                        this.fieldAddress.getText().toString(),
                        0,
                        this.fieldCidade.getText().toString())
        ).merge();

        this.contato.setName(this.fieldName.getText().toString());
        this.contato.setAddress(this.fieldAddress.getText().toString());
        new ContatoService(this.contato).merge();

    }

}
