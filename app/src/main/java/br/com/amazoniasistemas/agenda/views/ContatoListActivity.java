package br.com.amazoniasistemas.agenda.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

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

        ListView listView = (ListView) findViewById(R.id.contato_list_list);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(presenter.intentContato(i));
            }
        });


        this.presenter.startListening();
    }


    @Override
    protected void onDestroy() {
        this.presenter.stopListening();
        super.onDestroy();
    }
}
