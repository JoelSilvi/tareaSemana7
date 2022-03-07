package com.example.sistemaatencin;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button btnNumAte;
    private EditText etRut;
    private TextView txtPorcentaje;
    private ProgressBar progressBar;
    Handler proceso = new Handler();
    boolean active = false;
    int x = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnNumAte = (Button) findViewById(R.id.btnNumAte);
        etRut = (EditText) findViewById(R.id.etRut);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        txtPorcentaje = (TextView) findViewById((R.id.txtPorcentaje));

        btnNumAte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.btnNumAte) {
                    if (!active) {
                        Thread hilo = new Thread(new Runnable() {

                            @Override
                            public void run() {
                                while (1 < 100) {

                                    proceso.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            txtPorcentaje.setText(x + " %");
                                            progressBar.setProgress(x);
                                        }
                                    });
                                    try {
                                        Thread.sleep(60);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    x++;
                                    active = true;
                                }
                            }
                        });
                        hilo.start();
                        new TaskRecuperar().execute(etRut.getText().toString());
                    }
                }
            }

            class TaskRecuperar extends AsyncTask<String, Void, String> {

                @Override
                protected void onPreExecute() {
                    progressBar.setVisibility(View.VISIBLE);
                    btnNumAte.setEnabled(false);
                }

                @Override
                protected String doInBackground(String... strings) {
                    try {
                        Thread.sleep(6000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return strings[0];
                }

                @Override
                protected void onPostExecute(String s) {
                    progressBar.setVisibility(View.VISIBLE);
                    btnNumAte.setEnabled(true);
                    Intent intent = new Intent(MainActivity.this, ResultadoActivity.class);
                    intent.putExtra("rut", etRut.getText().toString());
                    startActivity(intent);
                }
            }
        });
    }
}