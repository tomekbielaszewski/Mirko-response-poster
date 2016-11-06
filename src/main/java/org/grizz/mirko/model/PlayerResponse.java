package org.grizz.mirko.model;

import lombok.Data;
import lombok.Builder;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "commandResponses")
public class PlayerResponse {
    private String id;
    private String receiver;
    private String response;
    private boolean sent;
}
