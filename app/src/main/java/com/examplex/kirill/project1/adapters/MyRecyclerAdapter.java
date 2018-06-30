
package com.examplex.kirill.project1.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.examplex.kirill.project1.Hacker;
import com.examplex.kirill.project1.ItemActivity;
import com.examplex.kirill.project1.R;


import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;


public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.Viho>{

    public static RealmList<Hacker> list;
    Context context;
    //protected SwipeItemRecyclerMangerImpl mItemManger = new SwipeItemRecyclerMangerImpl(this);

    public void setList(RealmList<Hacker> list)
    {
        this.list = list;
    }

    public MyRecyclerAdapter(RealmList<Hacker> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public void removeItem(int position) {
        list.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, list.size());
    }
    public void restoreItem(Hacker hacker, int position) {
        list.add(position, hacker);
        // notify item added by position
        notifyItemInserted(position);
        notifyItemRangeChanged(position, list.size());
    }

    @Override
    public Viho onCreateViewHolder(ViewGroup parent, int viewType) {
        final Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.rowitem, parent, false);
        return new Viho(view);
    }

    @Override
    public void onBindViewHolder(final Viho holder, final int position) {
        Hacker hacker = list.get(position);
        holder.nickname.setText(list.get(position).getNickname());
        holder.avatar.setImageResource(list.get(position).getAva());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ItemActivity.class);
                intent.putExtra("nickname", list.get(position).getNickname());
                v.getContext().startActivity(intent);

            }
        });
    }




    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addItem(Hacker results) {
        list.add(results);
        notifyItemInserted(getItemCount());

    }

    public class Viho extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView nickname;
        ImageView avatar;



        public Viho(View itemView) {
            super(itemView);

            nickname = itemView.findViewById(R.id.nickname);
            avatar = itemView.findViewById(R.id.avatar);
        }


        @Override
        public void onClick(View v) {

        }
    }

}

