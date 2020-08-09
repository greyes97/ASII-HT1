package gt.com.antiguaburger.model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CsvFile implements IFileControl {
    @Override
    public void createFile(OrderEntity order) throws IOException {
        String cadena = new String();
        String menu = new String();
        FileWriter flwriter = null;
        String observaciones = new String();
        for(int i=0;i<order.getExtras().size();i++){
            observaciones += order.getExtras().get(i)+",";
        }
        for(int i=0;i<order.getMenu().size();i++){
            menu += order.getMenu().get(i)+",";
        }


        cadena += ""+order.getOrder()+","+order.getCashier()+","+order.getDate()+","+order.getCustomer()+","+order.getTaxId()+","
                + ""+menu+""+observaciones+""+order.getTotal()+"";
        flwriter = new FileWriter(order.getOrder()+".csv");
        String replace = cadena.replace(" ", ",");
        try (BufferedWriter bfwriter = new BufferedWriter(flwriter)) {
            bfwriter.write(replace);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
