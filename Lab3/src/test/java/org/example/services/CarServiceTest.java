package org.example.services;
import junit.framework.TestCase;
import org.example.cars.Car;
import org.example.cars.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import junit.framework.TestCase;
import org.example.storage.CarStorage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CarServiceTest {

    @Captor
    private ArgumentCaptor<String> stringArgumentCaptor;

    @Mock
    private CarStorage carStorage;

    @InjectMocks
    private CarService carService;

    @Test
    void shouldFindFirstCarName() {
        when(carStorage.getCarNames(stringArgumentCaptor.capture()))
                .thenReturn(List.of("Peugeot", "Mercedes"));

        String firstCarName = carService.getFirstCarName();

        String value = stringArgumentCaptor.getValue();

        assertThat(firstCarName).isEqualTo("Peugeot");
        assertThat(value).isEqualTo("Peugeot");
    }
}