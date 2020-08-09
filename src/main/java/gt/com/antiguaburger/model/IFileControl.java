package gt.com.antiguaburger.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public interface IFileControl {
    public void createFile(OrderEntity order) throws IOException;
}
