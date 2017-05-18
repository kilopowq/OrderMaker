package com.kilopo.ordersMaker.adapter;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.kilopo.ordersMaker.dto.MenuDTO;
import com.kilopo.ordersMaker.fragment.AbstractTabFragment;
import com.kilopo.ordersMaker.test.OtherFragment;
import com.kilopo.ordersMaker.test.DesertsFragment;
import com.kilopo.ordersMaker.fragment.DishesFragment;
import com.kilopo.ordersMaker.fragment.DrinksFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TabsFragmentAdapter extends FragmentPagerAdapter {

    private Map<Integer, AbstractTabFragment> tabs;
    private Context context;

    private List<MenuDTO> data;

    private DishesFragment dishesFragment;
    private DrinksFragment drinksFragment;
    private DesertsFragment desertsFragment;
    private OtherFragment otherFragmen;

    public TabsFragmentAdapter(Context context, FragmentManager fm, List<MenuDTO> data1) {
        super(fm);
        this.data = data1;
        this.context = context;
        initTabsMap(context);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabs.get(position).getTitle();
    }

    @Override
    public Fragment getItem(int position) {
        return tabs.get(position);
    }

    @Override
    public int getCount() {
        return tabs.size();
    }

    private void initTabsMap(Context context) {
        tabs = new HashMap<>();
        dishesFragment = DishesFragment.getInstance(context, data);
        drinksFragment = DrinksFragment.getInstance(context, data);
        //desertsFragment = DesertsFragment.getInstance(context, data);
        //otherFragmen = OtherFragment.getInstance(context, data);


        tabs.put(0, dishesFragment);
        tabs.put(1, drinksFragment);
        //tabs.put(2, desertsFragment);
        //tabs.put(3, otherFragmen);
    }

    public void setData(List<MenuDTO> data) {
        this.data = data;
        ArrayList<MenuDTO> dishesList = new ArrayList<>();
        ArrayList<MenuDTO> drinksList = new ArrayList<>();
        //ArrayList<MenuDTO> desertsList = new ArrayList<>();
        //ArrayList<MenuDTO> otherList = new ArrayList<>();



        for (int i=0;i<data.size();i++){
            if (data.get(i).getType()==1){
                dishesList.add(data.get(i));
            }

            if (data.get(i).getType()==2){
                drinksList.add(data.get(i));
            }
            //if (data.get(i).getType()==3){
             //   desertsList.add(data.get(i));
            //}
           // if (data.get(i).getType()==4){
                //otherList.add(data.get(i));
           // }

    }


        dishesFragment.refreshList(dishesList);
        drinksFragment.refreshList(drinksList);
        //desertsFragment.refreshList(desertsList);
        //otherFragmen.refreshList(otherList);

    }
}