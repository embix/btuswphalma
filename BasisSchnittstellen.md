# UML Diagramm #
![http://btuswphalma.googlecode.com/svn/wiki/Interfaces.jpg](http://btuswphalma.googlecode.com/svn/wiki/Interfaces.jpg)

# IGuiCom #
Die IGuiCom Schnittstelle wird von der Server-Engine implementiert. Die GUI kann sich dann mittels _registerGuiListener_ an der Server-Engine anmelden und wird von diesem Zeitpunkt an über Ereignisse, wie z.B. Züge, Änderungen am Spielbrett, usw., informiert. Weiterhin kann die GUI mit _recvMessage_ selbst Nachrichten an die Spiel-Engine schicken.

# INetCom #
INetCom dient als Kommunikationsschnittstelle zwischen der Server-Engine und dem Netzwerksystem.

# IMessage #
Alle Nachrichttypklassen müssen dieses Interface implementieren. Dadurch wird sichergestellt, dass jeder Nachrichtentyp die für die Nachrichtverteilung relevanten Informationen für die Server-Engine bereitstellt.

# IGuiListener #
Scnittstelle, die von Objekten, die über Engine-Ereignisse informiert werden müssen, implementiert wird.

# MessageType #
Dieses enum enthält die IDs für alle Nachrichtentypen, die zwischen den einzelnen Komponenten verschickt werden.