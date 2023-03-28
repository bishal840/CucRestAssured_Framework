package Resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.*;
import java.util.Properties;

public class Utilities {
    static RequestSpecification req;
    public RequestSpecification requestSpec() throws IOException {
        if(req==null) {
            PrintStream logStream = new PrintStream(new FileOutputStream("target//LOG.txt"));
            req = new RequestSpecBuilder().setBaseUri(getGlobalvalue("baseUrl")).setContentType(ContentType.JSON)
                    .addQueryParam("key", "qaclick123")
                    .addFilter(RequestLoggingFilter.logRequestTo(logStream))
                    .addFilter(ResponseLoggingFilter.logResponseTo(logStream))
                    .build();
        }
        return req;
    }

    public String getGlobalvalue(String key) throws IOException {
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//test//java//Resources//global.properties");
        Properties prop = new Properties();
        prop.load(fis);

        return prop.getProperty(key);
    }

    public String getJsonPathValue(Response response, String key)
    {
        JsonPath jsonPath = new JsonPath(response.asString());
        return jsonPath.getString(key);
    }

}
