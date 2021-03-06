package com.pattern.proxy;

/*
 * Proxy pattern: provide a surrogate or placeholder for another object to control access to it.
 */
public class GamePlayerProxy implements IGamePlayer {

    private IGamePlayer mPlayer = null;

    public GamePlayerProxy(IGamePlayer player) {
        this.mPlayer = player;
    }

    public GamePlayerProxy(String name) {
        try {
            mPlayer = new GamePlayer(this, name);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void login(String user, String password) {
        this.mPlayer.login(user, password);
    }

    @Override
    public void killBoss() {
        this.mPlayer.killBoss();
    }

    @Override
    public void upgrade() {
        this.mPlayer.upgrade();
    }

}
