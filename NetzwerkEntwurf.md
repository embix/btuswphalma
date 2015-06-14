﻿#summary Entwurf der Netzwerkkomponenten (TP3)

# Entwurf Netzwerk #
## Problem ##
die Methode getId() gibt es für Thread schon.

Vorschlag -> abändern von id in playerId

## allgemein Netzwerk ##
  * NWK = Netzwerkkomponente
  * Netzwerk über TCP/IP, blockierende Sockets, ein Server, 1-5 Clients
  * Nachrichten im Netzwerk über NetMessage, diese ist serializable und enhält eine IMessage

## NWK Server ##

  * Server-IP fest, Port frei wählbar (Defaultwert möglich)
  * ServerSocket horcht solange auf einkommende Verbindungsversuche bis maximale Spielerzahl erreicht,
  * weist der Verbindung mit einem Client einen Socket (mit jeweiligem In-/Outputstream) zu
  * hat nur eine Queue für eingehende Nachrichten

Startphase:
  * ServerSocket, mit Port und eventuell Timeout, im Konstruktor von NWK Server erzeugt
  * MessageQueue, NotifyObject und ConnectionMap ebenfalls vorher erzeugt
  * ServerSocket schläft solange, bis Verbindungsversuch von außen, max. Spieleranzahl erreicht oder Timeout
  * Schleife: id=2 bis (max. Spieleranzahl) do
    * Socket = ServerSocket.accept()
    * new Connection (Socket, id, NotifyObject, Queue)
    * ConnectionMap.putConnection(Connection)		//in HashMap: key=id, value=Connection
  * wenn alle Sockets eingerichtet: run()

einkommende Nachricht vom Netzwerk:
  * in Connection: while(notInterrupted) do
    * readObject(ObjectOutputStream) try ... catch ... //blockierender Prozess
    * //NetMessage "rein" gekommen, aber nur als reines Objekt -> "wakeup"
    * Cast des Objektes auf NetMessage
    * hole IMessage heraus
    * ändere Destination =1  und from =id
    * IMessage auf MessageQueue
    * Notify auf NotifyObject

einkommende Nachricht vom Dispatcher:
  * IMessage 'msg' mit Destination =0 //broadcast
    * Schleife: while(hasnext) do		//key->Iterator als Laufindex
    * Connection = ConnectionMap.getNext
    * Connection.sendMessage(msg)
  * 'msg' mit Destination =2|3|4|5|6
    * Connection = ConnectionMap.get(key == destination)
    * Connection.sendMessage(msg)
  * ansonsten Fehler abfangen

Beenden-Prozess:
  * an alle Connection's:  Horch-Prozess beenden (Interrupt senden) und Socket.close()

http://btuswphalma.googlecode.com/svn/wiki/EWnwkServer.PNG

_Abb.1 Entwurfsklassendiagramm NWK Server_

## NWK Client ##

  * hat keine extra Connection, Thread zum Nachrichtensenden /-empfangen im Client selbst
  * hat nur die Verbindung mit dem Server

Startphase:
  * an Konstruktor von NWK Client wird Server-IP und -Port übergeben, Client 'merkt' sich beides
  * im Konstruktor wird der Thread gestartet
  * in run() geschieht einmalig der Anmeldeprozess am Server
  * danach wird in run die while(notInterrupted) -Schleife gestartet, die auf Nachrichten horcht
    * arbeitet in etwa analog zum Server

einkommende Nachricht vom Netzwerk:
  * analog zur Verarbeitung im Server, nur dass:
  * ändern von Destination =1, from aber unverändert

einkommende Nachricht vom Dispatcher:
  * analog zur Verarbeitung im Server, nur dass:
  * senden aller Nachrichten an Server, unverändert

http://btuswphalma.googlecode.com/svn/wiki/EWnwkClient.PNG

_Abb.2 Entwurfsklassendiagramm NWK Client_