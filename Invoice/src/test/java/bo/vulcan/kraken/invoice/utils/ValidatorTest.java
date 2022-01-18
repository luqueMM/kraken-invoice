package bo.vulcan.kraken.invoice.utils;

import org.junit.Test;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ValidatorTest {
    @Test
    public void validationStringSize() {
        final String str = "prueba";

        final String resultado = Validations.Str.size(str, 1, 3);
        final String resultadoEsperado = "string length must be less than or equal to 3";
        assertEquals(resultadoEsperado, resultado);
    }

    @Test
    public void validationStringEmail() {
        final String str = "username@domain.com ";

        final String resultado = Validations.Str.email(str);
        final String resultadoEsperado = null;
        assertEquals(resultadoEsperado, resultado);
    }

    @Test
    public void validationIntegerRange() {
        final Integer value = 1;

        final String resultado = Validations.IntegerNumber.range(value, 2, 3);
        final String resultadoEsperado = "must be greater than or equal to 2";
        assertEquals(resultadoEsperado, resultado);
    }

    @Test
    public void validationBigDecimalRange() {
        final BigDecimal bigDec = new BigDecimal(1);

        final String resultado = Validations.BigDec.range(bigDec, new BigDecimal(2), new BigDecimal(3));
        final String resultadoEsperado = "must be greater than or equal to 2";
        assertEquals(resultadoEsperado, resultado);
    }

    @Test
    public void validationListRange() {
        final List<String> value = Collections.singletonList("dasda");

        final String resultado = Validations.Lists.range(value, 2, 3);
        final String resultadoEsperado = "must be greater than or equal to 2";
        assertEquals(resultadoEsperado, resultado);
    }
}
