package gt.com.antiguaburger.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class JsonFile implements IFileControl{
    @Override
    public void createFile(OrderEntity order) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        String ruta = order.getOrder()+".json";
        File file = new File(ruta);
        try (FileWriter writer = new FileWriter(file)) {
            gson.toJson(order, writer);

        } catch (IOException e) {
        }
    }
}
