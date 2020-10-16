# Oppgave 2 - GPS data innlesing og referansetabeller

Denne delen av prosjektet handler om hvordan GPS data kan leses inn fra fil og hvordan GPS punktene i filen kan representeres ved å bruke en tabell med pekere til `GPSPoint`-objekt.

Klassen `GPSDataRFileReader.java` inneholder ferdig Java-kode for å lese inn en CVS-datafil med GPS punkter i formatet forklart tidligere. Denne Java-koden er der ikke behov for endre i og det er først litt senere i faget vi skal ta for oss hvordan hvordan en leser fra - og skriver til - filer i Java.

Fokus i denne oppgavene er på å implementere metoder i klassen `GPSData.java` som skal lagre innlest GPS datapunkter i form av en referansetabell av `GPSPoint`-objekt og hjelpeklassen `GPSDataConverter.java` som inneholder hjelpemetoder for å konvertere det data som leses inn til `GPSPoint`-objekt.

#### a) GPS data konvertering

Når data leses inn fra fil vil informasjon om GPS punktet være representert som strenger. Eksempelvis vil tiden for et innlest GPS punkt være representert som strengen:

```
"2017-08-13T08:52:26.000Z"
```

Gjør ferdig implementasjon av metoden

```java
public static int toSeconds(String timestr)
```

som omregner tidsdata som representert ovenfor til antall sekunder. For eksemplet ovenfor skal de 8 timer, 52 minutter og 26  regnes om til sekunder og returneres.

**Hint:** Se på `substring`-metoden i `String`-klassen samt `parseInt`-metoden i `Integer`-klassen.  

Implementer metoden

```java
public static GPSPoint convert(String timeStr, String latitudeStr, String longitudeStr, String elevationStr) {
```

som tar String-representasjoner av tid, breddegrad, lengdegrad og høyd, konverterer disse og oppretter et `GPSPoint`-objekt med de tilsvarende data. Metoden skal returnere en peker til det `GPSPoint`-objekt som blev opprettet.

Hvis eksempelvis skal metoden kalles med

```java
convert("2017-08-13T08:52:26.000Z","60.385390","5.217217","61.9")
```

da skal metoden returner et `GPSPoint`-objekt der `time` er `31946`, `latitude` er `60.385390`, `longitude` er `5.217217` og `elevation` er `61.9`.

#### b) Referansetabell med GPS punkter

GPS punktene som leses inn fra fil og konverteres skal representeres ved å bruke en referansetabell dvs. en tabell der elementene som er lagret er pekere (referanser) til `GPSPoint`-objekt.

*For denne oppgaven er spesielt Kap 6.4 samt Undervisning 14 relevant.

Starten på implementasjonen finnes i klassen `GPSData.java`:

```java
public class GPSData {

	private GPSPoint[] gpspoints;
	protected int antall = 0;

  [...]
```

Objektvariablen `gpspoints` skal brukes til å peke på referansetabellen av GPS punkter. Objektvariabelen `antall` skal brukes ifm. med innsettelse i tabellen til å holde kontroll på hvor (dvs. på hvilken posisjon/indeks) neste punkt skal settes inn.

Variablen `antall` vil til ethvert tidspunkt angi hvor mange GPS punkter som er satt inn i tabellen. Indeks der det ikke er satt inn noen vare vil ha verdien `null` (en null-peker). Objektvariablen `antall` har modifikatoren `protected` for å gjøre det enklere å teste klassen. Testene for klassen finnes i klassen `GPSDataTester.java`.

Gjør ferdig implementasjonen av følgende metoder:

- `public GPSData(int n)` som er en kontruktør for klassen. Konstruktøren skal opprette en tabell av GPS punkter med størrelsen gitt ved parameteren `n` og sette `antall` lik `0` (siden første element skal inn på position 0).

- `protected boolean insertGPS(GPSPoint gpspoint)` som setter inn GPS punktet `gpspoint` i `gpspoints`-tabellen på posisjonen angitt ved objektvariablen `antall`. Videre skal metoden inkrementere `antall` slik neste punkt kommmer inn på neste posisjon. Metoden skal kun sette inn `gpspoint` om der er plass i tabellen dvs. hvis `antall` er strengt mindre enn `gpspoints.length`. Metoden skal returnere `true` om punktet blev satt inn og `false` ellers.

- `public boolean insert(String time, String latitude, String longitude, String elevation)` som tar GPS punkt data i streng-representasjon og setter inn et tilsvarende `GPSPoint`-objekt i `gpspoints`-tabellen. **Hint:** Metoden skal konvertere data, opprette et nytt `GPSPoint-objekt` og bruke `insertGPS`-metoden ovenfor.

- `public void print()` som skal skrive ut GPS data som finnes i `gpspoints`-tabellen på følgende formen

```
====== Konvertert GPS Data - START ======
1 (1.0,2.0) 3.0
2 (4.0,5.0) 6.0
3 (7.0,8.0) 9.0
====== Konvertert GPS Data - SLUTT ======
```

**Hint**: bruk løkke og `toString`-metoden på `GPSPoint`-objekt

Metodene som allerede er implementert i klassen `GPSDataFileReader.java` leser - linje for linje - i GPS datafilen og lagrer data i tabellen ved å bruke `insert`-metoden. Dette betyr at punktene i `gpspoint`-tabellen svarer til ruten som er representert i GPS datafilen.
