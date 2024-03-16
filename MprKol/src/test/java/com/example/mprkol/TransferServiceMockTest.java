package com.example.mprkol;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class TransferServiceMockTest {
    @Mock
    private ClientStorage clientStorage;
    @InjectMocks
    private TransferService transferService;

}