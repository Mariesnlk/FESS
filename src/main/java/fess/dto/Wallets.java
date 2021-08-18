package fess.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Wallets {
    private Long id;
    private String address;
    private String status;
    private String response;
    private Double amount;
}
