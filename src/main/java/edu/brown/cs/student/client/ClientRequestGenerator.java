package edu.brown.cs.student.client;

import edu.brown.cs.student.client.ClientAuth;

import java.net.URI;
import java.net.http.HttpRequest;
import java.time.Duration;

/**
 * This class generates the HttpRequests that are then used to make requests from the ApiClient.
 */
public class ClientRequestGenerator {

  /**
   * The basic introductory GET request. You should fill it out so it calls our server at the given URL.
   *
   * @return an HttpRequest object for accessing the introductory resource.
   */
  public static HttpRequest getIntroGetRequest() {
    String reqUri = "https://epb3u4xo11.execute-api.us-east-1.amazonaws.com/Prod/introResource";
    return HttpRequest.newBuilder(URI.create(reqUri)).GET().build();
  }

  /**
   * Similar to the introductory GET request, but restricted to api key holders only. Try calling it without the API
   * Key configured and see what happens!
   *
   * @return an HttpRequest object for accessing the secured resource.
   */
  public static HttpRequest getSecuredGetRequest() {
    String reqUri = "https://epb3u4xo11.execute-api.us-east-1.amazonaws.com/Prod/securedResource";
    String apiKey = ClientAuth.getApiKey();
    return HttpRequest.newBuilder(URI.create(reqUri))
            .GET()
            .header("x-api-key", apiKey)
            .build();
  }

  /**
   * Similar to the secured GET request, but is a POST request instead.
   *
   * @param param - the body of the POST request. This should be your name, passed in from the REPL.
   * @return an HttpRequest object for accessing and posting to the secured resource.
   */
  public static HttpRequest getSecuredPostRequest(String param) {
    String reqUri = "https://epb3u4xo11.execute-api.us-east-1.amazonaws.com/Prod/securedResource";
    String apiKey = ClientAuth.getApiKey();
    return HttpRequest.newBuilder(URI.create(reqUri))
            .POST(HttpRequest.BodyPublishers.ofString("{\"name\":\"" + param + "\"}"))
            .header("x-api-key", apiKey)
            .build();
  }

  /**
   * This is another secured GET request that has an optional string parameter in the URL. Find out what the staff's
   * horoscopes are!
   *
   * @param param - the name of the staff member whose horoscope you want to find; an empty string here will indicate
   *              that the server should return a list of all staff members instead.
   * @return an HttpRequest object for accessing and posting to the secured resource.
   */
  public static HttpRequest getHoroscopeGetRequest(String param) {
    String taName = param.isEmpty() ? "" : "?taName=" + param;
    String reqUri =
        "https://epb3u4xo11.execute-api.us-east-1.amazonaws.com/Prod/horoscopeResource/" + taName;
    String apiKey = ClientAuth.getApiKey();
    System.out.println("Getting star sign for " + param);
    return HttpRequest.newBuilder(URI.create(reqUri))
            .GET()
            .header("x-api-key", apiKey)
            .build();
  }
}
