package com.example.activity.conversions;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;


public class ConversionActivity extends AppCompatActivity {

    private String LOG_TAG = ConversionActivity.class.getSimpleName();
    public static String UNIT="UNITS";
    public static String CURRENCY="CURRENCY";
    private String parameter;
    private String [] allCurrency;
    private String [] allMeasure;
    private String [] allUnits;
    private String currentFromValue;
    private String currentToValue;
    private String currentMeasure;
    private int positionFrom=0;
    private int positionTo=0;
    private int positionMeasure=0;
    private ImageButton btnAddFav;
    private Spinner spinnerFrom;
    private Spinner spinnerTo;
    private Spinner spinnerMeasure;
    private TextView resultEditTextfrom;
    private TextView resultEditText;
    private TextView equals;
    private EditText valueEditText;

//    Constantes de Idiomas
    public static String lenValue;
    public static String capValue;
    public static String weiValue;
    public static String stoValue;
    public static String temValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversion);
        Intent intent = getIntent();
        parameter = intent.getStringExtra("parameter").toString();

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        if(parameter.contains(UNIT)){
            myToolbar.setTitle(getString(R.string.units));
        }else if(parameter.contains(CURRENCY)){
            myToolbar.setTitle(getString(R.string.currency));
        }
        myToolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(myToolbar);

        Favorites oneFavorite = intent.getParcelableExtra("FavoriteSelected");
        btnAddFav = (ImageButton) findViewById(R.id.btnAddFav);
        spinnerFrom = (Spinner) findViewById(R.id.spinnerFrom);
        spinnerTo = (Spinner) findViewById(R.id.spinnerTo);
        resultEditTextfrom = (TextView) findViewById(R.id.resultTextViewFrom);
        resultEditText = (TextView) findViewById(R.id.resultTextView);
        equals = (TextView) findViewById(R.id.equalsTextView);

        if(parameter.contains(CURRENCY)){
            //Vamos al array para obtener la lista de paises
            fillSpinners(oneFavorite);
            onValidate();
            onInsertFavorite();
            onSeeFavorites();
            onExchange();
        }else if(parameter.contains(UNIT)){
            //Vamos a llenar de informacion los tres spinners
            lenValue = this.getString(R.string.Len);
            capValue = this.getString(R.string.Cap);
            weiValue = this.getString(R.string.Wei);
            stoValue = this.getString(R.string.Sto);
            temValue = this.getString(R.string.Tem);
            fillSpinners(oneFavorite);
            onValidate();
            onInsertFavorite();
            onSeeFavorites();
            onExchange();
        }
    }

    @Override
    public void onResume(){
        super.onResume();
        if (parameter.contentEquals(CURRENCY)){
            currentFromValue = allCurrency[positionFrom];
            currentToValue = allCurrency[positionTo];
        }else if(parameter.contentEquals(UNIT)){
            String arrayType = allMeasure[positionMeasure];
            int idArray = getResources().getIdentifier(arrayType, "array", ConversionActivity.this.getPackageName());
            String units[] = getResources().getStringArray(idArray);
            currentFromValue = units[positionFrom];
            currentToValue = units[positionTo];
        }
        onCheckFavorite();
        spinnerFrom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parameter.contentEquals(CURRENCY)) {
                    currentFromValue = allCurrency[position];
                    positionFrom = position;
                } else if (parameter.contentEquals(UNIT)) {
                    currentFromValue = allUnits[position];
                    positionFrom = position;
                }
                onCheckFavorite();
                //onValidate();
                valueEditText = (EditText) findViewById(R.id.valueEditText);
                onCalculate(valueEditText.getText());
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerTo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parameter.contentEquals(CURRENCY)) {
                    currentToValue = allCurrency[position];
                    positionTo = position;
                } else if (parameter.contentEquals(UNIT)) {
                    currentToValue = allUnits[position];
                    positionTo = position;
                }
                onCheckFavorite();
//                onValidate();
                valueEditText = (EditText) findViewById(R.id.valueEditText);
                onCalculate(valueEditText.getText());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void fillSpinners(final Favorites oneSelected){
        if(parameter.contentEquals(CURRENCY)) {
                allCurrency = getResources().getStringArray(R.array.currency_options);
                ArrayAdapter adapter = new SpinnerAdapter(this, android.R.layout.simple_spinner_item, allCurrency);
                spinnerFrom.setAdapter(adapter);
                spinnerTo.setAdapter(adapter);

                if (oneSelected != null) {
                    spinnerFrom.setSelection(adapter.getPosition(oneSelected.getConversionFROM()));
                    spinnerTo.setSelection(adapter.getPosition(oneSelected.getConversionTO()));
                }
                spinnerFrom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        currentFromValue = allCurrency[position];
                        onCheckFavorite();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                spinnerTo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        currentToValue = allCurrency[position];
                        onCheckFavorite();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
       }else if(parameter.contentEquals(UNIT)) {
            spinnerMeasure = (Spinner) findViewById(R.id.spinnerMeasure);
            allMeasure = getResources().getStringArray(R.array.measure_options);
            ArrayAdapter adapterUnits = new SpinnerAdapter(this, android.R.layout.simple_spinner_item, allMeasure);
            spinnerMeasure.setAdapter(adapterUnits);
            spinnerMeasure.setVisibility(View.VISIBLE);
            TextView textMeasure = (TextView) findViewById(R.id.textLabelMeasure);
            textMeasure.setVisibility(View.VISIBLE);

            if (oneSelected != null) {
                spinnerMeasure.setSelection(adapterUnits.getPosition(oneSelected.getMeasureUnit()));
            }
            spinnerMeasure.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    Spinner spinnerFrom = (Spinner) findViewById(R.id.spinnerFrom);
                    Spinner spinnerTo = (Spinner) findViewById(R.id.spinnerTo);
                    currentMeasure = allMeasure[position].toString();
                    positionMeasure = position;
                    ArrayAdapter adapter = null;
                    int idArray;
                    if(currentMeasure.contentEquals(lenValue)){
                        idArray = getResources().getIdentifier(lenValue, "array", ConversionActivity.this.getPackageName());
                        allUnits = getResources().getStringArray(idArray);
                        adapter = new SpinnerAdapter(ConversionActivity.this,android.R.layout.simple_spinner_item, allUnits);
                    }else if(currentMeasure.contentEquals(capValue)){
                        idArray = getResources().getIdentifier(capValue, "array", ConversionActivity.this.getPackageName());
                        allUnits = getResources().getStringArray(idArray);
                        adapter = new SpinnerAdapter(ConversionActivity.this,android.R.layout.simple_spinner_item, allUnits);
                    }else if(currentMeasure.contentEquals(weiValue)){
                        idArray = getResources().getIdentifier(weiValue, "array", ConversionActivity.this.getPackageName());
                        allUnits = getResources().getStringArray(idArray);
                        adapter = new SpinnerAdapter(ConversionActivity.this,android.R.layout.simple_spinner_item, allUnits);
                    }else if(currentMeasure.contentEquals(stoValue)){
                        idArray = getResources().getIdentifier(stoValue, "array", ConversionActivity.this.getPackageName());
                        allUnits = getResources().getStringArray(idArray);
                        adapter = new SpinnerAdapter(ConversionActivity.this,android.R.layout.simple_spinner_item, allUnits);
                    }else if(currentMeasure.contentEquals(temValue)){
                        idArray = getResources().getIdentifier(temValue, "array", ConversionActivity.this.getPackageName());
                        allUnits = getResources().getStringArray(idArray);
                        adapter = new SpinnerAdapter(ConversionActivity.this,android.R.layout.simple_spinner_item, allUnits);
                    }

//                    switch (currentMeasure) {
//                        case "Length":
//                            idArray = R.array.Length;
//                            allUnits = getResources().getStringArray(idArray);
//                            adapter = new SpinnerAdapter(ConversionActivity.this,android.R.layout.simple_spinner_item, allUnits);
//                            break;
//                        case "Capacity":
//                            idArray = R.array.Capacity;
//                            allUnits = getResources().getStringArray(idArray);
//                            adapter = new SpinnerAdapter(ConversionActivity.this,android.R.layout.simple_spinner_item, allUnits);
//                            break;
//                        case "Weight":
//                            idArray = R.array.Weight;
//                            allUnits = getResources().getStringArray(idArray);
//                            adapter = new SpinnerAdapter(ConversionActivity.this,android.R.layout.simple_spinner_item, allUnits);
//                            break;
//                        case "Storage":
//                            idArray = R.array.Storage;
//                            allUnits = getResources().getStringArray(idArray);
//                            adapter = new SpinnerAdapter(ConversionActivity.this,android.R.layout.simple_spinner_item, allUnits);
//                            break;
//                        case "Temperature":
//                            idArray = R.array.Temperature;
//                            allUnits = getResources().getStringArray(idArray);
//                            adapter = new SpinnerAdapter(ConversionActivity.this,android.R.layout.simple_spinner_item, allUnits);
//                            break;
//                    }

                    spinnerFrom.setAdapter(adapter);
                    spinnerTo.setAdapter(adapter);
                    if (oneSelected != null) {
                        for (int i = 0; i < allUnits.length; i++) {
                            if (allUnits[i].contentEquals(oneSelected.getConversionFROM())) {
                                spinnerFrom.setSelection(i);
                                break;
                            }
                        }
                        for (int i = 0; i < allUnits.length; i++) {
                            if (allUnits[i].contentEquals(oneSelected.getConversionTO())) {
                                spinnerTo.setSelection(i);
                                break;
                            }
                        }
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }

    }

    public void onValidate(){

        valueEditText = (EditText) findViewById(R.id.valueEditText);
        Button btnCalculate = (Button) findViewById(R.id.btnCalculate);
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCalculate(valueEditText.getText());
            }
        });
        //onCalculate(valueEditText.getText());
    }

    public void onCalculate(CharSequence charSequence){
        if(charSequence.length()>0 && Double.parseDouble(charSequence.toString())>0){
            if(parameter.contentEquals(CURRENCY)) {
                //We are going to the ws to bring the data
                AsyncCurrency async = new AsyncCurrency();
                async.execute(charSequence.toString());
            }else if(parameter.contentEquals(UNIT)){
                //We are going to the formulas class
                UnitConversion unitConversion = new UnitConversion(this);
                DecimalFormat decimal = new DecimalFormat("#,###.#####");
                double result = unitConversion.convert(charSequence.toString(), currentMeasure, currentFromValue, currentToValue);
                double valueFrom = Double.valueOf(charSequence.toString());

                //Format
                String resultFrom = decimal.format(valueFrom);
                String resultTo = decimal.format(result);
                resultFrom = resultFrom+" "+currentFromValue;
                resultTo = resultTo+" "+currentToValue;

                //Send
                resultEditTextfrom.setText(resultFrom);
                resultEditText.setText(resultTo);
                equals.setVisibility(View.VISIBLE);
            }
        }else{
            Toast.makeText(ConversionActivity.this,R.string.ToastInsertValue, Toast.LENGTH_SHORT).show();
        }
    }

    public void onInsertFavorite(){
            btnAddFav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                 AlertDialog.Builder adBuilder = new AlertDialog.Builder(ConversionActivity.this);
                 adBuilder.setTitle(R.string.DialogTitle);
                 SQLHelper sqlHelp = new SQLHelper(ConversionActivity.this);
                 //Validate duplicated first
                 if(sqlHelp.onValidateDuplicate(positionFrom,positionTo, positionMeasure, parameter)) {
                     SQLiteDatabase sqlDB = sqlHelp.getWritableDatabase();
                     ContentValues values = new ContentValues();
                     if(parameter.contentEquals(CURRENCY)) {
                         values.put(SQLHelper.conversionFROM, currentFromValue);
                         values.put(SQLHelper.conversionTO, currentToValue);
                         values.put(SQLHelper.id_localeFrom, positionFrom);
                         values.put(SQLHelper.id_localeTo, positionTo);
                         sqlDB.insert(SQLHelper.TABLE_FAV_CURRENCY, null, values);
                     }else if(parameter.contentEquals(UNIT)) {
                         values.put(SQLHelper.conversionFROM, currentFromValue);
                         values.put(SQLHelper.conversionTO, currentToValue);
                         values.put(SQLHelper.measureUnit, currentMeasure);
                         values.put(SQLHelper.id_localeFrom, positionFrom);
                         values.put(SQLHelper.id_localeTo, positionTo);
                         values.put(SQLHelper.id_localeMeasure, positionMeasure);
                         sqlDB.insert(SQLHelper.TABLE_FAV_UNITS, null, values);
                     }
                        sqlDB.close();
                        Log.d(LOG_TAG, "Correct");
                        adBuilder.setMessage(R.string.DialogCorrect).setNeutralButton("OK", new DialogInterface.OnClickListener() {
                         @Override
                         public void onClick(DialogInterface dialog, int which) {
                             dialog.dismiss();
                         }
                     });
                     AlertDialog alertDialog = adBuilder.create();
                     alertDialog.show();
                     btnAddFav.setBackgroundResource(R.mipmap.favon);
                 }else{
                     adBuilder.setMessage(R.string.DialogDuplicate).setNeutralButton("OK", new DialogInterface.OnClickListener() {
                         @Override
                         public void onClick(DialogInterface dialog, int which) {
                             dialog.dismiss();
                         }
                     });
                     AlertDialog alertDialog = adBuilder.create();
                     alertDialog.show();
                 }
                }
            });

    }


    public void onSeeFavorites(){
        ImageButton btnSeeFav = (ImageButton) findViewById(R.id.btnSeeFav);
        btnSeeFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(),FavoritesActivity.class);
                intent.putExtra("parameter",parameter);
                startActivity(intent);
            }
        });
    }


    public void onExchange(){
        ImageButton btnExchange = (ImageButton) findViewById(R.id.btnExchange);
        final Spinner spinnerFrom = (Spinner) findViewById(R.id.spinnerFrom);
        final Spinner spinnerTo = (Spinner) findViewById(R.id.spinnerTo);
        btnExchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinnerFrom.setSelection(positionTo);
                spinnerTo.setSelection(positionFrom);
                onValidate();
            }
        });
    }

    public void onCheckFavorite(){
        SQLHelper sql = new SQLHelper(this);
        if(!sql.onValidateDuplicate(positionFrom, positionTo, positionMeasure, parameter)){
            btnAddFav.setBackgroundResource(R.mipmap.favon);
        }else{
            btnAddFav.setBackgroundResource(R.mipmap.favoff);
        }
    }

    class AsyncCurrency extends AsyncTask<String,String,HashMap> {
        ProgressDialog pgRing;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
             pgRing = ProgressDialog.show(
                    ConversionActivity.this,getString(R.string.PleaseWait),getString(R.string.ProcessingConversion),true);
                pgRing.setCancelable(false);
        }

        @Override
        protected HashMap<String,String> doInBackground(String... params) {
            CurrencyClient wsClient = new CurrencyClient();
            HashMap result=null;
            try {
                result = wsClient.calculateConversion(params[0], currentFromValue, currentToValue);
            }catch (XmlPullParserException | IOException ex){

            }
            return result;
        }

        @Override
        protected void onPostExecute(HashMap s) {
            super.onPostExecute(s);
            if(pgRing.isShowing()) {
                pgRing.dismiss();
                if(s!=null && !s.get(CurrencyClient.ERROR).toString().contentEquals(CurrencyClient.INTERNET_ERROR)) {
                    if(!s.get(CurrencyClient.ERROR).toString().contentEquals(CurrencyClient.ERROR)) {
                        //Values from service
                        String resultFrom = s.get(CurrencyClient.FROM).toString();
                        String resultTo = s.get(CurrencyClient.TO).toString();

                        //Send
                        resultEditTextfrom.setText(resultFrom);
                        resultEditText.setText(resultTo);
                        equals.setVisibility(View.VISIBLE);

                    }else{
                        AlertDialog.Builder adBuilder = new AlertDialog.Builder(ConversionActivity.this);
                        adBuilder.setTitle(R.string.ConversionError);
                        adBuilder.setMessage(R.string.ConversionErrorDueService).setNeutralButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        AlertDialog alertDialog =  adBuilder.create();
                        alertDialog.show();
                    }
                }else{
                    //No Internet
                    AlertDialog.Builder adBuilder = new AlertDialog.Builder(ConversionActivity.this);
                    adBuilder.setTitle(R.string.ConversionError);
                    adBuilder.setMessage(R.string.ConversionErrorDueInternet).setNeutralButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    AlertDialog alertDialog =  adBuilder.create();
                    alertDialog.show();
                }
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_conversion, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }
}
