# Inkosistenzen #
Unterschied im Vergleich zu Spielengine:
  * Das Sequenzdiagramm für die ServerEngine ist an einer (bzw zwei) Stellen falsch.
    1. Die Nachricht die bei einem korrekten Zug zunächst geschickt wird ist wieder eine        MoveMessage (oder, alternativ eine Move+Board, falls das für die GUI besser ist). Diese Nachricht geht an alle.
    1. Bevor die PlayerActivateMessage geschickt wird, muss noch eine BoardMessage geschickt werden.

# Änderungen #
Im Vergleich zum Diagramm von ca. 04:40:
  * Die executeMove() Methode der Game Klasse bekommt den Rückgabewert int, in den einkodiert wird, ob der Zug normal durchgeführt wurde, oder ob es besondere Ereignisse gab, also ob ein Spieler fertig wurde und falls, dann welcher oder ob das Spiel fertig ist.

# Kommunikation #
GUI und SpieleEngine müssen sich noch über die Nachrichten einigen (was wird gebaraucht)