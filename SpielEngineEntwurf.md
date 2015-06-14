#summary Entwurf der Spiele-Engine (TP2)
#labels Phase-Design,Deprecated

# Spiel-Engine #

Erste Entwürfe der Spiele-Engine, diese soll v.a. die Spieler verwalten und die Zugprüfung durchführen.

![http://btuswphalma.googlecode.com/svn/wiki/Manager-ZD(ck).jpg](http://btuswphalma.googlecode.com/svn/wiki/Manager-ZD(ck).jpg)

_Abb.1 Zustandsdiagramm für Manager_

![http://btuswphalma.googlecode.com/svn/wiki/EW_SpE_Spiel_ZD.png](http://btuswphalma.googlecode.com/svn/wiki/EW_SpE_Spiel_ZD.png)

_Abb.2 Zustandsdiagramm für Spiel_

![http://btuswphalma.googlecode.com/svn/wiki/EW_SpE_KD.png](http://btuswphalma.googlecode.com/svn/wiki/EW_SpE_KD.png)

_Abb.3 Klassendiagramm für Spiele-Engine_

Hinweis:
Ein TokenMove enthält, wenn ifPush wahr ist, nur startPosition und endPosition, wenn ifPush falsch ist, enhält TokenMove noch eine jumpList.
jumpList enthält Sprungpositionen (jumpPosition) und deren Reihenfolge (jumpCount). Die Spielstein-Position ist immer ein Integer-Wert,
dieser wird aus dem Spielfeld (Board) berechnet.

## Sequenzdiagramm: ##
Initialisierung der Spiele-Engine für 2 Spieler
![http://btuswphalma.googlecode.com/svn/wiki/SD_init_2-Player.jpg](http://btuswphalma.googlecode.com/svn/wiki/SD_init_2-Player.jpg)

_Abb.4 Sequenzdiagramm init 2 Spieler_