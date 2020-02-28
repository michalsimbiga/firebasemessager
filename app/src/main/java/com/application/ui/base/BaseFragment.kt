package com.application.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import dagger.android.support.DaggerFragment
import timber.log.Timber

abstract class BaseFragment: DaggerFragment() {

    @CallSuper
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Timber.tag(javaClass.simpleName).i("on_create_view")

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Timber.tag(javaClass.simpleName).i("on_view_created")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Timber.tag(javaClass.simpleName).i("on_create")
    }

    override fun onResume() {
        super.onResume()

        Timber.tag(javaClass.simpleName).i("on_resume")
    }

    override fun onPause() {
        super.onPause()

        Timber.tag(javaClass.simpleName).i("on_pause")
    }

    override fun onDestroyView() {
        super.onDestroyView()

        Timber.tag(javaClass.simpleName).i("on_destroy_view")
    }

    override fun onDestroy() {
        super.onDestroy()

        Timber.tag(javaClass.simpleName).i("on_destroy")
    }
}