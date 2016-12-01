package br.com.amazoniasistemas.agenda.presenters;


import android.widget.EditText;

import br.com.amazoniasistemas.agenda.R;
import br.com.amazoniasistemas.agenda.views.MainActivity;

public class MainPresenter {

    private final EditText fieldMsg;

    public MainPresenter(MainActivity mainActivity) {
        fieldMsg = (EditText) mainActivity.findViewById(R.id.main_field_msg);
    }

    public String message(){
        return fieldMsg.getText().toString();
    }

}
