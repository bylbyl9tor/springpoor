package com.springpoor.annotations;

import java.time.LocalTime;

/**
 * Хранит возможные значения для свойства {@link PoorComponent#scope()}
 *
 * @version 1.0
 * @autor Vitaliy Ritus
 */
public enum ScopeType {
    SINGLETON {
        /**
         * If isContextHaveObjectInstance==true method will return true.
         * If bean singleton will created.
         *
         * @see ScopeType#needNewObject(boolean)
         * @return opposite input value
         */
        public boolean needNewObject(boolean isContextHaveObjectInstance) {
            return !isContextHaveObjectInstance;
        }
    },


    PROTOTYPE {
        /**
         * Always returns true because the prototype always creates a new instance.
         *
         * @see ScopeType#needNewObject(boolean)
         * @return true
         */
        public boolean needNewObject(boolean isContextHaveObjectInstance) {
            return true;
        }
    },


    EVENMINUTE {
        /**
         * If the minutes are even when the bean is received, method returns true, which means that a new object will be created
         * , if uneven, method returns true, which means that the existing object will be returned.
         *
         * @see ScopeType#needNewObject(boolean)
         * @return true
         */
        public boolean needNewObject(boolean isContextHaveObjectInstance) {
            if (LocalTime.now().getMinute() % 2 == 0) {
                return true;
            } else {
                return isContextHaveObjectInstance ? false : true;
            }
        }
    };

    /**
     * Abstract method which Enum elements implement
     * When it returns true it means that a new instance will be created, when it returns false it means that the old instance will be taken
     *
     * @param isContextHaveObjectInstance true when the bean instance be in the context
     * @return
     */
    public abstract boolean needNewObject(boolean isContextHaveObjectInstance);
}