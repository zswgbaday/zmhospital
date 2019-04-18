package com.zm.hospital.common.shiro.listener;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 自定义session监听器
 * Created by ange on 2016/11/14.
 */
public class CustomSessionListener implements SessionListener {

    private static Logger LOGGER = LoggerFactory.getLogger(CustomSessionListener.class);


    /**
     * Notification callback that occurs when the corresponding Session has started.
     *
     * @param session the session that has started.
     */
    public void onStart(Session session) {
        LOGGER.debug("session {} :onStart", session);
    }

    /**
     * Notification callback that occurs when the corresponding Session has stopped, either programmatically via
     * {@link Session#stop} or automatically upon a subject logging out.
     *
     * @param session the session that has stopped.
     */
    public void onStop(Session session) {
        LOGGER.debug("session {} :onStop", session);
    }

    /**
     * Notification callback that occurs when the corresponding Session has expired.
     * <p/>
     * <b>Note</b>: this method is almost never called at the exact instant that the {@code Session} expires.  Almost all
     * session management systems, including Shiro's implementations, lazily validate sessions - either when they
     * are accessed or during a regular validation interval.  It would be too resource intensive to monitor every
     * single session instance to know the exact instant it expires.
     * <p/>
     * If you need to perform time-based logic when a session expires, it is best to write it based on the
     * session's {@link Session#getLastAccessTime() lastAccessTime} and <em>not</em> the time
     * when this method is called.
     *
     * @param session the session that has expired.
     */
    public void onExpiration(Session session) {
        LOGGER.debug("session {} :onExpiration", session);
    }
}
