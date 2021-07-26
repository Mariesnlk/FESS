package fess.config.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import fess.config.resources.Statuses;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
public class MVCResponse {
    @Getter
    @Setter
    @JsonProperty("status")
    protected int status;

    public MVCResponse(int status){
        this.status = status;
    }

    public MVCResponse(Statuses status){
        this.status = status.getStatus();
    }
}
