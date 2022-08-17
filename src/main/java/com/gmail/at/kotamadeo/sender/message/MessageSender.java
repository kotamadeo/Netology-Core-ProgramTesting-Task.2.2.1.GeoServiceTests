package com.gmail.at.kotamadeo.sender.message;

import java.util.Map;

public interface MessageSender {
    String send(Map<String, String> headers);
}
