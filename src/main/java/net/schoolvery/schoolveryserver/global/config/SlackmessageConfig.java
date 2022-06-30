package net.schoolvery.schoolveryserver.global.config;

import com.slack.api.Slack;
import com.slack.api.webhook.Payload;
import com.slack.api.webhook.WebhookResponse;

import java.io.IOException;

public class SlackmessageConfig {
    public static WebhookResponse send(String text) {
        try {
            WebhookResponse response = null;
            Slack slack = Slack.getInstance();

            String webhookUrl = "https://hooks.slack.com/services/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";

            Payload payload = Payload.builder().text(text).build();
            response = slack.send(webhookUrl, payload);
            return response;
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
