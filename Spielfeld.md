# Beschreibung des Spielfelds: #
## Mathematisch ##

Das Spielbrett wird durch ein zweidimensionales Byte-Array dargestellt.

Dieses Array hat die Dimensionen 13x17

Die Indizes (x,y) liegen also im zulässigen Bereich wenn gilt:

**IN DER IMPLEMENTIERUNG X UND Y VERTAUSCHT!!!!!!!!!!!!!**

![http://btuswphalma.googlecode.com/svn/wiki/zulaessig.png](http://btuswphalma.googlecode.com/svn/wiki/zulaessig.png)

Ob die Indizes auch eine Position im Array beschreibt, die tatsächlich ein Feld auf dem Halmafeld (sechszackigen Stern) ist ergibt sich aus dem Wahrheitswert dieser Formel:

![http://btuswphalma.googlecode.com/svn/wiki/SpielbrettMathe.png](http://btuswphalma.googlecode.com/svn/wiki/SpielbrettMathe.png)

## Anschaulich ##

Dieses Bild stellt den Array dar. Die Schwarzen Felder stellen das Halmafeld dar, die grauen unbenutzte Felder. Die Linien symbolisieren die Verbindungen der Positionen.

![http://btuswphalma.googlecode.com/svn/wiki/Spielbrett.png](http://btuswphalma.googlecode.com/svn/wiki/Spielbrett.png)