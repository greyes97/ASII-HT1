package gt.com.antiguaburger.model;

public class IOrderControlFactory {

    public IOrderControl getMenu(String whatMenu){
        switch (whatMenu){
            case "Desayuno 1":
                return new BreakfastOne();
            case "Desayuno 2":
                return new BreakfastTwo();
            case "Desayuno 3":
                return new BreakfastThree();
            case "Almuerzo 1":
                return new LunchOne();
            case "Almuerzo 2":
                return new LunchTwo();
            case "Cena 1":
                return new DinnerOne();
            default:
                return new NullObject();
        }
    }
}
