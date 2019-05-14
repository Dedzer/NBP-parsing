package pl.parser.nbp;

public class MainClass {

    public static void main(String[] args) {
        ExchangingRates rates = new ExchangingRates();
        GetScanner getScanner = new GetScanner();
        try {
            rates.myURLConnection(getScanner.codeScanner(), getScanner.startDateScanner(), getScanner.endDateScanner());
            Calculations calculations = new Calculations();
            System.out.println("Average buying rate is: " + calculations.getAverage(rates.buyingCursesList));
            System.out.println("Standard deviation of sales rates is: " + calculations.getStandartDeviation(rates.sellingCursesList));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
