package me.amryousef.lib.presentation

import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.util.concurrent.atomic.AtomicBoolean

class SingleLiveEvent<T> : MutableLiveData<T>() {
    private val mPending: AtomicBoolean = AtomicBoolean(false)
    private var currentObserver: Observer<in T>? = null

    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        val intermediateObserver = Observer<T> { t ->
            if (mPending.compareAndSet(true, false)) {
                observer.onChanged(t)
            }
        }
        currentObserver = intermediateObserver
        super.observe(owner, intermediateObserver)
    }

    override fun removeObserver(observer: Observer<in T>) {
        super.removeObserver(observer)
        currentObserver?.let { super.removeObserver(it) }
        currentObserver = null
    }

    @MainThread
    override fun setValue(t: T?) {
        mPending.set(true)
        super.setValue(t)
    }
}
