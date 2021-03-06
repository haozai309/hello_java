package com.pattern.proxy;

public class Client {

    public static void main(String[] args) {
        IGamePlayer player = new GamePlayer("Harry Potter");
        System.out.println("---- Game started ----");
        player.login("harry", "wind");
        player.killBoss();
        player.upgrade();
        System.out.println("---- Game end ----");

        System.out.println("\n**** Use proxy ****");
        System.out.println("---- Game started ----");
        GamePlayerProxy proxy = new GamePlayerProxy(player);
        proxy.login("harry", "wand");
        proxy.killBoss();
        proxy.upgrade();
        System.out.println("---- Game end ----");

        System.out.println("\n**** Use proxy (new form) ****");
        System.out.println("---- Game started ----");
        GamePlayerProxy newProxy = new GamePlayerProxy("Snape");
        newProxy.login("harry", "wand");
        newProxy.killBoss();
        newProxy.upgrade();
        System.out.println("---- Game end ----");
    }

}
