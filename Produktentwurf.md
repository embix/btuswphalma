# Softwareentwurf #

Beim Architekturentwurf für das Softwareprodukt haben wir Architekturen von existierenden verteilten Anwendungen miteinander verglichen. Das Hauptkriterium für unseren Entwurf war eine gute Wartbarkeit und insbesondere die damit einhergehende gute Erweiterbarkeit, welche es uns ermöglicht, das Projekt in Teilprojekte zu zerlegen. Damit ist sichergestellt, dass am Ende des zweiten Teilprojektes bereits ein Prototyp zur Verfügung steht, dessen Funktionalität dann in den weiteren Teilprojekten schrittweise erhöht werden kann.

Für die Darstellung der Architektur in einem einfachen Blockdiagramm haben wir uns entschieden, weil diese Darstellungsform bei der Zusammenarbeit mit Beteiligten aus unterschiedlichen Fachbereichen für alle verständlich ist und somit eine erste Diskussionsgrundlage bietet.

Als Rahmenwerk haben wir uns für J2SE Edition 5 entschieden, da uns dort Komponenten für die Grafische Benutzeroberfläche, wie auch Komponenten für verteilte Anwendungen zur Verfügung stehen. Auch wenn das Framework einges an Einarbeitungszeit erfordert wird dadurch ein großer Teil an Entwicklungszeit eingespart, was den Einsatz durchaus rechtfertigt.

## Softwarearchitektur ##

Aus Abbildung 1 kann man entnehmen, daß die Architektur einfach aufgebaut ist. Für die Interaktion mit dem Benutzer wird eine GUI-Komponente entwickelt. Auf dieser wird das Spielbrett dargestellt, sämtliche Wechselwirkungen zwischen dargestelltem Spielstand und Zugeingabe werden mit der Engine ausgehandelt.

![http://btuswphalma.googlecode.com/svn/wiki/GrobEntwurfVerteilung.png](http://btuswphalma.googlecode.com/svn/wiki/GrobEntwurfVerteilung.png)
Abb1

Die spielrelevanten Berechnungen werden in der Engine durchgeführt. Diese ist entsprechend ihrer Aufgaben in zwei wesentliche Teile gegliedert:

  1. Die sogenannte **Spielengine**. Sie ist für Regelchecks, Verwaltung der Spieler, des Spielbrettes und der sich darauf befindlichen Spielsteine verantwortlich. Sie koordiniert den eigentlichen Spielablauf.
  1. Die **Serverengine** ist im wesentlichen baugleich der Clientengine und ist zuständig für die Interkomponentenkommunikation, insbesondere der Anbindung der Spielengine an die anderen Komponenten.

Die Server-Netzwerkomponente ist zuständig für die Kommunikation mit entfernten Spielern und sorgt für die Übertragung der Spielnachrichten zu den Clients. Die Client-Netwerkomponenten jedes entfernten Spielers registrieren sich am Server, senden und empfangen die Spielnachrichten und leiten sie somit zu der entsprechenden Engine.

Der Computerspieler wird auf dem Hostrechner von der Spielengine bereitgestellt. Dazu wird eine KI-Engine über eine Netzwerkclientkomponente an den eigentlichen Server angebunden. Die KI-Engine verarbeitet die gleichen Nachrichten wie auch die Server- und Clientengine, weiterhin steht ihr eine Algorithmuseinheit zur Verfügung, mit der sie aufgrund der Spieldaten eigenständig Entscheidungen für die Zugwahl trifft. Je nach benötigem Schwierigkeitsgrad kommt eine andere Algorithmuseinheit zum Einsatz.

Diese Architektur erscheint uns für das geforderte Produkt ideal, weil sie eine etappenweise Entwicklung begünstigt. Der Umfang der Teilprojekte wird so in etwa gleich groß und die vorhandenen Betriebsmittel können optimal genutzt werden.

## Teilprojekt 2 ##
Für das zweite Teilprojekt wurde zunächst eine einfachere Architektur gewählt, die jedoch die geforderte Erweiterbarkeit bietet um sämtliche gestellten Anforderungen zu erfüllen.

Die Grafische Benutzerschnittstelle enthält alle rein spielrelevanten Funktionen. Lediglich Netzwerkeinstellungen und Einstellungen für den Computerspieler bleiben offen und kommen in den späteren Teilprojekten hinzu. Der gewählte [Entwurf](http://code.google.com/p/btuswphalma/wiki/GuiEntwurf) ist flexibel genug um zukünfitge Erweiterungen aufzunehmen.

![http://btuswphalma.googlecode.com/svn/wiki/GrobEntwurf.png](http://btuswphalma.googlecode.com/svn/wiki/GrobEntwurf.png)
Abb2

Wie in Abbildung 2 zu sehen ist wird auch die Engine soweit fertiggestellt, daß ein Einzelspielerspiel durchgeführt werden kann. Möglich wird dies durch Fertigstellung der Spielengine, die schon die gesamte Spiellogik umfaßt. Die Serverengine wird ebenfalls bereitgestellt.

Diese Aufteilung erfodert ebenfalls die Entwicklung eines Netzwerkdummys, der sämtliche geforderten Schnittstellen der Servernetzwerkkomponente implementiert und sich an das vorgegebene Protokoll hält, allerdings keine Netzwerkfunktionalität bereitstellt.

Die für Teilprojekt 1 gwählte Aufteilung begünstig somit auch den Test der Einzelkomponenten, die Testfälle sind damit auch in den kommenden Teilprojekten weiter verwendbar und bei Bedarf erweiterbar um neue Funktionalität zu prüfen.

## Teilprojekt 3 ##
In Teilprojekt 3 wird der Prototyp aus Teilprojekt 1 um die Netzwerkfunktionalitäten angereichert. Dazu werden auf Basis des Netzwerkdummys die entsprechenden Komponenten für Server und Client entwickelt. An der Engine sind im Idealfall keine, sonst geringe Änderungen vorzunehmen. Geplant ist für Clients statt der Spielengine eine schnittstellenkonforme Dummyengine zu verwenden. Die Serverengine wird um die hostspezifischen Funktionen erleichtert und als Clientengine verwendet. Das Userinterface wird sowohl Server als auch Client Spielmodi unterstützen.

## Teilprojekt 4 ##
Die Entwicklung des Computerspielers, vor allem also der KI-Engine, erfolgt im vorerst letzten Teilprojekt. Dafür wird die Clientengine um die notwendigen Modelle und Algorithmen zur Teilnahme als Spieler erweitert. Für die verschiedenen Schwierigkeitsgrade werden unterschiedliche algorithmische Ansätze umgesetzt und als Spieleralgorithmusobjekt gekapselt, so daß die KI-Engine im wesentlichen gleich bleibt.

## Was ist noch zu tun ##
Im Verlauf des Teilprojektes 2 werden noch erstellt
  * ein dynamisches Prozessmodell
  * ein Schnittstellenmodell