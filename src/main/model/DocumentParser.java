package main.model;


import main.bin.RailroadDocument;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;


/**
 * Created by Yuriy on 29.06.2016.
 */
public class DocumentParser implements Parser {
    /*
     * Парсинг жд документа из URL ссылки
     */
    @Override
    public RailroadDocument parseFromURL(String url) throws IOException, ParseException {
        Connection connection = Jsoup.connect(url);
        Document htmlDoc = connection.get();

        return parseDocument(htmlDoc);
    }
    /*
     * Парсинг жд документа из файла
     */
    @Override
    public RailroadDocument parseFromFile(File file) throws IOException, ParseException {
        Document document = Jsoup.parse(file,"UTF-8");

        return parseDocument(document);
    }
    /*
     * Парсинг данных по документу из представления xml документа
     */
    protected RailroadDocument parseDocument(Document jSoupDocument) throws ParseException {
        RailroadDocument railDoc = new RailroadDocument();

        railDoc.setDocNumber(jSoupDocument.getElementsByAttribute("nom_doc").attr("nom_doc").toString());
        railDoc.setDocDate(jSoupDocument.getElementsByAttribute("date_otpr").attr("date_otpr").toString());
        //Добавление учасников трансп процесса:
        RailroadDocument.Participant sender = parseAndAddParticipants(jSoupDocument.getElementsByAttributeValue("type","1"),railDoc);
        railDoc.setCargoSender(sender);
        RailroadDocument.Participant receiver = parseAndAddParticipants(jSoupDocument.getElementsByAttributeValue("type","2"),railDoc);
        railDoc.setCargoReceiver(receiver);
        //добавление инфо про маршрут:
        parseAndAddStations(jSoupDocument,railDoc);
        //добавление инфо по вагонам:
        parseAndAddVagonsToDoc(jSoupDocument,railDoc);
        //Добавление инфо по грузу:
        parseAndAddCarriageInfoToDoc(jSoupDocument,railDoc);


        return railDoc;
    }
    /*
     * Добавление инфо о станциях из документа
     */
    private void parseAndAddStations(Document jSoupDoc, RailroadDocument railDoc){
        RailroadDocument.Station sendStation = new RailroadDocument.Station();
        sendStation.setName(jSoupDoc.getElementsByAttribute("name_from").attr("name_from").toString());
        sendStation.setCode(jSoupDoc.getElementsByAttribute("stn_from").attr("stn_from").toString());

        RailroadDocument.Station receiveStation = new RailroadDocument.Station();
        receiveStation.setName(jSoupDoc.getElementsByAttribute("name_to").attr("name_to").toString());
        receiveStation.setCode(jSoupDoc.getElementsByAttribute("stn_to").attr("stn_to").toString());

        railDoc.setSendStation(sendStation);
        railDoc.setReceiveStation(receiveStation);
    }
    /*
     * Добавление инфо о получателе/отправителе из єлемента документа // Completed
     */
    private RailroadDocument.Participant parseAndAddParticipants(Elements element, RailroadDocument railDoc){

        RailroadDocument.Participant participant = new RailroadDocument.Participant();
        participant.setName(element.attr("name").toString());
        participant.setAddress(element.attr("adress").toString());
        participant.setRailroadCode(element.attr("kod").toString());
        participant.setEdrpuCode(element.attr("okpo").toString());

        return participant;
    }
    /*
     * Добавление инфо про вагоны //Completed
     */
    private void parseAndAddVagonsToDoc(Document jSoupDoc, RailroadDocument railDoc){
        Elements elements = jSoupDoc.getElementsByAttribute("nomer");
        for(Element element: elements){

            String number = element.getElementsByAttribute("nomer").attr("nomer").toString();
            int netVeight = Integer.parseInt(element.getElementsByAttribute("vesg").attr("vesg").toString());
            int tareVeight = Integer.parseInt(element.getElementsByAttribute("ves_tary_arc").attr("ves_tary_arc").toString());

            RailroadDocument.Vagon vagon = new RailroadDocument.Vagon(number,netVeight,tareVeight);

            double capasity = Double.parseDouble(element.getElementsByAttribute("gruzp").attr("gruzp").toString());
            vagon.setCarryingCapasity(capasity);

            railDoc.addVagon(vagon);
        }
    }

    /*
     * Добавление инфо про груз в документ
     */
    private void parseAndAddCarriageInfoToDoc(Document jSoupDoc, RailroadDocument railDoc){
        String carriageName = jSoupDoc.getElementsByAttribute("name_etsng").attr("name_etsng").toString();
        String carriageCode = jSoupDoc.getElementsByAttribute("kod_etsng").attr("kod_etsng").toString();
        railDoc.setCargoName(carriageName);
        railDoc.setCargoCode(carriageCode);
    }

}
