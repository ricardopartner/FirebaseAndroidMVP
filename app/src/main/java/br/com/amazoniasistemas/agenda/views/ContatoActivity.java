package br.com.amazoniasistemas.agenda.views;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import br.com.amazoniasistemas.agenda.R;
import br.com.amazoniasistemas.agenda.presenters.ContatoPresenter;

public class ContatoActivity extends AppCompatActivity {

    private ContatoPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contato);

        this.presenter = new ContatoPresenter(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_contato_form, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_contato_form_ok :
                this.presenter.merge();
                finish();
                break;
        }


        return super.onOptionsItemSelected(item);
    }
}
