package com.unite.okhttpdemo.domain.limit;

/**
 *
 */
public class Meta {

    private String title;
    private Boolean requireAuth;
    private Boolean NoTabPage;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getRequireAuth() {
        return requireAuth;
    }

    public void setRequireAuth(Boolean requireAuth) {
        this.requireAuth = requireAuth;
    }

    public Boolean getNoTabPage() {
        return NoTabPage;
    }

    public void setNoTabPage(Boolean noTabPage) {
        NoTabPage = noTabPage;
    }
}
