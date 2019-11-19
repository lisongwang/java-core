package com.lisong.learn.core.type.factory;

import com.lisong.learn.core.util.Null;

public class NullPart extends Part implements Null {

    private NullPart() {}
    private static final NullPart NULL = new NullPart();
    static class Factory implements com.lisong.learn.core.util.Factory<NullPart> {
        @Override
        public NullPart create() {
            return NULL;
        }
    }

    @Override
    public String toString() {
        return "Null";
    }
}
