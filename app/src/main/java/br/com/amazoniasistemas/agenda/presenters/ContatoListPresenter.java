package br.com.amazoniasistemas.agenda.presenters;

import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Observable;
import java.util.Observer;

import br.com.amazoniasistemas.agenda.R;
import br.com.amazoniasistemas.agenda.models.Contato;
import br.com.amazoniasistemas.agenda.models.ContatoDao;
import br.com.amazoniasistemas.agenda.views.ContatoListActivity;

public class ContatoListPresenter implements Observer {

    private final ListView listView;
    private final ContatoDao contatoDao;
    private final ContatoListActivity contatoListActivity;

    public ContatoListPresenter(ContatoListActivity activity) {
        this.contatoListActivity = activity;
        this.listView = (ListView) activity.findViewById(R.id.contato_list_list);
        this.contatoDao = new ContatoDao();
        contatoDao.addObserver(this);
    }


    private void refreshListView() {
        ArrayAdapter<Contato> adapter = new ArrayAdapter<>(this.contatoListActivity, android.R.layout.simple_list_item_1,
                this.contatoDao.getContatos());
        this.listView.setAdapter(adapter);
    }

    @Override
    public void update(Observable observable, Object o) {
        this.refreshListView();
    }

    public void startListening(){
        this.contatoDao.startListening();
    }

    public void stopListening() {
        this.contatoDao.stopListening();
    }
}
