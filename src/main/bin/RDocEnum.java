package main.bin;


/**
 * Created by Yuriy on 04.07.2016.
 * Перечисления всех необходимых компонентов ЖД документа
 * для использования при экспорте
 */
public enum RDocEnum {

    ORDER_NUMBER("№ п/п"),
    DOC_NUMBER("№ накладной"),
    DOC_DATE("Дата док"),

    SEND_STATION_NAME("ст. Отправления"),
    SEND_STATION_CODE("код ст. Отпр."),
    SEND_STATION_ROAD("дорога ст. Отпр."),
    SEND_STATION_NAME_CODE("ст. Отправления (код)"),

    RECEIVE_STATION_NAME("ст. Назначения"),
    RECEIVE_STATION_CODE("код ст. Назнач."),
    RECEIVE_STATION_ROAD("дорога ст. Назнач."),
    RECEIVE_STATION_NAME_CODE("ст. Назначения (код)"),

    SENDER_NAME("Отправитель"),
    SENDER_ADDRESS("Адрес отправителя"),
    SENDER_RAILROAD_CODE("жд код отправителя"),
    SENDER_EDRPU_CODE("код ЕДРПУ отправителя"),

    RECEIVER_NAME("Получатель"),
    RECEIVER_ADDRESS("Адрес получателя"),
    RECEIVER_RAILROAD_CODE("жд код получателя"),
    RECEIVER_EDRPU_CODE("код ЕДРПУ получателя"),
    CARGO_NAME("груз"),
    CARGO_CODE("год груза"),

    VAGON_NUMBER("№ вагона"),
    VAGON_GROSS_WEIGHT("Вес брутто"),
    VAGON_NET_WEIGHT("Вес нетто"),
    VAGON_TARE_WEIGTH("Вес тары"),
    VAGON_CARRYING_CAPASITY("Г/п вагона"),

    VOID_SPACE(" ");

    private String fullName;

    RDocEnum(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public String toString(){
        return fullName;
    }
}
