package com.kilopo.ordersMaker.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kilopo.ordersMaker.adapter.MenuListAdapter;
import com.kilopo.ordersMaker.dto.MenuDTO;
import com.kilopo.remindme1.R;

import java.util.List;

public class DishesFragment extends AbstractTabFragment {

    private static final int LAYOUT = R.layout.fragment_dishes;

    private List<MenuDTO> data;

    private MenuListAdapter adapter;

    public static DishesFragment getInstance(Context context, List<MenuDTO> data) {
        Bundle args = new Bundle();
        DishesFragment fragment = new DishesFragment();
        fragment.setArguments(args);
        fragment.setContext(context);
        fragment.setData(data);
        fragment.setTitle(context.getString(R.string.tab_dishes));

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT, container, false);

        RecyclerView rv = (RecyclerView) view.findViewById(R.id.recycleView);
        rv.setLayoutManager(new LinearLayoutManager(context));

        adapter = new MenuListAdapter(data);
        rv.setAdapter(adapter);

        return view;
    }

    public void refreshList(List<MenuDTO> data) {
        adapter.setData(data);
        adapter.notifyDataSetChanged();
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setData(List<MenuDTO> data) {
        this.data = data;
    }

    public List<MenuDTO> getData() {
        return data;
    }

}