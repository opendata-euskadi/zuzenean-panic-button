package x47b.internal.services;

import x47b.model.X47BAlarmMessage;

public interface X47BPanicButtonNotifierServices {
	/**
	 * @return true if the notifier is enabled, false otherwise
	 */
	public boolean isEnabled();
	/**
	 * Send the notification
	 * @param alarmMessage
	 */
	public void sendNotification(final X47BAlarmMessage alarmMessage);
}
