package pl.parser.nbp;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class Calculations {
    public double getAverage(List<Double> buyingCursesList) {
        double sum = 0;
        double average;
        for (int i = 0; i < buyingCursesList.size(); i++) {
            sum = sum + buyingCursesList.get(i);
        }
        average = sum / buyingCursesList.size();
        double roundedAverage = new BigDecimal(average).setScale(4, RoundingMode.UP).doubleValue();
        return roundedAverage;
    }

    public double getStandartDeviation(List<Double> sellingCurseList) {
        double average = getAverage(sellingCurseList);
        double sum = 0;
        for (int i = 0; i < sellingCurseList.size(); i++){
            sum = sum + (sellingCurseList.get(i) - average) * (sellingCurseList.get(i) - average);
        }
        double standartDeviation = Math.sqrt((sum/sellingCurseList.size()));
        double roundedStandartDeviation = new BigDecimal(standartDeviation).setScale(4, RoundingMode.UP).doubleValue();
        return roundedStandartDeviation;
    }
}
