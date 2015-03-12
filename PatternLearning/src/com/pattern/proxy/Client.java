package com.pattern.proxy;

public class Client {

    public static void main(String[] args) {
        IGamePlayer player = new GamePlayer("harry potter");
        System.out.println("---- Game started ----");
        player.login("harry potter", "wind");
        player.killBoss();
        player.upgrade();
        System.out.println("---- Game end ----");
    }

}
