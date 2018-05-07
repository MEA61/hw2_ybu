package com.example.mehmetemnakdoan.hw2;

/**
 * Created by MEHMET EMİN AKDOĞAN on 7.05.2018.
 */
        import android.app.ProgressDialog;
        import android.os.AsyncTask;
        import android.os.Bundle;
        import android.support.annotation.Nullable;
        import android.support.v4.app.Fragment;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.widget.ListView;

        import com.example.mehmetemnakdoan.hw2.R;

        import org.jsoup.Jsoup;
        import org.jsoup.nodes.Document;
        import org.jsoup.nodes.Element;
        import org.jsoup.select.Elements;

        import java.io.IOException;
        import java.util.ArrayList;
        import java.util.Iterator;
        import java.util.List;


public class TabFragment1 extends Fragment {
    private static final String TAG = "Tab1Fragment";
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> arrayList= new ArrayList<>();

    public ProgressDialog progressDialog;
    private static String URL="http://ybu.edu.tr/sks/";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_fragment_1,container,false);
        listView =(ListView) view.findViewById(R.id.foodlist);
        adapter= new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,arrayList);

        new parsing().execute();
        return view;
    }
    private class parsing extends AsyncTask<Void, Void, Void>{


        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog= new ProgressDialog(TabFragment1.this.getContext());
            progressDialog.setTitle("Data is loading");
            progressDialog.setMessage("Please wait");
            progressDialog.setIndeterminate(false);
            progressDialog.show();

        }

        @Override
        protected Void doInBackground(Void... voids) {
            arrayList.removeAll(arrayList);
            try {
                Document document= Jsoup.connect(URL).get();

                 Element foods = document.select("table").get(1) ;
                Elements tr= foods.select("tr");
                for (int i=1;i<tr.size();i++) {

                    arrayList.add(tr.select("td").get(i).text().toString());

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
            progressDialog.dismiss();
        }
    }
}
