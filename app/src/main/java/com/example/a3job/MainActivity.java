package com.example.a3job;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a3job.Classes.BatFamilia;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private EditText etNome, etIdade;
    private Button btnCadastrar;
    private DatabaseReference myRef;
    private BatFamilia batFamilia;
    private TextView txtNome, txtIdade;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        myRef = database.getReference();
        BatFamilia batFamilia = new BatFamilia();
        database = FirebaseDatabase.getInstance();
        etNome = findViewById(R.id.etNome);
        etIdade = findViewById(R.id.etIdade);
        btnCadastrar = findViewById(R.id.btnCadastrar);
        txtNome = findViewById(R.id.txtNome);
        txtIdade = findViewById(R.id.txtIdade);



        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CadastrarBatFamilia(etNome.getText().toString(),etIdade.getText().toString());

            }
        });
    }

public void CadastrarBatFamilia(String nome, String idade){

    String key = myRef.child("BatFamilia").push().getKey();
    batFamilia.setNome(nome);
    batFamilia.setIdade(idade);
    myRef.child(key).setValue(batFamilia);
    Toast.makeText(this, "Dados Inseridos com sucesso!", Toast.LENGTH_SHORT).show();

}

    public void  preencherDadosUsuario(){

        myRef.child("usuario").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()){
                    BatFamilia batFamilia = userSnapshot.getValue(BatFamilia.class);

                    txtNome.setText(batFamilia.getNome());
                    txtIdade.setText(batFamilia.getIdade());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){

            case R.id.Desconectar:
                mAuth.signOut();
                Toast.makeText(this, "Usuario desconectado", Toast.LENGTH_SHORT).show();

        }

        return super.onOptionsItemSelected(item);
    }

    public void voltarTelaLogin(){
        Toast.makeText(this, "Usuario desconectado", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);

    }


}
