package gt.com.antiguaburger.model;


import java.util.List;

public interface IMenuDao {
    public List<String> menus(String nameMenu);
    public int getPrecioMenu(String whatMenu);
}
