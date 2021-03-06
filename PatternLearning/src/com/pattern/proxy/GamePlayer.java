package com.pattern.proxy;

public class GamePlayer implements IGamePlayer {

    private String mName = "";

    public GamePlayer(String name) {
        this.mName = name;
    }

    public GamePlayer(IGamePlayer gamePlayer, String name) throws Exception {
        if (gamePlayer == null) {
            throw new Exception();
        } else {
            this.mName = name;
        }
    }

    @Override
    public void login(String user, String password) {
        System.out.println(this.mName + " login using name " + user);

    }

    @Override
    public void killBoss() {
        System.out.println(this.mName + " kill boss");
    }

    @Override
    public void upgrade() {
       System.out.println(this.mName + " udgrade");
    }

}
