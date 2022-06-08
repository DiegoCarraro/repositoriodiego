package carraro.diego.uno.calcular;//package carraro.diego.uno.calcular;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    public static final String TAG           = "MainActivity";
    public static final String DIVISAO       = "Divisão";
    public static final String MULTIPLICACAO = "Multiplicação";
    public static final String SOMA          = "Soma";
    public static final String SUBTRACAO     = "Subtração";
    private Spinner spiOpcoes;
    private Button btnCalcular;
    private EditText edtOperando1, edtOperando2;
    private ImageView imgOperacao, imgView2;
    private TextView tvResultado;
    private ImageView imageLimpar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setTitle("Calculadora Diego Carraro");
        }

        spiOpcoes       = findViewById(R.id.spiOpcoes);
        btnCalcular     = findViewById(R.id.btnCalcular);
        edtOperando1    = findViewById(R.id.edtOperando1);
        edtOperando2    = findViewById(R.id.edtOperando2);
        imgOperacao     = findViewById(R.id.imgOperacao);
        imgView2        = findViewById(R.id.imageView2);
        tvResultado     = findViewById(R.id.tvResultado);
        imageLimpar     = findViewById(R.id.imageLimpar);


        imgOperacao.setVisibility(View.INVISIBLE);


        ArrayAdapter<String> adapterOperacoes = new ArrayAdapter<String>(this,
        android.R.layout.simple_spinner_item,
        getResources().getStringArray(R.array.operacoes_matematica));
        adapterOperacoes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spiOpcoes.setAdapter(adapterOperacoes);
        spiOpcoes.setOnItemSelectedListener(this);

        btnCalcular.setOnClickListener(new View.OnClickListener() {
    @Override
     public void onClick(View view) {
         String operacaoSelecionada = spiOpcoes.getSelectedItem().toString();

        if (operacaoSelecionada.equals(DIVISAO)) {
           if(validarTermosVazios()) {
              if(validarDivisor()){
          tvResultado.setText(divisao());
          } else{
          Toast.makeText(MainActivity.this,"Divisor não pode ser zero", Toast.LENGTH_SHORT).show();
             } } else{
          Toast.makeText(MainActivity.this, "Preencha o valor!", Toast.LENGTH_SHORT).show();
        }
          } else if (operacaoSelecionada.equals(MULTIPLICACAO)) {
        if (validarTermosVazios()) {
          tvResultado.setText(multiplicacao());
          } else{
          Toast.makeText(MainActivity.this, "Preencha o valor!", Toast.LENGTH_SHORT).show();
        }
          } else if (operacaoSelecionada.equals(SOMA)) {
             if (validarTermosVazios()) {
          tvResultado.setText(somar());
          } else{
          Toast.makeText(MainActivity.this, "Preencha o valor!", Toast.LENGTH_SHORT).show();
        }
          } else if (operacaoSelecionada.equals(SUBTRACAO)) {
              if (validarTermosVazios()) {
          tvResultado.setText(subtracao());
          } else{
          Toast.makeText(MainActivity.this, "Preencha o valor!", Toast.LENGTH_SHORT).show();
        }
                }else {
                    Toast.makeText(MainActivity.this,
                            "Selecione um número e uma opção matemática!!",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        imageLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtOperando1.setText("");
                edtOperando2.setText("");
                tvResultado.setText("");
                imgOperacao.setVisibility(View.INVISIBLE);

            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(MainActivity.this, adapterView.getItemAtPosition(i).toString(),
                Toast.LENGTH_SHORT).show();

        if(adapterView.getItemAtPosition(i).toString().equals(DIVISAO)){
            imgOperacao.setImageDrawable(getResources().getDrawable(R.drawable.divisao, getTheme()));
            imgOperacao.setVisibility(View.VISIBLE);
            edtOperando1.setHint("Dividendo");
            edtOperando2.setHint("Divisor");

        } else if(adapterView.getItemAtPosition(i).toString().equals(MULTIPLICACAO)){
            imgOperacao.setImageDrawable(getResources().getDrawable(R.drawable.multiplica, getTheme()));
            imgOperacao.setVisibility(View.VISIBLE);
            edtOperando1.setHint("Multiplicando");
            edtOperando2.setHint("Multiplicador");

        } else if(adapterView.getItemAtPosition(i).toString().equals(SOMA)){
            imgOperacao.setImageDrawable(getResources().getDrawable(R.drawable.soma, getTheme()));
            imgOperacao.setVisibility(View.VISIBLE);
            edtOperando1.setHint("Parcela");
            edtOperando2.setHint("Parcela");

        } else if(adapterView.getItemAtPosition(i).toString().equals(SUBTRACAO)){
            imgOperacao.setImageDrawable(getResources().getDrawable(R.drawable.subtracao, getTheme()));
            imgOperacao.setVisibility(View.VISIBLE);
            edtOperando1.setHint("Minuendo");
            edtOperando2.setHint("Subtraendo");

        } else{
            Log.d(TAG, "Nenhuma opção foi selecionada.");
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    private String somar(){
        int n1 = Integer.valueOf(edtOperando1.getText().toString()).intValue();
        int n2 = Integer.valueOf(edtOperando2.getText().toString()).intValue();
        int resultado = n1+n2;

        return "O resultado da soma é: " + resultado ;
    }
    private String divisao(){
        int n1 = Integer.valueOf(edtOperando1.getText().toString()).intValue();
        int n2 = Integer.valueOf(edtOperando2.getText().toString()).intValue();
        int resultado = n1/n2;

        return "O resultado da divisão é:" + resultado;
    }
    private String multiplicacao(){
        int n1 = Integer.valueOf(edtOperando1.getText().toString()).intValue();
        int n2 = Integer.valueOf(edtOperando2.getText().toString()).intValue();
        int resultado = n1*n2;

        return "O resultado da multiplicação é:" +  resultado;
    }

    private String subtracao(){
        int n1 = Integer.valueOf(edtOperando1.getText().toString()).intValue();
        int n2 = Integer.valueOf(edtOperando2.getText().toString()).intValue();
        int resultado = n1-n2;

        return " O resultado da subtração é:" + resultado;
    }
    private boolean validarTermosVazios(){
        if(!edtOperando1.getText().toString().isEmpty()){
            if(!edtOperando2.getText().toString().isEmpty()){
                return true;
            }else{
                edtOperando2.requestFocus();
                return false;
            }

        }else {
            edtOperando1.requestFocus();
            return false;
        }
    }

    private boolean validarDivisor(){
        int n2 = Integer.valueOf(edtOperando2.getText().toString());
        if(n2 != 0){
            return true;

        }else {
            return false;
        }
    }

}

