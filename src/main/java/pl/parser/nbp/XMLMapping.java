package pl.parser.nbp;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "tabela_kursow")
public class XMLMapping {
    @XmlElement(name = "data_publikacji")
    private String date;
    @XmlElement(name = "pozycja")
    private List<Currency> currencies;
    // get and return an object list from xml file
    public Currency getCurrencyByCode(String code) {
        for (Currency currency : currencies) {
            if (currency.getName().equals(code)) {
                return currency;
            }
        }
        return null;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Currency> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(List<Currency> currencies) {
        this.currencies = currencies;
    }
}
