package com.ice.androidpractice09_recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ContactAdapter extends RecyclerView .Adapter<ContactAdapter.ViewHolder>{
    private List<Contact> mContactList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        View contactView;//存储解析到的view
        ImageView imageView;
        TextView textView;
        public ViewHolder(View view){
            super(view);
            contactView = view;
            imageView = view.findViewById(R.id.iv);
            textView = view.findViewById(R.id.tv);
        }
    }

    public ContactAdapter(List<Contact> contactList){
        mContactList = contactList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_layout,parent,false);//解析layout
        final ViewHolder viewHolder = new ViewHolder(view);//新建一个viewHolder绑定解析到的view
        //监听每一项的点击事件
        viewHolder.contactView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = viewHolder.getAdapterPosition();
                Contact contact = mContactList.get(position);
                Toast.makeText(view.getContext(),contact.getName(),Toast.LENGTH_SHORT).show();
            }
        });
        //监听每一项里的控件的点击事件，如点击了ImageView
        viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = viewHolder.getAdapterPosition();
                Contact contact = mContactList.get(position);
                Toast.makeText(view.getContext(),contact.getImageId(),Toast.LENGTH_SHORT).show();
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Contact contact = mContactList.get(position);
        holder.imageView.setImageResource(contact.getImageId());
        holder.textView.setText(contact.getName());
    }

    @Override
    public int getItemCount() {
        return mContactList.size();
    }


}
