package com.yory3r.e_learning.model;

public class BangunDatarRuang
{
    String namaBangun;
    String deskripsiBangun;
    String kelilingBangun;
    String luasBangun;

    public BangunDatarRuang(String namaBangun, String deskripsiBangun, String kelilingBangun, String luasBangun)
    {
        this.namaBangun = namaBangun;
        this.deskripsiBangun = deskripsiBangun;
        this.kelilingBangun = kelilingBangun;
        this.luasBangun = luasBangun;
    }

    public String getNamaBangun()
    {
        return namaBangun;
    }

    public void setNamaBangun(String namaBangun)
    {
        this.namaBangun = namaBangun;
    }

    public String getDeskripsiBangun()
    {
        return deskripsiBangun;
    }

    public void setDeskripsiBangun(String deskripsiBangun)
    {
        this.deskripsiBangun = deskripsiBangun;
    }

    public String getKelilingBangun()
    {
        return kelilingBangun;
    }

    public void setKelilingBangun(String kelilingBangun)
    {
        this.kelilingBangun = kelilingBangun;
    }

    public String getLuasBangun()
    {
        return luasBangun;
    }

    public void setLuasBangun(String luasBangun)
    {
        this.luasBangun = luasBangun;
    }
}
