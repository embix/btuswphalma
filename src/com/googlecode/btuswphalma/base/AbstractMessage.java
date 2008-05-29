/**
 * 
 */
package com.googlecode.btuswphalma.base;

/**
 * Abstrakte Nachrichtenklasse als Basis fuer die einfachen
 * Nachrichtentypen.
 * 
 * @author embix
 *
 */
public abstract class AbstractMessage implements IMessage {
   
   // Die ID des Quell-Clients
   private int source;
   
   // Die ID des Ziel-Clients
   private int destination;

   /**
    * Erzeugt eine neue Nachricht
    * 
    * @param source Sender als int-ID
    * @param destination Ziel als int-ID
    */
   public AbstractMessage(int source, int destination){
       this.source = source;
       this.destination = destination;
   }
    
   /**
    * Gibt den Empfaenger zurueck 
    * 
    * @see com.googlecode.btuswphalma.base.IMessage#getDestination()
    */
   public int getDestination() {
	return destination;
   }

   /**
    * Gibt den Sender zurueck
    * 
    * @see com.googlecode.btuswphalma.base.IMessage#getSource()
    */
   public int getSource() {
	return source;
   }

}
