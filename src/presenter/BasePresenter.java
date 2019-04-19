package presenter;

public abstract class BasePresenter<T extends IMVPView> implements IMVPPresenter<T> {

    private T view;

    @Override
    public void attachView(T view) {
        this.view=view;
    }

    @Override
    public void detachView() {
        view=null;
    }

    @Override
    public void destroy() {}

    public T getView(){
        return view;
    }
}
