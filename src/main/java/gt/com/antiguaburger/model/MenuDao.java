package gt.com.antiguaburger.model;



import gt.com.antiguaburger.model.IMenuDao;

import java.util.ArrayList;
import java.util.List;

public class MenuDao implements IMenuDao {


    public List<String> menus(String nameMenu) {
        List<String>lista = new ArrayList<>();

        switch (nameMenu) {
            case "Desayuno 1":

                lista.add("Huevos");
                lista.add("Frijoles");
                lista.add("Platanos");
                lista.add("Pan");
                lista.add("Bebida");
                return lista;
            case "Desayuno 2":
                lista.add("Cereal");
                lista.add("Fruta");
                lista.add("Bebida");
                return lista;
            case "Desayuno 3":
                lista.add("Avena");
                lista.add("Fruta");
                lista.add("Bebida");
                return lista;
            case "Almuerzo 1":
                lista.add("Hamburguesa");
                lista.add("Papas");
                lista.add("Bebida");
                lista.add("Postre");
                return lista;
            case "Almuerzo 2":
                lista.add("Ensalada");
                lista.add("Pollo");
                lista.add("Sopa");
                lista.add("Bebida");
                return lista;
            case "Cena 1":
                lista.add("Huevos");
                lista.add("Frijoles");
                lista.add("Tortillas");
                lista.add("Bebida");
                return lista;
            default:
                lista.add("error");
                lista.add("No se encuentra el menu en la base de datos");
                return lista;
        }

    }

    @Override
    public int getPrecioMenu(String whatMenu) {
        switch (whatMenu) {
            case "Desayuno 1":
                return 25;
            case "Desayuno 2":
                return 18;
            case "Desayuno 3":
                return 20;
            case "Almuerzo 1":
                return 30;
            case "Almuerzo 2":
                return 35;
            case "Cena 1":
                return 28;
            default:
                return 0;
        }
    }
}
