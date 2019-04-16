package com.example.activity.conversions;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class FavoritesActivity extends AppCompatActivity {

    ListView listFavorites;
    MyArrayAdapter myAdapter;
    TextView emptyList;
    ArrayList<Favorites> favorites;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
        Intent intent = getIntent();
        String parameter = intent.getStringExtra("parameter");

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        if(parameter.contains(ConversionActivity.UNIT)){
            myToolbar.setTitle(getString(R.string.favUnits));
        }else if(parameter.contains(ConversionActivity.CURRENCY)){
            myToolbar.setTitle(getString(R.string.favCurrency));
        }
        myToolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(myToolbar);

        listFavorites = (ListView) findViewById(R.id.listFavs);
        emptyList = (TextView) findViewById(R.id.emptyList);
        onSelectFav(parameter);
        //onDeleteFav(parameter);
    }

    public void onSelectFav(final String parameter){
        //Query para ir por favoritos
        SQLHelper sqlHelper = new SQLHelper(this);
        ArrayList<Favorites> favoritesDB = sqlHelper.onSelectFavorites(parameter);
        //Re acomodar los parametros desde BD
        favorites = onTranslateFavoritesFromDB(parameter, favoritesDB);
        myAdapter = new MyArrayAdapter(this, android.R.layout.simple_list_item_1,parameter,favorites);
        listFavorites.setAdapter(myAdapter);
        listFavorites.setEmptyView(emptyList);
        myAdapter.notifyDataSetChanged();
        listFavorites.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Favorites oneFav = favorites.get(position);
                Intent intent = new Intent(FavoritesActivity.this, ConversionActivity.class);
                intent.putExtra("FavoriteSelected", oneFav);
                intent.putExtra("parameter", parameter);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }

    public ArrayList<Favorites> onTranslateFavoritesFromDB(String parameter, ArrayList<Favorites> favoritesDB){
        ArrayList<Favorites> favorites= new ArrayList<>();
        if(parameter.contentEquals(ConversionActivity.UNIT)){
            String [] allMeasure = getResources().getStringArray(R.array.measure_options);
            String [] allUnits = null;
            for(Favorites favorite:favoritesDB){
                String currentMeasure = allMeasure[favorite.getId_localeMeasure()];
                int idArray;
                if(currentMeasure.contentEquals(ConversionActivity.lenValue)){
                    idArray = getResources().getIdentifier(ConversionActivity.lenValue, "array", FavoritesActivity.this.getPackageName());
                    allUnits = getResources().getStringArray(idArray);
                }else if(currentMeasure.contentEquals(ConversionActivity.capValue)){
                    idArray = getResources().getIdentifier(ConversionActivity.capValue, "array", FavoritesActivity.this.getPackageName());
                    allUnits = getResources().getStringArray(idArray);
                }else if(currentMeasure.contentEquals(ConversionActivity.weiValue)){
                    idArray = getResources().getIdentifier(ConversionActivity.weiValue, "array", FavoritesActivity.this.getPackageName());
                    allUnits = getResources().getStringArray(idArray);
                }else if(currentMeasure.contentEquals(ConversionActivity.stoValue)){
                    idArray = getResources().getIdentifier(ConversionActivity.stoValue, "array", FavoritesActivity.this.getPackageName());
                    allUnits = getResources().getStringArray(idArray);
                }else if(currentMeasure.contentEquals(ConversionActivity.temValue)){
                    idArray = getResources().getIdentifier(ConversionActivity.temValue, "array", FavoritesActivity.this.getPackageName());
                    allUnits = getResources().getStringArray(idArray);
                }

                String from = allUnits[favorite.getId_localeFrom()];
                String to = allUnits[favorite.getId_localeTo()];
                favorite.setMeasureUnit(currentMeasure);
                favorite.setConversionFROM(from);
                favorite.setConversionTO(to);
                favorites.add(favorite);
            }
        }else if(parameter.contentEquals(ConversionActivity.CURRENCY)){
            String [] allCurrency = getResources().getStringArray(R.array.currency_options);
            for(Favorites favorite:favoritesDB){
                String from = allCurrency[favorite.getId_localeFrom()];
                String to = allCurrency[favorite.getId_localeTo()];
                favorite.setConversionFROM(from);
                favorite.setConversionTO(to);
                favorites.add(favorite);
            }
        }
        return favorites;
    }

    public void onDeleteFav(String parameter){
        SQLHelper sqlHelper = new SQLHelper(this);
        ListView listFavorites = (ListView) findViewById(R.id.listFavs);
        final ArrayList<Favorites> favorites = sqlHelper.onSelectFavorites(parameter);
        final MyArrayAdapter myAdapter = new MyArrayAdapter(this, android.R.layout.simple_list_item_1,parameter,favorites);
        listFavorites.setAdapter(myAdapter);

        listFavorites.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {

            }

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                return false;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {

            }
        });

        listFavorites.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_favorites, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }


    class ActionBarCallBack implements ActionMode.Callback {

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            // TODO Auto-generated method stub
            return false;
        }

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            // TODO Auto-generated method stub
            mode.getMenuInflater().inflate(R.menu.context_menu, menu);
            return true;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            // TODO Auto-generated method stub

        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            // TODO Auto-generated method stub

            mode.setTitle("CheckBox is Checked");
            return false;
        }
    }
}
