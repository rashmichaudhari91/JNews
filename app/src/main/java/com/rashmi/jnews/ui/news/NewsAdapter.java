package com.rashmi.jnews.ui.news;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.rashmi.jnews.R;
import com.rashmi.jnews.constant.IConstant;
import com.rashmi.jnews.model.ArticlesItem;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class NewsAdapter extends BaseAdapter {

    private final List<ArticlesItem> newsList;

    public NewsAdapter(List<ArticlesItem> newsList) {
        this.newsList = newsList;
    }

    @Override
    public int getCount() {
        return newsList.size();
    }

    @Override
    public Object getItem(int position) {
        return newsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ArticlesItem article = (ArticlesItem) getItem(position);
        Context context = parent.getContext();

        TableRow tableRow = (TableRow) LayoutInflater.from(context).inflate(R.layout.adapter_news, null);
        Glide.with(context)
                .load(article.getUrlToImage())
                .placeholder(R.drawable.ic_menu_camera)
                .into((ImageView) tableRow.findViewById(R.id.newsImage));

        TextView newsTitle = tableRow.findViewById(R.id.newsTitle);
        TextView newsDescription = tableRow.findViewById(R.id.newsDescription);
        TextView newsPublishedDate = tableRow.findViewById(R.id.newsPublishedDate);

        newsTitle.setText(article.getTitle());
        newsDescription.setText(article.getDescription());

        String formattedDate = formatDate(article.getPublishedAt());
        newsPublishedDate.setText(formattedDate != null ? formattedDate : "N/A");

        return tableRow;
    }

    private String formatDate(String inputDateString) {
        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat(IConstant.dateFormat, Locale.getDefault());
            Date date = inputFormat.parse(inputDateString);

            SimpleDateFormat outputFormat = new SimpleDateFormat(IConstant.requiredDateFormat, Locale.getDefault());
            return outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

}
