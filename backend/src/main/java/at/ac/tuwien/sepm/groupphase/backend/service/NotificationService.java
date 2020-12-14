package at.ac.tuwien.sepm.groupphase.backend.service;

import at.ac.tuwien.sepm.groupphase.backend.entity.Notification;

import java.util.Set;

public interface NotificationService {

    /**
     * Save a notification
     *
     * @param notification to save
     * @return the notification
     */
    Notification createNotification(Notification notification);

    /**
     * Get all notifications from a profile
     *
     * @param id of the profile
     * @return list of all notifications from a profile
     */
    Set<Notification> getAllByProfileId(Long id);

    /**
     * Delete a notification
     *
     * @param id to delete
     */
    void deleteNotification(Long id, String authorization);
}
