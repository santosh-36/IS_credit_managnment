package com.santosh.androiddev.is_sparks_cm;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{

    private List<list_item> list_items;
    private Context context;

    public MyAdapter(List<list_item> list_items, Context context) {
        this.list_items = list_items;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final list_item listItem = list_items.get(position);
        holder.name1.setText(listItem.getName1());
        holder.id1.setText(listItem.getId1());
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (view.getContext(), Main2Activity.class);
                intent.putExtra("id",listItem.getId1());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list_items.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder{

        TextView name1;
        TextView id1;
        LinearLayout layout;
        ViewHolder(View itemView) {
            super(itemView);

            name1 = itemView.findViewById(R.id.tv_name1);
            id1 = itemView.findViewById(R.id.tv_id);
            layout = itemView.findViewById(R.id.cv);

        }
    }
}
