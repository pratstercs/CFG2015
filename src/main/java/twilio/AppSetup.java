package twilio;

import twilio.UndefinedEnvironmentVariableException;

import java.util.Map;

/**
 * Class that holds methods to obtain configuration parameters from the environment.
 */
public class AppSetup {
  private Map<String, String> env;

  public AppSetup() {
    this.env = System.getenv();
  }

  public String getAccountSid() throws UndefinedEnvironmentVariableException {
    String sid = "AC844596575b2edf5146cb5be0cff9e259";
    if (sid == null) {
      throw new UndefinedEnvironmentVariableException("TWILIO_ACCOUNT_SID is not defined");
    } else {
      return sid;
    }
  }

  public String getAuthToken() throws UndefinedEnvironmentVariableException {
    String token = "52b8b19551287db5b37c53b9d435f5b8";
    if (token == null) {
      throw new UndefinedEnvironmentVariableException("TWILIO_AUTH_TOKEN is not set");
    } else {
      return token;
    }
  }

  public String getTwilioNumber() throws UndefinedEnvironmentVariableException {
    String phoneNumber = "+441143031107";
    if (phoneNumber == null) {
      throw new UndefinedEnvironmentVariableException("TWILIO_NUMBER is not set");
    } else {
      return phoneNumber;
    }
  }
}
