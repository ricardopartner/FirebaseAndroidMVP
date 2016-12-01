package br.com.amazoniasistemas.agenda.views;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import br.com.amazoniasistemas.agenda.R;
import br.com.amazoniasistemas.agenda.models.Contato;
import br.com.amazoniasistemas.agenda.presenters.MainPresenter;

public class MainActivity extends AppCompatActivity {

    private MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.presenter = new MainPresenter(this);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Hello, World!");


        Button buttonPrincipal = (Button) findViewById(R.id.main_button_principal);
        buttonPrincipal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ContatoListActivity.class);
                intent.putExtra("message", presenter.message());
                startActivity(intent);
            }
        });
    }
}
