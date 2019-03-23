package flaskoski.faire.apicommunication;

import java.util.List;

public interface ApiReader {
    List getAllItems();
    List getItensOfOnePage(String content);
}
