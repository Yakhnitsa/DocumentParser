package main.bin;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Yuriy on 29.06.2016.
 * Файл для сохранения данных
 * по железнодорожному документу
 */
public class RailroadDocument {
    private String docNumber;
    private Date docDate;

    private Station sendStation;
    private Station receiveStation;

    private Participant cargoSender;
    private Participant cargoReceiver;

    private String cargoName;
    private String cargoCode;

    private List<Vagon> vagonList = new ArrayList<Vagon>();

    /*
     * getters and setters:
     */
    public void addVagon(Vagon vagon) {
        vagonList.add(vagon);
    }

    public List<Vagon> getVagonList() {
        return vagonList;
    }

    public Participant getCargoReceiver() {
        return cargoReceiver;
    }

    public void setCargoReceiver(Participant cargoReceiver) {
        this.cargoReceiver = cargoReceiver;
    }

    public Participant getCargoSender() {
        return cargoSender;
    }

    public void setCargoSender(Participant cargoSender) {
        this.cargoSender = cargoSender;
    }

    public Date getDocDate() {
        return docDate;
    }

    public void setDocDate(String stringDate) throws ParseException {
        if(stringDate == "")
            return;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy kk:mm:ss");
        docDate = dateFormat.parse(stringDate);
    }

    public void setDocDate(Date docDate) {
        this.docDate = docDate;
    }

    public String getDocNumber() {
        return docNumber;
    }

    public void setDocNumber(String docNumber) {
        this.docNumber = docNumber;
    }

    public String getCargoCode() {
        return cargoCode;
    }

    public void setCargoCode(String cargoCode) {
        this.cargoCode = cargoCode;
    }

    public String getCargoName() {
        return cargoName;
    }

    public void setCargoName(String cargoName) {
        this.cargoName = cargoName;
    }

    public Station getReceiveStation() {
        return receiveStation;
    }

    public void setReceiveStation(Station receiveStation) {
        this.receiveStation = receiveStation;
    }

    public Station getSendStation() {
        return sendStation;
    }

    public void setSendStation(Station sendStation) {
        this.sendStation = sendStation;
    }

    /*
     * Получение полной массы груза
     */
    public int getFullVeight() {
        int fullVeight = 0;
        for (Vagon vagon : vagonList) {
            fullVeight += vagon.netVeight;
        }
        return fullVeight;
    }

    public int getVagonCount() {
        return vagonList.size();
    }

    /*
     * Получение полной массы груза в виде формулы для excel
     */
    public String getFullVeightToString() {
        StringBuilder sb = new StringBuilder();
        sb.append("=");
        for (Vagon vagon : vagonList) {
            sb.append(vagon.getNetVeight());
            if (vagon != vagonList.get(vagonList.size() - 1))
                sb.append("+");
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("№ документа: %s, дата: %2$td/%2$tm/%2$tY%n", docNumber, docDate));
        sb.append(String.format("ст. Отправления: %s, код: %s%n", sendStation.name, sendStation.code));
        sb.append(String.format("ст. Назначения: %s, код: %s%n", receiveStation.name, receiveStation.code));
        sb.append(String.format("Отправитель: %s%n", cargoSender));
        sb.append(String.format("Получатель: %s%n", cargoReceiver));
        sb.append(String.format("Масса груза: %d кг%n", getFullVeight()));
        sb.append(String.format("Груз: %s, код груза: %s%n", cargoName, cargoCode));

        sb.append("Вагоны:\n");
        for (Vagon vagon : vagonList) {
            sb.append(String.format("\t%s%n", vagon));
        }

        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RailroadDocument)) return false;

        RailroadDocument document = (RailroadDocument) o;

        if (!docNumber.equals(document.docNumber)) return false;
        return docDate.equals(document.docDate);

    }

    @Override
    public int hashCode() {
        return docNumber.hashCode();
    }

    public String getShortRepresentation() {
        return String.format("№ док: %s, %s - %s дата: %4$td_%4$tm_%4$tY %5$d ваг%n", docNumber, sendStation.getName(), receiveStation.getName(), docDate, getVagonCount());
    }

    /*
     * Класс для сохранения данных про вагон:
     */
    public static class Vagon {
        private String number;
        private int grossVeight;
        private int netVeight;
        private int tareVeight;
        private double carryingCapasity;

        public Vagon() {
        }

        public Vagon(String number, int netVeight, int tareVeight) {
            this.number = number;
            this.netVeight = netVeight;
            this.tareVeight = tareVeight;
            this.grossVeight = netVeight + tareVeight;
        }

        public int getGrossVeight() {
            return grossVeight;
        }

        public void setGrossVeight(int grossVeight) {
            this.grossVeight = grossVeight;
        }

        public int getNetVeight() {
            return netVeight;
        }

        public void setNetVeight(int netVeight) {
            this.netVeight = netVeight;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public int getTareVeight() {
            return tareVeight;
        }

        public void setTareVeight(int tareVeight) {
            this.tareVeight = tareVeight;
        }

        public double getCarryingCapasity() {
            return carryingCapasity;
        }

        public void setCarryingCapasity(double carryingCapasity) {
            this.carryingCapasity = carryingCapasity;
        }

        @Override
        public String toString() {
            return String.format("%s гп: %.1f; нетто: %d, тара %d, брутто: %d.", number, carryingCapasity, netVeight, tareVeight, grossVeight);
        }

    }

    /*
     * Класс для сохранения данных про станцию
     */
    public static class Station {
        private String name;
        private String code;
        private String road;

        /*
         * getters And Setters
         */

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getRoad() {
            return road;
        }

        public void setRoad(String road) {
            this.road = road;
        }

        @Override
        public String toString() {
            return String.format("ст. %s, код: %s", name, code);
        }

        public String getNameAndCode() {
            return String.format("%s (%s)", name, code);
        }
    }

    /*
     * Класс для сохранения данных по учасникам
     * танспортного процесса (отправитель/получатель)
     */
    public static class Participant {
        private String name;
        private String railroadCode;
        private String edrpuCode;
        private String address;

        /*
         * getters and setters
         */

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getEdrpuCode() {
            return edrpuCode;
        }

        public void setEdrpuCode(String edrpuCode) {
            this.edrpuCode = edrpuCode;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getRailroadCode() {
            return railroadCode;
        }

        public void setRailroadCode(String railroadCode) {
            this.railroadCode = railroadCode;
        }

        @Override
        public String toString() {
            return String.format("%s, код: %s, адресс: %s", name, railroadCode, address);
        }
    }
}
