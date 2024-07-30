package com.pdrarth.calculadoraimc;

import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.pdrarth.calculadoraimc.databinding.ActivityMainBinding;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    //calcuadora
    Button calcular = binding.Calculadora;
    //altura
    TextInputEditText texaltura = binding.altura;
    //peso
    TextInputEditText textpeso = binding.editPeso;
    //Text
    TextView result = binding.Resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());

        binding.Calculadora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String peso = textpeso.getText().toString();
                String altura = texaltura.getText().toString();

                if(peso.isEmpty()){
                    textpeso.setError("Preencha o campo!");
                } else if (altura.isEmpty()) {
                    texaltura.setError("Preencha o campo!");
                }
                calcular();

            }
        });

    }
    private void calcular(){
        //aqui convertendo o texto em double
        //e depois eu passo que eu posso digitar com virgula e ele vai passar para ponto usando o replace
        double peso = Double.parseDouble(textpeso.getText().toString().replace(",","."));
        double altura = Double.parseDouble(texaltura.getText().toString().replace(",","."));
        double imc = peso / (altura*altura);
        DecimalFormat decimalFormat = new DecimalFormat("0.00");

        if(imc < 18.5){
            result.setText("Seu IMC "+decimalFormat.format(imc)+" \n Peso baixo");
            result.setTextColor(getColor(R.color.Yellow));
        } else if (imc >= 18.5 && imc <=24.9) {
            result.setText("Seu IMC "+decimalFormat.format(imc)+" \n Peso normal");
            result.setTextColor(getColor(R.color.Chartreuse));
        } else if (imc >=25.0 && imc<=29.9 ) {
            result.setText("Seu IMC "+decimalFormat.format(imc)+" \n Peso sobrepeso");
            result.setTextColor(getColor(R.color.SandyBrown));
        } else if (imc >=30.0 && imc<=34.9 ) {
            result.setText("Seu IMC "+decimalFormat.format(imc)+" \n Obesidade (Grau I)");
            result.setTextColor(getColor(R.color.Chocolate));
        } else if ((imc>=35.00 && imc <=39.9) ) {
            result.setText("Seu IMC "+decimalFormat.format(imc)+" \n Obesidade Severa (Grau II)");
                result.setTextColor(getColor(R.color.Tomato));
        }
        else {
            result.setText("Seu IMC "+decimalFormat.format(imc)+" \n Obesidade Móbida (Grau III)");
            result.setTextColor(getColor(R.color.Red));

        }

    }

    //definiir um menu para actionbar, fazer uma sobreescrita
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal,menu);
        return super.onCreateOptionsMenu(menu);
    }
    //acao para o menu
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int intemId = item.getItemId();

        if(intemId == R.id.limpar){
            binding.editPeso.setText("");
            binding.altura.setText("");
            binding.Resultado.setText("");
        }
        return super.onOptionsItemSelected(item);
    }
}