package gt.com.antiguaburger.model;

import gt.com.antiguaburger.controller.Controlador;
import gt.com.antiguaburger.model.IMenuDao;
import gt.com.antiguaburger.model.MenuDao;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderControl {
   public OrderEntity selectCombo(HttpServletRequest request){
        String nameCombo = request.getParameter("combo");
        OrderEntity order = new OrderEntity();
        int numberOrder =(int) (5 + Math.random()*20);
        switch (nameCombo){
            case "Desayuno 1":

                //HEAD_ORDER
                System.out.println(Controlador.name);
                order.setOrder(Integer.toString(numberOrder));
                order.setCashier(Controlador.name);
                long millis = System.currentTimeMillis();
                order.setDate(new Date(millis));
                order.setCustomer(request.getParameter("text_customer"));
                order.setTaxId(request.getParameter("text_taxId"));

                //BODY_ORDER
                IMenuDao menu = new MenuDao();
                List<String>nuevoMenu = new ArrayList<>();
                List<String>alternativa = new ArrayList<>();

                alternativa.add(request.getParameter("alt_huevos"));
                alternativa.add(request.getParameter("alt_frijoles"));
                alternativa.add(request.getParameter("alt_platanos"));
                alternativa.add(request.getParameter("alt_pan"));
                alternativa.add(request.getParameter("alt_bebida"));

                for(int i=0;i<menu.menus(nameCombo).size();i++){
                        nuevoMenu.add(menu.menus(nameCombo).get(i)+" ["+alternativa.get(i)+"]");
                }
                order.setMenu(nuevoMenu);

                Map<String,String>extras = new HashMap<>();
                extras.put("Huevos",request.getParameter("extra_huevos"));
                extras.put("Frijoles",request.getParameter("extra_frijoles"));
                extras.put("Platanos",request.getParameter("extra_platanos"));
                extras.put("Crema",request.getParameter("extra_crema"));
                extras.put("Queso",request.getParameter("extra_queso"));
                extras.put("Pan",request.getParameter("extra_pan"));
                extras.put("Tortillas",request.getParameter("extra_tortillas"));
                extras.put("Fruta",request.getParameter("extra_fruta"));
                extras.put("Jugo de naranja",request.getParameter("extra_jugon"));

                List<String>listaFood = new ArrayList<>();

                listaFood.add("Huevos");
                listaFood.add("Frijoles");
                listaFood.add("Platanos");
                listaFood.add("Crema");
                listaFood.add("Queso");
                listaFood.add("Pan");
                listaFood.add("Tortillas");
                listaFood.add("Fruta");
                listaFood.add("Jugo de naranja");

                List<String> listExtras = new ArrayList<>();

                int totalExtras = 0;
                AdditionalControl add = new AdditionalControl();

                for (int i=0;i<extras.size();i++){
                    totalExtras +=  add.getValue(listaFood.get(i)) * Integer.parseInt(extras.get(listaFood.get(i)));
                    if(Integer.parseInt(extras.get(listaFood.get(i)))!=0){

                        listExtras.add(listaFood.get(i) + " ("+extras.get(listaFood.get(i))+")");
                    }
                }

                order.setExtras(listExtras);

                System.out.println(getPrecioMenu(nameCombo)+totalExtras);

                order.setTotal(getPrecioMenu(nameCombo)+totalExtras);

                //ORDEREND


                return order;

            default:
                return order;
        }
    }

    public int getPrecioMenu(String nameMenu) {
        switch (nameMenu) {
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
