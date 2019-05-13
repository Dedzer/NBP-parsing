package pl.parser.nbp;

import javax.xml.bind.annotation.*;
import java.io.Serializable;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"name", "buy", "sell"})
@XmlRootElement(name = "pozycja")
public class Currency implements Serializable {
    @XmlElement(name = "kod_waluty")
    public String name;
    @XmlElement(name = "kurs_kupna")
    public String buy;
    @XmlElement(name = "kurs_sprzedazy")
    public String sell;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBuy() {
        return buy;
    }

    public void setBuy(String buy) {
        this.buy = buy;
    }

    public String getSell() {
        return sell;
    }

    public void setSell(String sell) {
        this.sell = sell;
    }

    @Override
    public String toString() {
        return "pl.parser.nbp.Currency{" +
                "name='" + name + '\'' +
                ", buy='" + buy + '\'' +
                ", sell='" + sell + '\'' +
                '}';
    }
}
