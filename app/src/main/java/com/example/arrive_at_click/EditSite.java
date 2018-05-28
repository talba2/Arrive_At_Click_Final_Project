package com.example.arrive_at_click;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.arrive_at_click.adapter.ListSiteAdapter;
import com.example.arrive_at_click.database.DatabaseHelper;
import com.example.arrive_at_click.model.Site;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class EditSite extends AppCompatActivity {

    public static String site;
    public static String field;
    Spinner CategorySpinner;
    Spinner SecondCategorySpinner;
    Spinner SiteSpinner;
    private ArrayList<Site> SitesList;
    private ListSiteAdapter adapter;
    public int NumOfRows;
    private int NumOfBanks = 17;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_site);


        CategorySpinner=(Spinner)findViewById(R.id.CategorySpinner);
        SiteSpinner=(Spinner)findViewById(R.id.SiteSpinner);
        SecondCategorySpinner=(Spinner)findViewById(R.id.SecondCategorySpinner);

        File database=getApplicationContext().getDatabasePath(DatabaseHelper.DBNAME);
        if(database.exists()==false){
            ConnectionClass.DBHelper.getReadableDatabase();
            //copy db
            ConnectionClass con=new ConnectionClass();
            if(con.copyDatabase(this))
                Toast.makeText(this,"copy db successfully",Toast.LENGTH_SHORT).show();
            else
            {
                Toast.makeText(this,"copy db error",Toast.LENGTH_SHORT).show();
                return;
            }
        }


        SitesList  = ConnectionClass.DBHelper.getListSites("distinct(category)","Sites","category IS NOT NULL" );

        //init adapter
        adapter = new ListSiteAdapter(this,SitesList);

        NumOfRows=adapter.getCount();
        String[] sitesAddress=new String[NumOfRows];
        for(int i=0;i<NumOfRows;++i)
            sitesAddress[i]=SitesList.get(i).getAddSite();

        ArrayAdapter<String> SpinnerAdapter=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,sitesAddress);
        SpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        CategorySpinner.setAdapter(SpinnerAdapter);

        CategorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {


            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                List<String> BranchArray = new ArrayList<String>();
                ArrayAdapter<String> categoryAdapter;
                switch (CategorySpinner.getSelectedItem().toString()) {
                    case "סניף בנק":
                        BranchArray.clear();
                        BranchArray.add("בחר סניף...");
                        BranchArray.add("בנק לאומי");
                        BranchArray.add("בנק הפועלים");
                        BranchArray.add("בנק דיסקונט");
                        BranchArray.add("בנק מרכנתיל");
                        BranchArray.add("בנק מזרחי טפחות");
                        BranchArray.add("יובנק");
                        BranchArray.add("Citibank");
                        BranchArray.add("בנק מסד");
                        BranchArray.add("סליקה בנקאי");
                        BranchArray.add("בנק אגוד");
                        BranchArray.add("בנק יהב");
                        BranchArray.add("בנק אוצר החייל");
                        BranchArray.add("הבנק הבינלאומי");
                        BranchArray.add("בנק ירושלים");
                        BranchArray.add("בנק פועלי אגודת ישראל");
                        BranchArray.add("שירותי בנק אוטומטיים");
                        BranchArray.add("בנק דקסיה");

                        categoryAdapter = new ArrayAdapter<String>(EditSite.this, android.R.layout.simple_spinner_item, BranchArray);
                        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        SecondCategorySpinner.setAdapter(categoryAdapter);
                        break;
                    case "בית חולים":
                        BranchArray.clear();
                        BranchArray.add("בחר סניף...");
                        BranchArray.add("איכילוב");
                        BranchArray.add("שיבא תל השומר");

                        categoryAdapter = new ArrayAdapter<String>(EditSite.this, android.R.layout.simple_spinner_item, BranchArray);
                        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        SecondCategorySpinner.setAdapter(categoryAdapter);

                        break;
                    case "קופת חולים":
                        BranchArray.clear();
                        BranchArray.add("בחר סניף...");
                        BranchArray.add("כללית");
                        BranchArray.add("מאוחדת");
                        BranchArray.add("מכבי");
                        BranchArray.add("לאומית");

                        categoryAdapter = new ArrayAdapter<String>(EditSite.this, android.R.layout.simple_spinner_item, BranchArray);
                        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        SecondCategorySpinner.setAdapter(categoryAdapter);
                        break;
                    case "סניף דואר":
                        BranchArray.clear();
                        BranchArray.add("בחר סניף...");
                        BranchArray.add("סניפי דואר");

                        categoryAdapter = new ArrayAdapter<String>(EditSite.this, android.R.layout.simple_spinner_item, BranchArray);
                        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        SecondCategorySpinner.setAdapter(categoryAdapter);

                        //add address

                        break;
                    case "בית מרקחת":
                        BranchArray.clear();
                        BranchArray.add("בחר סניף...");
                        BranchArray.add("בית מרקחת");
                        BranchArray.add("סופר פארם");
                        BranchArray.add("ניו פארם");

                        categoryAdapter = new ArrayAdapter<String>(EditSite.this, android.R.layout.simple_spinner_item, BranchArray);
                        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        SecondCategorySpinner.setAdapter(categoryAdapter);

                        break;
                    case "עירייה":
                        BranchArray.clear();
                        BranchArray.add("בחר סניף...");
                        //add branches

                        break;
                    default: // in case "choose category"
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        SecondCategorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView adapt, View v, int i, long lng)
            {
                //check if the database exists
                ConnectionClass.DBHelper= new DatabaseHelper(EditSite.this);

                File database=getApplicationContext().getDatabasePath(DatabaseHelper.DBNAME);
                if(database.exists()==false){
                    ConnectionClass.DBHelper.getReadableDatabase();
                    //copy db
                    ConnectionClass con=new ConnectionClass();
                    if(con.copyDatabase(EditSite.this))
                        Toast.makeText(EditSite.this,"copy db successfully",Toast.LENGTH_SHORT).show();
                    else
                    {
                        Toast.makeText(EditSite.this,"copy db error",Toast.LENGTH_SHORT).show();
                        return;
                    }
                }

                List<String> AddressArray=new ArrayList<String>();
                ArrayAdapter<String> categoryAdapter;

                String selectedBranch=SecondCategorySpinner.getSelectedItem().toString();

                switch(CategorySpinner.getSelectedItem().toString())
                {
                    case "סניף בנק":
                        site=selectedBranch;
                        field="name";
                        break;
                    case "בית חולים":
                        site="בית-חולים";
                        field="category";
                        break;
                    case "קופת חולים":
                        site=selectedBranch;
                        field="name";
                        break;
                    case "סניף דואר":
                        site="סניף דואר";
                        field="category";
                        break;
                    case "בית מרקחת":
                        site=selectedBranch;
                        field="name";
                        break;
                    case "עירייה":
                        site=selectedBranch;
                        field="category";
                        break;
                    default:
                        site=null;
                        break;
                }

                SitesList  = ConnectionClass.DBHelper.getListSites("*","Sites",field+" LIKE '%" + site + "%'");
                //init adapter
                adapter = new ListSiteAdapter(EditSite.this,SitesList);

                int NumOfSites=adapter.getCount();
                String[] sitesAddress=new String[NumOfSites];
                for(int j=0;j<NumOfSites;++j)
                    sitesAddress[j]=SitesList.get(j).getAddSite();

                ArrayAdapter<String> SpinnerAdapter=new ArrayAdapter<String>(EditSite.this, android.R.layout.simple_spinner_item,sitesAddress);
                SpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                SiteSpinner.setAdapter(SpinnerAdapter);

            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView)
            {

            }
        });

    }

    public void OnClickSearch(View v)
    {
        if(CategorySpinner.getSelectedItem().toString()=="בחר קטגוריה" && SiteSpinner.getSelectedItem().toString()=="בחר כתובת" &&SecondCategorySpinner.getSelectedItem().toString()=="בחר סניף")
            Toast.makeText(this, "לא בחרת מה אתה רוצה לחפש, נסה שנית", Toast.LENGTH_LONG).show();
        else if(CategorySpinner.getSelectedItem().toString()!="בחר קטגוריה" && SecondCategorySpinner.getSelectedItem().toString()=="בחר סניף")
            Toast.makeText(this, "לא בחרת סניף, נסה שנית", Toast.LENGTH_LONG).show();
        else
        {
            Intent i = new Intent(EditSite.this,SiteToUpdate.class);
            startActivity(i);
        }
    }

    public void OnClickChoose(View v)
    {
        if(CategorySpinner.getSelectedItem().toString()=="בחר קטגוריה")
        {
            Intent i = new Intent(EditSite.this,Categories.class);
            startActivity(i);
        }
        else
        {
            Intent i=null;
            switch (CategorySpinner.getSelectedItem().toString())
            {
                case "סניף בנק":
                    i = new Intent(EditSite.this,Banks.class);
                    Categories.categoryName="Banks";
                    break;
                case "בית חולים":
                    i = new Intent(EditSite.this,MapPage.class);
                    Categories.categoryName="Hospitals";
                    break;
                case "קופת חולים":
                    i = new Intent(EditSite.this,HMO.class);
                    Categories.categoryName="HMO";
                    break;
                case "סניף דואר":
                    i = new Intent(EditSite.this,MapPage.class);
                    Categories.categoryName="Postal";
                    break;
                case "בית מרקחת":
                    i = new Intent(EditSite.this,Pharmacies.class);
                    Categories.categoryName="Pharmacies";
                    break;
                case "עירייה":
                    i = new Intent(EditSite.this,Municipality.class);
                    Categories.categoryName="Municipality";
                    break;
                default: // in case "choose category"
                    break;
            }
            startActivity(i);
        }
    }
}
