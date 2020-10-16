# Oppgave 1: Klasse for GPS datapunkter

I denne oppgaven skal der implementeres en klasse `GPSPoint.java` for å kunne representere GPS datapunkter.

Ideen er at et GPS punkt skal representeres som et objekt av `GPSPoint`-klassen og programmet skal ha et slikt objekt for hvert GPS punkt. For hvert GPS punkt får vi behov for å lagre tidspunkt, breddegrad, lengdegrad og høyde.

*For denne oppgaven er spesielt Kap. 6.1 - 6.3 i Java-boken samt Undervisning 13 relevant.*

### a) Objektvariable og konstruktør

Se på start-koden for klassen `GPSPoint.java` i pakken `no.hvl.dat100ptc.oppgave1`. Utvid startkoden for klassen slik klassen får følgende objektvariable:

-	`time` (heltall) som angir tiden i sekunder
-	`latitude` (desimaltall) som angir breddegrad
-	`longitude` (desimaltall) som angir lengdegrad
- `elevation` (desimaltall) som angir høyde i meter

som alle skal være `private` dvs. kun synlige innenfor klassen.

Videre skal klassen ha en *konstruktør*

```java
public GPSPoint(int time, double latitude, double longitude, double elevation)
```

som kan gi verdi til alle objektvariable.

Test implementasjonen ved å kjøre testene i test-klassen `GPSPointeTester.java`

### b) Hent/sett-metoder

Gjør ferdig implementasjonen av hent/sett (get/set)-metoder og test de med enhetstestene.

### c) Representasjon som tekststreng

Gjør ferdig implementasjonen av `toString()`-metoden som returnerer en strengrepresentasjon av et `GPSPoint`-objekt på formen:

`1 (2.0,3.0) 5.0\n`

der `1` er tiden, `(2.0,3.0)` er (breddegrad,lengdegrad) og `5.0` er høyden.

Test implementasjonen ved å bruke enhetstestene.
