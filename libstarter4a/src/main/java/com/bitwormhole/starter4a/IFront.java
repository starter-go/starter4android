package com.bitwormhole.starter4a;

import com.bitwormhole.starter4j.application.tasks.PromiseBuilder;

public interface IFront {

    <T> PromiseBuilder<T> createNewPromise(Class<T> t);

}
