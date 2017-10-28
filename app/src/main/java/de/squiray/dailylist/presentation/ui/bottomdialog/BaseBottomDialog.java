package de.squiray.dailylist.presentation.ui.bottomdialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import de.squiray.dailylist.util.annotation.BottomSheetDialog;

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

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final android.support.design.widget.BottomSheetDialog dialog = (android.support.design.widget.BottomSheetDialog) super.onCreateDialog(savedInstanceState);

        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                android.support.design.widget.BottomSheetDialog d = (android.support.design.widget.BottomSheetDialog) dialogInterface;
                View bottomSheetInternal = d.findViewById(android.support.design.R.id.design_bottom_sheet);
                BottomSheetBehavior.from(bottomSheetInternal).setState(getState());
            }
        });

        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflateView();
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

    private int getState() {
        return getClass().getAnnotation(BottomSheetDialog.class).state();
    }

    protected abstract void setupView(View view);
}
