package com.kilopo.ordersMaker.adapter;


import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.kilopo.ordersMaker.Constants;
import com.kilopo.remindme1.R;
import com.kilopo.ordersMaker.dto.MenuDTO;

import java.util.List;

public class MenuListAdapter extends RecyclerView.Adapter<MenuListAdapter.RemindViewHolder> {

    private List<MenuDTO> data;

    public MenuListAdapter(List<MenuDTO> data) {
        this.data = data;
    }

    @Override
    public RemindViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.remind_item, parent, false);

        return new RemindViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RemindViewHolder holder, final int position) {
        MenuDTO item = data.get(position);
        holder.title.setText(item.getName());
        holder.consist.setText(item.getConsist());
        holder.weight.setText(item.getWeight());
        holder.price.setText("Ціна: "+item.getPrice());
        holder.id = item.getId();
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (holder.checkBox.isChecked()) {
                    Constants.menuDTOs.get((int) holder.id - 1 ).setCheck(true);
                    Constants.menuDTOs.get((int) holder.id - 1 ).setCount(Integer.parseInt(String.valueOf(holder.count.getText())));
                    holder.checkBox.setText("Відняти");

                }
                if (!holder.checkBox.isChecked()) {
                    Constants.menuDTOs.get((int) holder.id - 1).setCheck(false);
                    holder.checkBox.setText("Додати");
                }

            }
        });
    }


    public void setData(List<MenuDTO> data) {
        this.data = data;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class RemindViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView title;
        CheckBox checkBox;
        TextView consist;
        TextView weight;
        TextView price;
        EditText count;
        long id;

        public RemindViewHolder(View itemView) {
            super(itemView);

            cardView = (CardView) itemView.findViewById(R.id.cardView);
            title = (TextView) itemView.findViewById(R.id.title);
            consist = (TextView) itemView.findViewById(R.id.consist);
            weight = (TextView) itemView.findViewById(R.id.weight);
            price = (TextView) itemView.findViewById(R.id.price);
            checkBox = (CheckBox) itemView.findViewById(R.id.checkBox);
            count = (EditText) itemView.findViewById(R.id.editText);

        }
    }
}
