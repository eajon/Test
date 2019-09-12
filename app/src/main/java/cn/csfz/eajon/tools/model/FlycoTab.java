package cn.csfz.eajon.tools.model;

import com.flyco.tablayout.listener.CustomTabEntity;

public class FlycoTab implements CustomTabEntity {
    public String title;
    public int selectedIcon;
    public int unSelectedIcon;

    public FlycoTab(String title, int selectedIcon, int unSelectedIcon) {
        this.title = title;
        this.selectedIcon = selectedIcon;
        this.unSelectedIcon = unSelectedIcon;
    }

    @Override
    public String getTabTitle() {
        return title;
    }

    @Override
    public int getTabSelectedIcon() {
        return selectedIcon;
    }

    @Override
    public int getTabUnselectedIcon() {
        return unSelectedIcon;
    }
}