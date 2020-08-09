package gt.com.antiguaburger.controller;

import gt.com.antiguaburger.model.*;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@Controller
public class Controlador {
    public static String name;
    public static OrderEntity order;
    public static IMenuDao menu = new MenuDao();
    private static File fileCreate;


    //INICIO DE SESION
    @GetMapping("/")
    public String startControllerr(Model model){
        model.addAttribute("nameCashier", new Cashier());
        return "index";
    }

    @PostMapping("/")
    public String startController(Model model){
        return "menus";
    }

    //VIEW MENUS
   @GetMapping("/menus")
    public String menuController(Model model) {
        //MenuItems
       return getString(model);
   }



    @PostMapping("/menus")
    public String menuController(HttpServletRequest request,Model model) {
        name = request.getParameter("txt_name");
        model.addAttribute("nameCashier",name);

        return getString(model);
    }

    //VIEW ORDER
    @GetMapping("/factura")
    public String exitoController(Model model, HttpServletRequest request){
        System.out.println(order.getTotal());
        model.addAttribute("total",order.getTotal());

        return "factura";
    }
    @PostMapping("/factura")
    public String exitoController(HttpServletRequest request,Model model){
        IOrderControlFactory menu = new IOrderControlFactory();
        IOrderControl createOrder = menu.getMenu(request.getParameter("combo"));
        order = createOrder.createOrder(request);

        model.addAttribute("nameCashier",name);
        model.addAttribute("order",order.getOrder());
        model.addAttribute("date",order.getDate());
        model.addAttribute("nameCustomer",order.getCustomer());
        model.addAttribute("taxId",order.getTaxId());
        model.addAttribute("combo",request.getParameter("combo"));
        model.addAttribute("total",order.getTotal());
        model.addAttribute("menu",order.getMenu());
        model.addAttribute("extras",order.getExtras());
        model.addAttribute("total",order.getTotal());

        model.addAttribute("download", order.getOrder());



        return "factura";
    }
    @GetMapping("/descarga")
    public ResponseEntity<Object> downloadFile(HttpServletRequest request) throws IOException
            //ARCHIVO DE DESCARGA
    {

        FileControlFactory factory = new FileControlFactory();
        IFileControl iFile = factory.getFile(request.getParameter("btn_file"));
        iFile.createFile(order);


        if(request.getParameter("btn_file").equals("txt")){

            File file = new File(order.getOrder()+".txt");
            return getObjectResponseEntity(file);
        } else if(request.getParameter("btn_file").equals("json")){
            File file = new File(order.getOrder()+".json");
            return getObjectResponseEntity(file);
        }else if(request.getParameter("btn_file").equals("csv")){
            File file = new File(order.getOrder()+".csv");
            return getObjectResponseEntity(file);
        }else if(request.getParameter("btn_file").equals("xml")){
            File file = new File(order.getOrder()+".xml");
            return getObjectResponseEntity(file);
        }

        return null;

    }

    private ResponseEntity<Object> getObjectResponseEntity(File file) throws FileNotFoundException {
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition",
                String.format("attachment; filename=\"%s\"", file.getName()));
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        ResponseEntity<Object> responseEntity = ResponseEntity.ok().headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/txt")).body(resource);

        return responseEntity;
    }

    private String getString(Model model) {
        model.addAttribute("combo1",menu.menus("Desayuno 1"));
        model.addAttribute("combo2",menu.menus("Desayuno 2"));
        model.addAttribute("combo3",menu.menus("Desayuno 3"));
        model.addAttribute("combo4",menu.menus("Almuerzo 1"));
        model.addAttribute("combo5",menu.menus("Almuerzo 2"));
        model.addAttribute("combo6",menu.menus("Cena 1"));
        model.addAttribute("nameCashier",name);
        return "menus";
    }

}
