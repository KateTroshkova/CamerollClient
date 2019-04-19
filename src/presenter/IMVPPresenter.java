package presenter;

public interface IMVPPresenter<T extends IMVPView> {

    void attachView(T view);
    void detachView();
    void viewIsReady();
    void destroy();
}
