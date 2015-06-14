# Zielbestimmung #
## Mußkriterien ##
  * Mehrere Anwender können das Produkt über ein Netzwerk gemeinsam nutzen.
  * Geeignet um nach den Halmaregeln zu Spielen (siehe Anhang)
  * Spielerzahlen: 2, 3, 4 und 6
  * Spielmodus: Nur regelkonforme Spielzüge
  * Server:
    * Start durch mitspielenden "Game Master"
    * Beim Start werden die Spielerzahl und die KI-Spielerzahl festgelegt
    * Geeignet, ein einziges Spiel zu spielen
    * Verschickt Ergebnis des gerade beendeten Spiels
  * Client:
    * Wahl eines Spielernamens
    * Wahl eines Servers
    * 2D-Darstellung des Spielbretts
    * Zugeingabe: auf dem 2D-Spielbrett
    * Spielerliste
    * Anzeige des aktiven Spielers
    * Spielzüge grafisch darstellen und für ca. 10 Sekunden angezeigt lassen
    * Anzeige des Spielergebnisses
  * KI:
    * 3 Spielstärken
    * Spielt nach den Regeln
    * Bis zu 2 Bots pro Spiel müssen unterstützt werden
    * Bots werden durch Game Master gestartet
## Wunschkriterien ##
  * Das verwendete _Spielbrett_ ist wählbar
  * 2. Spielmodus: Vetos durch Clients, keine Regleprüfung
  * Speichern und Wiederherstellen von Spielständen
  * Server:
    * Spielmodus beim Start festgelegt
    * Eine Highscoreliste
    * Macht nach Veto letzten Spielzug rückgängig
    * Nach Beendigung eines Spiels kann ein neues Spiel gestartet werden
  * Client:
    * Vetomöglichkeit innerhalb der Anzeigezeit des Zuges
    * Anzeige der Highscoreliste des aktuellen Servers
  * KI:
    * Beliebige Anzahl KI-Spieler
    * Höchste KI-Stufe muss Vetorecht korrekt nutzen
## Abgrenzungskriterien ##
  * Das Produkt ist nicht für hochmobile Systeme, wie Smartphones oder PDA ausgelegt.
  * Nicht mehr als 6 Spieler
  * Server:
    * kein dedicated Server
    * Kein Dauerbetrieb
  * Client:
    * Keine 3D-Darstellung
    * Keine Animationen
    * Nur kompletter Zug wird angezeigt
  * KI:
    * Läuft auf demselben Computer wie Server

# Produkteinsatz #
## Anwendungsbereiche ##
  * Das Produkt wird zum freundschaftlichen Halma spielen in der Freizeit benutzt
## Zielgruppen ##
  * Grundlegende PC-Kenntnisse zu Inbetriebnahme und Netzwerkeinstellungen werden vorausgesetzt
  * Zum Spielen selbst sind keine besonderen Qualifikationen erforderlich.
  * Der Anwender muss die Spielregeln verstehen können (Deutsche Sprache, Lesen)
## Betriebsbedingungen ##
  * Das Spiel soll auf handelsüblichen Desktop PCs ausgeführt werden können.
  * Der Server wird auf dem PC eines Mitspielers ausgeführt
  * Der Server läuft nur während des Spiels / der Spiele

# Produktumgebung #
## Software ##
Das Produkt setzt eine SUN Java JRE 1.5  mit nutzbarer graphischer Oberfläche (Swing) voraus.
(Es wird IPv4 benutzt)
## Hardware ##
Das Produkt ist für netzwerkfähigen x86 kompatible PC Systeme mit 1GHz Taktfrequenz, mindestens 512MiByte nutzbarem Arbeitsspeicher und 2D beschleunigender Graphikkarte/-Chipsatz konzipiert.

# Produktfunktionen #
## Serverstart ##
### /F1/ ###
Festlegen der Spielerzahl
### /F2/ ###
Festlegen der Botanzahl
### /F3/ ###
Festlegen der Netzwerkadresse
### /F4/ ###
Bestimmen des Spielmodus (Regelprüfung/Veto)
### /F5/ ###
Spielernamen des Game Master bestimmen
## Clientstart ##
### /F6/ ###
Eingeben der Serveradresse
### /F7/ ###
Festlegen eines Spielernamens
## Spielen durch Client ##
### /F8/ ###
Eingabe eines Zuges
### /F9/ ###
Züge der anderen Spieler anzeigen
### /F10/ ###
Veto gegen Zug eines anderen Spielers einlegen
### /F11/ ###
Mitteilung wenn Spieler fertig ist
## Nach Abschluss eines Session ##
### /F12/ ###
Anzeigen der Platzierung des Spiels mit Anzahl der Züge
### /F13/ ###
Game Master bestimmt ob neues Spiel
## Mit laufenden Client ##
### /F14/ ###
Anzeige der Highscoreliste

# Produktdaten #
## Highscoreliste ##
### /D1/ ###
Spielernamen
### /D2/ ###
Schnellste gewonnene Session je Spieler
### /D3/ ###
Anzahl der gewonnen Sessions je Spieler

# Produktleistung #
## Zeit ##
### /L1/ ###
Die Antwortzeiten liegen durschnittlich unter 60 Sekunden um ein flüssiges Spiel zu ermöglichen.

# Benutzeroberfläche #
Auf der Benutzeroberfläche werden eine Menüleiste, das Spielfeld sowie kontextabhängige Schaltflächen und Textfelder sichtbar sein. Das Spielfeld ist als 2D Draufsicht realisiert. Die Spielzüge werden, ebenso wie Schaltflächen, mit dem Standard-Zeigergerät (Maus, Trackball, Touchpad) eingegeben.

![http://btuswphalma.googlecode.com/svn/wiki/skizze_frontend_spiel.png](http://btuswphalma.googlecode.com/svn/wiki/skizze_frontend_spiel.png)

# Qualitäts-Zielbestimmungen #
Das Projekt wird den objektorientierten Konzepten folgend entworfen und umgesetzt. Die Dokumentation der Klassenfunktionalitäten erfolgt im Quellcode.

# Globale Testszenarien / Testfälle #
## Mehrspielertest ##
Bei einem Spiel mit mehreren Teilnehmern wird die Integration der einzelnen Produktkomponenten geprüft, insbesondere auch die Netzwerkfunktionalität.
## Einzelspielertest ##
Bei einem Spiel eines menschlichen Teilnehmers gegen den Computergegner werden, neben der Integration der einzelnen Produktkomponenten, die Regelkonformität und "Intelligenz" des Computergeners geprüft.

# Entwicklungs-Umgebung #
## Software ##
  * IDE (Eclipse 3.3)
  * Java 1.5 SDK, Java 1.6 SDK
## Hardware ##
  * handelsübliche x86 kompatible PCs
## Orgware ##
  * Versionierungssystem (Subversion)
  * Wiki
  * Issuetracker
  * OpenOffice
  * InternetRelayChat

# Ergänzungen #
Der Code wird unter die [neue dreiklauselige BSD](http://www.opensource.org/licenses/bsd-license.php) Lizenz gestellt
  * Anhang [Spielregeln](http://code.google.com/p/btuswphalma/wiki/Spielregeln)
  * Anhang [Glossar](http://code.google.com/p/btuswphalma/wiki/Glossar)
  * Anhang [Java-Code-Convention](http://java.sun.com/docs/codeconv/CodeConventions.pdf)