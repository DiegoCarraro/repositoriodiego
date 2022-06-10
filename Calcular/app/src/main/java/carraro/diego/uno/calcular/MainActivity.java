package carraro.diego.uno.calcular;//package carraro.diego.uno.calcular;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.util.SparseIntArray;
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
    public static final String TAG                  = "MainActivity";
    public static final String DIVISAO              = "Divisão";
    public static final String MULTIPLICACAO        = "Multiplicação";
    public static final String SOMA                 = "Soma";
    public static final String SUBTRACAO            = "Subtração";
    public static final String POTENCIA10           = "Potência de 10";
    public static final String RAIZ_QUADRADA        = "Raiz Quadrada";
    public static final String POTENCIACAO          = "Potenciação";
    public static final String LOGARITMO            = "Logaritmo";
    public int ZERO                                 = 0;
    public int DEZ                                  = 10;
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
        if (actionBar != null) {
            actionBar.setTitle("Calculadora DC");
        }

        spiOpcoes = findViewById(R.id.spiOpcoes);
        btnCalcular = findViewById(R.id.btnCalcular);
        edtOperando1 = findViewById(R.id.edtOperando1);
        edtOperando2 = findViewById(R.id.edtOperando2);
        imgOperacao = findViewById(R.id.imgOperacao);
        imgView2 = findViewById(R.id.imageView2);
        tvResultado = findViewById(R.id.tvResultado);
        imageLimpar = findViewById(R.id.imageLimpar); // Instanciação

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
                    if (validarTermosVazios()) {
                        if (validarDivisor()) {
                            tvResultado.setText(divisao());

                        } else {
                            Toast.makeText(MainActivity.this, "Divisor não pode ser zero", Toast.LENGTH_SHORT).show();
                        }
                    } else {

                        Toast.makeText(MainActivity.this, "Preencha o valor!", Toast.LENGTH_SHORT).show();
                    }

                } else if (operacaoSelecionada.equals(MULTIPLICACAO)) {
                    if (validarTermosVazios()) {
                        tvResultado.setText(multiplicacao());

                    } else {
                        Toast.makeText(MainActivity.this, "Preencha o valor!", Toast.LENGTH_SHORT).show();
                    }

                } else if (operacaoSelecionada.equals(SOMA)) {
                    if (validarTermosVazios()) {
                        tvResultado.setText(somar());

                    } else {
                        Toast.makeText(MainActivity.this, "Preencha o valor!", Toast.LENGTH_SHORT).show();
                    }

                } else if (operacaoSelecionada.equals(SUBTRACAO)) {
                    if (validarTermosVazios()) {
                        tvResultado.setText(subtracao());

                    } else {
                        Toast.makeText(MainActivity.this, "Preencha o valor!", Toast.LENGTH_SHORT).show();
                    }

                } else if (operacaoSelecionada.equals(RAIZ_QUADRADA)) {
                    if (validarRaizPot()) {
                        tvResultado.setText(raizquadrada());
                    } else {
                        Toast.makeText(MainActivity.this, "Preencha o valor!", Toast.LENGTH_SHORT).show();
                    }

                } else if (operacaoSelecionada.equals(LOGARITMO)) {
                    if (validarTermosVazios()) {
                        tvResultado.setText(logaritmo());
                    } else {
                        Toast.makeText(MainActivity.this, "Preencha o valor!", Toast.LENGTH_SHORT).show();
                    }


                } else if (operacaoSelecionada.equals(POTENCIA10)) {
                    if (validarRaizPot()) {
                        tvResultado.setText(potencia10());
                    } else {
                        Toast.makeText(MainActivity.this, "Preencha o valor!", Toast.LENGTH_SHORT).show();
                    }

                } else if (operacaoSelecionada.equals(POTENCIACAO)) {
                    if (validarTermosVazios()) {
                        tvResultado.setText(potenciacao());
                    } else {
                        Toast.makeText(MainActivity.this, "Preencha o valor!", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(MainActivity.this, "Selecione uma operação matematica", Toast.LENGTH_SHORT).show();
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
    private void setEdtOperando2Behavior(boolean block){
            if (block){
                edtOperando2.setFocusable(false);
                edtOperando2.setEnabled(false);
            }else {
                edtOperando2.setFocusable(true);
                edtOperando2.setEnabled(true);
            }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        //Inicia com a view no comportamento padrão, ou seja, liberada

        setEdtOperando2Behavior(false);

        Toast.makeText(MainActivity.this, adapterView.getItemAtPosition(i).toString(),
                Toast.LENGTH_SHORT).show();

        if(adapterView.getItemAtPosition(i).toString().equals(DIVISAO)){
            imgOperacao.setImageDrawable(getResources().getDrawable(R.drawable.divisao, getTheme()));
            imgOperacao.setVisibility(View.VISIBLE);
            edtOperando1.setHint("Dividendo");
            edtOperando2.setHint("Divisor");
            edtOperando2.setVisibility(View.VISIBLE);

        } else if(adapterView.getItemAtPosition(i).toString().equals(MULTIPLICACAO)){
            imgOperacao.setImageDrawable(getResources().getDrawable(R.drawable.multiplica, getTheme()));
            imgOperacao.setVisibility(View.VISIBLE);
            edtOperando1.setHint("Multiplicando");
            edtOperando2.setHint("Multiplicador");
            edtOperando2.setVisibility(View.VISIBLE);

        } else if(adapterView.getItemAtPosition(i).toString().equals(SOMA)){
            imgOperacao.setImageDrawable(getResources().getDrawable(R.drawable.soma, getTheme()));
            imgOperacao.setVisibility(View.VISIBLE);
            edtOperando1.setHint("Parcela");
            edtOperando2.setHint("Parcela");
            edtOperando2.setVisibility(View.VISIBLE);

        } else if(adapterView.getItemAtPosition(i).toString().equals(SUBTRACAO)) {
            imgOperacao.setImageDrawable(getResources().getDrawable(R.drawable.subtracao, getTheme()));
            imgOperacao.setVisibility(View.VISIBLE);
            edtOperando1.setHint("Minuendo");
            edtOperando2.setHint("Subtraendo");
            edtOperando2.setVisibility(View.VISIBLE);

        } else if(adapterView.getItemAtPosition(i).toString().equals(POTENCIA10)) {
            imgOperacao.setImageDrawable(getResources().getDrawable(R.drawable.pot10, getTheme()));
            imgOperacao.setVisibility(View.VISIBLE);
            edtOperando1.setHint("Expoente");
            edtOperando2.setHint("Não aplicável");
            edtOperando2.setVisibility(View.VISIBLE);

            setEdtOperando2Behavior(true);


        } else if(adapterView.getItemAtPosition(i).toString().equals(POTENCIACAO)) {
            imgOperacao.setImageDrawable(getResources().getDrawable(R.drawable.x_elevado_y, getTheme()));
            imgOperacao.setVisibility(View.VISIBLE);
            edtOperando1.setHint("Potencia1");
            edtOperando2.setHint("Potencia2");
            edtOperando2.setVisibility(View.VISIBLE);

        } else if(adapterView.getItemAtPosition(i).toString().equals(RAIZ_QUADRADA)) {
            imgOperacao.setImageDrawable(getResources().getDrawable(R.drawable.raiz_quadrada, getTheme()));
            imgOperacao.setVisibility(View.VISIBLE);
            edtOperando1.setHint("Extrair");
            edtOperando2.setHint("Não aplicável");
            edtOperando2.setVisibility(View.VISIBLE);

            setEdtOperando2Behavior(true);


        } else if(adapterView.getItemAtPosition(i).toString().equals(LOGARITMO)) {
            imgOperacao.setImageDrawable(getResources().getDrawable(R.drawable.logaritmo, getTheme()));
            imgOperacao.setVisibility(View.VISIBLE);
            edtOperando1.setHint("Log1");
            edtOperando2.setHint("Log2");
            edtOperando2.setVisibility(View.VISIBLE);

        } else{
            Log.d(TAG, "Nenhuma opção foi selecionada.");
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    private String raizquadrada (){
        Double n1 = Double.parseDouble(edtOperando1.getText().toString());
        Double res = Math.sqrt(n1);

        return "O resultado da expressão é: " + res;
    }

    private String potencia10(){
        Double n1 = Double.parseDouble(edtOperando1.getText().toString());
        Double res = Math.pow(DEZ, n1);

        return "O resultado da expressão: " + res;

    }


    private String potenciacao(){
        Double n1 = Double.parseDouble(edtOperando1.getText().toString());
        Double n2 = Double.parseDouble(edtOperando2.getText().toString());

        Double res = Math.pow(n1,n2);

        return "O resultado da expressão é: " + res;
    }

    private String logaritmo(){
        Double n1 = Double.parseDouble(edtOperando1.getText().toString());
        Double n2 = Double.parseDouble(edtOperando2.getText().toString());

        Double res = Math.log(n1/n2);

        return "O retorno da operação é: " + res;
    }

    private String somar(){
        Double n1 = Double.parseDouble(edtOperando1.getText().toString());
        Double n2 = Double.parseDouble(edtOperando2.getText().toString());
        Double resultado = n1+n2;

        return "O resultado da soma é: " + resultado ;
    }
    private String divisao(){
        Double n1 = Double.parseDouble(edtOperando1.getText().toString());
        Double n2 = Double.parseDouble(edtOperando2.getText().toString());
        Double resultado = n1/n2;

        return "O resultado da divisão é:" + resultado;
    }
    private String multiplicacao(){
        Double n1 = Double.parseDouble(edtOperando1.getText().toString());
        Double n2 = Double.parseDouble(edtOperando2.getText().toString());
        Double resultado = n1*n2;

        return "O resultado da multiplicação é:" +  resultado;
    }

    private String subtracao(){
        Double n1 = Double.parseDouble(edtOperando1.getText().toString());
        Double n2 = Double.parseDouble(edtOperando2.getText().toString());
        Double resultado = n1-n2;

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
        if(n2 != ZERO){
            return true;

        }else {
            return false;
        }
    }

    private boolean validarRaizPot(){
        if(!edtOperando1.getText().toString().isEmpty()){
            return true;
        }else {
            return false;
        }
    }

}

