package pl.parser.nbp;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class Calculations {
    public double getAvarage(List<Double> buyingCursesList) {
        double sum = 0;
        double avarage;
        for (int i = 0; i < buyingCursesList.size(); i++) {
            sum = sum + buyingCursesList.get(i);
        }
        avarage = sum / buyingCursesList.size();
        double roundedAvarage = new BigDecimal(avarage).setScale(4, RoundingMode.UP).doubleValue();
        return roundedAvarage;
    }

    public double getStandartDeviation(List<Double> sellingCurseList) {
        double avarage = getAvarage(sellingCurseList);
        double sum = 0;
        double x = 0;
        double y = 0;
        double standartDeviation;
        for (int i = 0; i < sellingCurseList.size(); i++) {
            sum = sum + sellingCurseList.get(i);
            y = avarage - sellingCurseList.get(i);
            y = Math.pow(y, 2);
            x = x + y;
        }
        x = x / sum;
        standartDeviation = Math.sqrt(x);
        double roundedStandartDeviation = new BigDecimal(standartDeviation).setScale(4, RoundingMode.UP).doubleValue();
        return roundedStandartDeviation;
    }
}
