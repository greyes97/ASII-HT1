package gt.com.antiguaburger.model;

public class FileControlFactory {
    public IFileControl getFile(String whatFile){
        switch (whatFile){
            case "json":
                return new JsonFile();
            case "txt":
                return new TxtFile();
            case "csv":
                return new CsvFile();
            case "xml":
                return new XmlFile();
            default:
                return null;
        }
    }
}
