package de.squiray.dailytodo.presentation.ui.bottomdialog;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;

import de.squiray.dailytodo.util.annotation.BottomSheetDialog;

public abstract class BaseBottomDialog<Callback extends BaseBottomDialog.BaseCallback>
        extends BottomSheetDialogFragment {

    private BaseCallback baseCallback;

    public interface BaseCallback {

    }

    Callback getCallback() {
        return (Callback) baseCallback;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            this.baseCallback = (BaseCallback) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString() + " must implement Callback");
        }
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        View contentView = inflateView();
        dialog.setContentView(contentView);
    }

    private View inflateView() {
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        View view = layoutInflater.inflate(getDialogResource(), null);
        setupView(view);
        return view;
    }

    private int getDialogResource() {
        return getClass().getAnnotation(BottomSheetDialog.class).layout();
    }

    protected abstract void setupView(View view);
}
