package com.kilopo.ordersMaker;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.kilopo.ordersMaker.dto.MenuDTO;
import com.kilopo.remindme1.R;
import com.kilopo.ordersMaker.adapter.TabsFragmentAdapter;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private static final int LAYOUT = R.layout.activity_main;

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    public ViewPager viewPager;

    public List<Integer> idList;
    public List<MenuDTO> remList;
    public List<MenuDTO> stList;

    TabsFragmentAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppDefault);

        setContentView(LAYOUT);
        super.onCreate(savedInstanceState);
        initToolbar();
        initNavigationView();
        initTabs();

    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                sentMail();
                return false;
            }
        });

        toolbar.inflateMenu(R.menu.menu);
    }

    private void initTabs() {
        new MenuTask().execute();
        //moqList();
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        adapter = new TabsFragmentAdapter(getApplicationContext(), getSupportFragmentManager(), Constants.menuDTOs);
        //adapter = new TabsFragmentAdapter(getApplicationContext(), getSupportFragmentManager(), moqList());
        viewPager.setAdapter(adapter);





        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);


    }

    private void initNavigationView() {

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.view_navigation_open, R.string.view_navigation_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.actionNavigationItem:
                        refreshMenu();
                }
                drawerLayout.closeDrawers();

                return true;
            }
        });
    }

    private void refreshMenu(){
        new MenuTask().execute();
        adapter.setData(Constants.menuDTOs);
        //adapter.setData(moqList());
        Toast.makeText(MainActivity.this,"Оновлено",Toast.LENGTH_SHORT).show();
    }

    private void sentMail(){
        final Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("plain/text");
        // Кому
        emailIntent.putExtra(Intent.EXTRA_EMAIL,
                new String[] { "marian.brynetskyy@outlook.com" });
        // Тема
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");
        emailIntent.putExtra(Intent.EXTRA_SUBJECT,
                "Замовлення від: " +  dateFormat.format(new Date()));

        // Текст
        String text = "";
        int price = 0;
        for(int i = 0; i<Constants.menuDTOs.size(); i++){
            if(Constants.menuDTOs.get(i).isCheck()){
                text=text+Constants.menuDTOs.get(i).getCount()+"x "+Constants.menuDTOs.get(i).getName()+"\t"+Constants.menuDTOs.get(i).getPrice()+" грн.\n";
                price=price + (Constants.menuDTOs.get(i).getPrice()*Constants.menuDTOs.get(i).getCount());
            }
        }

        text=text+"\n\nЗагалом до сплати: "+price+" грн.";
        text=text+"\n\nПобажання щодо замовлення: ";
        emailIntent.putExtra(Intent.EXTRA_TEXT,
                text);


        // Гого!
        MainActivity.this.startActivity(Intent.createChooser(emailIntent,
                "Відправлення..."));
    }

    private class MenuTask extends AsyncTask<Void, Void, ArrayList<MenuDTO>> {

        @Override
        protected ArrayList<MenuDTO> doInBackground(Void... params) {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

            ArrayList<MenuDTO> list1 = new ArrayList<MenuDTO>();


            int i = 1;
                while(restTemplate.getForObject(Constants.URL.GET_REMIND_ITEM + "" + i,MenuDTO.class) != null){
                list1.add(restTemplate.getForObject(Constants.URL.GET_REMIND_ITEM + "" + i,MenuDTO.class));
                i++;
            }

            Constants.menuDTOs = list1;
            Constants.standartList = list1;
            stList=list1;
            return list1;
        }


        @Override
        protected void onPostExecute(ArrayList<MenuDTO> menuDTOs) {
            adapter.setData(menuDTOs);
            //adapter.setData(moqList());
        }


    }

    public ArrayList<MenuDTO> moqList(){
        ArrayList<MenuDTO> list1 = new ArrayList<>();
        MenuDTO m1 = new MenuDTO();
        MenuDTO m2 = new MenuDTO();
        MenuDTO m3 = new MenuDTO();
        MenuDTO m4 = new MenuDTO();
        MenuDTO m5 = new MenuDTO();
        MenuDTO m6 = new MenuDTO();

        m5.setId(5);
        m5.setWeight("200");
        m5.setType(1);
        m5.setName("Борщ");
        m5.setConsist("Буряк, картопля, капуста, морква");
        m5.setPrice(1);

        m6.setId(6);
        m6.setWeight("300");
        m6.setType(1);
        m6.setName("Олів'є");
        m6.setConsist("Картопля, огірки, ковбаса,яйця");
        m6.setPrice(1);

        m1.setId(1);
        m1.setWeight("300гр");
        m1.setType(2);
        m1.setName("Картопля");
        m1.setConsist("Картопля, часник");
        m1.setPrice(20);

        m2.setId(2);
        m2.setWeight("0,3л");
        m2.setType(2);
        m2.setName("Чай чорний");
        m2.setConsist("Вода, чай");
        m2.setPrice(2);

        m3.setId(3);
        m3.setWeight("0,3л");
        m3.setType(2);
        m3.setName("Чай зелений");
        m3.setConsist("Вода, чай");
        m3.setPrice(3);

        m4.setId(4);
        m4.setWeight("0,3л");
        m4.setType(2);
        m4.setName("Чай фруктовий");
        m4.setConsist("Вода, чай");
        m4.setPrice(3);

        list1.add(m1);
        list1.add(m2);
        list1.add(m3);
        list1.add(m4);
        list1.add(m5);
        list1.add(m6);
        Constants.menuDTOs.add(m1);
        Constants.menuDTOs.add(m2);
        Constants.menuDTOs.add(m3);
        Constants.menuDTOs.add(m4);
        Constants.menuDTOs.add(m5);
        return list1;
    }

}
