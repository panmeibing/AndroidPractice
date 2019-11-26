package com.ice.androidpractice08_listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ContactAdapter extends ArrayAdapter<Contact>{
    private int resourceId;
    public ContactAdapter(Context context, int layoutResourceId, List<Contact> objects){
        super(context,layoutResourceId,objects);
        resourceId = layoutResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Contact contact = getItem(position);
        View view; //定义一个view用于存储解析的自定义layout
        ViewHolder viewHolder;//将控件实例都放在viewHolder中
        if (convertView == null){
            view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.imageView = view.findViewById(R.id.iv);
            viewHolder.textView = view.findViewById(R.id.tv);
            view.setTag(viewHolder);//将viewHolder存储在view中
        }else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.imageView.setImageResource(contact.getImageId());
        viewHolder.textView.setText(contact.getName());
        return view;
    }

    class ViewHolder{
        ImageView imageView;
        TextView textView;
    }
}
