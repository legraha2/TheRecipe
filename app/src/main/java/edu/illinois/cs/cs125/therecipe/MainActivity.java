package edu.illinois.cs.cs125.therecipe;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.FileOutputStream;


public class MainActivity extends Activity {

    Button newButton,saveButton,openButton;
    EditText text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newButton=(Button)findViewById(R.id.newButton);
        saveButton=(Button)findViewById(R.id.saveButton);
        openButton=(Button)findViewById(R.id.openButton);
        text=(EditText)findViewById(R.id.text);
    }

    public class JsonReader {

        private String readAll(Reader rd) throws IOException {
            StringBuilder sb = new StringBuilder();
            int cp;
            while ((cp = rd.read()) != -1) {
                sb.append((char) cp);
            }
            return sb.toString();
        }

        public JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
            InputStream is = new URL(url).openStream();
            try {
                BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
                String jsonText = readAll(rd);
                JSONObject json = new JSONObject(jsonText);
                return json;
            } finally {
                is.close();
            }
        }

        public void main(String[] args) throws IOException, JSONException {
            JSONObject json = readJsonFromUrl("https://www.themealdb.com/api/json/v1/1/random.php");
            //System.out.println(json.toString());
            //System.out.println(json.get("id"));

        }
    }

    public void buttonAction(View v) {
        final EditText fileName=new EditText(this);
        AlertDialog.Builder ad=new AlertDialog.Builder(this);
        ad.setView(fileName);

        if (v.getId() == R.id.saveButton) {
            ad.setMessage("Save File");

            ad.setPositiveButton("Save",new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    try {
                        FileOutputStream fout=openFileOutput(fileName.getText().toString()+".txt", Context.MODE_PRIVATE);
                        fout.write(text.getText().toString().getBytes());
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "Error Occured: "+e,Toast.LENGTH_LONG).show();
                    }
                }
            });

            ad.setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            ad.show();

        }

        if(v.getId()==R.id.openButton) {
            ad.setMessage("Open File");

            ad.setPositiveButton("Open",new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    int c;
                    text.setText("");

                    try {
                        FileInputStream fin = openFileInput(fileName.getText().toString()+".txt");

                        while ((c = fin.read()) != -1)
                        {
                            text.setText((text.getText().toString() + Character.toString((char) c)));
                        }
                    }catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "Error Occured: "+e,Toast.LENGTH_LONG).show();
                    }
                }
            });

            ad.setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            ad.show();
        }

        if(v.getId()==R.id.newButton) {
            text.setText("");
        }
        if(v.getId()==R.id.randomButton) {
            text.setText("");
        }

    }
}
