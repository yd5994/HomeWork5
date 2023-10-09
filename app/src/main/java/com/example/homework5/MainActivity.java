package com.example.homework5;

import static java.lang.Double.parseDouble;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    AlertDialog.Builder adb;
    ArrayAdapter<Double> adp;
    ListView lv;
    TextView tx,tx2,tx3,tx4;
    LinearLayout mydialog;
    EditText type,Findex,difference;
    String Stype="",SFindex="",Sdifference="";
    Double[] numbers = new Double[20];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tx=findViewById(R.id.textView13);
        tx2=findViewById(R.id.textView14);
        tx3=findViewById(R.id.textView15);
        tx4=findViewById(R.id.textView16);
        lv=findViewById(R.id.listview);
        startlistview();
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (Stype.equals("a")){
            double Math1= Double.valueOf(Double.valueOf(SFindex)+(Double.valueOf(numbers[position])));
            double a1= parseDouble(String.valueOf(position+1))/2;
            tx4.setText(String.valueOf(Math1*a1));
            position++;
            tx3.setText(" "+position);
        }
        else if (Stype.equals("g")) {
            double Math2 = parseDouble(SFindex)*(parseDouble(String.valueOf(Math.pow(parseDouble(Sdifference),position+1)))-1);
            tx4.setText(String.valueOf(Math2));
            position++;
            tx3.setText(" "+position);
        }
    }

    DialogInterface.OnClickListener myclick = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            if (which==DialogInterface.BUTTON_POSITIVE){
                Stype=type.getText().toString();
                SFindex=Findex.getText().toString();
                Sdifference=difference.getText().toString();
                numbers[0]=Double.valueOf(SFindex);
                if(!Stype.equals("a")&&!Stype.equals("g")){
                    Stype="a";
                }
                tx.setText(SFindex);
                tx2.setText(Sdifference);
                if (Stype.equals("a")){
                    for(int i=1;i<numbers.length;i++){
                        numbers[i]=numbers[i-1]+Double.valueOf(Sdifference);
                    }
                }
                else if (Stype.equals("g")) {
                    for(int i=1;i<numbers.length;i++){
                        numbers[i]=numbers[i-1]*Double.valueOf(Sdifference);
                    }
                }
                lv.setAdapter(adp);
            }
            if (which==DialogInterface.BUTTON_NEGATIVE){
                dialog.cancel();
            }
        }
    };

    public void btn(View view) {
        /**
         * Activates the custom alert dialog box
         */
        mydialog = (LinearLayout) getLayoutInflater().inflate(R.layout.my_dialog,null);
        type= (EditText) mydialog.findViewById(R.id.editTextText);
        Findex= (EditText) mydialog.findViewById(R.id.editTextNumber);
        difference= (EditText) mydialog.findViewById(R.id.editTextNumber2);
        adb=new AlertDialog.Builder(this);
        adb.setView(mydialog);
        mydialog.setBackgroundColor(Color.YELLOW);
        adb.setTitle("Enter Data");
        adb.setMessage("enter the type 'a'(for Arithmetic progression) or 'g' (for Geometric series)");
        adb.setPositiveButton("Enter",myclick);
        adb.setNegativeButton("Cancel",myclick);
        adb.show();

    }

    public void clear(View view) {
        /**
         * Clears the values in the custom alert dialog box
         */
        type.setText("");
        Findex.setText("");
        difference.setText("");
    }

    public void startlistview() {
        /**
         * Creates the list view and updates the data
         */
        numbers[0] = 0.0;

        for(int i=1;i<numbers.length;i++){
            numbers[i]=0.0;
        }

        adp = new ArrayAdapter<Double>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, numbers);
        lv.setAdapter(adp);
        lv.setOnItemClickListener(this);
    }
}