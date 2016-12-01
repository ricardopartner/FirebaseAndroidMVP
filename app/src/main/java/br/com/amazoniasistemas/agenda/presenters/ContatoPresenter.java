package br.com.amazoniasistemas.agenda.presenters;


import android.content.Intent;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Observable;
import java.util.Observer;

import br.com.amazoniasistemas.agenda.R;
import br.com.amazoniasistemas.agenda.models.Contato;
import br.com.amazoniasistemas.agenda.models.ContatoService;
import br.com.amazoniasistemas.agenda.views.ContatoActivity;

public class ContatoPresenter {

    private final TextView fieldMessage;
    private final EditText fieldName;
    private final EditText fieldAddress;
    private final ContatoActivity contatoActivity;
    private Contato contato;

    public ContatoPresenter(ContatoActivity contatoActivity) {

        this.contatoActivity = contatoActivity;

        this.fieldMessage = (TextView) contatoActivity.findViewById(R.id.contato_message);

        this.fieldName = (EditText) contatoActivity.findViewById(R.id.contato_field_name);
        this.fieldAddress = (EditText) contatoActivity.findViewById(R.id.contato_field_address);

        Init();
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
        this.contato.setName(this.fieldName.getText().toString());
        this.contato.setAddress(this.fieldAddress.getText().toString());
        new ContatoService(this.contato).merge();
    }

}
