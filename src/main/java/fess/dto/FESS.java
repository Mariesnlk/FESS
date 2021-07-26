package fess.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FESS {

    private String from;
    private String to;
    private BigDecimal amount;

}
