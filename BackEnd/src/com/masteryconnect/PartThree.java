package com.masteryconnect;

import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.*;

/**
 * Created by jasonsmith on 2/5/16.
 */
public class PartThree extends javax.servlet.http.HttpServlet {
    private void executeLetterCounter(javax.servlet.http.HttpServletRequest request,
                                      javax.servlet.http.HttpServletResponse response){
       try{
           PrintWriter out = response.getWriter();
           String output = displayLetterCounterOutput(letterCounter("foo bar baz"));
           out.println(output);
       }catch(Exception e){
           System.err.print(e);
       }

    }
    private void executeDivisors(javax.servlet.http.HttpServletRequest request,
                                 javax.servlet.http.HttpServletResponse response){
        try{
            PrintWriter out = response.getWriter();
            String output = getDivisorsFromNumber(12).toString();
            out.println(output);
        }catch(Exception e){
            System.err.print(e);
        }

    }
    private void executeJSONArray(javax.servlet.http.HttpServletRequest request,
                                  javax.servlet.http.HttpServletResponse response){
        try{
            HttpSession session = request.getSession();
            Integer numErrors = Integer.parseInt((session.getAttribute("errorCount") != null)?
                    (String)session.getAttribute("errorCount"):"0");
            Integer numSuccess = Integer.parseInt((session.getAttribute("okCount") != null)?
                    (String)session.getAttribute("okCount"):"0");
            PrintWriter out = response.getWriter();
            try {
                String output = formatJSONOutputFromURL("http://codetest.socrative.com/v1/api/array/");
                out.print(output);
                numSuccess++;
                session.setAttribute("okCount",numSuccess.toString());
            }catch(Exception e) {
                numErrors++;
                session.setAttribute("errorCount",numErrors.toString());
                out.println("An unexpected error occurred");
            }
            float percent = (numErrors * 100.0f) / numSuccess;
            String stats = "\n\nError Percentage: "+Math.round(percent)+"%";
            out.println(stats);
        }catch(Exception e){
            System.err.print(e);
        }

    }
    // letter Counter methods
    private TreeMap<String,Integer> letterCounter(String parseString){
        TreeMap<String, Integer> letterMap = new TreeMap<String, Integer>();
        String strippedString = parseString.replaceAll(" ","");
        for(int i = 0; i < strippedString.length(); i++){
            String letter = String.valueOf(strippedString.charAt(i));
            int count = StringUtils.countMatches(strippedString, letter);
            letterMap.put(count+letter,count);
        }
        return letterMap;
    }
    private String displayLetterCounterOutput(TreeMap map){
        String outputString = "{";
        Set set = map.descendingMap().entrySet();
        Iterator iterator = set.iterator();
        while(iterator.hasNext()) {
            Map.Entry entry = (Map.Entry)iterator.next();
            String key = entry.getKey().toString().substring(1,2);
            outputString += key+"=>"+entry.getValue()+((iterator.hasNext())?",":"");
        }
        return outputString+"}";
    }
    //divisor method
    private ArrayList getDivisorsFromNumber(Integer num){
        ArrayList<Integer> divisors = new ArrayList<Integer>();

        for(int i = 1; i <=num;i++){
            if(num % i== 0 && i != 1 && i != num){
                divisors.add(i);
            }
        }
        return divisors;
    }
    //array sorting
    private String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    private String formatJSONOutputFromURL(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);

            JSONArray data = json.getJSONArray("data");
            List<String> jsonValues = new ArrayList<String>();
            for (int i = 0; i < data.length(); i++) {
                jsonValues.add(data.getString(i));
            }
            String[] values = jsonValues.toArray(new String[0]);
            Arrays.sort(values);
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < values.length;i++){
                sb.append("* ").append(values[i]).append("\n");
            }
            return sb.toString();
        } finally {
            is.close();
        }
    }

    protected void doPost(javax.servlet.http.HttpServletRequest request,
                          javax.servlet.http.HttpServletResponse response)
            throws javax.servlet.ServletException, IOException {
        //launch appropriate test result depending on request value
        if(request.getParameter("mode").equals("array")){
            executeJSONArray(request, response);
        }else if(request.getParameter("mode").equals("divisors")){
            executeDivisors(request, response);
        }else if(request.getParameter("mode").equals("letter-count")){
            executeLetterCounter(request, response);
        }

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request,
                         javax.servlet.http.HttpServletResponse response)
            throws javax.servlet.ServletException, IOException {
        //launch appropriate test result depending on request value
        if(request.getParameter("mode").equals("array")){
            executeJSONArray(request, response);
        }else if(request.getParameter("mode").equals("divisors")){
            executeDivisors(request, response);
        }else if(request.getParameter("mode").equals("letter-count")){
            executeLetterCounter(request, response);
        }
    }
}
