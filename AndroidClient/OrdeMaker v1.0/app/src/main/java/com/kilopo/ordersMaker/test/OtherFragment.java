package com.kilopo.ordersMaker.test;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kilopo.ordersMaker.fragment.AbstractTabFragment;
import com.kilopo.remindme1.R;
import com.kilopo.ordersMaker.adapter.MenuListAdapter;
import com.kilopo.ordersMaker.dto.MenuDTO;

import java.util.List;

public class OtherFragment extends AbstractTabFragment {

    private static final int LAYOUT = R.layout.fragment_dishes;
    private List<MenuDTO> data;
    private MenuListAdapter adapter;


    public static OtherFragment getInstance(Context context, List<MenuDTO> data){

        Bundle args = new Bundle();
        OtherFragment fragment = new OtherFragment();
        fragment.setArguments(args);
        fragment.setContext(context);
        fragment.setData(data);
        fragment.setTitle(context.getString(R.string.tab_birthdays));

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

}
