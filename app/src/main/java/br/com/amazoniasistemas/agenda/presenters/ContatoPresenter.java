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

public class ContatoPresenter implements Observer {

    private final TextView fieldMessage;
    private final EditText fieldName;
    private final EditText fieldAddress;
    private final ContatoActivity contatoActivity;


    public ContatoPresenter(ContatoActivity contatoActivity) {

        this.contatoActivity = contatoActivity;

        this.fieldMessage = (TextView) contatoActivity.findViewById(R.id.contato_message);

        this.fieldName = (EditText) contatoActivity.findViewById(R.id.contato_field_name);
        this.fieldAddress = (EditText) contatoActivity.findViewById(R.id.contato_field_address);

        //Init();
    }


    private void Init() {
        Intent intent = contatoActivity.getIntent();

        String msg = intent.getStringExtra("message");
        this.fieldMessage.setText(msg);

        Contato contato = intent.getParcelableExtra("contato");

        if (contato != null) {
            this.fieldName.setText(contato.getName());
            this.fieldAddress.setText(contato.getAddress());
        }
    }


    public void merge() {
        new ContatoService(
                new Contato(
                        null,
                        this.fieldName.getText().toString(),
                        this.fieldAddress.getText().toString(),
                        0)
        ).merge();
    }

    @Override
    public void update(Observable observable, Object o) {

    }
}
