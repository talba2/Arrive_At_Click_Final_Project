package com.example.arrive_at_click;

import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.arrive_at_click.adapter.ListSiteAdapter;
import com.example.arrive_at_click.database.DatabaseHelper;
import com.example.arrive_at_click.model.Site;
import com.google.android.gms.maps.model.LatLng;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ChooseSearchMethod extends AppCompatActivity {

    Spinner CategorySpinner;
    Spinner AddressSpinner;
    Spinner BranchSpinner;
    public static String SiteName;
    public static String FieldName;
    public static String Address;
    private ArrayList<Site> SitesList;
    private ListSiteAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_search_method);

        TextView tvTitle = (TextView) findViewById(R.id.tvTitle);
        //set the font size 23
        tvTitle.setTextSize(23);

        TextView tvChooseCategory = (TextView) findViewById(R.id.tvChooseCategory);
        //set the font size 20
        tvChooseCategory.setTextSize(20);

        TextView tvChooseBranch = (TextView) findViewById(R.id.tvChooseBranch);
        //set the font size 20
        tvChooseBranch.setTextSize(20);

        TextView tvChooseAddress = (TextView) findViewById(R.id.tvChooseAddress);
        //set the font size 20
        tvChooseAddress.setTextSize(20);

        CategorySpinner = (Spinner) findViewById(R.id.CategorySpinner);
        AddressSpinner = (Spinner) findViewById(R.id.AddressSpinner);
        BranchSpinner = (Spinner) findViewById(R.id.BranchSpinner);

        CategorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView adapt, View v, int i, long lng) {

                BranchSpinner.setAdapter(null);
                AddressSpinner.setAdapter(null);

                List<String> BranchArray = new ArrayList<String>();
                ArrayAdapter<String> branchAdapter;
                switch (CategorySpinner.getSelectedItem().toString()) {
                    case "סניף בנק":
                        BranchArray.clear();
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

                        branchAdapter = new ArrayAdapter<String>(ChooseSearchMethod.this, android.R.layout.simple_spinner_item, BranchArray);
                        branchAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        BranchSpinner.setAdapter(branchAdapter);
                        break;
                    case "בית חולים":
                        BranchArray.clear();
                        BranchArray.add("איכילוב");
                        BranchArray.add("שיבא תל השומר");

                        branchAdapter = new ArrayAdapter<String>(ChooseSearchMethod.this, android.R.layout.simple_spinner_item, BranchArray);
                        branchAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        BranchSpinner.setAdapter(branchAdapter);

                        break;
                    case "קופת חולים":
                        BranchArray.clear();
                        BranchArray.add("כללית");
                        BranchArray.add("מאוחדת");
                        BranchArray.add("מכבי");
                        BranchArray.add("לאומית");

                        branchAdapter = new ArrayAdapter<String>(ChooseSearchMethod.this, android.R.layout.simple_spinner_item, BranchArray);
                        branchAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        BranchSpinner.setAdapter(branchAdapter);
                        break;
                    case "סניף דואר":
                        BranchArray.clear();
                        BranchArray.add("סניפי דואר");

                        branchAdapter = new ArrayAdapter<String>(ChooseSearchMethod.this, android.R.layout.simple_spinner_item, BranchArray);
                        branchAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        BranchSpinner.setAdapter(branchAdapter);

                        //add address

                        break;
                    case "בית מרקחת":
                        BranchArray.clear();
                        BranchArray.add("בית מרקחת");
                        BranchArray.add("סופר פארם");
                        BranchArray.add("ניו פארם");

                        branchAdapter = new ArrayAdapter<String>(ChooseSearchMethod.this, android.R.layout.simple_spinner_item, BranchArray);
                        branchAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        BranchSpinner.setAdapter(branchAdapter);

                        break;
                    case "עירייה":
                        BranchArray.clear();
                        //add branches
                        break;
                    case "בחר קטגוריה...":
                        break;
                    default: // in case "choose category"
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }
        });

        BranchSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView adapt, View v, int i, long lng) {
                String selectedBranch = BranchSpinner.getSelectedItem().toString();
                if (selectedBranch != "") {
                    //check if the database exists
                    ConnectionClass.DBHelper = new DatabaseHelper(ChooseSearchMethod.this);

                    File database = getApplicationContext().getDatabasePath(DatabaseHelper.DBNAME);

                    if (database.exists() == false) {
                        ConnectionClass.DBHelper.getReadableDatabase();
                        //copy db
                        ConnectionClass con = new ConnectionClass();
                        if (con.copyDatabase(ChooseSearchMethod.this))
                            Toast.makeText(ChooseSearchMethod.this, "copy db successfully", Toast.LENGTH_SHORT).show();
                        else {
                            Toast.makeText(ChooseSearchMethod.this, "copy db error", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }


                    switch (CategorySpinner.getSelectedItem().toString()) {
                        case "סניף בנק":
                            SiteName = selectedBranch;
                            FieldName = "name";
                            break;
                        case "בית חולים":
                            SiteName = "בית-חולים";
                            FieldName = "category";
                            break;
                        case "קופת חולים":
                            SiteName = selectedBranch;
                            FieldName = "name";
                            break;
                        case "סניף דואר":
                            SiteName = "סניף דואר";
                            FieldName = "category";
                            break;
                        case "בית מרקחת":
                            SiteName = selectedBranch;
                            FieldName = "name";
                            break;
                        case "מחלקת-רווחה":
                            SiteName = selectedBranch;
                            FieldName = "category";
                            break;
                        case "בית אבות":
                            SiteName = selectedBranch;
                            FieldName = "category";
                            break;
                        case "מוסד סיעוד גריאטרי":
                            SiteName = selectedBranch;
                            FieldName = "category";
                            break;
                        case "מרפאה כירורגית":
                            SiteName = selectedBranch;
                            FieldName = "category";
                            break;
                        default:
                            SiteName = null;
                            break;
                    }

                    SitesList = ConnectionClass.DBHelper.getListSites("*", FieldName + " LIKE '%" + SiteName + "%'");
                    //init adapter
                    adapter = new ListSiteAdapter(ChooseSearchMethod.this, SitesList);

                    int NumOfSites = adapter.getCount();
                    String[] sitesAddress = new String[NumOfSites];
                    for (int j = 0; j < NumOfSites; ++j)
                        sitesAddress[j] = SitesList.get(j).getAddSite();

                    ArrayAdapter<String> addressAdapter = new ArrayAdapter<String>(ChooseSearchMethod.this, android.R.layout.simple_spinner_item, sitesAddress);
                    addressAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    AddressSpinner.setAdapter(addressAdapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }

        });
    }

    public void OnClickChooseSite(View v)
    {
        String categorySelected=CategorySpinner.getSelectedItem().toString();
        if(categorySelected.equals("בחר קטגוריה..."))
            Toast.makeText(this, "לא בחרת מה אתה רוצה לחפש, נסה שנית", Toast.LENGTH_LONG).show();
        else
        {
            Address =AddressSpinner.getSelectedItem().toString();
            Intent i = new Intent(ChooseSearchMethod.this,MapPage.class);
            startActivity(i);
        }
    }

    public void OnClickChoose(View v)
    {
        String categorySelected=CategorySpinner.getSelectedItem().toString();
        if(categorySelected.equals("בחר קטגוריה..."))
        {
            Intent i = new Intent(ChooseSearchMethod.this,Categories.class);
            startActivity(i);
        }
        else {
            Intent i = null;
            switch (CategorySpinner.getSelectedItem().toString()) {
                case "סניף בנק":
                    i = new Intent(ChooseSearchMethod.this, Banks.class);
                    Categories.categoryName = "Banks";
                    break;
                case "בית חולים":
                    i = new Intent(ChooseSearchMethod.this, MapPage.class);
                    Categories.categoryName = "Hospitals";
                    break;
                case "קופת חולים":
                    i = new Intent(ChooseSearchMethod.this, HMO.class);
                    Categories.categoryName = "HMO";
                    break;
                case "סניף דואר":
                    i = new Intent(ChooseSearchMethod.this, MapPage.class);
                    Categories.categoryName = "Postal";
                    break;
                case "בית מרקחת":
                    i = new Intent(ChooseSearchMethod.this, Pharmacies.class);
                    Categories.categoryName = "Pharmacies";
                    break;
                case "מחלקת-רווחה":
                    i = new Intent(ChooseSearchMethod.this, MapPage.class);
                    Categories.categoryName = "Municipality";
                    Municipality.MunicipalityWasClicked="מחלקת-רווחה";
                    break;
                case "בית אבות":
                    i = new Intent(ChooseSearchMethod.this, MapPage.class);
                    Categories.categoryName = "Municipality";
                    Municipality.MunicipalityWasClicked="בית אבות";
                    break;
                case "מוסד סיעוד גריאטרי":
                    i = new Intent(ChooseSearchMethod.this, MapPage.class);
                    Categories.categoryName = "Municipality";
                    Municipality.MunicipalityWasClicked="מוסד סיעוד גריאטרי";
                    break;
                case "מרפאה כירורגית":
                    i = new Intent(ChooseSearchMethod.this, MapPage.class);
                    Categories.categoryName = "Municipality";
                    Municipality.MunicipalityWasClicked="מרפאה כירורגית";
                    break;
                default: // in case "choose category"
                    break;
            }
            startActivity(i);
        }
    }
}
