
package temp;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the temp package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _CheckResponse_QNAME = new QName("http://temp/", "checkResponse");
    private final static QName _Check_QNAME = new QName("http://temp/", "check");
    private final static QName _SendEmailDemoResponse_QNAME = new QName("http://temp/", "sendEmailDemoResponse");
    private final static QName _SendEmailDemo_QNAME = new QName("http://temp/", "sendEmailDemo");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: temp
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SendEmailDemo }
     * 
     */
    public SendEmailDemo createSendEmailDemo() {
        return new SendEmailDemo();
    }

    /**
     * Create an instance of {@link CheckResponse }
     * 
     */
    public CheckResponse createCheckResponse() {
        return new CheckResponse();
    }

    /**
     * Create an instance of {@link Check }
     * 
     */
    public Check createCheck() {
        return new Check();
    }

    /**
     * Create an instance of {@link SendEmailDemoResponse }
     * 
     */
    public SendEmailDemoResponse createSendEmailDemoResponse() {
        return new SendEmailDemoResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CheckResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://temp/", name = "checkResponse")
    public JAXBElement<CheckResponse> createCheckResponse(CheckResponse value) {
        return new JAXBElement<CheckResponse>(_CheckResponse_QNAME, CheckResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Check }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://temp/", name = "check")
    public JAXBElement<Check> createCheck(Check value) {
        return new JAXBElement<Check>(_Check_QNAME, Check.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendEmailDemoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://temp/", name = "sendEmailDemoResponse")
    public JAXBElement<SendEmailDemoResponse> createSendEmailDemoResponse(SendEmailDemoResponse value) {
        return new JAXBElement<SendEmailDemoResponse>(_SendEmailDemoResponse_QNAME, SendEmailDemoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendEmailDemo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://temp/", name = "sendEmailDemo")
    public JAXBElement<SendEmailDemo> createSendEmailDemo(SendEmailDemo value) {
        return new JAXBElement<SendEmailDemo>(_SendEmailDemo_QNAME, SendEmailDemo.class, null, value);
    }

}
