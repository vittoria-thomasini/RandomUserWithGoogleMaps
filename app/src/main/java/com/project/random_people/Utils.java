package com.project.random_people;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.format.DateFormat;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
    public PessoaObj getInformacao(String end){
        String json;
        PessoaObj retorno;
        json = NetworkUtils.getJSONFromAPI(end);
        Log.i("Resultado", json);
        retorno = parseJson(json);

        return retorno;
    }

    private PessoaObj parseJson(String json){
        try {
            PessoaObj pessoa = new PessoaObj();

            JSONObject jsonObj = new JSONObject(json);
            JSONArray array = jsonObj.getJSONArray("results");

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            JSONObject objArray = array.getJSONObject(0);

            pessoa.setGenero(objArray.getString("gender"));

                //Nome da pessoa é um objeto, instancia um novo JSONObject
               JSONObject nome = objArray.getJSONObject("name");
               pessoa.setOcupacao(nome.getString("title"));
               pessoa.setNome(nome.getString("first"));
               pessoa.setSobrenome(nome.getString("last"));

            pessoa.setEmail(objArray.getString("email"));

                JSONObject login = objArray.getJSONObject("login");
                pessoa.setUsername(login.getString("username"));
                pessoa.setSenha(login.getString("password"));

            pessoa.setTelefone(objArray.getString("phone"));

                JSONObject dob = objArray.getJSONObject("dob");
                pessoa.setNascimento(dob.getString("date"));

                JSONObject endereco = objArray.getJSONObject("location");
                    JSONObject rua = endereco.getJSONObject("street");
                    pessoa.setEndereco(rua.getString("name"));
                    pessoa.setComplemento(rua.getString("number"));

                pessoa.setEstado(endereco.getString("state"));
                pessoa.setCidade(endereco.getString("city"));

                JSONObject coordinates = endereco.getJSONObject("coordinates");
                pessoa.setLatitude(coordinates.getString("latitude"));
                pessoa.setLongitude(coordinates.getString("longitude"));

           //Imagem eh um objeto
            JSONObject foto = objArray.getJSONObject("picture");
            pessoa.setFoto(baixarImagem(foto.getString("large")));

            return pessoa;
        }catch (JSONException e){
            e.printStackTrace();
            return null;
        }
    }

    private Bitmap baixarImagem(String url) {
        try{
            URL endereco;
            InputStream inputStream;
            Bitmap imagem; endereco = new URL(url);
            inputStream = endereco.openStream();
            imagem = BitmapFactory.decodeStream(inputStream);
            inputStream.close();
            return imagem;
        }catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}