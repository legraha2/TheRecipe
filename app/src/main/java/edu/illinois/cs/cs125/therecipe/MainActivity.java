package edu.illinois.cs.cs125.therecipe;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class MainActivity extends Activity {
    private static final String TAG = "TheRecipe:Main";
    private static RequestQueue requestQueue;

    Button newButton, saveButton, openButton;
    EditText text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newButton = (Button) findViewById(R.id.newButton);
        saveButton = (Button) findViewById(R.id.saveButton);
        openButton = (Button) findViewById(R.id.openButton);
        text = (EditText) findViewById(R.id.text);

        requestQueue = Volley.newRequestQueue(this);
        JsonParser parser = new JsonParser();

        final Button randomButton = findViewById(R.id.randomButton);
        randomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Log.d(TAG, "Start API button clicked");
                startAPIcall();
            }
        });
    }


    public static String getName(final String json) {
        JsonParser parser = new JsonParser();
        JsonObject result = parser.parse(json).getAsJsonObject();
        JsonArray mealinfo = result.get("meals").getAsJsonArray();
        JsonObject mealname = mealinfo.get(0).getAsJsonObject();
        String meal = mealname.get("strMeal").getAsString();
        String instructions = mealname.get("strInstructions").getAsString();
        String ingrd1 = mealname.get("strIngredient1").getAsString();
        String ingrd2 = mealname.get("strIngredient2").getAsString();
        String ingrd3 = mealname.get("strIngredient3").getAsString();
        String ingrd4 = mealname.get("strIngredient4").getAsString();
        String ingrd5 = mealname.get("strIngredient5").getAsString();
        String ingrd6 = mealname.get("strIngredient6").getAsString();
        String ingrd7 = mealname.get("strIngredient7").getAsString();
        String ingrd8 = mealname.get("strIngredient8").getAsString();
        String ingrd9 = mealname.get("strIngredient9").getAsString();
        String ingrd10 = mealname.get("strIngredient10").getAsString();
        String ingrd11 = mealname.get("strIngredient11").getAsString();
        String ingrd12 = mealname.get("strIngredient12").getAsString();
        String ingrd13 = mealname.get("strIngredient13").getAsString();
        String ingrd14 = mealname.get("strIngredient14").getAsString();
        String ingrd15 = mealname.get("strIngredient15").getAsString();
        String ingrd16 = mealname.get("strIngredient16").getAsString();
        String ingrd17 = mealname.get("strIngredient17").getAsString();
        String ingrd18 = mealname.get("strIngredient18").getAsString();
        String ingrd19 = mealname.get("strIngredient19").getAsString();
        String ingrd20 = mealname.get("strIngredient20").getAsString();
        String mes1 = mealname.get("strMeasure1").getAsString();
        String mes2 = mealname.get("strMeasure2").getAsString();
        String mes3 = mealname.get("strMeasure3").getAsString();
        String mes4 = mealname.get("strMeasure4").getAsString();
        String mes5 = mealname.get("strMeasure5").getAsString();
        String mes6 = mealname.get("strMeasure6").getAsString();
        String mes7 = mealname.get("strMeasure7").getAsString();
        String mes8 = mealname.get("strMeasure8").getAsString();
        String mes9 = mealname.get("strMeasure9").getAsString();
        String mes10 = mealname.get("strMeasure10").getAsString();
        String mes11 = mealname.get("strMeasure11").getAsString();
        String mes12 = mealname.get("strMeasure12").getAsString();
        String mes13 = mealname.get("strMeasure13").getAsString();
        String mes14 = mealname.get("strMeasure14").getAsString();
        String mes15 = mealname.get("strMeasure15").getAsString();
        String mes16 = mealname.get("strMeasure16").getAsString();
        String mes17 = mealname.get("strMeasure17").getAsString();
        String mes18 = mealname.get("strMeasure18").getAsString();
        String mes19 = mealname.get("strMeasure19").getAsString();
        String mes20 = mealname.get("strMeasure20").getAsString();
        return meal + "\n" + "\n" +  instructions + "\n" + "\n" + mes1 + " " + ingrd1 + "\n"
                + mes2 + " " + ingrd2 + "\n" + mes3 + " " + ingrd3 + "\n" + mes4 + " " + ingrd4
                + "\n" + mes5 + " " + ingrd5 + "\n" + mes6 + " " + ingrd6 + "\n" + mes7 + " "
                + ingrd7 + "\n" + mes8 + " " + ingrd8 + "\n" + mes9 + " " + ingrd9 + "\n" + mes10
                + " " + ingrd10 + "\n" + mes11 + " " + ingrd11 + "\n" + mes12 + " " + ingrd12 + "\n"
                + mes13 + " " + ingrd13 + "\n" + mes14 + " " + ingrd14 + "\n" + mes15 + " "
                + ingrd15 + "\n" + mes16 + " " + ingrd16 + "\n" + mes17 + " " + ingrd17 + "\n"
                + mes18 + " " + ingrd18 + "\n" + mes19 + " " + ingrd19 + "\n" + mes20 + " "
                + ingrd20;
    }

    void startAPIcall() {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.GET,
                    "https://www.themealdb.com/api/json/v1/1/random.php",
                    (String) null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(final JSONObject response) {
                            if (response != null) {
                                final TextView text = findViewById(R.id.text);
                                if (text == null) {
                                    Log.d(TAG, "Null pointer.");
                                } else {
                                    text.setText(getName(response.toString()));
                                }
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(final VolleyError error) {
                    Log.w(TAG, error.toString());
                }
            });
            requestQueue.add(jsonObjectRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void buttonAction(View v) {
        final EditText fileName = new EditText(this);
        AlertDialog.Builder ad = new AlertDialog.Builder(this);
        ad.setView(fileName);

        if (v.getId() == R.id.saveButton) {
            ad.setMessage("Save File");

            ad.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    try {
                        FileOutputStream fout = openFileOutput(fileName.getText().toString() + ".txt", Context.MODE_PRIVATE);
                        fout.write(text.getText().toString().getBytes());
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "Error Occured: " + e, Toast.LENGTH_LONG).show();
                    }
                }
            });

            ad.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            ad.show();

        }

        if (v.getId() == R.id.openButton) {
            ad.setMessage("Open File");

            ad.setPositiveButton("Open", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    int c;
                    text.setText("");

                    try {
                        FileInputStream fin = openFileInput(fileName.getText().toString() + ".txt");

                        while ((c = fin.read()) != -1) {
                            text.setText((text.getText().toString() + Character.toString((char) c)));
                        }
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "Error Occured: " + e, Toast.LENGTH_LONG).show();
                    }
                }
            });

            ad.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            ad.show();
        }
        if (v.getId() == R.id.newButton) {
            text.setText("");
        }
    }
}
