package com.example.mehmetemnakdoan.hw2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by MEHMET EMİN AKDOĞAN on 7.05.2018.
 */

public class TabFragment2 extends Fragment {
    private static final String TAG = "Tab2Fragment";
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> arrayList= new ArrayList<>();
    private ArrayList<String> directedLink= new ArrayList<>();


    private static String URL="http://ybu.edu.tr/muhendislik/bilgisayar/";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_fragment_2,container,false);
        listView =(ListView) view.findViewById(R.id.announcement_lv);
        adapter= new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,arrayList);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(URL+directedLink.get(position))));
            }
        });
        new parsing().execute();
        return view;
    }
    private class parsing extends AsyncTask<Void, Void, Void> {


        protected void onPreExecute() {
            super.onPreExecute();


        }

        @Override
        protected Void doInBackground(Void... voids) {
            arrayList.removeAll(arrayList);
            try {
                Document document= Jsoup.connect(URL).get();

                Elements contentAnnouncements = document.select("div[class=contentAnnouncements]") ;
                Elements caContent = contentAnnouncements.select("div[class=caContent]");

                for (Element item : caContent.select("div[class=cncItem]")) {
                    arrayList.add(item.text().toString());
                    directedLink.add(item.select("span").select("a").attr("href"));
                }



            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            listView.setAdapter(adapter);
        }
    }
}
