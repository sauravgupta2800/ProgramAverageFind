package com.example.saurabh.programaveragefind;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Spinner spinnerDropDown;
    String[] cities = {
            "size 1",
            "size 2",
            "size 3",
            "size 4",
            "size 5",
            "size 6",
            "size 7",
            "size 8",
            "size 9",
            "size 10",
            "size 11",
            "size 12",
            "size 13",
            "size 14",
            "size 15",
            "size 16",
            "size 17",
            "size 18",
            "size 19",
            "size 20",
            "size 21",
            "size 22",
            "size 23",
            "size 24",
            "size 25",
            "size 26",
            "size 27",
            "size 28",
            "size 29",
            "size 30"
    };
    private ImageView run,reset;
    private EditText inputString;
    private TextView result;
    private int size;
    private String string;
    //defining AwesomeValidation object
    private AwesomeValidation awesomeValidation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        // Get reference of SpinnerView from layout/main_activity.xml
        spinnerDropDown =(Spinner)findViewById(R.id.spinner1);
        run = (ImageView) findViewById(R.id.run);
        reset = (ImageView) findViewById(R.id.reset);
        inputString = (EditText) findViewById(R.id.inputString);
        result = (TextView) findViewById(R.id.result);

        //OFF the mean
        result.setVisibility(View.INVISIBLE);
        ///^\s*([\-\+]?[0-9]+)((\s+[\-\+]?[0-9]+))*$/x

        awesomeValidation.addValidation(this,R.id.inputString,"^[-+]?[0-9_ ]*$",R.string.inputstringerror);

        ArrayAdapter<String> adapter= new ArrayAdapter<String>(this,android.
                R.layout.simple_spinner_dropdown_item ,cities);

        spinnerDropDown.setAdapter(adapter);

        spinnerDropDown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                // Get select item
                size=spinnerDropDown.getSelectedItemPosition();
                Toast.makeText(getBaseContext(), "You have selected  : " + cities[size],
                        Toast.LENGTH_SHORT).show();
                //Toast.makeText(getBaseContext(), "You tapped : " + (size+1),
                 //       Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });
        run.setOnClickListener(this);
        reset.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v == run)
        {
            if(/*awesomeValidation.validate() && */calculateSize())
            {
                Toast.makeText(this, "Validation Successfull", Toast.LENGTH_LONG).show();
                calculateMean();
            }
            else
            {
                Toast.makeText(this, "Size or format error", Toast.LENGTH_LONG).show();

            }
        }
        if(v == reset)
        {
            inputString.setText(null);
            inputString.setHint("Enter String here...");
            result.setText(null);

            result.setVisibility(View.INVISIBLE);
        }
    }

    private boolean calculateSize()
    {
        String numbers = inputString.getText().toString().trim();
        String[] tokens = numbers.split(" ");
        int[] arr = new int[tokens.length];

        int i = 0;
        int count=0;
        for (String token : tokens)
        {
            arr[i++] = Integer.parseInt(token);
        }
        //matching sizes

        if(arr.length == (size+1))
        {
            return true;
        }
        else
        {
            return false;
            //error generate
            //Toast.makeText(this, "Size should be matched...!!!!",Toast.LENGTH_LONG).show();
        }
    }

    //calculate mean
    private void calculateMean()
    {
        String numbers = inputString.getText().toString().trim();
        String[] tokens = numbers.split(" ");
        int[] arr = new int[tokens.length];

        int i = 0;
        for (String token : tokens)
        {
            arr[i++] = Integer.parseInt(token);
        }
        //do something
        int value = 0;
        ArrayList<Integer> list = new ArrayList<>();
        for(i = 0;i<arr.length;i++)
        {
            value = arr[i];
            if (!list.contains(value) && value >= 0)
            {
                list.add(value); // Add the value if it is not in the list
            }
        }
        //main calculation
        int total_elements=list.size()-2;
        int sum = 0;
        System.out.println();
        for(i = 1; i < list.size()-1;i++)
        {
            sum+=list.get(i);
        }
        result.setText( "Mean is : "+((float) sum/total_elements));
        result.setVisibility(View.VISIBLE);
        //inputString.setText("");
        list.clear();
        //Log.d("abc", (arr.length-1)+" "+ (size));
    }
}
