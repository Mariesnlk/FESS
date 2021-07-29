package fess.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FESS {

    private String from;
    private String to;
    private Long amount;

}
