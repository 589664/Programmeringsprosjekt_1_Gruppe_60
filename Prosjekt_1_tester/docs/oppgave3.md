### Oppgave 3 - GPS hjelpemetoder

I denne oppgaven skal det implementeres noen hjelpe-metoder i klassen `GPSUtils.java` som vi skal bruke seinere i prosjektet til å gjøre beregninger på de GPS datapunkter som blir lest inn og lagret via metodene og klassene implementert i oppgave 2.

Oppgaven består i å gjøre ferdig implementasjonen av metodene nedenfor i klassen `GPSUtils.java` og bruke enhetstestene i klassen `GPSUtilsTester.java` til løpende å teste koden.

*For denne oppgaven er spesielt Kap 5.1-5.3, 5.5-5.6 i Java-boken samt Undervisning 8 og 9 relevante*

#### a)

Implementer metoden

```java
double findMin(double[] da)
```

som finner minste tall i en tabell med flyttall. Det kan antas at der er minst et element i tabellen. **Hint:** se på implementasjonen av metoden `findMax` som allerede finnes i klassen.

#### b)

Implementer metoden

```java
public static double[] getLatitudes(GPSPoint[] gpspoints)
```

som tar en tabell med GPS punkter som parameter og returnerer en tabell av desimaltall inneholdende breddegradene for GPS-punktene.

**Hint:** metoden skal først opprette en tabell av desimaltall med samme lengde som `gpspoint` og så kopiere de enkelte breddegrader over i den nye tabellen. Husk at `getLatitudes`-metoden på et `GPSPoint`-objekt kan brukes til å lese ut breddegrad i et objekt.   

#### c)

Implementer metoden

```java
public static double[] getLongitudes(GPSPoint[] gpspoints)
```

som er tilsvarende `getLatitudes`-metoden ovenfor men for lengdegrader.

#### d)

Implementer metoden

```java
public static double distance(GPSPoint gpspoint1, GPSPoint gpspoint2) {

```

som beregner avstanden *d* i meter mellom to GPS punkter på jordkloden ved bruk av Haversine-formlen

![](assets/markdown-img-paste-20180909113408842.png)

der *R = 6371000* meter er jordens gjennomsnittsradius.

**Hint:** Math-klassen: https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/Math.html inneholder de metoder som trengs inkl. `atan2` og `toRadians` metoder.

#### e)

Implementer metoden

```java
public static double speed(GPSPoint gpspoint1, GPSPoint gpspoint2) {
```

som beregninger gjennomsnittshastighet i **km/t** om man beveger seg fra punktet gitt ved `gpspoint1` til punktet `gpspoint2` på det antall sekunder som er gitt med parameteren `secs`.

**Hint:** Bruk metoden `distance` fra d) samt get-metode(r) på `GPSPoint`-objekt.

#### f)

Implementer metoden

```java
public static String formatTime(int secs)
```

som returnerer en streng på formatet `hh:mm:ss` der tiden i sekunder fra midnatt er gitt av parameteren `secs`. I strengen på formatet `hh:mm:ss`  er `hh` er antall timer, `mm` er antall minutter og `ss` er antall sekunder. Videre skal metoden legge inn mellomrom foran tiden slik den total lengden på strengen blir 10. **Hint:** se på `format`-metoden i `String`-klassen.`

#### g)

Implementer metoden

```java
public static String formatDouble(double d)
```

som runder av et flyttall til to desimaler, setter resultat inn i en streng og fyller på med mellomrom foran i strengen slik at lengden på strengen blir 10.
