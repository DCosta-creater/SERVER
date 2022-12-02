package com.jhossi.server.enumeration;

public enum Status {
    SERVER_UP("SERVER_UP"),
    SERVER_DOWN("SERVER_DOWN");

    private final String value;
    Status(String value){
       this.value= value;
   }

   public String getValue(){
       return value;
   }
}
