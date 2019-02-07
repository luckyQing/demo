
package com.liyulin.webservice.client.shopping;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.liyulin.webservice.client.shopping package. 
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

    private final static QName _Buy_QNAME = new QName("http://service.webservice.liyulin.com/", "buy");
    private final static QName _BuyResponse_QNAME = new QName("http://service.webservice.liyulin.com/", "buyResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.liyulin.webservice.client.shopping
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link BuyResponse }
     * 
     */
    public BuyResponse createBuyResponse() {
        return new BuyResponse();
    }

    /**
     * Create an instance of {@link Buy }
     * 
     */
    public Buy createBuy() {
        return new Buy();
    }

    /**
     * Create an instance of {@link OrderRespBody }
     * 
     */
    public OrderRespBody createOrderRespBody() {
        return new OrderRespBody();
    }

    /**
     * Create an instance of {@link ProductReqBody }
     * 
     */
    public ProductReqBody createProductReqBody() {
        return new ProductReqBody();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Buy }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.webservice.liyulin.com/", name = "buy")
    public JAXBElement<Buy> createBuy(Buy value) {
        return new JAXBElement<Buy>(_Buy_QNAME, Buy.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BuyResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.webservice.liyulin.com/", name = "buyResponse")
    public JAXBElement<BuyResponse> createBuyResponse(BuyResponse value) {
        return new JAXBElement<BuyResponse>(_BuyResponse_QNAME, BuyResponse.class, null, value);
    }

}
