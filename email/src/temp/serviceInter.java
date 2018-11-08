package temp;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface serviceInter {
    @WebMethod
    public boolean sendEmailDemo(@WebParam(name = "url") List<String> url, @WebParam(name = "payload") String payload
    , @WebParam(name = "theme") String theme);
    @WebMethod
    public boolean check(@WebParam(name = "url") String email);
}
