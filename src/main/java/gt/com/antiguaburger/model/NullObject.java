package gt.com.antiguaburger.model;

import javax.servlet.http.HttpServletRequest;

public class NullObject implements IOrderControl{
    @Override
    public OrderEntity createOrder(HttpServletRequest request) {
        OrderEntity order = new OrderEntity();
        order.setCashier("Menu no encontrado");
        order.setOrder("Hubo un error");
        order.setCustomer("Revisa tu orden por favor");
        order.setTaxId("Hubo un error inesperado");
        return order;
    }


}
