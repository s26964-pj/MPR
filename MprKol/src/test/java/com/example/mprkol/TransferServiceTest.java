package com.example.mprkol;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class TransferServiceTest {
    private TransferService transferService;

    @BeforeEach
    void setup() {
        ClientStorage clientStorage = new ClientStorage();
        transferService = new TransferService(clientStorage);
    }

    @Test
    void clientCanSendMoney() {
        //when
        Transfer transfer =  transferService.sendMoney("1", 2000);
        //then
        assertThat(transfer.getTransferType()).isEqualTo(TransferType.ACCEPTED);
    }

    @Test
    void clientCanNotSendMoney() {
        //when
        Transfer transfer =  transferService.sendMoney("1", 200000);
        //then
        assertThat(transfer.getTransferType()).isEqualTo(TransferType.DECLINED);
    }

    @Test
    void clientCanAddMoney() {
        //when
        Transfer transfer =  transferService.addMoney("1", 2000);
        //then
        assertThat(transfer.getTransferType()).isEqualTo(TransferType.ACCEPTED);
    }

    @Test
    void clientCanNotAddMoney() {
        //when
        Transfer transfer =  transferService.addMoney("1", -2000);
        //then
        assertThat(transfer.getTransferType()).isEqualTo(TransferType.DECLINED);
    }
}
