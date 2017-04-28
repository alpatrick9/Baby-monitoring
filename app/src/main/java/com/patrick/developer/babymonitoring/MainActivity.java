package com.patrick.developer.babymonitoring;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.misc.TransactionManager;
import com.patrick.developer.babymonitoring.config.SqliteHelper;
import com.patrick.developer.babymonitoring.dao.BabySizeDao;
import com.patrick.developer.babymonitoring.dao.BabyWeightDao;
import com.patrick.developer.babymonitoring.fragment.HomeFragment;
import com.patrick.developer.babymonitoring.init.InitDataBoy;
import com.patrick.developer.babymonitoring.init.InitDataGirl;
import com.patrick.developer.babymonitoring.model.entity.BabySize;
import com.patrick.developer.babymonitoring.model.entity.BabyWeight;
import com.patrick.developer.babymonitoring.tools.constant.BackStatus;
import com.patrick.developer.babymonitoring.tools.constant.Constant;
import com.patrick.developer.babymonitoring.tools.FragmentTool;

import org.json.JSONException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.Callable;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar;
    private InputMethodManager gestionClavier = null;
    ProgressDialog myProgressDialog = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gestionClavier = (InputMethodManager)getSystemService(
                INPUT_METHOD_SERVICE);

        openHelperDatabase();

        setView();

        setNavigation();

        setHomeView();

        try {
            initializeData();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    protected void openHelperDatabase() {
        OpenHelperManager.getHelper(this, SqliteHelper.class);
    }

    protected void setView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
    }

    protected void setNavigation() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.drawer_open, R.string.drawer_close) {
            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                gestionClavier.hideSoftInputFromWindow(MainActivity.this.getCurrentFocus().getWindowToken(), 0);
            }
        };
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        TextView textView = (TextView)navigationView.getHeaderView(0).findViewById(R.id.copyright);
        textView.setText(Html.fromHtml(infoApp()));

    }

    protected String infoApp() {
        String copyright = "&copy; 2017";

        try {
            PackageInfo info = this.getPackageManager().getPackageInfo(this.getPackageName(), PackageManager.GET_META_DATA);
            copyright = copyright+" v"+info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return copyright;
    }

    protected void setHomeView() {
        Fragment fragment = new HomeFragment();
        FragmentTool.replaceFragment(this, fragment);
    }

    public void initializeData() throws SQLException {

        final SqliteHelper sqliteHelper = OpenHelperManager.getHelper(this, SqliteHelper.class);

        final Handler handler = new Handler();

        final Runnable run = new Runnable() {
            @Override
            public void run() {
                myProgressDialog.dismiss();
            }
        };

        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    insertDataBase(sqliteHelper);
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                /**********************************/
                handler.post(run);
            }
        };

        myProgressDialog = ProgressDialog.show(this, "Initialisation", "Initialisation des données ...", true);
        thread.start();
    }

    public void insertDataBase(SqliteHelper sqliteHelper) throws SQLException, IOException, JSONException {
        TransactionManager.callInTransaction(sqliteHelper.getConnectionSource(), new Callable<Void>() {
            public Void call() throws Exception {
                final BabySizeDao babySizeDao = new BabySizeDao(MainActivity.this);
                final BabyWeightDao babyWeightDao = new BabyWeightDao(MainActivity.this);
                final InitDataGirl initDataGirl = new InitDataGirl();
                final InitDataBoy initDataBoy = new InitDataBoy();

                if(babySizeDao.countRow() == 0) {
                    for (int i = 0; i < initDataBoy.getwMax().size(); i++) {
                        BabySize babyBoySizeMin = null;
                        BabySize babyBoySizeMax = null;
                        BabyWeight babyBoyWeightMin = null;
                        BabyWeight babyBoyWeightMax = null;

                        BabySize babyGirlSizeMin = null;
                        BabySize babyGirlSizeMax = null;
                        BabyWeight babyGirlWeightMin = null;
                        BabyWeight babyGirlWeightMax = null;

                        if(i == initDataBoy.getwMax().size() - 1) {
                            babyBoySizeMin = new BabySize(initDataBoy.getsMin().get(i),12,null);
                            babyBoySizeMax = new BabySize(initDataBoy.getsMax().get(i),12, null);
                            babyBoyWeightMin = new BabyWeight(initDataBoy.getwMin().get(i),12, null);
                            babyBoyWeightMax = new BabyWeight(initDataBoy.getwMax().get(i), 12, null);

                            babyGirlSizeMin = new BabySize(initDataGirl.getsMin().get(i),12,null);
                            babyGirlSizeMax = new BabySize(initDataGirl.getsMax().get(i),12, null);
                            babyGirlWeightMin = new BabyWeight(initDataGirl.getwMin().get(i),12, null);
                            babyGirlWeightMax = new BabyWeight(initDataGirl.getwMax().get(i), 12, null);

                        } else {
                            babyBoySizeMin = new BabySize(initDataBoy.getsMin().get(i),i,null);
                            babyBoySizeMax = new BabySize(initDataBoy.getsMax().get(i),i, null);
                            babyBoyWeightMin = new BabyWeight(initDataBoy.getwMin().get(i),i, null);
                            babyBoyWeightMax = new BabyWeight(initDataBoy.getwMax().get(i), i, null);

                            babyGirlSizeMin = new BabySize(initDataGirl.getsMin().get(i),i,null);
                            babyGirlSizeMax = new BabySize(initDataGirl.getsMax().get(i),i, null);
                            babyGirlWeightMin = new BabyWeight(initDataGirl.getwMin().get(i),i, null);
                            babyGirlWeightMax = new BabyWeight(initDataGirl.getwMax().get(i), i, null);
                        }

                        babyBoySizeMin.setObs(Constant.min_boy.name());
                        babyBoySizeMax.setObs(Constant.max_boy.name());
                        babyBoyWeightMin.setObs(Constant.min_boy.name());
                        babyBoyWeightMax.setObs(Constant.max_boy.name());

                        babyGirlSizeMin.setObs(Constant.min_girl.name());
                        babyGirlSizeMax.setObs(Constant.max_girl.name());
                        babyGirlWeightMin.setObs(Constant.min_girl.name());
                        babyGirlWeightMax.setObs(Constant.max_girl.name());

                        babySizeDao.create(babyBoySizeMin);
                        babySizeDao.create(babyBoySizeMax);
                        babySizeDao.create(babyGirlSizeMin);
                        babySizeDao.create(babyGirlSizeMax);

                        babyWeightDao.create(babyBoyWeightMin);
                        babyWeightDao.create(babyBoyWeightMax);
                        babyWeightDao.create(babyGirlWeightMin);
                        babyWeightDao.create(babyGirlWeightMax);
                    }
                }

                return null;
            }
        });
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            Fragment fragment = null;
            switch (BackStatus.previous) {
                case "home":
                    fragment = new HomeFragment();
                    break;
                default:
                    super.onBackPressed();
                    break;
            }
            BackStatus.previous = "";
            if(fragment != null)
                FragmentTool.replaceFragment(this, fragment);
        }
    }
}
