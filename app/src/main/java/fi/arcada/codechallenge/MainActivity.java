package fi.arcada.codechallenge;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    TextView answer;
    Button butt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        answer = findViewById(R.id.AwnsText);

        butt = (Button) findViewById(R.id.button);
        butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                double[] numbers = {0.2, 0.3, 3.5};
                ArrayList<Double> numbersA = new ArrayList<>();

                for (double value : numbers){
                    numbersA.add(value);
                }

                String finText = String.valueOf(Statistics.calcMean(numbersA))+ "\n";
                finText += String.valueOf(Statistics.calcMed(numbersA)) + "\n";
                finText += String.valueOf(Statistics.calcStdev(numbersA)) + "\n";
                answer.setText(finText);
            }
        });
    }
    public static class Statistics{

        public static double calcStdev(ArrayList<Double> values){

            double length = values.size();
            double mean = Statistics.calcMean(values);
            double sqareSum = 0;

            for(int i=0; i < length - 1; i++){
                sqareSum = Math.pow((values.get(i) - mean), 2);
            }

            double sqareAvg = sqareSum/length;

            return Math.sqrt(sqareAvg);
        }

        public static double calcMed(ArrayList<Double> values){

            double length = values.size();
            ArrayList<Double> sorted = new ArrayList<>(values);
            Collections.sort(sorted);
            if(length % 2 == 0){
                //is even, return mid of 2 values, sub two due to indexes.
                int i = (int) (length/2 - 2);

                return ((sorted.get(i)+sorted.get(i+1))/2);

            }
            else{
                //number is odd, so no problems, round down to pick one value, minus one due to index
                int i = (int) (Math.floor(length/2) - 1);
                return (sorted.get(i));
            }
        }


        public static Double calcMean(ArrayList<Double> values){
            double sum = 0;
            double length = values.size();
            for (int i=0; i < length - 1 ; i++) {
                sum += values.get(i);
            }
            return sum/length;
        }
    }

}