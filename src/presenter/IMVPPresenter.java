package presenter;

public interface IMVPPresenter<T extends IMVPView> {

    void attachView(T view);
    void viewIsReady();
}
