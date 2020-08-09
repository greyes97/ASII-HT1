package gt.com.antiguaburger.model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class XmlFile implements IFileControl {
    @Override
    public void createFile(OrderEntity order) throws IOException {
        String cadena = new String();
        String menu = new String();
        FileWriter flwriter = null;
        String observaciones = new String();
        for(int i=0;i<order.getMenu().size();i++){
            menu += "<menu>"+order.getMenu().get(i)+"\n</menu>";
        }
        for(int i=0;i<order.getExtras().size();i++){
            observaciones += "<observaciones>"+order.getExtras().get(i)+"\n</observaciones>";
        }

        cadena += "<order>"+order.getOrder()+"\n"
                + "<cajero>"+order.getCashier()+"\n"
                + "<date>"+order.getDate()+"\n"
                + "<customer>"+order.getCustomer()+"\n"
                + "<nit>"+order.getTaxId()+"\n"
                + ""+menu+""
                + ""+observaciones+""
                + "<total>"+order.getTotal()+"\n"
                + "</total>"
                + "</nit>"
                + "</customer>"
                + "</date>"
                + "</cajero>"
                + "</order>";
        flwriter = new FileWriter(order.getOrder()+".xml");
        try (BufferedWriter bfwriter = new BufferedWriter(flwriter)) {
            bfwriter.write(cadena);
        }
    }
}
