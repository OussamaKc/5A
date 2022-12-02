package org.polytech.covidapi.services;

import org.polytech.covidapi.entities.Center;
import org.polytech.covidapi.entities.Meeting;
import org.polytech.covidapi.repositories.ICenter;
import org.polytech.covidapi.repositories.IMeeting;
import org.polytech.covidapi.user.domain.User;
import org.polytech.covidapi.user.repository.UserRepository;
import org.polytech.covidapi.user.service.UserService;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import static org.apache.commons.lang3.BooleanUtils.or;

@Service
public class MeetingService {

    private final IMeeting iMeeting;
    private final UserRepository userRepository;
    private final ICenter iCenter;

    public MeetingService(IMeeting iMeeting, UserRepository userRepository, ICenter iCenter) {
        this.iMeeting = iMeeting;
        this.userRepository = userRepository;
        this.iCenter = iCenter;
    }

    public List<Meeting> getAllMeetings() {
        return this.iMeeting.findAll();
    }

    public Meeting addMeeting(Long userId, Long centerId, String date) throws ParseException {
        Meeting meeting = new Meeting();
        User user = this.userRepository.findById(userId).orElse(null);
        Center center = this.iCenter.findById(centerId).orElse(null);

        if (user == null || center == null) {
            throw new IllegalArgumentException("User or center not found");
        }

        LocalDate date1 = LocalDate.parse(date);
        System.out.println(date1 + date);
        meeting.setUser(user);
        meeting.setCenter(center);
        meeting.setAppointment(date1);
        this.iMeeting.save(meeting);
        return meeting;
    }

    public Meeting updateMeeting(Meeting meeting) {
        return this.iMeeting.save(meeting);
    }

    public void deleteMeetingById(Long id) {
        this.iMeeting.deleteById(id);
    }

    public void changeMeetingStatus(List<Meeting> meetings) {
        for (Meeting meeting : meetings) {
            if (meeting.getAppointment().isBefore(LocalDate.now())) {
                meeting.setIs_done(true);
            }
        }
    }
    public List<Meeting> getMeetingByUserId(Long id) {
        List<Meeting> meetings = this.iMeeting.findAllByUserIdOrderByAppointmentDesc(id);
        this.changeMeetingStatus(meetings);
        return meetings;
    }
}
