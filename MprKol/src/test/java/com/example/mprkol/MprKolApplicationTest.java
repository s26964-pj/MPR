package com.example.mprkol;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
@SpringBootTest
class MprKolApplicationTest {
    @Test
    void contextLoads() {
    }

    @MockBean
    private ClientStorage clientStorage;

    @Autowired
    private TransferService transferService;

    @Test
    void clientListIsNotNull(){
        List<Client> allClients = clientStorage.getClientList();
        assertThat(allClients).isNotNull();
    }

    @Test
    void clientCanSendMoney() {
        //when
        Transfer transfer =  transferService.sendMoney("1", 2000);
        //then
        Assertions.assertThat(transfer.getTransferType()).isEqualTo(TransferType.ACCEPTED);
    }

    @Test
    void clientCanNotSendMoney() {
        //when
        Transfer transfer =  transferService.sendMoney("1", 200000);
        //then
        Assertions.assertThat(transfer.getTransferType()).isEqualTo(TransferType.DECLINED);
    }

    @Test
    void clientCanAddMoney() {
        //when
        Transfer transfer =  transferService.addMoney("1", 2000);
        //then
        Assertions.assertThat(transfer.getTransferType()).isEqualTo(TransferType.ACCEPTED);
    }

    @Test
    void clientCanNotAddMoney() {
        //when
        Transfer transfer =  transferService.addMoney("1", -2000);
        //then
        Assertions.assertThat(transfer.getTransferType()).isEqualTo(TransferType.DECLINED);
    }
}