package com.liyulin.hibernate.validator.entity;

import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @author collin
 * @date 2021-03-02
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class QryTransResultReqVO implements Serializable {

    @NotEmpty
    private List<@Valid Transaction> transactions;

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Transaction {
        /**
         * 交易id
         */
        @NotNull
        private Long transactionId;

        /**
         * akulaku uid
         */
        @NotNull
        private Long akulakuUid;
    }

}