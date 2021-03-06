package twilio;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.RequestDispatcher;

@WebServlet("/call")
public class Call extends HttpServlet {
  AppSetup appSetup;
  TwilioRestClient client;

  public Call() {}

  public Call(AppSetup appSetup, TwilioRestClient client) {
    this.appSetup = appSetup;
    this.client = client;
  }

  /**
   * Method that triggers a call to the specified number in the request
   * @param request incoming servlet request object
   * @param response servlet response object
   * @throws ServletException
   * @throws IOException
   */
  public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String phoneNumber = "+447885765760";

    if (phoneNumber != null) {
      if (this.appSetup == null || this.client == null) {
        appSetup = new AppSetup();
        client = null;
        try {
          client = new TwilioRestClient(appSetup.getAccountSid(), appSetup.getAuthToken());
        } catch (UndefinedEnvironmentVariableException e) {
          response.getOutputStream().write(getJSONResponse(e.getMessage()).getBytes());
          return;
        }
      }

      Map<String, String> params = new HashMap<>();
      String twilioNumber;
      try {
        twilioNumber = appSetup.getTwilioNumber();
      } catch (UndefinedEnvironmentVariableException e) {
        response.getOutputStream().write(getJSONResponse(e.getMessage()).getBytes());
        return;
      }

      // Full path to the end point that will respond with the call TwiML
      String path =
          request.getRequestURL().toString().replace(request.getRequestURI(), "") + "/connect";
      params.put("From", twilioNumber);
      params.put("To", phoneNumber);
      params.put("Url", path);

      try {
        client.getAccount().getCallFactory().create(params);
      } catch (TwilioRestException e) {
        String message = "Twilio rest client error: " + e.getErrorMessage() +
            "\nRemember not to use localhost to access this app, use your ngrok URL";
        response.getOutputStream().write(getJSONResponse(message).getBytes());
        //return;
      }
      response.getOutputStream().write(getJSONResponse("Phone call incoming!").getBytes());
    } else {
      response.getOutputStream()
          .write(getJSONResponse("The phone number field can't be empty").getBytes());
    }
    
    response.sendRedirect("/CFG2015/Profile/a");
  }

  private String getJSONResponse(String message) {
    JSONObject obj = new JSONObject();
    obj.put("message", message);
    obj.put("status", "ok");

    return obj.toJSONString();
  }
}
