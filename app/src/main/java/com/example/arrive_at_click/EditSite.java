package com.example.arrive_at_click;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.arrive_at_click.adapter.ListSiteAdapter;
import com.example.arrive_at_click.database.DatabaseHelper;
import com.example.arrive_at_click.model.Site;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class EditSite extends AppCompatActivity {

    Spinner CategorySpinner;
    Spinner AddressSpinner;
    Spinner BranchSpinner;
    public static String SiteName;
    public static String FieldName;
    public static String Address=null;
    public static int idSite=-1;
    private ArrayList<Site> SitesList;
    private ArrayList<Site> siteProperties;
    private ListSiteAdapter adapter;
    private int NumOfBanks = 17;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_site);


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

                        branchAdapter = new ArrayAdapter<String>(EditSite.this, android.R.layout.simple_spinner_item, BranchArray);
                        branchAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        BranchSpinner.setAdapter(branchAdapter);
                        break;
                    case "בית חולים":
                        BranchArray.clear();
                        BranchArray.add("איכילוב");
                        BranchArray.add("שיבא תל השומר");

                        branchAdapter = new ArrayAdapter<String>(EditSite.this, android.R.layout.simple_spinner_item, BranchArray);
                        branchAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        BranchSpinner.setAdapter(branchAdapter);

                        break;
                    case "קופת חולים":
                        BranchArray.clear();
                        BranchArray.add("כללית");
                        BranchArray.add("מאוחדת");
                        BranchArray.add("מכבי");
                        BranchArray.add("לאומית");

                        branchAdapter = new ArrayAdapter<String>(EditSite.this, android.R.layout.simple_spinner_item, BranchArray);
                        branchAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        BranchSpinner.setAdapter(branchAdapter);
                        break;
                    case "סניף דואר":
                        BranchArray.clear();
                        BranchArray.add("סניפי דואר");

                        branchAdapter = new ArrayAdapter<String>(EditSite.this, android.R.layout.simple_spinner_item, BranchArray);
                        branchAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        BranchSpinner.setAdapter(branchAdapter);

                        break;
                    case "בית מרקחת":
                        BranchArray.clear();
                        BranchArray.add("בית מרקחת");
                        BranchArray.add("סופר פארם");
                        BranchArray.add("ניו פארם");

                        branchAdapter = new ArrayAdapter<String>(EditSite.this, android.R.layout.simple_spinner_item, BranchArray);
                        branchAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        BranchSpinner.setAdapter(branchAdapter);

                        break;
                    case "עירייה":
                        BranchArray.clear();
                        BranchArray.add("בית אבות");
                        BranchArray.add("מוסד סיעוד גריאטרי");
                        BranchArray.add("מחלקת-רווחה");
                        BranchArray.add("מרפאה כירורגית");

                        branchAdapter = new ArrayAdapter<String>(EditSite.this, android.R.layout.simple_spinner_item, BranchArray);
                        branchAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        BranchSpinner.setAdapter(branchAdapter);
                        break;
                    case "בית-ספר":
                        BranchArray.clear();
                        BranchArray.add("בתי ספר");

                        branchAdapter = new ArrayAdapter<String>(EditSite.this, android.R.layout.simple_spinner_item, BranchArray);
                        branchAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        BranchSpinner.setAdapter(branchAdapter);

                        break;
                    case "גן-ילדים":
                        BranchArray.clear();
                        BranchArray.add("גני ילדים");

                        branchAdapter = new ArrayAdapter<String>(EditSite.this, android.R.layout.simple_spinner_item, BranchArray);
                        branchAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        BranchSpinner.setAdapter(branchAdapter);

                        break;
                    case "מרחב-מוגן":
                        BranchArray.clear();
                        BranchArray.add("מרחב מוגן");

                        branchAdapter = new ArrayAdapter<String>(EditSite.this, android.R.layout.simple_spinner_item, BranchArray);
                        branchAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        BranchSpinner.setAdapter(branchAdapter);

                        break;
                    case "בחר קטגוריה...":
                        break;
                    default:
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
                    ConnectionClass.DBHelper = new DatabaseHelper(EditSite.this);

                    File database = getApplicationContext().getDatabasePath(DatabaseHelper.DBNAME);

                    if (database.exists() == false) {
                        ConnectionClass.DBHelper.getReadableDatabase();
                        //copy db
                        ConnectionClass con = new ConnectionClass();
                        if (!con.copyDatabase(EditSite.this))
                        {
                            Toast.makeText(EditSite.this, "copy db error", Toast.LENGTH_SHORT).show();
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
                            if(selectedBranch.equals("ניו פארם"))
                                SiteName="ניו פארם%' OR name LIKE '%ניופארם%' OR name LIKE '%ניופרם";
                            else
                                SiteName = selectedBranch;
                            FieldName = "name";
                            break;
                        case "עירייה":
                            SiteName = selectedBranch;
                            FieldName = "category";
                            break;
                        case "בית-ספר":
                            SiteName= "בית-ספר";
                            FieldName="category";
                            break;
                        case "גן-ילדים":
                            SiteName="גן-ילדים";
                            FieldName="category";
                            break;
                        case "מרחב-מוגן":
                            SiteName="מרחב-מוגן";
                            FieldName="category";
                            break;
                        default:
                            SiteName = null;
                            break;
                    }

                    SitesList = ConnectionClass.DBHelper.getListSites("*", "Sites",FieldName + " LIKE '%" + SiteName + "%'");
                    //init adapter
                    adapter = new ListSiteAdapter(EditSite.this, SitesList);

                    int NumOfSites = adapter.getCount();
                    String[] sitesAddress = new String[NumOfSites];
                    for (int j = 0; j < NumOfSites; ++j)
                        sitesAddress[j] = SitesList.get(j).getAddSite();

                    ArrayAdapter<String> addressAdapter = new ArrayAdapter<String>(EditSite.this, android.R.layout.simple_spinner_item, sitesAddress);
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
        {
            Address=null;
            Toast.makeText(this, "לא בחרת מה אתה רוצה לחפש, נסה שנית", Toast.LENGTH_LONG).show();
        }
        else if(!categorySelected.equals("בחר קטגוריה...")&& BranchSpinner.getSelectedItem() != null && AddressSpinner.getSelectedItem()==null)
            Toast.makeText(this, "לא בחרת מה אתה רוצה לחפש, נסה שנית", Toast.LENGTH_LONG).show();
        else
        {
            MapPage.SiteName=SiteName;
            Categories.categoryName=null;
            siteProperties = ConnectionClass.DBHelper.getListSites("*","Sites", "addSite LIKE '%" + AddressSpinner.getSelectedItem().toString() + "%' " + "AND " + FieldName + " LIKE '%" + SiteName + "%'");
            idSite=siteProperties.get(0).getIdSite();
            Intent i;
            if(AdminPage.chose==1)
            {
                i = new Intent(this,SiteToUpdate.class);
                startActivity(i);
            }
            else
            {
                i = new Intent(this,Opninions.class);
                startActivity(i);
            }

        }
    }
}
