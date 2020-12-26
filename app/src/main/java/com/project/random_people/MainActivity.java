package com.project.random_people;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.view.View;
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
    private TextView genero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        foto = (ImageView)findViewById(R.id.imageView);
        //genero = (TextView)findViewById(R.id.txt_out_name);
        nome = (TextView)findViewById(R.id.txt_out_name);
        sobrenome = (TextView)findViewById(R.id.txt_out_last_name);
        email = (TextView)findViewById(R.id.txt_out_email);
        endereco = (TextView)findViewById(R.id.txt_out_address);
        cidade = (TextView)findViewById(R.id.txt_out_city);
        estado = (TextView)findViewById(R.id.txt_out_state);
        username = (TextView)findViewById(R.id.txt_out_username);
        senha = (TextView)findViewById(R.id.txt_out_password);
        nascimento = (TextView)findViewById(R.id.txt_out_birthday);
        telefone = (TextView)findViewById(R.id.txt_out_phone);
        latitude = (TextView)findViewById(R.id.txt_out_coordinates);
        longitude = (TextView)findViewById(R.id.txt_out_longitude);
    }
    public void acionaRandom(View view){
        GetJson download = new GetJson();
        //Chama Async Task
        download.execute();
    }
//     public void acionaMaps(View view){
//         Button btnMaps = (Button) findViewById(R.id.button2);
//         btnMaps.setOnClickListener(new View.OnClickListener() {
//             public void onClick(View v) {
//                     Intent intent = new Intent(MainActivity.this, MapsActivity.class);
//                     intent.putExtra("latitude", (Parcelable) latitude);
//                     intent.putExtra("longitude", (Parcelable) longitude);
//                     startActivity(intent);
//             }
//         });
//     }
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
            //genero.setText(pessoa.getGenero());
            nome.setText(pessoa.getNome());
            sobrenome.setText(pessoa.getSobrenome());
            email.setText(pessoa.getEmail());
            endereco.setText(pessoa.getEndereco() + "," + pessoa.getComplemento());
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
