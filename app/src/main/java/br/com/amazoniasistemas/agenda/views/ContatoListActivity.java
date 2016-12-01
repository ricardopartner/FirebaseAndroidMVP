package br.com.amazoniasistemas.agenda.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import br.com.amazoniasistemas.agenda.R;
import br.com.amazoniasistemas.agenda.presenters.ContatoListPresenter;

public class ContatoListActivity extends AppCompatActivity {

    private ContatoListPresenter presenter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contato_list);

        this.presenter = new ContatoListPresenter(this);

        Button novoContato = (Button) findViewById(R.id.contato_list_novo);
        novoContato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ContatoListActivity.this, ContatoActivity.class);
                startActivity(intent);
            }
        });

        Log.i("Teste", "onCreate");
        this.presenter.startListening();
    }


    @Override
    protected void onDestroy() {
        Log.i("Teste", "onDestroy()");
        this.presenter.stopListening();
        super.onDestroy();
    }
}
