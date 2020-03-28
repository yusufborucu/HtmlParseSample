package com.yusufborucu.htmlparsesample;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class PharmacyTask extends AsyncTask<Void, Void, Void> {

    private ProgressDialog pd;
    private Context context;
    private List<PharmacyModel> pharmacyItems;
    public static String pharmacy;

    public PharmacyTask(Context context) {
        this.context = context;
        pharmacyItems = new ArrayList<>();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd = new ProgressDialog(context);
        pd.setMessage("Lütfen bekleyiniz...");
        pd.setIndeterminate(false);
        pd.setCancelable(false);
        pd.show();
    }

    @Override
    protected Void doInBackground(Void... params) {
        // TODO Auto-generated method stub
        String address, phone;
        Document doc;
        String url = "https://www.nobetcieczanebul.com/bursa-nobetci-eczane";

        try {
            pharmacyItems.remove(pharmacyItems);
            doc = Jsoup.connect(url).ignoreContentType(true).get();
            for (Element row : doc.select("div.col")) {
                Elements header = row.select("div.card-header");
                Elements body = row.select("div.card-body");
                address = body.text().substring(0, body.text().indexOf("©"));
                phone = body.text().substring(body.text().indexOf("Tel :") + 6, body.text().indexOf("Tel :") + 21);
                PharmacyModel item = new PharmacyModel(header.text(), address, phone);
                pharmacyItems.add(item);
                pharmacy = header.text();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        pd.dismiss();
        PharmacyAdapter adapter = new PharmacyAdapter(context, pharmacyItems);
        MainActivity.lvPharmacy.setAdapter(adapter);
    }

}
