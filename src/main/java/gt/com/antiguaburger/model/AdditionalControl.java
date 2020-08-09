package gt.com.antiguaburger.model;

public class AdditionalControl {

    public int getValue(String nameExtras){

        switch (nameExtras) {
            case "Huevos":
                return 1;
            case "Frijoles":
                return 4;
            case "Platanos":
                return 4;
            case "Crema":
                return 2;
            case "Queso":
                return 2;
            case "Tortillas":
                return 5;
            case "Pan":
                return 4;
            case "Fruta":
                return 8;
            case "Jugo de naranja":
                return 6;
            case "Torta de carne":
                return 10;
            case "Queso amarillo":
                return 6;
            case "Helado":
                return 7;
            case "Pastel":
                return 10;
            case "Flan":
                return 8;
            case "Gelatina":
                return 3;
            default:
                return 0;
        }
    }
}
