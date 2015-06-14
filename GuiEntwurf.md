# GUI #

Für den weiteren Entwurf wurde ein Automatenmodell (siehe Abb.1) für die graphische Benutzerschnittstelle erstellt, auf dessen Basis das Entwurfsklassendiagramm entwickelt wurde. Die Abläufe innerhalb der GUI werden durch den Controller gesteuert (siehe Abb.2), der als Zustandautomat realisiert ist. Zustandsübergänge werden durch Interaktion mit dem Benutzer und/oder Nachrichten von der Engine ausgelöst.

![http://btuswphalma.googlecode.com/svn/wiki/GuiTP2StateChart.jpg](http://btuswphalma.googlecode.com/svn/wiki/GuiTP2StateChart.jpg)

Abb.1 Automatenmodell

Aus dem Automatenmodell heraus wurde die Klasse GuiController entworfen. Um die Komplexität dieser Klasse nicht unüberschaubar werden zu lassen und da das Verhalten in jedem Zustand etwas anders ist, wurde für jeden Zustand eine eigene Zustandsklasse konstruiert.

![http://btuswphalma.googlecode.com/svn/wiki/GuiTP2ClassDiagramControllerObject.jpg](http://btuswphalma.googlecode.com/svn/wiki/GuiTP2ClassDiagramControllerObject.jpg)
Abb.2 GuiController


![http://btuswphalma.googlecode.com/svn/wiki/GuiTP2ClassDiagram.jpg](http://btuswphalma.googlecode.com/svn/wiki/GuiTP2ClassDiagram.jpg)
Abb.3 Überblick über die GUI-Komponente

### Anmerkungen zum Klassendiagram ###
Die gelb dargestellten Elemente werden durch die GUI Komponente realisiert. Die weiß dargestellten Komponennten werden für die Realisierung von Wunschkriterien benötigt - sie werden erst realisert, wenn die Musskriterien erfüllt sind. Die cyan dargestellten Elemente werden durch andere Komponenten des Projektes realisiert. Die Grau dargestellten Klassen entstammen dem verwendeten Framework.

![http://btuswphalma.googlecode.com/svn/wiki/GuiTP2ClassDiagramDialofenster.jpg](http://btuswphalma.googlecode.com/svn/wiki/GuiTP2ClassDiagramDialofenster.jpg)
Abb.4 Dialoge in der GUI-Komponente

siehe auch [GUI Analyse](GuiAnalyse.md), [Produktentwurf](http://code.google.com/p/btuswphalma/wiki/Produktentwurf)