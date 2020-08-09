package gt.com.antiguaburger.model;

import gt.com.antiguaburger.controller.Controlador;
import gt.com.antiguaburger.controller.IMenuDao;
import gt.com.antiguaburger.controller.MenuDao;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BreakfastThree implements IOrderControl {
    @Override
    public OrderEntity createOrder(HttpServletRequest request) {
        int numberOrder =(int) (5 + Math.random()*20);
        OrderEntity order = new OrderEntity();

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
        List<String> nuevoMenu = new ArrayList<>();
        List<String>alternativa = new ArrayList<>();

        alternativa.add(request.getParameter("alt_bebida"));


        nuevoMenu.add(menu.menus(request.getParameter("combo")).get(2)+" ["+alternativa.get(0)+"]");

        order.setMenu(nuevoMenu);

        Map<String,String> extras = new HashMap<>();
        extras.put("Fruta",request.getParameter("extra_fruta"));
        extras.put("Jugo de naranja",request.getParameter("extra_jugon"));

        List<String>listaFood = new ArrayList<>();


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

        System.out.println(menu.getPrecioMenu(request.getParameter("combo"))+totalExtras);

        order.setTotal(menu.getPrecioMenu(request.getParameter("combo"))+totalExtras);

        //ORDEREND



        return order;
    }
}
