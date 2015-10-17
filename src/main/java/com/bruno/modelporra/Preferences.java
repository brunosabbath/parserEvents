package com.bruno.modelporra;


public class Preferences {

	private String nameTag;
    private int counter;

    public Preferences(){}
    public Preferences(String nameTag, int counter){
        this.nameTag = nameTag;
        this.counter = counter;
    }

    public String getNameTag() {
        return nameTag;
    }

    public void setNameTag(String nameTag) {
        this.nameTag = nameTag;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
	
}
