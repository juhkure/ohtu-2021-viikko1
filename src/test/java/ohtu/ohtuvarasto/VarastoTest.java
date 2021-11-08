package ohtu.ohtuvarasto;

import com.google.errorprone.annotations.Var;
import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    Varasto varasto2;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
        varasto2 = new Varasto(-1);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void lisaysYlittaaVarastonTilan() {
        varasto.lisaaVarastoon(13);
        
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
        
    }
    
    @Test
    public void otetaanLiikaaTavaraa() {
        varasto.lisaaVarastoon(8);
        varasto.otaVarastosta(9);
        
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void alitettuTilavuus() {
        assertEquals(0, varasto2.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void lisataanAlleNolla() {
        varasto.lisaaVarastoon(5);
        varasto.lisaaVarastoon(-1);
        
        assertEquals(5, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void otetaanAlleNolla() {
        varasto.lisaaVarastoon(5);
        varasto.otaVarastosta(-1);
        
        assertEquals(5, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void toStringToimii() {
        assertEquals("saldo = 0.0, vielä tilaa 10.0", varasto.toString());
    }

    
    @Test
    public void alhainenMaaritettyTilavuus() {
        Varasto varastoA = new Varasto(-1, 5);
        
        assertEquals(0, varastoA.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void sopivaMaaritettyTilavuus() {
        Varasto varastoA = new Varasto(5, 5);
        
        assertEquals(5, varastoA.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void alhainenMaaritettySaldo() {
        Varasto varastoA = new Varasto(10, -1);
        
        assertEquals(0, varastoA.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void sopivaMaaritettySaldo() {
        Varasto varastoA = new Varasto(10, 9);
        
        assertEquals(9, varastoA.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void yliMaaritettySaldo() {
        Varasto varastoA = new Varasto(10, 9);
        
        assertEquals(10, varastoA.getSaldo(), vertailuTarkkuus);
    }
}