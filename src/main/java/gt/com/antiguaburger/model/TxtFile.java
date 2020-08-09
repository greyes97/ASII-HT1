package gt.com.antiguaburger.model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TxtFile implements IFileControl{
    @Override
    public void createFile(OrderEntity order) {

        String menu = "";
        String extras = "";
        String observacines = "";
        float to = (float) order.getTotal();

        String total = Float.toString(to);

        for(int i=0;i<order.getExtras().size();i++){
            extras += "\t- "+order.getExtras().get(i)+"\n";
        }
        for(int i=0;i<order.getMenu().size();i++){
                observacines += "\t- "+order.getMenu().get(i)+"\n";
        }
        try {
            String ruta = order.getOrder()+".txt";
            String contenido = "-----------------------------------------------------------\n"
                    + "-----------------------------------------------------------\n"
                    + "Orde: \t\t"+order.getOrder()+"\n"
                    + "Cajero: \t"+order.getCashier()+"\n"
                    + "Fecha/hora: \t"+order.getDate()+"\n"
                    + "Cliente: \t"+order.getCustomer()+"\n"
                    + "Nit: \t\t"+order.getTaxId()+"\n"
                    + "-----------------------------------------------------------\n"
                    + "Combo: \n"
                    + ""+observacines+"\n"
                    + "-----------------------------------------------------------\n"
                    + "Extras: \n"
                    + ""+extras+"\n"
                    + "\tTotal: \t\t"+total+"\n"
                    + "-----------------------------------------------------------\n"
                    + "-----------------------------------------------------------\n";
            File file = new File(ruta);
            // Si el archivo no existe es creado
            if (!file.exists()) {
                file.createNewFile();

            }
            FileWriter fw = new FileWriter(file);
            try (BufferedWriter bw = new BufferedWriter(fw)) {
                bw.write(contenido);
            }
        } catch (IOException e) {
        }
    }
}
