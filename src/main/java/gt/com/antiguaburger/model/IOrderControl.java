package gt.com.antiguaburger.model;

import javax.servlet.http.HttpServletRequest;

public interface IOrderControl {
    public OrderEntity createOrder(HttpServletRequest request);
}
