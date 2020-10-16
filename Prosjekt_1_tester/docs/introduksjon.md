## GPS data og mål med prosjektet

Mange sykkelcomputere, sportsklokker og fitness-applikasjoner bruker GPS data dvs. informasjon om bla. tid og posisjon for å beregne statistikk som distanse, hastighet, høydemeter og til å visualisere rute og høydeprofil. Dette gjøres i programkode på enheten.

### Formål

Formålet med prosjektet er å implementere Java-programmer som har tilsvarende funksjonalitet som en typisk sykkelcomputer. Avanserte sykkelcomputere gjør beregninger løpende basert på input data fra en GPS sensor. Java-programmene i dette prosjektet baseres på å lese GPS data fra en datafil. På den måten vil Java-programmet simulere en riktig sykkelcomputer og fungere som en slags virtuell (ikke fysisk) sykkelcomputer.

Det ultimative målet med prosjektet er en Java-applikasjon som bruker EasyGraphics biblioteket til løpende (live) å gi syklisten (brukeren) informasjon svarende til det som skissert på bildet nedenfor. Her er tid og aktuell hastighet vist i øverste venstre hjørne og høydeprofilen er i midten. Videre er ruten tegnet på et kort nederst der rød angir at sykkelruten går oppover, grønn at det går nedover og blå at det er flat.

![](assets/markdown-img-paste-20180909131911330.png)

### GPS data format

Der finnes ulike formater som kan brukes til å lagre GPS data. I dette prosjektet skal vi ta utgangs-punkt i tekstfiler som bruker en variant av *CSV* formatet (Comma-Separated-Values). Dette betyr at en datafil med eksempelvis fem GPS datapunkter representeres på følgende måte:

```
5
time,lat,lon,elevation,accuracy,bearing,speed,satellites,provider,
2017-08-13T08:52:26.000Z,60.385390,5.217217,61.9,7.0,219.93,0.94605947,0,gps
2017-08-13T08:53:00.000Z,60.385588,5.217857,56.2,11.1,0.0,0.0,0,gps
2017-08-13T08:53:57.000Z,60.385398,5.216950,56.1,10.5,0.0,0.0,0,gps
2017-08-13T08:55:55.000Z,60.383428,5.219823,57.0,5.8,143.5,4.989717,0,gps
2017-08-13T08:57:57.000Z,60.376988,5.227082,105.5,5.2,159.61,9.415813,0,gps,
```

Den første linjen inneholder et heltall (her 5) som angir hvor mange GPS datapunkter som finnes i filen. Linje 2 gir informasjon om hvordan de etterfølgende linjer (selve GPS datapunktene) med verdier oppdelt med komma skal tolkes (betyr). Her kan vi se at først kommer informasjon om tid (time), så breddegrad (engelsk: latitude), så lengdegrad (engelsk: longitude), så høyde (engelsk: elevation), så nøyaktighet (engelsk: accuracy) osv.  

Som eksempel har vi for det første GPS datapunkt (i linje 3 i filen) informasjonen at datoen var 13. august 2017 kl. 8:52:26, breddegraden var 60.305390 grader (nord pga. gradtallet er positivt) og lengdegraden var 5.217217 grader (øst pga. gradtallet er positivt). Høyden var 61.9 meter.

Datafilen forteller altså hvilke punkter vi har flyttet oss igjennom og til hvilket tidspunkt. Vi startet i punktet svarende til linje 3, så flyttet vi oss til punktet i linje 4 osv. Totalt sett har vi beveget oss gjennom fem punkter som da definerer den ruten vi har syklet.

I oppgaven skal dere bruke større GPS datafiler bla. en datafil hvor en DAT100 underviser har vært ute og syklet på ulike deler av løypene fra VM i Bergen, 2017. Ruten er vist her: http://doarama.com/view/1604599 der GPS datafilen er visualisert basert på en web-basert applikasjon.
