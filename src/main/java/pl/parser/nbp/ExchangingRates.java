package pl.parser.nbp;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.UnmarshalException;
import javax.xml.bind.Unmarshaller;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ExchangingRates {
    List<Double> buyingCursesList = new ArrayList<>();
    List<Double> sellingCursesList = new ArrayList<>();


    public void myURLConnection(String code, String startDate, String endDate) throws Exception {
        endDate = getNextDate(endDate);
        String url;
        while (true) {
            String check = startDate.substring(0, 4);
            if (check.equals("2019")) {
                url = "http://www.nbp.pl/kursy/xml/dir.txt";
            } else {
                url = "http://www.nbp.pl/kursy/xml/dir" + check + ".txt";
            }
            URL myUrl = new URL(url);
            HttpURLConnection myUrlCon = (HttpURLConnection) myUrl.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(myUrlCon.getInputStream()));
            String line;
            List<String> list = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                list.add(line);
            }
            String key = "";
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).substring(5, 11).equals(splitDate(startDate)) && list.get(i).charAt(0) == 'c') {
                    key = list.get(i);
                }
            }
            xmlParsing(key, code.toUpperCase(), startDate);
            startDate = getNextDate(startDate);
            if (startDate.equals(endDate)) {
                myUrlCon.disconnect();
                reader.close();
                break;
            }
        }
    }

    public void xmlParsing(String key, String code, String date) throws Exception {
        try {
            if (key.equals("")) {
            } else {
                JAXBContext jaxbContext = JAXBContext.newInstance(XMLMapping.class);
                Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
                URL xmlFileURL = new URL("http://www.nbp.pl/kursy/xml/" + key + ".xml");
                XMLMapping xmlMapping = (XMLMapping) unmarshaller.unmarshal(xmlFileURL);
                buyingCursesList.add(doubleAdapter(xmlMapping.getCurrencyByCode(code.toUpperCase()).getBuy(), ','));
                sellingCursesList.add(doubleAdapter(xmlMapping.getCurrencyByCode(code.toUpperCase()).getSell(), ','));
            }
        } catch (UnmarshalException e) {
            System.out.println("incorrect format of xmlFile at this date: " + date);
        }
    }

    public static double doubleAdapter(String toAdapt, char x) {
        String str = toAdapt.replace(x, '.');
        Double buyingCurse = new Double(str);
        return buyingCurse;
    }

    public static String getNextDate(String startDate) throws ParseException {
        final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        final Date date = format.parse(startDate);
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        return format.format(calendar.getTime());
    }

    public static String splitDate(String startDate) {
        startDate = startDate.substring(2, 10);
        String[] parts = startDate.split("-");
        String part1 = parts[0];
        String part2 = parts[1];
        String part3 = parts[2];
        String str = part1 + part2 + part3;
        return str;
    }

}