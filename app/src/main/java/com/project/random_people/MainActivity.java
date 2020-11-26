package com.project.random_people;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.graphics.Bitmap;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView nome;
    private TextView sobrenome;
    private TextView email;
    private TextView endereco;
    private TextView cidade;
    private TextView estado;
    private TextView username;
    private TextView senha;
    private TextView nascimento;
    private TextView telefone;
    private ImageView foto;
    private TextView latitude;
    private TextView longitude;
    private ProgressDialog load;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nome = (TextView)findViewById(R.id.textView5);
        sobrenome = (TextView)findViewById(R.id.textView11);
        email = (TextView)findViewById(R.id.textView8);
        endereco = (TextView)findViewById(R.id.textView7);
        cidade = (TextView)findViewById(R.id.textView4);
        estado = (TextView)findViewById(R.id.textView3);
        username = (TextView)findViewById(R.id.textView2);
        senha = (TextView)findViewById(R.id.textView10);
        nascimento = (TextView)findViewById(R.id.textView9);
        telefone = (TextView)findViewById(R.id.textView12);
        foto = (ImageView)findViewById(R.id.imageView);
        latitude = (TextView)findViewById(R.id.textView33);
        longitude = (TextView)findViewById(R.id.textView35);
    }
    public void acionaRandom(View view){
        GetJson download = new GetJson();
        //Chama Async Task
        download.execute();
    }
     public void acionaMaps(View view){
         Button btnMaps = (Button) findViewById(R.id.button2);
         btnMaps.setOnClickListener(new View.OnClickListener() {
             public void onClick(View v) {
                     Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                     intent.putExtra("latitude", (Parcelable) latitude);
                     intent.putExtra("longitude", (Parcelable) longitude);
                     startActivity(intent);
             }
         });
     }
    private class GetJson extends AsyncTask<Void, Void, PessoaObj> {

        @Override
        protected void onPreExecute(){
            load = ProgressDialog.show(MainActivity.this,
                    "Por favor Aguarde ...", "Recuperando Informações do Servidor...");
        }

        @Override
        protected PessoaObj doInBackground(Void... params) {
            Utils util = new Utils();
            return util.getInformacao("https://randomuser.me/api/");
        }

        @Override
        protected void onPostExecute(PessoaObj pessoa){
            nome.setText(pessoa.getNome().substring(0,1).toUpperCase()+pessoa.getNome().substring(1));
            sobrenome.setText(pessoa.getSobrenome().substring(0,1).toUpperCase()+pessoa.getSobrenome().substring(1));
            email.setText(pessoa.getEmail());
            endereco.setText(pessoa.getEndereco());
            cidade.setText(pessoa.getCidade().substring(0,1).toUpperCase()+pessoa.getCidade().substring(1));
            estado.setText(pessoa.getEstado());
            username.setText(pessoa.getUsername());
            senha.setText(pessoa.getSenha());
            nascimento.setText(pessoa.getNascimento());
            telefone.setText(pessoa.getTelefone());
            latitude.setText(pessoa.getLatitude());
            longitude.setText(pessoa.getLongitude());
            foto.setImageBitmap(pessoa.getFoto());
            load.dismiss();
        }
    }
}
