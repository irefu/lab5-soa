package soa.eip;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

// https://camel.apache.org/manual/latest/processor.html
public class SearchProcessor implements Processor {
    public void process(Exchange exchange) throws Exception {
        String originalBody = (String) exchange.getIn().getBody(String.class);
        String body = "";

        // I want to iterate and find max:n so i have to split the body
        String[] splitBody = originalBody.split(" ");

        // I'll use as count parameter in Header -> default is 5
        int n = 5;
        for (String i: splitBody){
            // Using Regular Expressions to find max:n item
            if(i.matches("max:[0-9]+")){
                n =  Integer.parseInt(i.substring(4));
            }
            else{
                body = body + i + " ";
            }
        }
        exchange.getIn().setBody(body);

        // https://camel.apache.org/components/latest/twitter-search-component.html
        exchange.getIn().setHeader("count", n);
    }
}
