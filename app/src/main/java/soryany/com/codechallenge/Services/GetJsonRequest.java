package soryany.com.codechallenge.Services;

import soryany.com.codechallenge.DataStructures.ScheduleModel;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Created by soriyanykeo on 6/16/16.
 */

public class GetJsonRequest{
    public List<ScheduleModel> getJSON(String id){
        DefaultHttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());
        HttpGet httpGet = new HttpGet("https://sampleAPIs.com/schedule/getSchedule?accountid="+id);
        httpGet.setHeader("Content-type", "application/x-www-form-urlencoded");

        InputStream inputStream = null;
        String result = null;
        try {
            HttpResponse response = httpclient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            inputStream = entity.getContent();
            // json is UTF-8 by default
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
            StringBuilder sb = new StringBuilder();

            String line = null;
            while ((line = reader.readLine()) != null)
            {
                sb.append(line + "\n");
            }
            result = sb.toString();
            JSONObject object = new JSONObject(result);
            JSONArray arrayList = object.getJSONArray("schedule");

            List<ScheduleModel> scheduleList = new ArrayList<ScheduleModel>();
            int i=0;
            while (i<arrayList.length()) {
                ScheduleModel model = new ScheduleModel();
                String source = arrayList.getJSONObject(i).getString("title").toString().toLowerCase();
                source = source.substring(0,1).toUpperCase() + source.substring(1).toLowerCase();
                String[] from = arrayList.getJSONObject(i).getString("from").toString().split("_");
                String[] until = arrayList.getJSONObject(i).getString("until").toString().split("_");
                int fromHInt = Integer.parseInt(from[0]);
                int untilHInt = Integer.parseInt(until[0]);
                model.id = i;
                model.idString = arrayList.getJSONObject(i).getString("title").toString().toUpperCase();
                model.title = source;
                model.fromHour = fromHInt;
                model.untilHour = untilHInt;
                model.fromMin = from[1];
                model.untilMin = until[1];
                model.fromMode = (fromHInt<12)?"AM":"PM";
                model.untilMode = (untilHInt<12)?"AM":"PM";
                model.temp = arrayList.getJSONObject(i).getInt("setto");
                model.tempMode = "F";
                model.isOn = arrayList.getJSONObject(i).getBoolean("isOn");
                model.checksum = object.getString("checksum");
                scheduleList.add(model);
                i++;
            }
            if (!scheduleList.isEmpty()) {
                Collections.sort(scheduleList);

                return scheduleList;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        finally {
            try{if(inputStream != null)inputStream.close();}catch(Exception squish){}
        }
    }
}

