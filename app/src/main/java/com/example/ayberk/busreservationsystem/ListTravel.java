package com.example.ayberk.busreservationsystem;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.lang.String;

//import static com.example.ayberk.busreservationsystem.R.id.txtMsg;

public class ListTravel extends Activity {


    TextView textList;
    Button writeWriteData;
    Button buttonReadData;
    String travelString;
    TextView showSelected;
    ArrayList<Travel> travels = new ArrayList<>();
    ArrayList<String> travelsWillBeListed = new ArrayList<>();
    public static String secilenOtobus;
    boolean listSelected=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_list);
        showSelected= (TextView)findViewById(R.id.showSelected);
        ListView lv = (ListView) findViewById(R.id.listTravel);



        // Create The Adapter with passing ArrayList as 3rd parameter
        ArrayAdapter<String> arrayAdapter =new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, travelsWillBeListed);
        // Set The Adapter
        lv.setAdapter(arrayAdapter);



        try {
            BufferedReader inputReader = new BufferedReader(new InputStreamReader(
                    openFileInput("travelList")));
            String inputString;
            StringBuffer stringBuffer = new StringBuffer();
            while ((inputString = inputReader.readLine()) != null) {
                String[]a =inputString.split(",");
                //trims the ","
                //trims the line and set them in to array
                String destinationCity = a[0].trim();
                String originCity = a[1].trim();
                String time = a[2].trim();
                Double price = Double.valueOf(a[3].trim());
                int year = Integer.parseInt(a[4].trim());
                int month = Integer.parseInt(a[5].trim());
                int day = Integer.parseInt(a[6].trim());

                Travel tr1=new Travel(destinationCity,originCity,time,price,year,month,day);
                travels.add(tr1);
                stringBuffer.append(inputString + "\n");
            }

        }
        catch (IOException e) {
            e.printStackTrace();
        }

        String a= String.valueOf(travels.get(0).toString());
        travelDetails();


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() { @Override
        public void onItemClick(AdapterView<?> av, View v, int position, long id) {
            String text = "Position: " + position
                    + "\nData: " + travelsWillBeListed.get(position);
            secilenOtobus=travelsWillBeListed.get(position);
            listSelected=true;
            showSelected.setText(text);


             }
        });
    }


/*
    public void writeDataTravel(View v){

        try {
            FileOutputStream fos = openFileOutput("travelList", Context.MODE_PRIVATE);

            String str="Ankara,Manisa,19:10,70.00,2017,01,02" + "\n"+
                    "Ankara,Manisa,19:45,65.00,2017,01,02"+"\n"+
                    "Ankara,Malatya,16:30,120.50,2017,01,03"+"\n"+
                    "Ankara,Malatya,17:15,82.50,2017,01,03"+"\n"+
                    "Istanbul,Ankara,15:45,120.45,2017,01,03"+"\n"+
                    "Istanbul,Ankara,16:20,100.00,2017,01,03"+"\n"+
                    "Ankara,Istanbul,17:20,75.00,2017,01,04"+"\n"+
                    "Ankara,Istanbul,18.40,55.50,2017,01,04"+"\n"+
                    "Ankara,Istanbul,18.40,70.50,2017,01,04"+"\n"+
"Ankara,Istanbul,19.40,80.50,2017,01,04"+"\n"+
"Ankara,Istanbul,20.40,90.50,2017,01,04"+"\n"+
"Ankara,Istanbul,21.40,100.50,2017,01,04"+"\n"+

"Istanbul,Ankara,21.40,100.50,2017,01,04"+"\n"+
"Istanbul,Ankara,18.40,70.50,2017,01,04"+"\n"+
"Istanbul,Ankara,19.40,80.50,2017,01,04"+"\n"+
"Istanbul,Ankara,20.40,90.50,2017,01,04"+"\n"+

"Istanbul,Ankara,21.40,100.50,2017,01,03"+"\n"+
"Istanbul,Ankara,18.40,70.50,2017,01,03"+"\n"+
"Istanbul,Ankara,19.40,80.50,2017,01,03"+"\n"+
"Istanbul,Ankara,20.40,90.50,2017,01,03"+"\n"+

"Izmir,Antalya,21.40,100.50,2017,01,03"+"\n"+
"Izmir,Antalya,18.40,70.50,2017,01,03"+"\n"+
"Izmir,Antalya,19.40,80.50,2017,01,03"+"\n"+
"Izmir,Antalya,20.40,90.50,2017,01,03"+"\n"+

"Izmir,Antalya,21.40,100.50,2017,01,04"+"\n"+
"Izmir,Antalya,18.40,70.50,2017,01,04"+"\n"+
"Izmir,Antalya,19.40,80.50,2017,01,04"+"\n"+
"Izmir,Antalya,20.40,90.50,2017,01,04"+"\n"+

"Antalya,Izmir,21.40,100.50,2017,01,04"+"\n"+
"Antalya,Izmir,18.40,70.50,2017,01,04"+"\n"+
"Antalya,Izmir,19.40,80.50,2017,01,04"+"\n"+
"Antalya,Izmir,20.40,90.50,2017,01,04"+"\n"+

"Antalya,Izmir,21.40,100.50,2017,01,03"+"\n"+
"Antalya,Izmir,18.40,70.50,2017,01,03"+"\n"+
"Antalya,Izmir,19.40,80.50,2017,01,03"+"\n"+
"Antalya,Izmir,20.40,90.50,2017,01,03"+"\n";


            fos.write(str.getBytes());
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }*/

    public void travelDetails(){

        int year = BusPage.sYear;
        int day =BusPage.sDay;
        int mounth= BusPage.sMonth +1;
        String destination= BusPage.selectedDestination;
        String origin= BusPage.selectedOrigin;

        for(int i =0; i<travels.size(); i++){

            travelString= travels.get(i).getTime() +" "+travels.get(i).getOriginCity()+" "+travels.get(i).getDestinationCity()+" "+travels.get(i).getPrice()+" "+travels.get(i).getDay()+"/"+travels.get(i).getMonth()+"/"+travels.get(i).getYear();
            if((travels.get(i).getDay())==day && travels.get(i).getYear()==year && travels.get(i).getMonth()==mounth && travels.get(i).getDestinationCity().equals(destination )&&travels.get(i).getOriginCity().equals(origin)){
                travelsWillBeListed.add(travels.get(i).getTime() +" "+travels.get(i).getOriginCity() +" "+ travels.get(i).getDestinationCity()+" "+travels.get(i).getPrice()+" "+travels.get(i).getDay()+"/"+travels.get(i).getMonth()+"/"+travels.get(i).getYear());
            }

            }
        if (travelsWillBeListed.size()==0){
            Toast.makeText(ListTravel.this, "There is no bus, Please select a different day", Toast.LENGTH_LONG).show();
        }
        }



    public  void selectSeat(View v){

        if (listSelected==true){
            Intent intent = new Intent(this, SeatSelection.class);
            startActivity(intent);
        }
        else{
            Toast.makeText(ListTravel.this, "Select a travel", Toast.LENGTH_LONG).show();
        }

    }
    }
   /* public void readDataTravel(View v) {

    }*/



