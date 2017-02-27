package io.reactivesw.message.client.utils;

/**
 * broker type.
 */
public enum BrokerType {
  GOOGLE("google");

  private String value;

  BrokerType(String value) {
  }

  public String getValue() {
    return value;
  }

  public BrokerType getType(String value) {
    switch (value) {
      case "google":
        return GOOGLE;
      default:
        return null;
    }
  }
}
