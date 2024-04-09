package fi.arcada.codechallenge;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
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

        double[] numbers = {0.2, 0.3, 3.5, 4, 6, 7, 8};

        //shared preferences
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        SharedPreferences.Editor prefEditor;
        prefEditor = prefs.edit();

        int viewCount = prefs.getInt("num1", 0);
        viewCount++;
        prefEditor.putInt("num1", viewCount);

        prefEditor.apply();
        //end shared pref

        butt = (Button) findViewById(R.id.button);
        butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



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

        public static Double calcLQ(ArrayList<Double> values){

            double length = values.size();
            ArrayList<Double> sorted = new ArrayList<>(values);
            Collections.sort(sorted);
            //find the length of a "segment", round down to deal with odd numbers
            int segment = (int) Math.floor(length/4);

            int halfSegment = segment / 2;

            //half that and grab the number on that index... -1 due to index at 0?

            return sorted.get(halfSegment - 1);
        }
        public static Double calcHQ(ArrayList<Double> values){

            double length = values.size();
            ArrayList<Double> sorted = new ArrayList<>(values);
            Collections.sort(sorted);
            //find the length of a "segment", round down to deal with odd numbers
            int segment = (int) Math.floor(length/4);

            int halfSegment = segment / 2;

            //half that and grab the number on that index... -1 due to index at 0?

            return sorted.get(segment * 3 + halfSegment - 1);

        }
        public static Double calcTQR(ArrayList<Double> values){

            double length = values.size();
            ArrayList<Double> sorted = new ArrayList<>(values);
            Collections.sort(sorted);
            //find the length of a "segment", round down to deal with odd numbers
            int segment = (int) Math.floor(length/4);


            //funny thing when grabbing the median in 2 sectors... its the end of the first/start of the second

            return sorted.get(segment * 2 - 1);
        }

    }

}